package ldn.cs.decision.controller;


import ldn.cs.decision.pojo.staff.StaffMeasureInfo;
import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.pojo.wealth.Wealth;
import ldn.cs.decision.pojo.wealth.WealthInfo;
import ldn.cs.decision.pojo.wealth.WealthMeasureInfo;
import ldn.cs.decision.pojo.wealth.WealthWarningInfo;
import ldn.cs.decision.service.DecisionThresholdService;
import ldn.cs.decision.service.WealthDecisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest/decision/element/wealth")
public class WealthDecisionController {
    @Autowired
    private WealthDecisionService wealthDecisionService;

    @Autowired
    private DecisionThresholdService decisionThresholdService;

    private static final Map<String, Map<Integer, String>> upperThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdReason = new HashMap<>();

    private static final Map<String, Map<Integer, String>> upperThresholdMeasure = new HashMap<>();

    private static final Map<String, Map<Integer, String>> lowerThresholdMeasure = new HashMap<>();

    private static final Map<String, String> attributeMap = new HashMap<>();

    static {
        Map<Integer, String> upperReason4Research = new HashMap<>();
        upperReason4Research.put(1, "公司可能在扩大研发力度，推出新产品或服务");
        upperReason4Research.put(2, "公司可能在多个领域进行高强度研发，以追求技术领先地位");
        upperThresholdReason.put("研发费用", upperReason4Research);

        Map<Integer, String> upperReason4Device = new HashMap<>();
        upperReason4Device.put(1, "公司可能在更新设备、提高生产效率或适应技术变革");
        upperReason4Device.put(2, "公司可能频繁更换设备，或者在设备采购上投入过多，可能影响盈利能力");
        upperThresholdReason.put("设备费用", upperReason4Device);

        Map<Integer, String> upperReason4Production = new HashMap<>();
        upperReason4Production.put(1, "生产成本可能受到季节性波动或供应链变化影响");
        upperReason4Production.put(2, "生产过程出现严重问题，导致成本剧增，需要紧急处理");
        upperThresholdReason.put("生产费用", upperReason4Production);

        Map<Integer, String> upperReason4Store = new HashMap<>();
        upperReason4Store.put(1, "库存管理可能存在一些短期波动，可能与销售季节性有关");
        upperReason4Store.put(2, "库存积压严重，可能与销售不畅有关，需要及时调整库存策略");
        upperThresholdReason.put("仓储费用", upperReason4Store);

        Map<Integer, String> upperReason4Material = new HashMap<>();
        upperReason4Material.put(1, "原材料价格波动可能导致短期内费用上升");
        upperReason4Material.put(2, "原材料供应链受到严重干扰，导致费用急剧增加，可能需要紧急寻找替代供应渠道");
        upperThresholdReason.put("物料费用", upperReason4Material);

        Map<Integer, String> upperReason4Convey = new HashMap<>();
        upperReason4Convey.put(1, "物流成本上升，可能由于运输费用的增加或交通拥堵等因素");
        upperReason4Convey.put(2, "物流问题严重，可能导致运输成本大幅增加，可能是供应链中断或交通问题所致");
        upperThresholdReason.put("运输费用", upperReason4Convey);

        Map<Integer, String> upperReason4Staff = new HashMap<>();
        upperReason4Staff.put(1, "员工薪资增加，可能是为了留住人才或符合法定调整");
        upperReason4Staff.put(2, "员工薪资支出远超预期，可能导致人力成本过高，需要审视薪酬政策");
        upperThresholdReason.put("人工费用", upperReason4Staff);

        Map<Integer, String> upperReason4Profit = new HashMap<>();
        upperReason4Profit.put(1, "市场份额增加，销售推广效果好，或者公司推出了热门产品");
        upperReason4Profit.put(2, "销售增加，市场需求上升");
        upperThresholdReason.put("利润", upperReason4Profit);

        Map<Integer, String> upperReason4Assets = new HashMap<>();
        upperReason4Assets.put(1, "公司进行资产扩张，投资于新的固定资产或流动资产项目");
        upperReason4Assets.put(2, "资产负债比例失衡，可能是资产配置不当或负债过高的结果，需要调整资产结构");
        upperThresholdReason.put("流动资产", upperReason4Assets);

        Map<Integer, String> upperReason4Finance = new HashMap<>();
        upperReason4Finance.put(1, "公司正在积极寻求融资，以支持扩张计划或新项目");
        upperReason4Finance.put(2, "公司可能面临严重的资金问题，可能是因为市场信心下降或经营不善");
        upperThresholdReason.put("融资", upperReason4Finance);
    }

    static {
        Map<Integer, String> upperMeasure4Research = new HashMap<>();
        upperMeasure4Research.put(1, "确保研发项目的有效管理和资源分配，优化研发流程，提高项目产出");
        upperMeasure4Research.put(2, "审查研发项目的战略性和紧急性，可能需要削减一些非关键性研发项目，集中资源于关键项目上");
        upperThresholdMeasure.put("研发费用", upperMeasure4Research);

        Map<Integer, String> upperMeasure4Device = new HashMap<>();
        upperMeasure4Device.put(1, "定期评估设备更新的必要性，确保设备的维护和使用效率");
        upperMeasure4Device.put(2, "优化设备采购决策，进行成本效益分析，避免过度投入和频繁更换");
        upperThresholdMeasure.put("设备费用", upperMeasure4Device);

        Map<Integer, String> upperMeasure4Production = new HashMap<>();
        upperMeasure4Production.put(1, "建立灵活的生产计划，预测季节性需求变化，优化供应链管理");
        upperMeasure4Production.put(2, "紧急解决生产问题，避免进一步成本增加，审查生产流程以减少风险");
        upperThresholdMeasure.put("生产费用", upperMeasure4Production);

        Map<Integer, String> upperMeasure4Store = new HashMap<>();
        upperMeasure4Store.put(1, "优化库存管理策略，预测季节性需求变化，减少库存积压");
        upperMeasure4Store.put(2, "积极寻求销售渠道，采取促销策略，减少库存，提高周转率");
        upperThresholdMeasure.put("仓储费用", upperMeasure4Store);

        Map<Integer, String> upperMeasure4Material = new HashMap<>();
        upperMeasure4Material.put(1, "优化采购策略，与供应商建立稳定的合作关系，考虑采用替代原材料");
        upperMeasure4Material.put(2, "寻找替代供应商，分散供应链风险，确保原材料稳定供应");
        upperThresholdMeasure.put("物料费用", upperMeasure4Material);

        Map<Integer, String> upperMeasure4Convey = new HashMap<>();
        upperMeasure4Convey.put(1, "优化物流运输路线，寻找更经济高效的运输方式");
        upperMeasure4Convey.put(2, "紧急处理物流问题，确保物流畅通，减少成本增加");
        upperThresholdMeasure.put("运输费用", upperMeasure4Convey);

        Map<Integer, String> upperMeasure4Staff = new HashMap<>();
        upperMeasure4Staff.put(1, "评估薪资结构，合理调整员工薪资，提供福利以留住人才");
        upperMeasure4Staff.put(2, "审查公司薪酬政策，寻找降低人力成本的方法，如优化组织结构或自动化流程");
        upperThresholdMeasure.put("人工费用", upperMeasure4Staff);

        Map<Integer, String> upperMeasure4Profit = new HashMap<>();
        upperMeasure4Profit.put(1, "继续强化市场推广，保持产品创新，提高销售效率");
        upperMeasure4Profit.put(2, "有效管理供应链，确保能够满足市场需求，同时考虑扩大产能");
        upperThresholdMeasure.put("利润", upperMeasure4Profit);

        Map<Integer, String> upperMeasure4Assets = new HashMap<>();
        upperMeasure4Assets.put(1, "审查资产配置计划，确保投资项目的可行性和回报");
        upperMeasure4Assets.put(2, "重新平衡资产负债结构，优化资产配置，降低负债风险");
        upperThresholdMeasure.put("流动资产", upperMeasure4Assets);

        Map<Integer, String> upperMeasure4Finance = new HashMap<>();
        upperMeasure4Finance.put(1, "积极寻找融资渠道，制定详细的融资计划，吸引投资者");
        upperMeasure4Finance.put(2, "紧急筹措资金，评估经营状况，制定可行的盈利增长计划，恢复市场信心");
        upperThresholdMeasure.put("融资", upperMeasure4Finance);
    }

    static {
        Map<Integer, String> lowerReason4Research = new HashMap<>();
        lowerReason4Research.put(1, "公司可能在研发领域削减支出，可能会影响未来创新和竞争力");
        lowerReason4Research.put(2, "公司可能削减了研发投入，导致研发项目停滞或减少了创新活动");
        lowerThresholdReason.put("研发费用", lowerReason4Research);

        Map<Integer, String> lowerReason4Device = new HashMap<>();
        lowerReason4Device.put(1, "公司可能推迟了设备的更新或维护，可能导致生产效率下降");
        lowerReason4Device.put(2, "公司可能减少了设备投资，导致设备老化或无法满足生产需求");
        lowerThresholdReason.put("设备费用", lowerReason4Device);

        Map<Integer, String> lowerReason4Production = new HashMap<>();
        lowerReason4Production.put(1, "生产效率可能提高，或者公司可能调整了生产策略");
        lowerReason4Production.put(2, "公司可能降低了生产成本，导致生产质量下降或效率低下");
        lowerThresholdReason.put("生产费用", lowerReason4Production);

        Map<Integer, String> lowerReason4Store = new HashMap<>();
        lowerReason4Store.put(1, "库存管理可能变得更加高效，或者销售可能下降");
        lowerReason4Store.put(2, "公司可能在仓储方面削减成本，可能导致库存积压或物流问题");
        lowerThresholdReason.put("仓储费用", lowerReason4Store);

        Map<Integer, String> lowerReason4Material = new HashMap<>();
        lowerReason4Material.put(1, "公司可能在原材料采购方面进行了成本削减或者供应链有所改进");
        lowerReason4Material.put(2, "公司可能在采购和物料管理方面削减成本，可能导致原材料短缺或质量问题");
        lowerThresholdReason.put("物料费用", lowerReason4Material);

        Map<Integer, String> lowerReason4Convey = new HashMap<>();
        lowerReason4Convey.put(1, "可能与物流策略的优化有关，例如采用更经济高效的运输方式");
        lowerReason4Convey.put(2, "公司可能在物流和运输方面削减成本，可能导致交货延迟或物流问题");
        lowerThresholdReason.put("运输费用", lowerReason4Convey);

        Map<Integer, String> lowerReason4Staff = new HashMap<>();
        lowerReason4Staff.put(1, "公司可能采用了自动化技术");
        lowerReason4Staff.put(2, "公司可能在人力资源管理和薪酬方面削减成本，可能导致员工流失或生产效率下降");
        lowerThresholdReason.put("人工费用", lowerReason4Staff);

        Map<Integer, String> lowerReason4Profit = new HashMap<>();
        lowerReason4Profit.put(1, "市场竞争可能加剧，销售下降，或者成本控制不足");
        lowerReason4Profit.put(2, "公司可能面临经营困难，盈利能力受到严重压制，可能导致财务健康状况不佳");
        lowerThresholdReason.put("利润", lowerReason4Profit);

        Map<Integer, String> upperReason4Assets = new HashMap<>();
        upperReason4Assets.put(1, "公司可能在资产方面进行了削减，可能是为了应对资金需求或调整战略");
        upperReason4Assets.put(2, "公司可能面临流动性问题，可能导致无法满足短期支付需求");
        lowerThresholdReason.put("流动资产", upperReason4Assets);

        Map<Integer, String> upperReason4Finance = new HashMap<>();
        upperReason4Finance.put(1, "公司可能已经成功获得融资，或者决定不再依赖外部融资");
        upperReason4Finance.put(2, "公司可能面临资金短缺，无法满足资金需求，可能影响正常经营和发展");
        lowerThresholdReason.put("融资", upperReason4Finance);
    }

    static {
        Map<Integer, String> lowerMeasure4Research = new HashMap<>();
        lowerMeasure4Research.put(1, "审慎评估研发项目，确保核心研发项目得到充分支持，避免影响公司长期创新能力");
        lowerMeasure4Research.put(2, "重新评估研发投入决策，恢复关键项目的支持，保持创新持续进行");
        lowerThresholdMeasure.put("研发费用", lowerMeasure4Research);

        Map<Integer, String> lowerMeasure4Device = new HashMap<>();
        lowerMeasure4Device.put(1, "优化设备维护计划，确保设备性能保持高效");
        lowerMeasure4Device.put(2, "重新考虑设备投资决策，确保设备现代化以保持生产能力");
        lowerThresholdMeasure.put("设备费用", lowerMeasure4Device);

        Map<Integer, String> lowerMeasure4Production = new HashMap<>();
        lowerMeasure4Production.put(1, "继续优化生产流程，确保高效生产运营");
        lowerMeasure4Production.put(2, "确保成本削减不影响产品质量，重视质量管理，避免长期影响");
        lowerThresholdMeasure.put("生产费用", lowerMeasure4Production);

        Map<Integer, String> lowerMeasure4Store = new HashMap<>();
        lowerMeasure4Store.put(1, "优化库存管理，减少库存积压，确保库存与实际销售需求相匹配");
        lowerMeasure4Store.put(2, "重新调整库存管理策略，确保库存控制不影响物流畅通");
        lowerThresholdMeasure.put("仓储费用", lowerMeasure4Store);

        Map<Integer, String> lowerMeasure4Material = new HashMap<>();
        lowerMeasure4Material.put(1, "审查成本削减策略，确保不影响原材料质量和供应稳定性");
        lowerMeasure4Material.put(2, "重新评估采购策略，确保稳定供应，维护产品质量");
        lowerThresholdMeasure.put("物料费用", lowerMeasure4Material);

        Map<Integer, String> lowerMeasure4Convey = new HashMap<>();
        lowerMeasure4Convey.put(1, "持续优化物流策略，降低运输成本，提高运输效率");
        lowerMeasure4Convey.put(2, "确保物流渠道畅通，避免影响供应链的稳定性和运输准时性");
        lowerThresholdMeasure.put("运输费用", lowerMeasure4Convey);

        Map<Integer, String> lowerMeasure4Staff = new HashMap<>();
        lowerMeasure4Staff.put(1, "继续推进自动化技术应用，提高生产效率");
        lowerMeasure4Staff.put(2, "审视人力资源策略，确保员工稳定性，避免影响生产运营");
        lowerThresholdMeasure.put("人工费用", lowerMeasure4Staff);

        Map<Integer, String> lowerMeasure4Profit = new HashMap<>();
        lowerMeasure4Profit.put(1, "重新审视市场竞争状况，优化销售和营销策略，加强成本管控");
        lowerMeasure4Profit.put(2, "紧急制定盈利增长计划，削减不必要的开支，确保公司财务健康");
        lowerThresholdMeasure.put("利润", lowerMeasure4Profit);

        Map<Integer, String> lowerMeasure4Assets = new HashMap<>();
        lowerMeasure4Assets.put(1, "确保资产削减不影响正常经营，保持流动性");
        lowerMeasure4Assets.put(2, "积极筹措资金，考虑融资选项，确保短期资金需求得到满足");
        lowerThresholdMeasure.put("流动资产", lowerMeasure4Assets);

        Map<Integer, String> lowerMeasure4Finance = new HashMap<>();
        lowerMeasure4Finance.put(1, "管理好已获得的融资资金，优化资金使用");
        lowerMeasure4Finance.put(2, "紧急筹措资金，寻找融资渠道，确保公司运营不受资金问题影响");
        lowerThresholdMeasure.put("融资", lowerMeasure4Finance);
    }

    static {
        attributeMap.put("研发费用", "research");
        attributeMap.put("设备费用", "device");
        attributeMap.put("生产费用", "production");
        attributeMap.put("仓储费用", "storage");
        attributeMap.put("物料费用", "materiel");
        attributeMap.put("运输费用", "transportation");
        attributeMap.put("人工费用", "salary");
        attributeMap.put("利润", "profit");
        attributeMap.put("流动资产", "cashAssets");
        attributeMap.put("融资", "finance");
    }
    /**
     * 决策元 -- 财务链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 财务链报表数据WealthInfo
     */
    @GetMapping("/prediction/query")
    public WealthInfo getWealthInfos(long time, int granularity, int limit, int offset) {
        return wealthDecisionService.getWealthPredictionInfos(time, granularity, limit, offset);
    }

    /**
     * 预警查询功能
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 预警数据
     */
    @GetMapping("/warning/query")
    public Map<String, List<WealthWarningInfo>> getWealthWarningInfos(long time, int granularity, int categories, String attributes) {
        List<WealthWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        return warningInfos.stream().collect(Collectors.groupingBy(WealthWarningInfo::getCorporation));
    }

    @GetMapping("/measure/query")
    public Map<String, List<WealthMeasureInfo>> getWealthMeasureInfos(long time, int granularity, int categories, String attributes) {
        List<WealthWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        List<WealthMeasureInfo> wealthMeasureInfos = new ArrayList<>();
        for(WealthWarningInfo warningInfo : warningInfos) {
            WealthMeasureInfo measureInfo = new WealthMeasureInfo();
            BeanUtils.copyProperties(warningInfo, measureInfo);
            String measure = warningInfo.getAlarmType() == 1 ?
                    upperThresholdMeasure.get(attributes).get(warningInfo.getLevel()):
                    lowerThresholdMeasure.get(attributes).get(warningInfo.getLevel());

            measureInfo.setMeasure(measure);
            wealthMeasureInfos.add(measureInfo);
        }

        return wealthMeasureInfos.stream().collect(Collectors.groupingBy(WealthMeasureInfo::getCorporation));
    }

    private List<WealthWarningInfo> getWarningInfos(long time, int granularity, int categories, String attributes) {
        List<Wealth> wealths =wealthDecisionService.getWealthWarningInfos(time, granularity);
        List<DecisionThreshold> thresholds = decisionThresholdService.getDecisionThreshold(categories, attributes);
        List<WealthWarningInfo> warningInfos = new ArrayList<>();
        for (Wealth wealth : wealths) {
            if (thresholds == null || thresholds.size() <= 0) {
                continue;
            }
            DecisionThreshold threshold = thresholds.get(0);
            if (threshold != null) {
                WealthWarningInfo warningInfo = new WealthWarningInfo();
                BeanUtils.copyProperties(wealth, warningInfo);
                try {
                    Field field = Wealth.class.getDeclaredField(attributeMap.get(attributes));
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
