package com.agileboot.domain.pms.category.dto;

import com.agileboot.orm.pms.entity.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * description: CategoryDTO
 * date: 2023/5/28 20:37
 * author: hongyi.wei
 * version: 1.0
 */
@Data
@Schema(name = "CategoryDTO", description = "商品分类信息")
public class CategoryDTO {
    /** ID */
    private Long id;
    /** 上级分类的编号：0表示一级分类 */
    private Long parentId;
    /** NAME */
    private String name;
    /** 分类级别：0->1级；1->2级 */
    private Integer level;
    /** 显示状态：0->不显示；1->显示 */
    private Integer showStatus;
    /** SORT */
    private Integer sort;
    /** 图标 */
    private String icon;
    private List<CategoryDTO> children;

    public CategoryDTO(CategoryEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            level = entity.getLevel();
            parentId = entity.getParentId();
            sort = entity.getSort();
            showStatus = entity.getShowStatus();
            icon = entity.getIcon();
        }
    }


}
