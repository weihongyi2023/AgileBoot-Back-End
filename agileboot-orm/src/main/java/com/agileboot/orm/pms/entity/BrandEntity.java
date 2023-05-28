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
 * 品牌管理对象 pms_brand
 * 
 * @author zcc
 */
@Data
@ApiModel(description="品牌管理对象")
@TableName("pms_brand")
public class BrandEntity extends BaseEntity<BrandEntity> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("NAME")
    @TableField("name")
    private String name;

    @ApiModelProperty("SORT")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("SHOW_STATUS")
    @TableField("show_status")
    private Integer showStatus;

    @ApiModelProperty("品牌logo")
    @TableField("logo")
    private String logo;
}
