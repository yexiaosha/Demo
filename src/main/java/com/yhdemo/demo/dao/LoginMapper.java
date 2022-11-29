package com.yhdemo.demo.dao;

import com.yhdemo.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录验证接口
 * @author wyh
 * @data 2022/11/18 11:42
 */

@Mapper
public interface LoginMapper {

    /**
     * 验证账户账号密码
     * @param username 用户名
     * @param password 密码
     * @return 用户实例
     */
    User findUser(String username, String password);


    /**
     * 通过用户名找到用户信息
     * @param username 用户名
     * @return 用户实例
     */
    String findUserByUsername(String username);
}
