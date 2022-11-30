package com.yhdemo.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 错误信息excel类
 * @author wyh
 * @date 2022/11/30 13:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRegisterErr extends RegisterUser {

    @ExcelProperty(value = "错误信息", index = 5)
    private String errMsg;

}
