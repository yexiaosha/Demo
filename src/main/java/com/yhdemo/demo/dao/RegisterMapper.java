package com.yhdemo.demo.dao;

import com.yhdemo.demo.pojo.RegisterUser;
import com.yhdemo.demo.pojo.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 注册接口
 * @author wyh
 * @data 2022/11/18 14:16
 */

@Mapper
public interface RegisterMapper {

    /**
     * 传入用户类进行存入
     * @param user 用户对象
     * @return 是否返回成功
     */
    boolean saveUser(@Param("user") User user);

    /**
     * 保存注册信息
     * @param registerUser 用户信息实体类
     * @return 是否完成存储
     */
    boolean saveUserRegisterInfo(@Param("registerUser") RegisterUser registerUser);

    /**
     * 查找用户注册信息
     * @param username 用户名
     * @return 注册信息视图类
     */
    RegisterUser getRegisterInfo(String username);

    /**
     * @param list
     * @return
     */
    boolean updateUsers(List<RegisterUser> list);

}
