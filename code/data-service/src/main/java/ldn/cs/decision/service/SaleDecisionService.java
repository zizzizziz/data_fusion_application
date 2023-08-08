package ldn.cs.decision.service;

import ldn.cs.decision.dao.SaleDecisionDao;
import ldn.cs.decision.pojo.sale.Sale;
import ldn.cs.decision.pojo.sale.SaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDecisionService {
    @Autowired
    private SaleDecisionDao saleDecisionDao;

    /**
     * 决策元 -- 销售链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 銷售鏈报表数据SaleInfo
     */
    public SaleInfo getSalePredictionInfos(long time, int granularity, int limit, int offset) {
        return new SaleInfo(saleDecisionDao.getSalePredictionInfos(time, granularity, limit, offset),
                saleDecisionDao.getTotalPredictionSale(time, granularity));
    }

    /**
     * 决策元 -- 销售链查询对应时间的所有数据
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 銷售鏈列表
     */
    public List<Sale> getSaleWarningInfos(long time, int granularity) {
        return saleDecisionDao.getSaleWarningInfos(time, granularity, -1, -1);
    }
}
