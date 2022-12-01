package com.yhdemo.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.pojo.User;
import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.pojo.vo.ErrorCode;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.utils.JwtUtils;
import com.yhdemo.demo.utils.ResultUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 登录实现
 * @author wyh
 * @data 2022/11/18 11:41
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录
     * @param loginParam 用户对象
     * @return 通用返回对象
     */
    @Override
    @SystemServiceLog("登录")
    public Result<String> doLogin(LoginParam loginParam) {
        User user = copy(loginParam);
        if (loginMapper.findUserByUsername(user.getUsername()) == null) {
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        if (loginMapper.findUser(user.getUsername(), user.getPassword()) == null) {
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_ERROR.getMsg());
        }

        String token = JwtUtils.createToken(user.getUsername());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return ResultUtils.success(token);
    }

    @Override
    public String checkToken(String token) {
        Map<String, Object> map = JwtUtils.verifyToken(token);
        if (map == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (!(StringUtils.hasText(userJson))) {
            return null;
        }

        return JSON.parseObject(userJson, LoginParam.class).getUsername();
    }

    @Override
    @SystemServiceLog("用户登出")
    public Result<Boolean> logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return ResultUtils.success();
    }

    public User copy(LoginParam loginParam) {
        User user = new User();
        user.setUsername(loginParam.getUsername());
        user.setPassword(loginParam.getPassword());
        return user;
    }
}
