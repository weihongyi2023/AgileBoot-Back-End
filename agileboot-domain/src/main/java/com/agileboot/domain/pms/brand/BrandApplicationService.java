package com.agileboot.domain.pms.brand;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.pms.brand.command.BrandAddCommand;
import com.agileboot.domain.pms.brand.command.BrandUpdateCommand;
import com.agileboot.domain.pms.brand.dto.BrandDTO;
import com.agileboot.domain.pms.brand.model.BrandModel;
import com.agileboot.domain.pms.brand.model.BrandModelFactory;
import com.agileboot.domain.pms.brand.query.BrandQuery;
import com.agileboot.orm.pms.entity.BrandEntity;
import com.agileboot.orm.pms.service.IBrandService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : BrandApplicationService
 * @date : 2023/5/28 13:09
 */
@Service
@RequiredArgsConstructor
public class BrandApplicationService {

    @NonNull
    private BrandModelFactory brandModelFactory;

    @NonNull
    private IBrandService brandService;

    public PageDTO<BrandDTO> getBrandList(BrandQuery query) {
        Page<BrandEntity> page = brandService.page(query.toPage(), query.toQueryWrapper());
        List<BrandDTO> records = page.getRecords().stream().map(BrandDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public List<BrandDTO> getAllBrandList(BrandQuery query) {
        List<BrandEntity> list;
        if (Objects.isNull(query)){
            list = brandService.list();
        }else {
            list = brandService.list(query.toQueryWrapper());
        }
        List<BrandDTO> records =list.stream().map(BrandDTO::new).collect(Collectors.toList());
        return records;
    }

    /**
     * 新增品牌
     * @param brand
     */
    public void addBrand(BrandAddCommand brand) {
        BrandModel brandModel = brandModelFactory.create();
        brandModel.checkBrandNameIsUnique(brand.getName());
        brandModel.loadAddCommand(brand);
        brandModel.insert();
    }

    /**
     * 修改品牌
     * @param brand
     */
    public void updateBrand(BrandUpdateCommand brand) {
        BrandModel brandModel = brandModelFactory.loadById(brand.getId());
        brandModel.checkBrandNameIsUniqueByUpdate(brand.getName(),brand.getId());
        brandModel.loadUpdateCommand(brand);
        brandModel.updateById();
    }

    public BrandDTO getBrandById(Long id) {
        BrandEntity byId = brandService.getById(id);
        return new BrandDTO(byId);
    }

    public void deleteBrands(BulkOperationCommand<Long> command) {
        List<BrandModel> brandModels = brandModelFactory.loadByIds(command.getIds());
        for (BrandModel brandModel : brandModels) {
            brandModel.checkBrandCanBeDelete();
        }
        brandService.removeBatchByIds(command.getIds());
    }

}


