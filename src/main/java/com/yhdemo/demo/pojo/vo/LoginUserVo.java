package com.yhdemo.demo.pojo.vo;

import lombok.Data;

/**
 * 登录后的用户视图类
 * @author wyh
 * @date 2022/11/21 14:42
 */

@Data
public class LoginUserVo {

    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 性别
     */
    private String gender;
}
