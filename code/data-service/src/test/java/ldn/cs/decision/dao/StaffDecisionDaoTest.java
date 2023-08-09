//package ldn.cs.decision.dao;
//
//import ldn.cs.decision.pojo.staff.Staff;
//import ldn.cs.BaseTest;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class StaffDecisionDaoTest extends BaseTest {
//    @Autowired
//    private StaffDecisionDao staffDecisionDao;
//
//    @Test
//    public void addStaffPredictionInfos() {
//        List<Staff> staffs = new ArrayList<>();
//        staffs.add(new Staff(30, "小丫家电", 1, 2, "后台开发", 100, 1690946777L, 1690946777L));
//        staffs.add(new Staff(31, "小丫家电", 1, 2, "后台开发", 100, 1690946777L, 1690946777L));
//        staffs.add(new Staff(32, "小丫家电", 1, 2, "后台开发", 100, 1690946777L, 1690946777L));
//        staffs.add(new Staff(33, "小丫家电", 1, 2, "后台开发", 100, 1690946777L, 1690946777L));
//
//        int count = staffDecisionDao.addStaffPredictionInfos(staffs);
//        Assert.assertEquals(4, count);
//    }
//
//    @Test
//    public void getStaffPredictionInfos() {
//        List<Staff> getStaffInfoByYear = staffDecisionDao.getStaffPredictionInfos(1690946777L, 1, 10, 0);
//        Assert.assertEquals(10, getStaffInfoByYear.size());
//
//        List<Staff> getStaffInfoByQuarter = staffDecisionDao.getStaffPredictionInfos(1690946777L, 2, 10, 0);
//        Assert.assertEquals(10, getStaffInfoByQuarter.size());
//
//        List<Staff> getStaffInfoByMonth = staffDecisionDao.getStaffPredictionInfos(1690946777L, 2, 5, 0);
//        Assert.assertEquals(5, getStaffInfoByMonth.size());
//    }
//}