package ldn.cs.decision.controller;

import ldn.cs.decision.pojo.staff.Staff;
import ldn.cs.decision.pojo.staff.StaffMeasureInfo;
import ldn.cs.decision.pojo.staff.StaffInfo;
import ldn.cs.decision.pojo.staff.StaffWarningInfo;
import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.service.DecisionThresholdService;
import ldn.cs.decision.service.StaffDecisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/decision/element/staff")
public class StaffDecisionController {
    @Autowired
    private StaffDecisionService staffDecisionService;

    @Autowired
    private DecisionThresholdService decisionThresholdService;

    private static final Map<Integer, String> upperThresholdReason = new HashMap<Integer, String>() {{
        put(1, "人员扩招"); // 人员扩招
        put(2, "人员过剩"); // 人员过剩
    }};
    private static final Map<Integer, String> lowerThresholdReason = new HashMap<Integer, String>() {{
        put(1, "人员缩招"); // 人员缩招
        put(2, "人员流失"); // 人员流失
    }};

    // 1 --> 一般, 2 --> 严重
    private static final Map<Integer, String> upperThresholdMeasure = new HashMap<Integer, String>() {{
        put(1, "灵活用工"); // 灵活用工
        put(2, "发展新业务，开拓新航道"); // 发展新业务，开拓新航道
    }};
    private static final Map<Integer, String> lowerThresholdMeasure = new HashMap<Integer, String>() {{
        put(1, "灵活用工"); // 灵活用工
        put(2, "集中人员发展业务"); // 集中人员发展新业务
    }};

    /**
     * 决策元 -- 人力链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 人力链报表数据StaffInfo
     */
    @GetMapping("/prediction/query")
    public StaffInfo getStaffInfos(long time, int granularity, int limit, int offset) {
        return staffDecisionService.getStaffPredictionInfos(time, granularity, limit, offset);
    }

    /**
     * 预警查询功能
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 预警数据
     */
    @GetMapping("/warning/query")
    public Map<String, List<StaffWarningInfo>> getStaffWarningInfos(long time, int granularity, int categories, String attributes) {
        List<StaffWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);

        return warningInfos.stream()
                .collect(Collectors.groupingBy(StaffWarningInfo::getCorporation));
    }

    /**
     * 决策查询功能
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 决策数据
     */
    @GetMapping("/measure/query")
    public Map<String, List<StaffMeasureInfo>> getStaffMeasureInfos(long time, int granularity, int categories, String attributes) {
        List<StaffWarningInfo> warningInfos = getWarningInfos(time, granularity, categories, attributes);
        List<StaffMeasureInfo> staffMeasureInfos = new ArrayList<>();
        for (StaffWarningInfo warningInfo : warningInfos) {
            StaffMeasureInfo measureInfo = new StaffMeasureInfo();
            BeanUtils.copyProperties(warningInfo, measureInfo);

            String measure = warningInfo.getAlarmType() == 1 ?
                    upperThresholdMeasure.get(warningInfo.getLevel()) :
                    lowerThresholdMeasure.get(warningInfo.getLevel());

            measureInfo.setMeasure(measure);
            staffMeasureInfos.add(measureInfo);
        }
        return staffMeasureInfos.stream().collect(Collectors.groupingBy(StaffMeasureInfo::getCorporation));
    }


    private List<StaffWarningInfo> getWarningInfos(long time, int granularity, int categories, String attributes) {
        List<Staff> staffs = staffDecisionService.getStaffWarningInfos(time, granularity);
        List<DecisionThreshold> thresholds = decisionThresholdService.getDecisionThreshold(categories, attributes);
        List<StaffWarningInfo> warningInfos = new ArrayList<>();
        for (Staff staff : staffs) {
            if (thresholds == null || thresholds.size() <= 0) {
                continue;
            }
            DecisionThreshold threshold = thresholds.get(0);
            if (threshold != null) {
                StaffWarningInfo warningInfo = new StaffWarningInfo();
                BeanUtils.copyProperties(staff, warningInfo);

                BigDecimal difference = BigDecimal.valueOf(staff.getAmount()).subtract(threshold.getAttributesValue()).abs();
                int alarmType, level;

                int comparisonThreshold = threshold.getAttributesValue().compareTo(BigDecimal.valueOf(staff.getAmount()));
                if (comparisonThreshold < 0) {
                    alarmType = 1; // 高于阈值
                } else if (comparisonThreshold > 0) {
                    alarmType = 0; // 低于阈值
                } else {
                    continue;
                }

                // Check the level of difference
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
                warningInfo.setCauseType(alarmType == 1 ? upperThresholdReason.get(level) : lowerThresholdReason.get(level));

                warningInfos.add(warningInfo);
            }
        }
        return warningInfos;
    }
}
