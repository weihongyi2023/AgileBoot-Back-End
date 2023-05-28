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

    public PageDTO<CategoryDTO> getCategoryList(CategoryQuery query) {
        Page<CategoryEntity> page;
        List<CategoryEntity> list;
        long total;
        if (Objects.isNull(query)){
            list = categoryService.list(new CategoryQuery().toQueryWrapperTopLevel());
            total= list.size();
        }else {
            page = categoryService.page(query.toPage(), query.toQueryWrapper());
            list = page.getRecords();
            total = page.getTotal();
        }
        List<CategoryDTO> records =list.stream().map(CategoryDTO::new).collect(Collectors.toList());
        List<CategoryDTO> formatTree = formatTree(records);
        return new PageDTO<>(formatTree,total);
    }

    private List<CategoryDTO> formatTree(List<CategoryDTO> nodes) {
        List<CategoryDTO> tree = new ArrayList<>();
        List<CategoryDTO> children = new ArrayList<>();
        // 1）先获取到所有根节点
        for (CategoryDTO node : nodes) {
            if (node.getParentId() == null || node.getParentId() == 0) {
                tree.add(node);
            } else {
                children.add(node);
            }
        }
        // 2）把所有除根结点外的节点作为子节点，然后遍历每一个根节点
        for (CategoryDTO node : tree) {
            // 3）递归构建此根的子节点
            recur(node, children);
        }
        return tree;
    }

    private void recur(CategoryDTO rootNode, List<CategoryDTO> children) {
        // 1）遍历剩余子节点，找出当前根的子节点
        for (CategoryDTO node : children) {
            // 2）如果子节点的父id等于根节点的id，那么就将这个节点加到根节点的children列表中
            if (Objects.equals(rootNode.getId(),node.getParentId())) {
                if (rootNode.getChildren() == null) {
                    rootNode.setChildren(new ArrayList<>());
                }
                rootNode.getChildren().add(node);
                // 3）以当前节点作为根节点进行递归，检查是否还有子节点。
                recur(node, children);
            }
        }
    }


    public void addCategory(CategoryAddCommand command) {
        CategoryModel model = categoryModelFactory.create();
        model.checkCategoryNameIsUnique(command.getName(),command.getLevel());
        model.loadAddCommand(command);
        model.insert();
    }

    public void updateCategory(CategoryUpdateCommand command) {
        CategoryModel brandModel = categoryModelFactory.loadById(command.getId());
        brandModel.checkCategoryNameIsUniqueByUpdate(command.getName(),command.getParentId());
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
