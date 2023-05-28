package com.agileboot.orm.pms.service.impl;

import com.agileboot.orm.common.enums.StatusEnum;
import com.agileboot.orm.pms.entity.CategoryEntity;
import com.agileboot.orm.pms.mapper.CategoryMapper;
import com.agileboot.orm.pms.service.ICategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * description: CategoryServiceImpl
 * date: 2023/5/28 20:40
 * author: hongyi.wei
 * version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryEntity> implements ICategoryService {

    /**
     * 校验商品分类是否已经存在
     * @param name
     * @param level
     * @return
     */
    @Override
    public boolean isCategoryNameDuplicated(String name, Integer level) {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<CategoryEntity>()
                .eq(Objects.nonNull(name),"name",name)
                .eq(Objects.nonNull(level),"level", level)
                .eq("show_status", StatusEnum.ENABLE.getValue());
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isCategoryNameDuplicated(String name, Long parentId) {
        QueryWrapper<CategoryEntity> queryWrapper = new QueryWrapper<CategoryEntity>()
                .eq(Objects.nonNull(name),"name",name)
                .eq(Objects.nonNull(parentId),"parent_id", parentId)
                .eq("show_status", StatusEnum.ENABLE.getValue());
        return this.baseMapper.exists(queryWrapper);
    }


}
