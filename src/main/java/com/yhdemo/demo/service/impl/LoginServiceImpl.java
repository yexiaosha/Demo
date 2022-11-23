package com.yhdemo.demo.service.impl;

import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.pojo.User;
import com.yhdemo.demo.pojo.param.LoginParam;
import com.yhdemo.demo.service.LoginService;
import com.yhdemo.demo.utils.DateUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import com.yhdemo.demo.vo.ErrorCode;
import com.yhdemo.demo.vo.LoginUserVo;
import com.yhdemo.demo.vo.Result;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 登录实现
 * @author wyh
 * @data 2022/11/18 11:41
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    /**
     * 登录
     * @param loginParam 用户对象
     * @return 通用返回对象
     */
    @Override
    @SystemServiceLog("登录")
    public Result doLogin(LoginParam loginParam) {
        User user = copy(loginParam);
        if (loginMapper.findUserByUsername(user.getUsername()) == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        if (loginMapper.findUser(user.getUsername(), user.getPassword()) == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_ERROR.getMsg());
        }

        LoginUserVo loginUserVo;
        loginUserVo = copy(loginMapper.findUser(user.getUsername(), user.getPassword()));
        return new Result(true, "00000", "登陆成功", loginUserVo);
    }

    public User copy(LoginParam loginParam) {
        User user = new User();
        user.setUsername(loginParam.getUsername());
        user.setPassword(loginParam.getPassword());
        return user;
    }

    public LoginUserVo copy(User user) {
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setUsername(user.getUsername());
        loginUserVo.setBirthday(DateUtils.parseToString(user.getBirthday()));
        loginUserVo.setGender(user.getGender().getSex());
        loginUserVo.setEmail(user.getEmail());
        return loginUserVo;
    }
}
