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
    private StaffDao staffDao;

    /**
     * 数据融合--人力链新增
     *
     * @param staffs 人力链信息
     * @return 新增条数
     */
    public int addStaffInfos(List<Staff> staffs) {
        long updateTime = System.currentTimeMillis();
        staffs.forEach(staff -> staff.setUpdateTime(updateTime));
        return staffDao.addStaffInfos(staffs);
    }

    /**
     * 数据融合--人力链查询
     *
     * @param statement 查询条件
     * @param types     条件类型
     * @param limit     单页限制
     * @param offset    偏移量
     * @return 人力链信息
     */
    public StaffInfo getStaffInfos(String statement, int types, int limit, int offset) {
        StaffInfo staffInfo = new StaffInfo();
        List<Staff> staffs = staffDao.getStaffInfos(statement, types, limit, offset);
        int total = staffDao.getTotalStaff(statement, types);

        staffInfo.setStaffs(staffs);
        staffInfo.setTotal(total);
        return staffInfo;
    }

    // 员工分布
    public int addPersonInfos(List<Person> persons) {
        long updateTime = System.currentTimeMillis();
        persons.forEach(staff -> staff.setUpdateTime(updateTime));
        return staffDao.addPersonInfos(persons);
    }

    // 查询员工分布
    public Map<String, List<Person>> getPersonInfos(int time, int granularity) {
        return staffDao.getPersonInfos(time, granularity).stream().collect(Collectors.groupingBy(Person::getCorporation));
    }

    // 职位分布
    public int addPositionInfos(List<Position> positions) {
        long updateTime = System.currentTimeMillis();
        positions.forEach(staff -> staff.setUpdateTime(updateTime));
        return staffDao.addPositionInfos(positions);
    }

    // 查询职位信息
    public Map<String, List<Position>> getPositionInfos(int time, int granularity) {
        return staffDao.getPositionInfos(time, granularity).stream().collect(Collectors.groupingBy(Position::getCorporation));
    }

    // 技能分布
    public int addSkillInfos(List<Skill> skills) {
        long updateTime = System.currentTimeMillis();
        skills.forEach(staff -> staff.setUpdateTime(updateTime));
        return staffDao.addSkillInfos(skills);
    }

    // 查询技能信息
    public Map<String, List<Skill>> getSkillInfos(int time, int granularity) {
        return staffDao.getSkillInfos(time, granularity).stream().collect(Collectors.groupingBy(Skill::getCorporation));
    }

}