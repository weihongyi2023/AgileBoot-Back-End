package com.agileboot.domain.pms.brand.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.pms.brand.command.BrandAddCommand;
import com.agileboot.domain.pms.brand.command.BrandUpdateCommand;
import com.agileboot.orm.common.enums.StatusEnum;
import com.agileboot.orm.pms.entity.BrandEntity;
import com.agileboot.orm.pms.service.IBrandService;
import lombok.Data;

import java.util.Objects;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : BrandModel
 * @date : 2023/5/28 13:22
 */
@Data
public class BrandModel extends BrandEntity {

    private IBrandService brandService;

    public BrandModel(IBrandService brandService) {
        this.brandService = brandService;
    }

    public BrandModel(BrandEntity entity, IBrandService brandService) {
        BeanUtil.copyProperties(entity, this);
        this.brandService = brandService;
    }

    public void loadAddCommand(BrandAddCommand command) {
       this.setName(command.getName());
       this.setSort(command.getSort());
       this.setShowStatus(command.getShowStatus());
       this.setLogo(command.getLogo());
    }

    /**
     * 校验品牌名是否已经存在
     * @param name
     */
    public void checkBrandNameIsUnique(String name) {
        if (brandService.isBrandNameDuplicated(name)) {
            throw new ApiException(ErrorCode.Business.BRAND_NAME_IS_NOT_UNIQUE);
        }
    }

    /**
     * 校验品牌名是否已经存在
     *
     * @param name
     * @param id
     */
    public void checkBrandNameIsUniqueByUpdate(String name, Long id) {
        if (brandService.isBrandNameDuplicatedByUpdate(name,id)) {
            throw new ApiException(ErrorCode.Business.BRAND_NAME_IS_NOT_UNIQUE);
        }
    }

    /**
     * 加载
     * @param command
     */
    public void loadUpdateCommand(BrandUpdateCommand command) {
        this.setName(command.getName());
        this.setSort(command.getSort());
        this.setShowStatus(command.getShowStatus());
        this.setLogo(command.getLogo());
    }

    /**
     * 启用状态不能删除
     */
    public void checkBrandCanBeDelete() {
        if (Objects.equals(StatusEnum.ENABLE.getValue(),(this.getShowStatus()))) {
            throw new ApiException(ErrorCode.Business.STATUS_NOT_ALLOWED_TO_DELETE);
        }
    }

}
