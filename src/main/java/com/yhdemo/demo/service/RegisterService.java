package com.yhdemo.demo.service;

import com.yhdemo.demo.pojo.RegisterUser;
import com.yhdemo.demo.pojo.param.RegisterParam;
import com.yhdemo.demo.vo.Result;
import java.util.List;

/**
 * 注册业务service
 * @author wyh
 * @data 2022/11/18 14:20
 */
public interface RegisterService extends ExcelCheckManager<RegisterUser> {

    /**
     * 将注册字段传入数据库
     * @param registerParams 登录信息
     * @return 通用返回对象
     */
    Result register(RegisterParam registerParams);

    /**
     * 获取注册信息
     * @param username 用户名
     * @return 通用返回
     */
    Result getRegisterInfo(String username);

    /**
     * 批量上传用户信息
     * @param list
     * @return 通用返回
     */
    Result updateUsers(List<RegisterUser> list);
}
