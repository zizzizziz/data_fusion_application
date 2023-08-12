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
     * 数据融合 --人力链数据新增
     *
     * @param staffs 人力链信息
     * @return 新增条数
     */
    public int addStaffInfos(List<Staff> staffs) {
        long updateTime = System.currentTimeMillis() / 1000;
        staffs.forEach(staff -> staff.setUpdateTime(updateTime));
        return staffDao.addStaffInfos(staffs);
    }

    /**
     * 数据融合 --人力链查询
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

    /**
     * 数据感知 -- 企业员工分布数据新增
     * @param persons 企业员工分布信息
     * @return 新增条数
     */
    public int addPersonInfos(List<Person> persons) {
        long updateTime = System.currentTimeMillis() / 1000;
        persons.forEach(person -> person.setUpdateTime(updateTime));
        return staffDao.addPersonInfos(persons);
    }

    /**
     * 数据感知 -- 企业员工分布可视化查询功能
     * @param time 查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    // 查询员工分布
    public Map<String, List<Person>> getPersonInfos(long time, int granularity) {
        return staffDao.getPersonInfos(time, granularity).stream().collect(Collectors.groupingBy(Person::getCorporation));
    }

    /**
     * 数据感知 -- 企业员工职位分布数据新增
     * @param positions 企业员工职位分布信息
     * @return 新增条数
     */
    public int addPositionInfos(List<Position> positions) {
        long updateTime = System.currentTimeMillis() / 1000;
        positions.forEach(position -> position.setUpdateTime(updateTime));
        return staffDao.addPositionInfos(positions);
    }

    /**
     * 数据感知 -- 企业员工职位分布可视化查询功能
     * @param time 查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Position>> getPositionInfos(long time, int granularity) {
        return staffDao.getPositionInfos(time, granularity).stream().collect(Collectors.groupingBy(Position::getCorporation));
    }

    /**
     * 数据感知 -- 企业员工技能分布数据新增
     * @param skills 企业员工技能分布信息
     * @return 新增条数
     */
    public int addSkillInfos(List<Skill> skills) {
        long updateTime = System.currentTimeMillis() / 1000;
        skills.forEach(skill -> skill.setUpdateTime(updateTime));
        return staffDao.addSkillInfos(skills);
    }

    /**
     * 数据感知 -- 企业员工技能分布可视化查询功能
     * @param time 查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    public Map<String, List<Skill>> getSkillInfos(long time, int granularity) {
        return staffDao.getSkillInfos(time, granularity).stream().collect(Collectors.groupingBy(Skill::getCorporation));
    }

}
