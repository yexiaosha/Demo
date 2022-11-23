package com.yhdemo.demo.dao;

import com.yhdemo.demo.pojo.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 将日志存入数据库
 * @author wyh
 * @date 2022/11/23 10:51
 */
@Mapper
public interface LogMapper {

    /**
     * 添加日志
     * @param sysLog 日志实体
     */
    void addLog(@Param("sysLog") SysLog sysLog);
}
