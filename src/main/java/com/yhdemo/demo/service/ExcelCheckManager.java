package com.yhdemo.demo.service;

import com.yhdemo.demo.pojo.ExcelCheckResult;
import java.util.List;

/**
 * excel校验接口
 * @author wyh
 * @date 2022/11/30 11:51
 */

public interface ExcelCheckManager<T> {

    /**
     * 校验方法
     * @param objects 校验对象
     * @return 校验结果
     */
    ExcelCheckResult<T> checkImportExcel(List<T> objects);
}
