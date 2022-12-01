package com.yhdemo.demo.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.yhdemo.demo.handler.SexConverterToExcel;
import com.yhdemo.demo.pojo.SexEnum;
import java.util.Date;
import lombok.Data;

/**
 * 用户视图类
 * @author wyh
 * @date 2022/11/22 14:10
 */
@Data
public class UserVo {

    /**
     * 用户名
     */
    @ExcelProperty({"所有用户", "用户名"})
    private String username;
    /**
     * 邮箱
     */
    @ExcelProperty({"所有用户","邮箱"})
    private String email;
    /**
     * 性别
     */
    @TableField("gender")
    @ExcelProperty(value = {"所有用户","性别"}, converter = SexConverterToExcel.class)
    private SexEnum gender;
    /**
     * 注册时间
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty({"所有用户","注册时间"})
    private Date registerTime;
    /**
     * 生日
     */
    @DateTimeFormat("yyyy年MM月dd日")
    @ExcelProperty({"所有用户","生日"})
    private Date birthday;
}
