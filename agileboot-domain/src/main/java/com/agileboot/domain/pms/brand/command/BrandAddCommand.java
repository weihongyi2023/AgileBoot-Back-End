package com.agileboot.domain.pms.brand.command;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : BrandAddCommand
 * @date : 2023/5/28 13:36
 */
@Data
@Schema
public class BrandAddCommand {

    @ApiModelProperty("名字")
    @NotNull
    @NotEmpty
    private String name;

    @ApiModelProperty("排序")
    @NotNull
    private Integer sort;

    @ApiModelProperty("状态")
    @NotNull
    private Integer showStatus;

    @ApiModelProperty("logo")
    @NotNull
    @NotEmpty
    private String logo;
}
