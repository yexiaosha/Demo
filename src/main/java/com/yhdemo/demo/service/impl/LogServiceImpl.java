package com.yhdemo.demo.service.impl;

import com.yhdemo.demo.dao.LogMapper;
import com.yhdemo.demo.pojo.SysLog;
import com.yhdemo.demo.service.LogService;
import com.yhdemo.demo.utils.aspects.SystemServiceLog;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 日志业务实现
 * @author wyh
 * @date 2022/11/23 10:52
 */

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    @SystemServiceLog("日志请求错误")
    public void addLog(SysLog sysLog) {
        logMapper.addLog(sysLog);
    }
}