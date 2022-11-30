package com.yhdemo.demo.pojo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * excel数据导入结果
 * @author wyh
 * @date 2022/11/30 11:52
 */
@Data
@AllArgsConstructor
public class ExcelCheckResult<T> {

    private List<T> successes;
    private List<ExcelCheckErr<T>> errs;
}
