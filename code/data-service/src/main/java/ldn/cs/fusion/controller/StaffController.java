package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.staff.*;
import ldn.cs.fusion.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/data/element/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    /**
     * 数据融合 -- 人力链查询
     *
     * @param statement 查询条件
     * @param types     条件类型 ：1为按企业查询，2为按更新时间查询
     * @param limit     单页限制 这里要让前台记得乘以limit 要不然会重复
     * @param offset    偏移量
     * @return 人力链报表数据 StaffInfo
     */
    @GetMapping("/fusion/query")
    public StaffInfo getStaffInfos(String statement, int types, int limit, int offset) {
        return staffService.getStaffInfos(statement, types, limit, offset);
    }

    /**
     * 数据感知 -- 企业员工分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/person/query")
    public Map<String, List<Person>> getPersonInfos(long time, int granularity) {
        return staffService.getPersonInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业员工职位分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/position/query")
    public Map<String, List<Position>> getPositionInfos(long time, int granularity) {
        return staffService.getPositionInfos(time, granularity);
    }

    /**
     * 数据感知 -- 企业员工技能分布可视化查询
     *
     * @param time        查询条件时间戳（毫秒级）
     * @param granularity 条件类型：1-按年份 2-按季度 3-按月份
     * @return key-企业，value-对象列表
     */
    @GetMapping("/perception/skill/query")
    public Map<String, List<Skill>> getSkillInfos(long time, int granularity) {
        return staffService.getSkillInfos(time, granularity);
    }
}