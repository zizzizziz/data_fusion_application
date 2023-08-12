package ldn.cs.decision.controller;


import ldn.cs.decision.pojo.production.ProductionInfo;
import ldn.cs.decision.service.ProductionDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/decision/element/production")
public class ProductionDecisionController {
    @Autowired
    private ProductionDecisionService productionDecisionService;

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

}
