package com.yhdemo.demo.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.yhdemo.demo.handler.SexConverterToExcel;
import com.yhdemo.demo.utils.ExcelPatternMsg;
import java.util.Date;
import javax.validation.constraints.Pattern;
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
    @ExcelProperty(value = "用户名", index = 0)
    @ColumnWidth(15)
    private String username;
    /**
     * 密码
     */
    @ExcelProperty(value = "密码", index = 1)
    @Pattern(regexp = ExcelPatternMsg.PASSWORD, message = ExcelPatternMsg.PASSWORD_MSG)
    @ColumnWidth(30)
    private String password;
    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱", index = 2)
    @Pattern(regexp = ExcelPatternMsg.EMAIL, message = ExcelPatternMsg.EMAIL_MSG)
    @ColumnWidth(20)
    private String email;
    /**
     * 性别
     */
    @TableField("gender")
    @ExcelProperty(value = "性别", index = 3, converter = SexConverterToExcel.class)
    private SexEnum gender;
    /**
     * 注册时间
     */
    @ExcelIgnore()
    private Date registerTime;
    /**
     * 生日
     */
    @ExcelProperty(value = "生日", index = 4)
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(30)
    private Date birthday;
}
