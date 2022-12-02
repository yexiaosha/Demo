package com.yhdemo.demo.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mysql.cj.util.StringUtils;
import com.yhdemo.demo.dao.LoginMapper;
import com.yhdemo.demo.dao.RegisterMapper;
import com.yhdemo.demo.pojo.ExcelCheckErr;
import com.yhdemo.demo.pojo.ExcelCheckResult;
import com.yhdemo.demo.pojo.RegisterUser;
import com.yhdemo.demo.pojo.SexEnum;
import com.yhdemo.demo.pojo.User;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.pojo.vo.ErrorCode;
import com.yhdemo.demo.pojo.vo.RegisterUserVo;
import com.yhdemo.demo.pojo.vo.Result;
import com.yhdemo.demo.service.RegisterService;
import com.yhdemo.demo.utils.ResultUtils;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import io.netty.util.internal.StringUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 注册业务接口实现
 * @author wyh
 * @data 2022/11/18 14:21
 */

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private RegisterMapper registerMapper;

    @Resource
    private LoginMapper loginMapper;

    /**
     * 注册
     * @param registerParams 注册信息
     * @return 通用返回类型
     */
    @Override
    @SystemServiceLog("注册")
    public Result<Boolean> register(RegisterParam registerParams) {
        String username = registerParams.getUsername();
        String password = registerParams.getPassword();
        String email = registerParams.getEmail();
        SexEnum gender = SexEnum.valueOf(registerParams.getGender());
        Date birthday = DateUtil.parseDate(registerParams.getBirthday());
        Date registerTime = registerParams.getRegisterTime();

        if (StringUtils.isNullOrEmpty(username)
                || StringUtils.isNullOrEmpty(password)
                || StringUtils.isNullOrEmpty(email)) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        if (loginMapper.findUserByUsername(username) != null) {
            return ResultUtils.fail(ErrorCode.ACCOUNT_HAS_EXIST.getCode(), ErrorCode.ACCOUNT_HAS_EXIST.getMsg());
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthday(birthday);

        RegisterUser registerUser = new RegisterUser();
        registerUser.setUsername(username);
        registerUser.setBirthday(birthday);
        registerUser.setRegisterTime(registerTime);

        if (!(registerMapper.saveUser(user) && registerMapper.saveUserRegisterInfo(registerUser))) {
            return ResultUtils.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        return ResultUtils.success();
    }

    /**
     * 查询用户注册信息
     * @param username 用户名
     * @return 通用返回
     */
    @Override
    @SystemServiceLog("获取用户登录信息")
    public Result<RegisterUserVo> getRegisterInfo(String username) {
        if (loginMapper.findUserByUsername(username) == null) {
            return ResultUtils.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),
                    ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        RegisterUserVo registerUserVo = copy(registerMapper.getRegisterInfo(username));
        return ResultUtils.success(registerUserVo);
    }

    /**
     * 从表格上传用户
     * @param list 分段处理的用户列表
     */
    @Override
    @SystemServiceLog("从表格上传用户")
    public void updateUsers(List<RegisterUser> list) {
        for (RegisterUser user : list) {
            user.setRegisterTime(new Date());
        }
        registerMapper.updateUsers(list);
        registerMapper.saveUsersRegisterInfo(list);
    }

    @Override
    public List<File> getErrFiles(String filePath, int sign) {
        List<File> files = new LinkedList<>();

        for (int i = 0; i < sign; i++) {
            files.add(new File(filePath + "Err" + i + 1 + ".xlsx"));
        }

        return files;


    }

    public RegisterUserVo copy(RegisterUser registerUser) {
        RegisterUserVo registerUserVo = new RegisterUserVo();
        registerUserVo.setUsername(registerUser.getUsername());
        registerUserVo.setRegisterTime(DateUtil.formatDateTime(registerUser.getRegisterTime()));
        registerUserVo.setBirthday(DateUtil.formatDateTime(registerUser.getBirthday()));
        return registerUserVo;
    }

    /**
     * 非正则表达式法校验
     * @param objects 校验对象
     * @return 表格结果
     */
    @Override
    public ExcelCheckResult<RegisterUser> checkImportExcel(List<RegisterUser> objects) {
        List<RegisterUser> successList = new ArrayList<>();
        List<ExcelCheckErr<RegisterUser>> errList = new ArrayList<>();
        for (RegisterUser user : objects) {
            StringBuilder errMsg = new StringBuilder();
            if (loginMapper.findUserByUsername(user.getUsername()) != null) {
                errMsg.append("用户名重复了").append(";");
            }
            if (user.getGender() == null) {
                errMsg.append("性别信息有误").append(";");
            }
            if (StringUtil.isNullOrEmpty(errMsg.toString())) {
                successList.add(user);
            } else {
                errList.add(new ExcelCheckErr<>(user, errMsg.toString()));
            }
        }
        return new ExcelCheckResult<>(successList, errList);
    }


}