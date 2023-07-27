package ldn.cs.quick.started.service;


import ldn.cs.quick.started.pojo.UserDemo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.BaseTest;

import java.util.List;

public class UserDemoServiceTest extends BaseTest {
    @Autowired
    private UserDemoService userInfo;

    @Test
    public void query_user_info_by_id_test() {
        List<UserDemo> userInfos = userInfo.findById(666);
        Assert.assertEquals("ldncs", userInfos.get(0).getUserName());
    }

    @Test
    public void query_all_user_info_test() {
        List<UserDemo> userInfos = userInfo.queryAll();
        Assert.assertEquals(3, userInfos.size());
    }
}