package com.agileboot.domain.pms.category.command;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * description: CategoryUpdateCommand
 * date: 2023/5/28 20:37
 * author: hongyi.wei
 * version: 1.0
 */
@Data
@Schema
public class CategoryUpdateCommand {
    @ApiModelProperty("id")
    @NotNull
    private Long id;

    @ApiModelProperty("上级分类的编号：0表示一级分类")
    @NotNull
    private Long parentId;

    @ApiModelProperty("NAME")
    @NotNull
    @NotEmpty
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    @NotNull
    private Integer level;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    @NotNull
    private Integer showStatus;

    @ApiModelProperty("SORT")
    @NotNull
    private Integer sort;

    @ApiModelProperty("图标")
    @NotNull
    @NotEmpty
    private String icon;
}
