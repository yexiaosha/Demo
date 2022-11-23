package com.yhdemo.demo.pojo.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 注册和登陆时参数
 * @author wyh
 * @data 2022/11/18 14:24
 */

@Data
@AllArgsConstructor
public class LoginParam {

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
}
