package ldn.cs.decision.controller;

import ldn.cs.decision.pojo.convey.ConveyInfo;
import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.pojo.convey.Convey;
import ldn.cs.decision.pojo.convey.ConveyMeasureInfo;
import ldn.cs.decision.pojo.convey.ConveyWarningInfo;
import ldn.cs.decision.service.ConveyDecisionService;
import ldn.cs.decision.service.DecisionThresholdService;
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
@RequestMapping("/rest/decision/element/convey")
public class ConveyDecisionController {
    @Autowired
    private ConveyDecisionService conveyDecisionService;

    @Autowired
    private DecisionThresholdService decisionThresholdService;

    private static final Map<String, Map<Integer, String>> upperThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> upperThresholdMeasure = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdMeasure = new HashMap<>();

    private static final Map<String, String> attributeMap = new HashMap<>();

    static {
        Map<Integer, String> upperReason4Quantity = new HashMap<>();
        upperReason4Quantity.put(1, "市场需求超出预期，导致运输需求增加");
        upperReason4Quantity.put(2, "供应链中断，导致物流瓶颈，运输需求激增");
        upperThresholdReason.put("运输数量", upperReason4Quantity);

        Map<Integer, String> upperReason4Inventory = new HashMap<>();
        upperReason4Inventory.put(1, "市场需求下降，导致库存积压");
        upperReason4Inventory.put(2, "季节性需求波动较大，未能合理调整库存");
        upperThresholdReason.put("运输剩余库存", upperReason4Inventory);

        Map<Integer, String> upperReason4Mileage = new HashMap<>();
        upperReason4Mileage.put(1, "市场需求超出预期，导致运输量增加");
        upperReason4Mileage.put(2, "产品供应链中断，导致物流瓶颈，运输需求激增");
        upperThresholdReason.put("运输里程数", upperReason4Mileage);

        Map<Integer, String> upperReason4Cost = new HashMap<>();
        upperReason4Cost.put(1, "燃油价格上涨，导致运输成本增加");
        upperReason4Cost.put(2, "交通拥堵、路况不佳等因素导致运输时间延长，增加运输费用");
        upperThresholdReason.put("运输费用", upperReason4Cost);
    }

    static {
        Map<Integer, String> upperMeasure4Quantity = new HashMap<>();
        upperMeasure4Quantity.put(1, "优化物流运输计划，增加运力，确保及时满足市场需求");
        upperMeasure4Quantity.put(2, "紧急寻找备选供应渠道，恢复供应链稳定，满足运输需求");
        upperThresholdMeasure.put("运输数量", upperMeasure4Quantity);

        Map<Integer, String> upperMeasure4Inventory = new HashMap<>();
        upperMeasure4Inventory.put(1, "优化市场推广，拓展销售渠道，提高产品需求");
        upperMeasure4Inventory.put(2, "建立灵活的库存管理策略，预测季节性需求变化，避免库存积压");
        upperThresholdMeasure.put("运输剩余库存", upperMeasure4Inventory);

        Map<Integer, String> upperMeasure4Mileage = new HashMap<>();
        upperMeasure4Mileage.put(1, "优化物流运输计划，增加运力，确保及时满足市场需求");
        upperMeasure4Mileage.put(2, "紧急寻找备选供应渠道，恢复供应链稳定，满足运输需求");
        upperThresholdMeasure.put("运输里程数", upperMeasure4Mileage);

        Map<Integer, String> upperMeasure4Cost = new HashMap<>();
        upperMeasure4Cost.put(1, "优化物流运输路线，寻找更经济高效的运输方式，节约燃油成本");
        upperMeasure4Cost.put(2, "优化路线规划，避开拥堵路段，减少运输时间，降低运输费用");
        upperThresholdMeasure.put("运输费用", upperMeasure4Cost);
    }

    static {
        Map<Integer, String> lowerReason4Quantity = new HashMap<>();
        lowerReason4Quantity.put(1, "市场需求不足，导致运输需求减少");
        lowerReason4Quantity.put(2, "供应链中断，导致物流瓶颈，影响运输需求");
        lowerThresholdReason.put("运输数量", lowerReason4Quantity);

        Map<Integer, String> lowerReason4Inventory = new HashMap<>();
        lowerReason4Inventory.put(1, "市场需求超出预期，库存迅速被销售完毕");
        lowerReason4Inventory.put(2, "市场宣传活动效果好，导致产品迅速售罄");
        lowerThresholdReason.put("运输剩余库存", lowerReason4Inventory);

        Map<Integer, String> lowerReason4Mileage = new HashMap<>();
        lowerReason4Mileage.put(1, "市场需求暂时性减少，运输里程数下降");
        lowerReason4Mileage.put(2, "销售计划与库存管理不协调，导致运输里程数下降");
        lowerThresholdReason.put("运输里程数", lowerReason4Mileage);

        Map<Integer, String> lowerReason4Cost = new HashMap<>();
        lowerReason4Cost.put(1, "市场需求不足，导致运输量减少，从而降低了运输费用");
        lowerReason4Cost.put(2, "运输规模缩小，订单数量减少，降低了运输费用");
        lowerThresholdReason.put("运输费用", lowerReason4Cost);
    }

    static {
        Map<Integer, String> lowerMeasure4Quantity = new HashMap<>();
        lowerMeasure4Quantity.put(1, "加强市场调研，优化销售策略，拓展新的市场份额");
        lowerMeasure4Quantity.put(2, "紧急寻找备选供应渠道，恢复供应链稳定，增加运输需求");
        lowerThresholdMeasure.put("运输数量", lowerMeasure4Quantity);

        Map<Integer, String> lowerMeasure4Inventory = new HashMap<>();
        lowerMeasure4Inventory.put(1, "加强市场调研，预测市场需求，提前调整生产计划");
        lowerMeasure4Inventory.put(2, "优化市场宣传策略，提前做好库存补充准备");
        lowerThresholdMeasure.put("运输剩余库存", lowerMeasure4Inventory);

        Map<Integer, String> lowerMeasure4Mileage = new HashMap<>();
        lowerMeasure4Mileage.put(1, "灵活调整运输计划，减少运输资源浪费，降低运输里程");
        lowerMeasure4Mileage.put(2, "加强销售与库存协调，确保销售计划与运输需求匹配，提高运输里程数");
        lowerThresholdMeasure.put("运输里程数", lowerMeasure4Mileage);

        Map<Integer, String> lowerMeasure4Cost = new HashMap<>();
        lowerMeasure4Cost.put(1, "加强市场调研，优化销售策略，拓展新的市场份额，增加运输需求");
        lowerMeasure4Cost.put(2, "优化订单处理流程，提高运输效率，增加订单数量，提升运输费用");
        lowerThresholdMeasure.put("运输费用", lowerMeasure4Cost);
    }

    static {
        attributeMap.put("运输数量", "quantity");
        attributeMap.put("运输剩余库存", "inventory");
        attributeMap.put("运输里程数", "mileage");
        attributeMap.put("运输费用", "cost");
    }    

    /**
     * 决策元 -- 物流链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 物流链报表数据ConveyInfo
     */
    @GetMapping("/prediction/query")
    public ConveyInfo getConveyInfos(long time, int granularity, int limit, int offset) {
        return conveyDecisionService.getConveyPredictionInfos(time, granularity, limit, offset);
    }

    /**
     * 预警查询功能
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 预警数据
     */
    @GetMapping("/warning/query")
    public Map<String, List<ConveyWarningInfo>> getConveyWarningInfos(long time, int granularity, int categories, String attributes) {
        List<ConveyWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        return warningInfos.stream().collect(Collectors.groupingBy(ConveyWarningInfo::getCorporation));
    }

    @GetMapping("/measure/query")
    public Map<String, List<ConveyMeasureInfo>> getConveyMeasureInfos(long time, int granularity, int categories, String attributes) {
        List<ConveyWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        List<ConveyMeasureInfo> conveyMeasureInfos = new ArrayList<>();
        for(ConveyWarningInfo warningInfo : warningInfos) {
            ConveyMeasureInfo measureInfo = new ConveyMeasureInfo();
            BeanUtils.copyProperties(warningInfo, measureInfo);
            String measure = warningInfo.getAlarmType() == 1 ?
                    upperThresholdMeasure.get(attributes).get(warningInfo.getLevel()):
                    lowerThresholdMeasure.get(attributes).get(warningInfo.getLevel());

            measureInfo.setMeasure(measure);
            conveyMeasureInfos.add(measureInfo);
        }

        return conveyMeasureInfos.stream().collect(Collectors.groupingBy(ConveyMeasureInfo::getCorporation));
    }

    private List<ConveyWarningInfo> getWarningInfos(long time, int granularity, int categories, String attributes) {
        List<Convey> conveys =conveyDecisionService.getConveyWarningInfos(time, granularity);
        List<DecisionThreshold> thresholds = decisionThresholdService.getDecisionThreshold(categories, attributes);
        List<ConveyWarningInfo> warningInfos = new ArrayList<>();
        for (Convey convey : conveys) {
            if (thresholds == null || thresholds.size() <= 0) {
                continue;
            }
            DecisionThreshold threshold = thresholds.get(0);
            if (threshold != null) {
                ConveyWarningInfo warningInfo = new ConveyWarningInfo();
                BeanUtils.copyProperties(convey, warningInfo);
                try {
                    Field field = Convey.class.getDeclaredField(attributeMap.get(attributes));
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

