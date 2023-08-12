package ldn.cs.decision.service;

import ldn.cs.decision.dao.ConveyDecisionDao;
import ldn.cs.decision.pojo.convey.Convey;
import ldn.cs.decision.pojo.convey.ConveyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConveyDecisionService {
    @Autowired
    private ConveyDecisionDao conveyDecisionDao;

    /**
     * 决策元 -- 物流链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 物流链报表数据ConveyInfo
     */
    public ConveyInfo getConveyPredictionInfos(long time, int granularity, int limit, int offset) {
        return new ConveyInfo(conveyDecisionDao.getConveyPredictionInfos(time, granularity, limit, offset),
                conveyDecisionDao.getTotalPredictionConvey(time, granularity));
    }

    /**
     * 决策元 -- 物流链查询对应时间的所有数据
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 物流链列表
     */
    public List<Convey> getConveyWarningInfos(long time, int granularity) {
        return conveyDecisionDao.getConveyWarningInfos(time, granularity, -1, -1);
    }
}
