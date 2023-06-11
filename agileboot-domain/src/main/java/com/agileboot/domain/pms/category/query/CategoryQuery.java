package com.agileboot.domain.pms.category.query;

import com.agileboot.orm.common.enums.StatusEnum;
import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * description: CategoryQuery
 * date: 2023/5/28 20:36
 * author: hongyi.wei
 * version: 1.0
 */
@ApiModel(description="商品分类 查询 对象")
@Data
public class CategoryQuery extends AbstractPageQuery<CategoryEntity> {
    @ApiModelProperty("上级分类的编号：0表示一级分类 精确匹配")
    private Long parentId;

    @ApiModelProperty("NAME 精确匹配")
    private String nameLike;

    @ApiModelProperty("分类级别：0->1级；1->2级 精确匹配")
    private Integer level;

    @ApiModelProperty("显示状态：0->不显示；1->显示 精确匹配")
    private Integer showStatus;

    @ApiModelProperty("SORT 精确匹配")
    private Integer sort;

    @ApiModelProperty("图标 精确匹配")
    private String icon;

    @Override
    public QueryWrapper<CategoryEntity> toQueryWrapper() {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<CategoryEntity>()
                .eq(Objects.nonNull(showStatus), "show_status", showStatus)
                .like(StringUtils.isNotEmpty(nameLike), "name", nameLike);
        addSortCondition(queryWrapper);
        return queryWrapper;
    }

}
