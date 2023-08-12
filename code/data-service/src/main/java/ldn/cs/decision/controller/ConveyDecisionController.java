package ldn.cs.decision.controller;


import ldn.cs.decision.pojo.convey.ConveyInfo;
import ldn.cs.decision.service.ConveyDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/decision/element/convey")
public class ConveyDecisionController {
    @Autowired
    private ConveyDecisionService conveyDecisionService;

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

}
