package com.yhdemo.demo.service;

import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.pojo.vo.Result;

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
    Result<String> doLogin(LoginParam loginParam);

    /**
     * 用户token检查
     * @param token 令牌
     * @return 用户名
     */
    String checkToken(String token);

    /**
     * 用户登出
     * @param token 令牌
     * @return 通用结果
     */
    Result<Boolean> logout(String token);
}
