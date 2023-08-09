package ldn.cs.decision.service;

import ldn.cs.decision.dao.ProductionDecisionDao;
import ldn.cs.decision.pojo.production.Production;
import ldn.cs.decision.pojo.production.ProductionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionDecisionService {
    @Autowired
    private ProductionDecisionDao productionDecisionDao;

    /**
     * 决策元 -- 生产链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 生产链报表数据ProductionInfo
     */
    public ProductionInfo getProductionPredictionInfos(long time, int granularity, int limit, int offset) {
        return new ProductionInfo(productionDecisionDao.getProductionPredictionInfos(time, granularity, limit, offset),
                productionDecisionDao.getTotalPredictionProduction(time, granularity));
    }

    /**
     * 决策元 -- 生产链查询对应时间的所有数据
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 生产链列表
     */
    public List<Production> getProductionWarningInfos(long time, int granularity) {
        return productionDecisionDao.getProductionWarningInfos(time, granularity, -1, -1);
    }
}
