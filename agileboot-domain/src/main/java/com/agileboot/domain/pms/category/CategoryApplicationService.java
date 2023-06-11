package com.agileboot.domain.pms.category;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.pms.category.command.CategoryAddCommand;
import com.agileboot.domain.pms.category.command.CategoryUpdateCommand;
import com.agileboot.domain.pms.category.dto.CategoryDTO;
import com.agileboot.domain.pms.category.model.CategoryModel;
import com.agileboot.domain.pms.category.model.CategoryModelFactory;
import com.agileboot.domain.pms.category.query.CategoryQuery;
import com.agileboot.orm.pms.entity.CategoryEntity;
import com.agileboot.orm.pms.service.ICategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * description: CategoryApplicationService
 * date: 2023/5/28 20:36
 * author: hongyi.wei
 * version: 1.0
 */
@Service
@RequiredArgsConstructor
public class CategoryApplicationService {

    @NonNull
    private CategoryModelFactory categoryModelFactory;

    @NonNull
    private ICategoryService categoryService;

    public List<CategoryDTO> getCategoryList(CategoryQuery query) {
        List<CategoryEntity> list;
        if (Objects.nonNull(query)){
            list = categoryService.list(query.toQueryWrapper());
        }else {
            list = categoryService.list();
        }
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public void addCategory(CategoryAddCommand command) {
        CategoryModel model = categoryModelFactory.create();
        model.checkCategoryNameIsUnique(command.getName(),command.getLevel());
        model.loadAddCommand(command);
        model.insert();
    }

    public void updateCategory(CategoryUpdateCommand command) {
        CategoryModel brandModel = categoryModelFactory.loadById(command.getId());
        brandModel.checkCategoryNameIsUniqueByUpdate(command.getId(),command.getName(),command.getParentId());
        brandModel.loadUpdateCommand(command);
        brandModel.updateById();
    }

    public CategoryDTO getCategoryById(Long id) {
        CategoryEntity byId = categoryService.getById(id);
        return new CategoryDTO(byId);
    }

    public void deleteCategorys(BulkOperationCommand<Long> command) {
        categoryService.removeBatchByIds(command.getIds());
    }


}
