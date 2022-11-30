package com.yhdemo.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表格检索错误的实体类
 * @author wyh
 * @date 2022/11/30 10:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCheckErr<T> {

    private T t;
    private String errMessage;
}
