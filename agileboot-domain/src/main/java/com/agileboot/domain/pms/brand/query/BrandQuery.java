package com.agileboot.domain.pms.brand.query;

import com.agileboot.orm.common.query.AbstractPageQuery;
import com.agileboot.orm.pms.entity.BrandEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 品牌管理 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="品牌管理 查询 对象")
@Data
public class BrandQuery extends AbstractPageQuery<BrandEntity> {
    @ApiModelProperty("NAME 精确匹配")
    private String nameLike;

    @ApiModelProperty("SORT 精确匹配")
    private Integer sort;

    @ApiModelProperty("SHOW_STATUS 精确匹配")
    private Integer showStatus;

    @ApiModelProperty("品牌logo 精确匹配")
    private String logo;

    @Override
    public QueryWrapper<BrandEntity> toQueryWrapper() {
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<BrandEntity>()
                .like(StringUtils.isNotEmpty(nameLike), "name", nameLike)
                .eq(Objects.nonNull(showStatus), "show_status", showStatus);

        addSortCondition(queryWrapper);
        return queryWrapper;
    }

}
