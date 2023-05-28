package com.agileboot.orm.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.pms.entity.BrandEntity;
import com.agileboot.orm.pms.mapper.BrandMapper;
import com.agileboot.orm.pms.service.IBrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : BrandServiceImpl
 * @date : 2023/5/28 13:23
 */
@Service
public class BrandServiceImpl  extends ServiceImpl<BrandMapper, BrandEntity> implements IBrandService {

    /**
     * 校验品牌名是否唯一
     * @param name
     * @return
     */
    @Override
    public boolean isBrandNameDuplicated(String name) {
        if (StrUtil.isBlank(name)) {
            return false;
        }
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return this.baseMapper.exists(queryWrapper);
    }

    /**
     * 校验品牌名是否唯一
     * @param name
     * @return
     */
    @Override
    public boolean isBrandNameDuplicatedByUpdate(String name,Long id) {
        if (StrUtil.isBlank(name)) {
            return false;
        }
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        queryWrapper.ne("id", id);
        return this.baseMapper.exists(queryWrapper);
    }

}
