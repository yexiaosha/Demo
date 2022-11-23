package com.yhdemo.demo.controller;

import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.utils.aspects.SystemControllerLog;
import com.yhdemo.demo.vo.Result;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Transactional(rollbackFor = Exception.class)
    public Result doLogin(@Validated @RequestBody LoginParam loginParam, HttpServletRequest request) {
        Result result = loginService.doLogin(loginParam);
        if (result.isSuccess()) {
            request.getSession().setAttribute("username", loginParam.getUsername());
            return result;
        }
        return result;
    }
}