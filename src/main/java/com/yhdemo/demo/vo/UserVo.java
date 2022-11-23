package com.yhdemo.demo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    @TableField("gender")
    private SexEnum gender;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 生日
     */
    private Date birthday;
}
