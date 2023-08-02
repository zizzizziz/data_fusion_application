package ldn.cs.decision.controller;

import ldn.cs.decision.dao.DecisionThresholdDao;
import ldn.cs.decision.pojo.staff.Staff;
import ldn.cs.decision.pojo.staff.StaffMeasureInfo;
import ldn.cs.decision.pojo.staff.StaffInfo;
import ldn.cs.decision.pojo.staff.StaffWarningInfo;
import ldn.cs.decision.pojo.threshold.DecisionThreshold;
import ldn.cs.decision.service.StaffDecisionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/decision/element/staff")
public class StaffDecisionController {
    @Autowired
    private StaffDecisionService staffDecisionService;

    @Autowired
    private DecisionThresholdDao decisionThresholdDao;

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
    public Map<String, List<StaffWarningInfo>> getStaffWarningInfo(long time, int granularity) {
        List<StaffWarningInfo> warningInfos = getStaffWarningInfos(time, granularity);

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
    public Map<String, List<StaffMeasureInfo>> getStaffMeasureInfo(long time, int granularity) {
        List<StaffWarningInfo> warningInfos = getStaffWarningInfos(time, granularity);

        Map<Integer, String> levelToMeasureMapForUpperThreshold = new HashMap<Integer, String>() {{
            put(1, "灵活用工"); // 灵活用工
            put(2, "发展新业务，开拓新航道"); // 发展新业务，开拓新航道
        }};
        Map<Integer, String> levelToMeasureMapForLowerThreshold = new HashMap<Integer, String>() {{
            put(1, "灵活用工"); // 灵活用工
            put(2, "集中人员发展业务"); // 集中人员发展新业务
        }};

        List<StaffMeasureInfo> staffMeasureInfos = new ArrayList<>();
        for (StaffWarningInfo warningInfo : warningInfos) {
            StaffMeasureInfo measureInfo = new StaffMeasureInfo();
            BeanUtils.copyProperties(warningInfo, measureInfo);

            String measure = warningInfo.getAlarmType() == 1 ?
                    levelToMeasureMapForUpperThreshold.get(warningInfo.getLevel()) :
                    levelToMeasureMapForLowerThreshold.get(warningInfo.getLevel());

            measureInfo.setMeasure(measure);
            staffMeasureInfos.add(measureInfo);
        }
        return staffMeasureInfos.stream().collect(Collectors.groupingBy(StaffMeasureInfo::getCorporation));
    }


    private List<StaffWarningInfo> getStaffWarningInfos(long time, int granularity) {
        List<Staff> staffs = staffDecisionService.getStaffWarningInfo(time, granularity);
        List<DecisionThreshold> thresholds = decisionThresholdDao.getDecisionThreshold(1);

        Map<String, DecisionThreshold> categoryToThresholdMap = new HashMap<>();
        for (DecisionThreshold threshold : thresholds) {
            if ("amount".equals(threshold.getAttributes())) {
                categoryToThresholdMap.put(threshold.getAttributes(), threshold);
            }
        }

        Map<Integer, String> levelToCauseMapForUpperThreshold = new HashMap<Integer, String>() {{
            put(1, "人员扩招"); // 人员扩招
            put(2, "人员过剩"); // 人员过剩
        }};
        Map<Integer, String> levelToCauseMapForLowerThreshold = new HashMap<Integer, String>() {{
            put(1, "人员缩招"); // 人员缩招
            put(2, "人员流失"); // 人员流失
        }};

        List<StaffWarningInfo> warningInfos = new ArrayList<>();

        for (Staff staff : staffs) {
            DecisionThreshold threshold = categoryToThresholdMap.get("amount");
            if (threshold != null) {
                StaffWarningInfo warningInfo = new StaffWarningInfo();
                BeanUtils.copyProperties(staff, warningInfo);

                long difference = Math.abs(staff.getAmount() - threshold.getAttributesValue());
                int alarmType, level;

                if (staff.getAmount() > threshold.getAttributesValue()) {
                    alarmType = 1; // 高于阈值
                } else if (staff.getAmount() < threshold.getAttributesValue()) {
                    alarmType = 0; // 低于阈值
                } else {
                    continue;
                }

                // Check the level of difference
                if (difference > 0.5 * threshold.getAttributesValue()) {
                    level = 2; // 严重
                } else if (difference > 0.25 * threshold.getAttributesValue()) {
                    level = 1; // 一般
                } else {
                    continue;
                }

                warningInfo.setAlarmType(alarmType);
                warningInfo.setLevel(level);
                warningInfo.setCauseType(alarmType == 1 ? levelToCauseMapForUpperThreshold.get(level) : levelToCauseMapForLowerThreshold.get(level));

                warningInfos.add(warningInfo);
            }
        }
        return warningInfos;
    }
}
