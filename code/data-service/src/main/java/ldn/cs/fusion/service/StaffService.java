package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.StaffDao;
import ldn.cs.fusion.pojo.Staff;
import ldn.cs.fusion.pojo.StaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StaffService {
    @Autowired
    private StaffDao staff;

    public int addStaffInfos(List<Staff> staffs) {
        return staff.addStaffInfos(staffs);
    }

    public StaffInfo getStaffInfos(String statement, int types, int limit, int offset) {
        StaffInfo staffInfo = new StaffInfo();
        List<Staff> staffs = staff.getStaffInfos(statement, types, limit, offset);
        int total = staff.getTotalStaff(statement, types);

        staffInfo.setStaffs(staffs);
        staffInfo.setTotal(total);
        return staffInfo;
    }
}
