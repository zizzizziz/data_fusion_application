package ldn.cs.decision.controller;


import ldn.cs.decision.pojo.wealth.WealthInfo;
import ldn.cs.decision.service.WealthDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/decision/element/wealth")
public class WealthDecisionController {
    @Autowired
    private WealthDecisionService wealthDecisionService;

    /**
     * 决策元 -- 财务链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 人力链报表数据StaffInfo
     */
    @GetMapping("/prediction/query")
    public WealthInfo getWealthInfos(long time, int granularity, int limit, int offset) {
        return wealthDecisionService.getWealthPredictionInfos(time, granularity, limit, offset);
    }

}
