package ldn.cs.decision.service;

import ldn.cs.decision.dao.WealthDecisionDao;
import ldn.cs.decision.pojo.wealth.Wealth;
import ldn.cs.decision.pojo.wealth.WealthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WealthDecisionService {
    @Autowired
    private WealthDecisionDao wealthDecisionDao;

    /**
     * 决策元 -- 财务链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 财务链报表数据WealthInfo
     */
    public WealthInfo getWealthPredictionInfos(long time, int granularity, int limit, int offset) {
        return new WealthInfo(wealthDecisionDao.getWealthPredictionInfos(time, granularity, limit, offset),
                wealthDecisionDao.getTotalPredictionWealth(time, granularity));
    }

    /**
     * 决策元 -- 财务链查询对应时间的所有数据
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 财务链列表
     */
    public List<Wealth> getWealthWarningInfos(long time, int granularity) {
        return wealthDecisionDao.getWealthWarningInfos(time, granularity, -1, -1);
    }
}
