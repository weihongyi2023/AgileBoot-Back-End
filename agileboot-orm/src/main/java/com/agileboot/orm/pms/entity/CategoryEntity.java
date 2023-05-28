package com.agileboot.orm.pms.entity;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: CategoryEntity
 * date: 2023/5/28 20:39
 * author: hongyi.wei
 * version: 1.0
 */
@Data
@ApiModel(description="商品分类对象")
@TableName("pms_product_category")
public class CategoryEntity  extends BaseEntity<CategoryEntity> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("上级分类的编号：0表示一级分类")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("NAME")
    @TableField("name")
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    @TableField("level")
    private Integer level;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    @TableField("show_status")
    private Integer showStatus;

    @ApiModelProperty("SORT")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;
}
