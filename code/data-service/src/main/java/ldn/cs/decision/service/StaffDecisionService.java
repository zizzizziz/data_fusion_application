package ldn.cs.decision.service;

import ldn.cs.decision.dao.StaffDecisionDao;
import ldn.cs.decision.pojo.staff.Staff;
import ldn.cs.decision.pojo.staff.StaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaffDecisionService {
    @Autowired
    private StaffDecisionDao staffDecisionDao;

    /**
     * 决策元 -- 人力链查询
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @param limit       单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset      偏移量
     * @return 人力链报表数据StaffInfo
     */
    public StaffInfo getStaffPredictionInfos(long time, int granularity, int limit, int offset) {
        return new StaffInfo(staffDecisionDao.getStaffPredictionInfos(time, granularity, limit, offset),
                staffDecisionDao.getTotalPredictionStaff(time, granularity));
    }

    /**
     * 决策元 -- 人力链查询对应时间的所有数据
     *
     * @param time        查询时间
     * @param granularity 1-->年 2-->季度 3-->月
     * @return 人力链列表
     */
    public List<Staff> getStaffWarningInfos(long time, int granularity) {
        return staffDecisionDao.getStaffWarningInfos(time, granularity, -1, -1);
    }
}
