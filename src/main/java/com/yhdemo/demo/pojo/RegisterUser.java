package com.yhdemo.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.util.Date;
import lombok.Data;

/**
 * 注册用户类
 * @author wyh
 * @date 2022/11/22 10:16
 */

@Data
public class RegisterUser {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    @TableField("gender")
    private SexEnum gender;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 生日
     */
    private Date birthday;
}
