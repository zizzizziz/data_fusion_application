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
@RequestMapping("/rest/data/fusion/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping("/query")
    public StaffInfo query(String statement, int types, int limit, int offset) {
        return staffService.getStaffInfos(statement, types, limit, offset);
    }
}