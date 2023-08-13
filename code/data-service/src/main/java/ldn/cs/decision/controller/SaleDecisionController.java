package ldn.cs.decision.controller;

import ldn.cs.decision.pojo.sale.SaleInfo;
import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.pojo.sale.Sale;
import ldn.cs.decision.pojo.sale.SaleMeasureInfo;
import ldn.cs.decision.pojo.sale.SaleWarningInfo;
import ldn.cs.decision.service.DecisionThresholdService;
import ldn.cs.decision.service.SaleDecisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest/decision/element/sale")
public class SaleDecisionController {
    @Autowired
    private SaleDecisionService saleDecisionService;

    @Autowired
    private DecisionThresholdService decisionThresholdService;

    private static final Map<String, Map<Integer, String>> upperThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> upperThresholdMeasure = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdMeasure = new HashMap<>();

    private static final Map<String, String> attributeMap = new HashMap<>();

    static {
        Map<Integer, String> upperReason4Quantity = new HashMap<>();
        upperReason4Quantity.put(1, "市场需求增加，导致产品销量上升");
        upperReason4Quantity.put(2, "市场宣传活动效果好，推动产品热卖，销量大幅增加");
        upperThresholdReason.put("产品销量", upperReason4Quantity);

        Map<Integer, String> upperReason4Income = new HashMap<>();
        upperReason4Income.put(1, "市场需求增加，导致产品销售量和价格上升，从而提高销售营收");
        upperReason4Income.put(2, "市场宣传活动效果好，推动产品热卖，销售量和价格大幅上升，增加销售营收");
        upperThresholdReason.put("销售营收", upperReason4Income);

        Map<Integer, String> upperReason4Cost = new HashMap<>();
        upperReason4Cost.put(1, "市场拓展力度增加，需要加大市场宣传和推广投入，导致销售费用上升");
        upperReason4Cost.put(2, "市场竞争激烈，需要大量广告和促销活动来吸引客户，增加销售费用");
        upperThresholdReason.put("销售费用", upperReason4Cost);

        Map<Integer, String> upperReason4Inventory = new HashMap<>();
        upperReason4Inventory.put(1, "市场需求波动不稳定，未能准确预测季节性变化，导致产品库存超出预期");
        upperReason4Inventory.put(2, "过度采购原材料或生产产品，未能及时调整库存管理，导致库存积压增加");
        upperThresholdReason.put("产品库存", upperReason4Inventory);

        Map<Integer, String> upperReason4Score = new HashMap<>();
        upperReason4Score.put(1, "客户投诉率上升，需要加大售后服务投入来解决客户问题，导致销售服务成本增加");
        upperReason4Score.put(2, "市场竞争激烈，为了吸引和留住客户，需要提供更多的增值服务，导致销售服务成本增加");
        upperThresholdReason.put("销售服务", upperReason4Score);
    }

    static {
        Map<Integer, String> upperMeasure4Quantity = new HashMap<>();
        upperMeasure4Quantity.put(1, "加强市场调研，持续监测市场需求，灵活调整生产计划");
        upperMeasure4Quantity.put(2, "规划市场宣传活动，确保有足够的库存，及时补充生产");
        upperThresholdMeasure.put("产品销量", upperMeasure4Quantity);

        Map<Integer, String> upperMeasure4Income = new HashMap<>();
        upperMeasure4Income.put(1, "加强市场调研，灵活调整价格策略，满足市场需求，优化销售组合");
        upperMeasure4Income.put(2, "规划市场宣传活动，确保有足够的库存，及时补充生产，保持营收增长");
        upperThresholdMeasure.put("销售营收", upperMeasure4Income);

        Map<Integer, String> upperMeasure4Cost = new HashMap<>();
        upperMeasure4Cost.put(1, "优化市场调研，确保宣传活动的针对性和效果，控制宣传投入");
        upperMeasure4Cost.put(2, "制定精准的市场竞争策略，降低过度竞争导致的费用，确保广告投入的效果");
        upperThresholdMeasure.put("销售费用", upperMeasure4Cost);

        Map<Integer, String> upperMeasure4Inventory = new HashMap<>();
        upperMeasure4Inventory.put(1, "优化库存管理策略，加强市场调研，预测季节性需求变化，灵活调整采购和生产计划");
        upperMeasure4Inventory.put(2, "优化采购计划，确保与市场需求匹配，及时调整生产计划，降低过度库存积压");
        upperThresholdMeasure.put("产品库存", upperMeasure4Inventory);

        Map<Integer, String> upperMeasure4Score = new HashMap<>();
        upperMeasure4Score.put(1, "加强产品质量和售后服务，提高客户满意度，减少投诉率，降低售后成本");
        upperMeasure4Score.put(2, "优化增值服务策略，确保增值服务与客户需求匹配，控制成本");
        upperThresholdMeasure.put("销售服务", upperMeasure4Score);
    }

    static {
        Map<Integer, String> lowerReason4Quantity = new HashMap<>();
        lowerReason4Quantity.put(1, "市场需求不足，导致产品销量下降");
        lowerReason4Quantity.put(2, "市场宣传活动效果不佳，产品知名度下降，导致销量减少");
        lowerThresholdReason.put("产品销量", lowerReason4Quantity);

        Map<Integer, String> lowerReason4Income = new HashMap<>();
        lowerReason4Income.put(1, "市场需求不足，导致产品销售量和价格下降，从而降低销售营收");
        lowerReason4Income.put(2, "市场宣传活动效果不佳，产品知名度下降，导致销售量和价格大幅下降，降低销售营收");
        lowerThresholdReason.put("销售营收", lowerReason4Income);

        Map<Integer, String> lowerReason4Cost = new HashMap<>();
        lowerReason4Cost.put(1, "市场宣传和推广力度不足，导致宣传费用减少，影响销售效果");
        lowerReason4Cost.put(2, "销售团队规模缩减，导致销售人员成本减少，影响销售能力");
        lowerThresholdReason.put("销售费用", lowerReason4Cost);

        Map<Integer, String> lowerReason4Inventory = new HashMap<>();
        lowerReason4Inventory.put(1, "销售预测不准确，未能及时预测到市场需求增加，导致库存不足");
        lowerReason4Inventory.put(2, "市场需求突然增加，超出了预期，导致库存不足");
        lowerThresholdReason.put("产品库存", lowerReason4Inventory);

        Map<Integer, String> lowerReason4Score = new HashMap<>();
        lowerReason4Score.put(1, "客户投诉率较低，反馈不足，可能导致未能及时发现问题，影响销售服务质量");
        lowerReason4Score.put(2, "市场竞争激烈，为了节省成本，减少了销售服务投入，影响了客户满意度");
        lowerThresholdReason.put("销售服务", lowerReason4Score);
    }

    static {
        Map<Integer, String> lowerMeasure4Quantity = new HashMap<>();
        lowerMeasure4Quantity.put(1, "加强市场调研，优化销售策略，拓展新的市场份额，刺激市场需求");
        lowerMeasure4Quantity.put(2, "重新规划市场宣传活动，提高产品知名度，增加市场曝光度");
        lowerThresholdMeasure.put("产品销量", lowerMeasure4Quantity);

        Map<Integer, String> lowerMeasure4Income = new HashMap<>();
        lowerMeasure4Income.put(1, "加强市场调研，优化销售策略，拓展新的市场份额，刺激市场需求，提高销售营收");
        lowerMeasure4Income.put(2, "重新规划市场宣传活动，提高产品知名度，增加市场曝光度，恢复销售营收");
        lowerThresholdMeasure.put("销售营收", lowerMeasure4Income);

        Map<Integer, String> lowerMeasure4Cost = new HashMap<>();
        lowerMeasure4Cost.put(1, "加强市场调研，制定合适的市场宣传策略，增加宣传投入，提高销售效果");
        lowerMeasure4Cost.put(2, "适当增加销售团队规模，提高销售人员数量，确保有足够的销售力量");
        lowerThresholdMeasure.put("销售费用", lowerMeasure4Cost);

        Map<Integer, String> lowerMeasure4Inventory = new HashMap<>();
        lowerMeasure4Inventory.put(1, "加强市场调研和数据分析，优化销售预测模型，提高预测准确性");
        lowerMeasure4Inventory.put(2, "建立灵活的库存补充机制，与供应链紧密合作，确保及时增加库存");
        lowerThresholdMeasure.put("产品库存", lowerMeasure4Inventory);

        Map<Integer, String> lowerMeasure4Score = new HashMap<>();
        lowerMeasure4Score.put(1, "积极与客户沟通，主动收集反馈，建立客户反馈机制，提高问题发现和解决的效率");
        lowerMeasure4Score.put(2, "重新评估销售服务的重要性，合理分配资源，确保客户满意度和服务质量");
        lowerThresholdMeasure.put("销售服务", lowerMeasure4Score);
    }

    static {
        attributeMap.put("产品销量", "quantity");
        attributeMap.put("销售营收", "income");
        attributeMap.put("销售费用", "cost");
        attributeMap.put("产品库存", "inventory");
        attributeMap.put("销售服务", "score");
    }

    /**
     * 决策元 -- 销售链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 销售链报表数据SaleInfo
     */
    @GetMapping("/prediction/query")
    public SaleInfo getSaleInfos(long time, int granularity, int limit, int offset) {
        return saleDecisionService.getSalePredictionInfos(time, granularity, limit, offset);
    }

    /**
     * 预警查询功能
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 预警数据
     */
    @GetMapping("/warning/query")
    public Map<String, List<SaleWarningInfo>> getSaleWarningInfos(long time, int granularity, int categories, String attributes) {
        List<SaleWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        return warningInfos.stream().collect(Collectors.groupingBy(SaleWarningInfo::getCorporation));
    }

    @GetMapping("/measure/query")
    public Map<String, List<SaleMeasureInfo>> getSaleMeasureInfos(long time, int granularity, int categories, String attributes) {
        List<SaleWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        List<SaleMeasureInfo> saleMeasureInfos = new ArrayList<>();
        for(SaleWarningInfo warningInfo : warningInfos) {
            SaleMeasureInfo measureInfo = new SaleMeasureInfo();
            BeanUtils.copyProperties(warningInfo, measureInfo);
            String measure = warningInfo.getAlarmType() == 1 ?
                    upperThresholdMeasure.get(attributes).get(warningInfo.getLevel()):
                    lowerThresholdMeasure.get(attributes).get(warningInfo.getLevel());

            measureInfo.setMeasure(measure);
            saleMeasureInfos.add(measureInfo);
        }

        return saleMeasureInfos.stream().collect(Collectors.groupingBy(SaleMeasureInfo::getCorporation));
    }

    private List<SaleWarningInfo> getWarningInfos(long time, int granularity, int categories, String attributes) {
        List<Sale> sales = saleDecisionService.getSaleWarningInfos(time, granularity);
        List<DecisionThreshold> thresholds = decisionThresholdService.getDecisionThreshold(categories, attributes);
        List<SaleWarningInfo> warningInfos = new ArrayList<>();
        for (Sale sale : sales) {
            if (thresholds == null || thresholds.size() <= 0) {
                continue;
            }
            DecisionThreshold threshold = thresholds.get(0);
            if (threshold != null) {
                SaleWarningInfo warningInfo = new SaleWarningInfo();
                BeanUtils.copyProperties(sale, warningInfo);
                try {
                    Field field = Sale.class.getDeclaredField(attributeMap.get(attributes));
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

