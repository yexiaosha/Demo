package com.yhdemo.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 * @author wyh
 * @data 2022/11/18 11:43
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

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
