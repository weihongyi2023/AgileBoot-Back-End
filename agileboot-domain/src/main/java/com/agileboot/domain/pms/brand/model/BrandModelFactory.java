package com.agileboot.domain.pms.brand.model;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.orm.pms.entity.BrandEntity;
import com.agileboot.orm.pms.service.IBrandService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : BrandModelFactory
 * @date : 2023/5/28 13:21
 */
@Component
@RequiredArgsConstructor
public class BrandModelFactory {

    @NonNull
    private IBrandService brandService;

    public BrandModel create() {
        return new BrandModel(brandService);
    }


    public BrandModel loadById(Long id) {
        BrandEntity byId = brandService.getById(id);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, id, "商品品牌");
        }
        return new BrandModel(byId, brandService);
    }

    public List<BrandModel> loadByIds(Collection<Long> ids) {
        List<BrandEntity> brandEntities = brandService.listByIds(ids);
        if (CollectionUtils.isEmpty(brandEntities)) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, ids, "商品品牌");
        }

        return brandEntities.stream().map(entity -> new BrandModel(entity, brandService)).collect(Collectors.toList());
    }


}
