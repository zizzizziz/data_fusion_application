package ldn.cs.decision.controller;


import ldn.cs.decision.pojo.sale.SaleInfo;
import ldn.cs.decision.service.SaleDecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/decision/element/sale")
public class SaleDecisionController {
    @Autowired
    private SaleDecisionService saleDecisionService;

    /**
     * 决策元 -- 销售链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 人力链报表数据StaffInfo
     */
    @GetMapping("/prediction/query")
    public SaleInfo getSaleInfos(long time, int granularity, int limit, int offset) {
        return saleDecisionService.getSalePredictionInfos(time, granularity, limit, offset);
    }

}
