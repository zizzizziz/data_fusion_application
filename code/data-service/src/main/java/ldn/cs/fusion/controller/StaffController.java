package ldn.cs.fusion.controller;

import ldn.cs.fusion.pojo.Staff;
import ldn.cs.fusion.pojo.StaffInfo;
import ldn.cs.fusion.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/data/fusion/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping("/query")
    public StaffInfo query() {
        return staffService.getStaffInfos("byd", 1, 10, 0);
    }

}
