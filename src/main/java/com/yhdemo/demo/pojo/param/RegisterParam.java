package com.yhdemo.demo.pojo.param;

import com.yhdemo.demo.handler.validator.Email;
import com.yhdemo.demo.handler.validator.Sex;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户注册时所有信息
 * @author wyh
 * @date 2022/11/21 13:58
 */

@Data
@AllArgsConstructor
public class RegisterParam {

    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 20, min = 3)
    private String username;
    /**
     * 密码
     */
    @NotBlank
    private String password;
    /**
     * 邮箱
     */
    @NotBlank
    @Email
    private String email;
    /**
     * 性别
     */
    @NotBlank
    @Sex
    private String gender;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 生日
     */
    @NotBlank
    @com.yhdemo.demo.handler.validator.Date
    private String birthday;
}
