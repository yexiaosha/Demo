package com.yhdemo.demo.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 页面分页参数
 * @author wyh
 * @date 2022/12/01 11:59
 */
@Data
@ApiModel("分页参数")
public class PageParam {

    @NotNull
    @ApiModelProperty("当前行")
    private int beginLine;

    @NotBlank
    @ApiModelProperty("每页数据量")
    private Integer pageSize;

    @NotBlank
    @ApiModelProperty("当前页码")
    private Integer currentPage = 0;
}
