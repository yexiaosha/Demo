package com.yhdemo.demo;

import com.yhdemo.demo.controller.LoginController;
import com.yhdemo.demo.controller.RegisterController;
import com.yhdemo.demo.dao.FindMapper;
import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.dao.RegisterMapper;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.service.FindService;
import com.yhdemo.demo.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    RegisterService registerService;
    @Autowired
    LoginController loginController;
    @Autowired
    RegisterController registerController;

    @Autowired
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    FindMapper findMapper;

    @Autowired
    FindService findService;

    RegisterParam user = new RegisterParam("Red", "123456", "hansondeg@qq.com", "MALE", null, "2001-5-21");

    @Test
    void deleteByUsername() {
        registerService.register(user);

    }

    @Test
    void TestDate() {
        System.out.println();
        //System.out.println(findMapper.findAll());

    }
}
