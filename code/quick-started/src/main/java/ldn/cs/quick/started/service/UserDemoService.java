package ldn.cs.quick.started.service;

import ldn.cs.quick.started.dao.UserDemoMapper;
import ldn.cs.quick.started.pojo.UserDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserDemoService {
    @Autowired
    private UserDemoMapper userInfo;

    public List<UserDemo> findById(int id) {
        return userInfo.findById(id);
    }

    public List<UserDemo> queryAll() {
        return userInfo.queryAll();
    }
}
