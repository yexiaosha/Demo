package com.yhdemo.demo.pojo;

import java.util.Date;
import lombok.Data;

/**
 * 日志实体类
 * @author wyh
 * @date 2022/11/23 10:36
 */

@Data
public class SysLog {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String params;
    /**
     * ip
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createDate;
}
