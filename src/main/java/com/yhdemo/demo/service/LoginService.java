package com.yhdemo.demo.service;

import com.yhdemo.demo.pojo.User;
import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.vo.Result;

/**
 * 登录业务service层接口
 * @author wyh
 * @data 2022/11/18 11:36
 */
public interface LoginService {

    /**
     * 验证账户账号密码，返回所有信息
     * @param loginParam 用户对象
     * @return 通用返回
     */
    Result doLogin(LoginParam loginParam);

    /**
     *
     * @param token
     * @return
     */
    LoginParam checkToken(String token);

    Result logout(String token);
}
