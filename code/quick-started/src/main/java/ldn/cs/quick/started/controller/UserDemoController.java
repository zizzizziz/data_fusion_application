package ldn.cs.quick.started.controller;

import ldn.cs.quick.started.pojo.UserDemo;
import ldn.cs.quick.started.service.UserDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ldn/cs")
public class UserDemoController {
    @Autowired
    UserDemoService userInfo;

    @GetMapping("/findById")
    public List<UserDemo> findById(int id) {

        return userInfo.findById(id);




    }

    @GetMapping("/queryAll")
    public List<UserDemo> queryAll() {
        return userInfo.queryAll();
    }
}
