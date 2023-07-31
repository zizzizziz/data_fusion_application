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

    @GetMapping("/fusion/query")
    public StaffInfo getStaffInfos(String statement, int types, int limit, int offset) {
        return staffService.getStaffInfos(statement, types, limit, offset);
    }

    @GetMapping("/perception/position/query")
    public Map<String, List<Position>> getPositionInfos(long time, int granularity) {
        return staffService.getPositionInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/skill/query")
    public Map<String, List<Skill>> getSkillInfos(long time, int granularity) {
        return staffService.getSkillInfos(time / 1000, granularity);
    }

    @GetMapping("/perception/person/query")
    public Map<String, List<Person>> getPersonInfos(long time, int granularity) {
        return staffService.getPersonInfos(time / 1000, granularity);
    }
}