package com.yhdemo.demo.pojo.param;

import com.yhdemo.demo.handler.validator.Email;
import com.yhdemo.demo.handler.validator.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 用户注册时所有信息
 * @author wyh
 * @date 2022/11/21 13:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("注册参数类")
public class RegisterParam {

    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 20, min = 3, message = "用户名不能少于{min}个字符，不能大于{max}个字符")
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @NotBlank
    @ApiModelProperty("密码")
    @Length(max = 20, min = 6, message = "密码不能少于{min}个字符，不能大于{max}个字符")
    private String password;
    /**
     * 邮箱
     */
    @NotBlank
    @Email
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 性别
     */
    @NotBlank
    @Sex
    @ApiModelProperty("性别")
    private String gender;
    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private Date registerTime;
    /**
     * 生日
     */
    @NotBlank
    @com.yhdemo.demo.handler.validator.Date
    @ApiModelProperty("生日")
    private String birthday;
}
