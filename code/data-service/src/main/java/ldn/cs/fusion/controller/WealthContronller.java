package ldn.cs.fusion.controller;

import ldn.cs.fusion.service.StaffService;
import ldn.cs.fusion.service.WealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/data/fusion/wealth")
public class WealthContronller {
    @Autowired
    WealthService wealthService;

}
