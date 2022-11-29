package com.yhdemo.demo.pojo.param;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 订单参数实体
 * @author wyh
 * @date 2022/11/25 09:35
 */

@Data
public class OrderParam {

    /**
     * 用户名
     */
    @NotBlank
    @Length(max = 30, min = 3, message = "用户名不能少于{min}个字符，不能大于{max}个字符")
    private String username;

    @NotBlank
    private String orderName;

    @NotBlank
    private String orderContent;

}
