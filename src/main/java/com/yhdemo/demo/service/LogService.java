package com.yhdemo.demo.service;

import com.yhdemo.demo.pojo.SysLog;

/**
 * 日志保存等业务流程
 * @author wyh
 * @date 2022/11/23 10:51
 */
public interface LogService {

    /**
     * 添加日志
     * @param sysLog 日志类
     */
    void addLog(SysLog sysLog);
}
