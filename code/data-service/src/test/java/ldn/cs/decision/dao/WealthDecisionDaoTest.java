//package ldn.cs.decision.dao;
//
//import ldn.cs.BaseTest;
//import ldn.cs.decision.pojo.wealth.Wealth;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class WealthDecisionDaoTest extends BaseTest {
//    @Autowired
//    private WealthDecisionDao wealthDecisionDao;
//
//    @Test
//    public void addWealthPredictionInfos() {
//        List<Wealth> wealth = new ArrayList<>();
//        wealth.add(new Wealth(100, "比亚迪", 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1688268377, 1688268377));
//        wealth.add(new Wealth(101, "比亚迪", 3000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1688268577, 1688268577));
//        wealth.add(new Wealth(102, "比亚迪", 4000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 1688268677, 1688268677));
//
//        int count = wealthDecisionDao.addWealthPredictionInfos(wealth);
//        Assert.assertEquals(3, count);
//    }
//
//    @Test
//    public void getWealthPredictionInfos() {
//        List<Wealth> getStaffInfoByYear = wealthDecisionDao.getWealthPredictionInfos(1690946777L, 1, 10, 0);
//        Assert.assertEquals(10, getStaffInfoByYear.size());
//
//        List<Wealth> getStaffInfoByQuarter = wealthDecisionDao.getWealthPredictionInfos(1690946777L, 2, 10, 0);
//        Assert.assertEquals(10, getStaffInfoByQuarter.size());
//
//        List<Wealth> getStaffInfoByMonth = wealthDecisionDao.getWealthPredictionInfos(1690946777L, 2, 5, 0);
//        Assert.assertEquals(5, getStaffInfoByMonth.size());
//    }
//}