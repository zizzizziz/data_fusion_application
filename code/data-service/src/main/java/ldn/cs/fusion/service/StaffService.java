package ldn.cs.fusion.service;

import ldn.cs.fusion.dao.StaffDao;
import ldn.cs.fusion.pojo.staff.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffDao staff;

    public int addStaffInfos(List<Staff> staffs) {
        long updateTime = System.currentTimeMillis();
        staffs.forEach(staff -> staff.setUpdateTime(updateTime));
        return staff.addStaffInfos(staffs);
    }

    public int addPersonInfos(List<Person> persons) {
        long updateTime = System.currentTimeMillis();
        persons.forEach(staff -> staff.setUpdateTime(updateTime));
        return staff.addPersonInfos(persons);
    }

    public int addPositionInfos(List<Position> positions) {
        long updateTime = System.currentTimeMillis();
        positions.forEach(staff -> staff.setUpdateTime(updateTime));
        return staff.addPositionInfos(positions);
    }

    public int addSkillInfos(List<Skill> skills) {
        long updateTime = System.currentTimeMillis();
        skills.forEach(staff -> staff.setUpdateTime(updateTime));
        return staff.addSkillInfos(skills);
    }

    public StaffInfo getStaffInfos(String statement, int types, int limit, int offset) {
        StaffInfo staffInfo = new StaffInfo();
        List<Staff> staffs = staff.getStaffInfos(statement, types, limit, offset);
        int total = staff.getTotalStaff(statement, types);

        staffInfo.setStaffs(staffs);
        staffInfo.setTotal(total);
        return staffInfo;
    }

    public Map<String, List<Position>> getPositionInfos(int time, int granularity) {
        return staff.getPositionInfos(time, granularity).stream().collect(Collectors.groupingBy(Position::getCorporation));
    }

    public Map<String, List<Person>> getPersonInfos(int time, int granularity) {
        return staff.getPersonInfos(time, granularity).stream().collect(Collectors.groupingBy(Person::getCorporation));
    }

    public Map<String, List<Skill>> getSkillInfos(int time, int granularity) {
        return staff.getSkillInfos(time, granularity).stream().collect(Collectors.groupingBy(Skill::getCorporation));
    }

}
