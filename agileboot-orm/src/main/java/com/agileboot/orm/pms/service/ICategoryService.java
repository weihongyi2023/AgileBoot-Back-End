package com.agileboot.orm.pms.service;

import com.agileboot.orm.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * description: ICategoryService
 * date: 2023/5/28 20:40
 * author: hongyi.wei
 * version: 1.0
 */
public interface ICategoryService extends IService<CategoryEntity> {

    /**
     * 校验商品分类是否已经存在
     * @param name
     * @param level
     * @return
     */
    boolean isCategoryNameDuplicated(String name, Integer level);

    boolean isCategoryNameDuplicated(String name, Long parentId);
}
