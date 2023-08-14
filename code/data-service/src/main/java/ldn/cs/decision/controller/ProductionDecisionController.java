package ldn.cs.decision.controller;

import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.pojo.production.Production;
import ldn.cs.decision.pojo.production.ProductionInfo;
import ldn.cs.decision.pojo.production.ProductionMeasureInfo;
import ldn.cs.decision.pojo.production.ProductionWarningInfo;
import ldn.cs.decision.service.DecisionThresholdService;
import ldn.cs.decision.service.ProductionDecisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/decision/element/production")
public class ProductionDecisionController {
    @Autowired
    private ProductionDecisionService productionDecisionService;

    @Autowired
    private DecisionThresholdService decisionThresholdService;

    private static final Map<String, Map<Integer, String>> upperThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> upperThresholdMeasure = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdMeasure = new HashMap<>();

    private static final Map<String, String> attributeMap = new HashMap<>();

    static {
        Map<Integer, String> upperReason4Quantity = new HashMap<>();
        upperReason4Quantity.put(1, "市场需求超出预期，导致生产需求增加");
        upperReason4Quantity.put(2, "生产过程中存在严重的工艺问题，影响产能稳定性");
        upperThresholdReason.put("生产产量", upperReason4Quantity);

        Map<Integer, String> upperReason4Cost = new HashMap<>();
        upperReason4Cost.put(1, "原材料价格上涨，导致生产成本增加");
        upperReason4Cost.put(2, "生产过程中存在严重浪费，增加了成本负担");
        upperThresholdReason.put("生产费用", upperReason4Cost);
    }

    static {
        Map<Integer, String> upperMeasure4Quantity = new HashMap<>();
        upperMeasure4Quantity.put(1, "优化生产计划，加强供应链协调，确保原材料供应，提高生产效率");
        upperMeasure4Quantity.put(2, "紧急解决工艺问题，可能需要停产检修，彻底排查问题原因");
        upperThresholdMeasure.put("生产产量", upperMeasure4Quantity);

        Map<Integer, String> upperMeasure4Cost= new HashMap<>();
        upperMeasure4Cost.put(1, "优化采购策略，与供应商协商降低成本，寻找替代原材料");
        upperMeasure4Cost.put(2, "优化生产工艺，减少废品产生，提高生产效率");
        upperThresholdMeasure.put("生产费用", upperMeasure4Cost);
    }

    static {
        Map<Integer, String> lowerReason4Quantity = new HashMap<>();
        lowerReason4Quantity.put(1, "市场需求不足，导致生产减少");
        lowerReason4Quantity.put(2, "生产设备故障频繁，影响正常生产");
        lowerThresholdReason.put("生产产量", lowerReason4Quantity);

        Map<Integer, String> lowerReason4Cost = new HashMap<>();
        lowerReason4Cost.put(1, "市场需求不足，导致生产规模减小");
        lowerReason4Cost.put(2, "生产工艺效率高，废品率降低，降低生产成本");
        lowerThresholdReason.put("生产费用", lowerReason4Cost);
    }

    static {
        Map<Integer, String> lowerMeasure4Quantity = new HashMap<>();
        lowerMeasure4Quantity.put(1, "加强市场调研，优化销售推广策略，开拓新的市场");
        lowerMeasure4Quantity.put(2, "加强设备维护和检修，考虑设备更新以提高稳定性");
        lowerThresholdMeasure.put("生产产量", lowerMeasure4Quantity);

        Map<Integer, String> lowerMeasure4Cost = new HashMap<>();
        lowerMeasure4Cost.put(1, "加强市场调研，优化销售策略，拓展新的市场份额");
        lowerMeasure4Cost.put(2, "继续优化生产工艺，提高产品质量，减少废品产生");
        lowerThresholdMeasure.put("生产费用", lowerMeasure4Cost);
    }

    static {
        attributeMap.put("生产产量", "quantity");
        attributeMap.put("生产费用", "cost");
    }


    /**
     * 决策元 -- 生产链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 生产链报表数据ProductionInfo
     */
    @GetMapping("/prediction/query")
    public ProductionInfo getProductionInfos(long time, int granularity, int limit, int offset) {
        return productionDecisionService.getProductionPredictionInfos(time, granularity, limit, offset);
    }

    /**
     * 预警查询功能
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 预警数据
     */
    @GetMapping("/warning/query")
    public Map<String, List<ProductionWarningInfo>> getProductionWarningInfos(long time, int granularity, int categories, String attributes) {
        List<ProductionWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        return warningInfos.stream().collect(Collectors.groupingBy(ProductionWarningInfo::getCorporation));
    }

    @GetMapping("/measure/query")
    public Map<String, List<ProductionMeasureInfo>> getProductionMeasureInfos(long time, int granularity, int categories, String attributes) {
        List<ProductionWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        List<ProductionMeasureInfo> productionMeasureInfos = new ArrayList<>();
        for(ProductionWarningInfo warningInfo : warningInfos) {
            ProductionMeasureInfo measureInfo = new ProductionMeasureInfo();
            BeanUtils.copyProperties(warningInfo, measureInfo);
            String measure = warningInfo.getAlarmType() == 1 ?
                    upperThresholdMeasure.get(attributes).get(warningInfo.getLevel()):
                    lowerThresholdMeasure.get(attributes).get(warningInfo.getLevel());

            measureInfo.setMeasure(measure);
            productionMeasureInfos.add(measureInfo);
        }

        return productionMeasureInfos.stream().collect(Collectors.groupingBy(ProductionMeasureInfo::getCorporation));
    }

    private List<ProductionWarningInfo> getWarningInfos(long time, int granularity, int categories, String attributes) {
        List<Production> productions = productionDecisionService.getProductionWarningInfos(time, granularity);
        List<DecisionThreshold> thresholds = decisionThresholdService.getDecisionThreshold(categories, attributes);
        List<ProductionWarningInfo> warningInfos = new ArrayList<>();
        for (Production production : productions) {
            if (thresholds == null || thresholds.size() <= 0) {
                continue;
            }
            DecisionThreshold threshold = thresholds.get(0);
            if (threshold != null) {
                ProductionWarningInfo warningInfo = new ProductionWarningInfo();
                BeanUtils.copyProperties(production, warningInfo);
                try {
                    Field field = Production.class.getDeclaredField(attributeMap.get(attributes));
                    field.setAccessible(true);
                    Object propertyValue = field.get(warningInfo);
                    BigDecimal targetValue = null;
                    if (propertyValue instanceof BigDecimal) {
                        targetValue = (BigDecimal) propertyValue;
                    }
                    if (targetValue == null) {
                        break;
                    }
                    BigDecimal difference = targetValue.subtract(threshold.getAttributesValue()).abs();
                    int alarmType, level;
                    int comparisonThreshold = threshold.getAttributesValue().compareTo(targetValue);
                    if (comparisonThreshold < 0) {
                        alarmType = 1; // 高于阈值
                    } else if (comparisonThreshold > 0) {
                        alarmType = 0; // 低于阈值
                    } else {
                        continue;
                    }
                    int severityLevel = difference.compareTo(BigDecimal.valueOf(0.5).multiply(threshold.getAttributesValue()));
                    int generalLevel = difference.compareTo(BigDecimal.valueOf(0.25).multiply(threshold.getAttributesValue()));
                    if (severityLevel > 0) {
                        level = 2;
                    } else if (severityLevel < 0 && generalLevel > 0) {
                        level = 1;
                    } else {
                        continue;
                    }
                    warningInfo.setAlarmType(alarmType);
                    warningInfo.setLevel(level);
                    warningInfo.setCauseType(alarmType == 1 ? upperThresholdReason.get(attributes).get(level) : lowerThresholdReason.get(attributes).get(level));
                    warningInfos.add(warningInfo);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return warningInfos;
    }
}
