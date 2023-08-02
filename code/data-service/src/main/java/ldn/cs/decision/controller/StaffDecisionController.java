package ldn.cs.decision.controller;

import ldn.cs.decision.alghthrims.staff.StaffPrediction;
import ldn.cs.decision.pojo.staff.Staff;
import ldn.cs.decision.pojo.staff.StaffInfo;
import ldn.cs.decision.service.StaffDecisionService;
import ldn.cs.decision.utils.ComputeFutureTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/decision/element/staff")
public class StaffDecisionController {
    @Autowired
    private StaffDecisionService staffDecisionService;

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
}
