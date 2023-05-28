package com.agileboot.orm.pms.service;

import com.agileboot.orm.pms.entity.BrandEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : IBrandService
 * @date : 2023/5/28 13:23
 */
public interface IBrandService extends IService<BrandEntity> {

    /**
     * 校验品牌名是否唯一
     * @param name
     * @return
     */
    boolean isBrandNameDuplicated(String name);

    /**
     * 校验品牌名是否唯一
     * @param name
     * @return
     */
    boolean isBrandNameDuplicatedByUpdate(String name,Long id);
}
