package com.agileboot.domain.pms.category.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.pms.entity.CategoryEntity;
import com.agileboot.orm.pms.service.ICategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * description: CategoryModelFactory
 * date: 2023/5/28 20:38
 * author: hongyi.wei
 * version: 1.0
 */
@Component
@RequiredArgsConstructor
public class CategoryModelFactory {

    @NonNull
    private ICategoryService categoryService;

    public CategoryModel create() {
        return new CategoryModel(categoryService);
    }


    public CategoryModel loadById(Long id) {
        CategoryEntity byId = categoryService.getById(id);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, id, "商品分类");
        }
        return new CategoryModel(byId, categoryService);
    }

}
