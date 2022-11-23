package com.yhdemo.demo.vo;

import lombok.Data;

/**
 * 注册用户信息返回视图类
 * @author wyh
 * @date 2022/11/22 10:02
 */

@Data
public class RegisterUserVo {

    /**
     * 用户名
     */
    private String username;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 生日
     */
    private String birthday;
}
