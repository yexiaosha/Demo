package com.yhdemo.demo.pojo.param;

import com.yhdemo.demo.utils.ExcelPatternMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
@ApiModel("登录信息参数类")
public class LoginParam {

    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 20, min = 3, message = "用户名必须大于{min}，小于{max}")
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @NotBlank
    @Pattern(regexp = ExcelPatternMsg.PASSWORD, message = ExcelPatternMsg.PASSWORD_MSG)
    @ApiModelProperty("密码")
    private String password;
}
