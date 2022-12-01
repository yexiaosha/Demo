package com.yhdemo.demo.controller;

import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单的登录接口
 * @author wyh
 * @data 2022/11/18 11:11
 */

@CrossOrigin
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录验证
     * @param loginParam 用户对象
     * @return 通用返回类型
     */
    @PostMapping("/login")
    @SystemControllerLog("用户登录")
    public Result<String> doLogin(@Validated @RequestBody LoginParam loginParam) {
        return loginService.doLogin(loginParam);
    }

    /**
     * 用户登出
     * @param token 令牌
     * @return 是否完成登出
     */
    @GetMapping("/logout")
    @SystemControllerLog("用户登出")
    public Result<Boolean> logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }
}