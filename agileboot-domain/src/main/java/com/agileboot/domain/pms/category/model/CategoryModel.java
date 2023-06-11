package com.agileboot.domain.pms.category.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.pms.category.command.CategoryAddCommand;
import com.agileboot.domain.pms.category.command.CategoryUpdateCommand;
import com.agileboot.orm.pms.entity.CategoryEntity;
import com.agileboot.orm.pms.service.ICategoryService;
import lombok.Data;

/**
 * description: CategoryModel
 * date: 2023/5/28 20:38
 * author: hongyi.wei
 * version: 1.0
 */
@Data
public class CategoryModel extends CategoryEntity {

    private ICategoryService categoryService;

    public CategoryModel(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryModel(CategoryEntity entity, ICategoryService categoryService) {
        BeanUtil.copyProperties(entity, this);
        this.categoryService = categoryService;
    }


    public void checkCategoryNameIsUnique(String name, Integer level) {
        if (categoryService.isCategoryNameDuplicated(name,level)) {
            throw new ApiException(ErrorCode.Business.PRODUCT_CATEGORY_NAME_IS_NOT_UNIQUE);
        }
    }

    public void loadAddCommand(CategoryAddCommand command) {
        this.setName(command.getName());
        this.setSort(command.getSort());
        this.setShowStatus(command.getShowStatus());
        this.setLevel(command.getLevel());
        this.setParentId(command.getParentId());
        this.setIcon(command.getIcon());
    }

    public void checkCategoryNameIsUniqueByUpdate(long id,String name, Long parentId) {
        if (categoryService.isCategoryNameDuplicated(id,name,parentId)) {
            throw new ApiException(ErrorCode.Business.PRODUCT_CATEGORY_NAME_IS_NOT_UNIQUE);
        }
    }

    public void loadUpdateCommand(CategoryUpdateCommand command) {
        this.setName(command.getName());
        this.setSort(command.getSort());
        this.setShowStatus(command.getShowStatus());
        this.setLevel(command.getLevel());
        this.setParentId(command.getParentId());
        this.setIcon(command.getIcon());
    }

}
