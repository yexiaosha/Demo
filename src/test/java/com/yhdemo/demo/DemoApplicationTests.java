package com.yhdemo.demo;

import com.yhdemo.demo.controller.LoginController;
import com.yhdemo.demo.controller.RegisterController;
import com.yhdemo.demo.dao.FindMapper;
import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.dao.OrderMapper;
import com.yhdemo.demo.dao.RegisterMapper;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.service.FindService;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.service.OrderService;
import com.yhdemo.demo.service.RegisterService;
import com.yhdemo.demo.utils.JwtUtils;
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
    OrderMapper orderMapper;
    @Autowired
    LoginService loginService;
    @Autowired
    RegisterMapper registerMapper;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    FindMapper findMapper;

    @Autowired
    OrderService orderService;
    @Autowired
    FindService findService;

    RegisterParam user = new RegisterParam("Red", "123456", "hansondeg@qq.com", "MALE", null, "2001-5-21");

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzA0ODc1NzQsImlhdCI6MTY2OTU5ODU0MSwidXNlcm5hbWUiOiJKYW1lcyJ9._Ob7R1S48xxSTbRpq3FUBWGPW2JsXhWtk_lxgkvZCAM";
    @Test
    void test() {
        System.out.println(JwtUtils.verifyToken(token));

    }

    @Test
    void Test() {
        System.out.println(orderService.findAllOrders(1,3));
    }
}
