package com.yhdemo.demo.pojo.param;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.yhdemo.demo.handler.SexConverterToExcel;
import com.yhdemo.demo.handler.validator.Email;
import com.yhdemo.demo.handler.validator.Sex;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册时所有信息
 * @author wyh
 * @date 2022/11/21 13:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {

    /**
     * 用户名
     */
    @NotBlank
    @Size(max = 20, min = 3)
    @ExcelProperty(index = 0)
    private String username;
    /**
     * 密码
     */
    @NotBlank
    @ExcelProperty(index = 1)
    private String password;
    /**
     * 邮箱
     */
    @NotBlank
    @Email
    @ExcelProperty(index = 2)
    private String email;
    /**
     * 性别
     */
    @NotBlank
    @Sex
    @ExcelProperty(index = 3, converter = SexConverterToExcel.class)
    private String gender;
    /**
     * 注册时间
     */
    @ExcelIgnore()
    private Date registerTime;
    /**
     * 生日
     */
    @NotBlank
    @com.yhdemo.demo.handler.validator.Date
    @ExcelProperty(index = 5)
    private String birthday;
}
