package ldn.cs.fusion.dao;

import ldn.cs.fusion.BaseTest;
import ldn.cs.fusion.pojo.Staff;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StaffDaoTest extends BaseTest {
    @Autowired
    private StaffDao staffDao;

    @Test
    public void addStaffInfos() {
        Staff staff = new Staff();
        staff.setCategories(1);
        staff.setCorporation("byd");
        staff.setSkill("后台开发");
        staff.setEventTime(20231112001L);
        staff.setUpdateTime(202307172250L);
        List<Staff> staffs = new ArrayList<>();
        staffs.add(staff);

        int count = staffDao.addStaffInfos(staffs);
        Assert.assertEquals(2, count);
    }

    @Test
    public void getStaffInfos() {
    }

    @Test
    public void getTotalStaff() {
        int count = staffDao.getTotalStaff("byd", 1);
        System.out.println(count);
    }
}