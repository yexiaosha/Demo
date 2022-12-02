package com.yhdemo.demo.pojo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 性别枚举
 * @author wyh
 * @date 2022/11/22 11:17
 */
@Getter
@ApiModel("性别枚举")
public enum SexEnum {
    /**
     * 性别
     */
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private Integer code;

    @JsonValue
    private String sex;

    SexEnum(Integer code, String sex) {
        this.code = code;
        this.sex = sex;
    }
}
