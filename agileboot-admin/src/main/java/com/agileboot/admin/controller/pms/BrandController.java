package com.agileboot.admin.controller.pms;


import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.pms.brand.BrandApplicationService;
import com.agileboot.domain.pms.brand.command.BrandAddCommand;
import com.agileboot.domain.pms.brand.command.BrandUpdateCommand;
import com.agileboot.domain.pms.brand.dto.BrandDTO;
import com.agileboot.domain.pms.brand.query.BrandQuery;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 品牌管理Controller
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/pms/brand")
@RequiredArgsConstructor
@Tag(name = "品牌API", description = "品牌管理接口列表")
public class BrandController {

    @NonNull
    private BrandApplicationService service;

    @ApiOperation("查询品牌管理列表")
    @PreAuthorize("@permission.has('pms:brand:list')")
    @PostMapping("/list")
    public ResponseDTO<PageDTO<BrandDTO>> list(@RequestBody BrandQuery query) {
        PageDTO<BrandDTO> list = service.getBrandList(query);
        return ResponseDTO.ok(list);
    }

    @ApiOperation("所有品牌管理列表")
    @PreAuthorize("@permission.has('pms:brand:list')")
    @PostMapping("/all")
    public ResponseDTO<List<BrandDTO>> all(@RequestBody BrandQuery query) {
        return ResponseDTO.ok(service.getAllBrandList(query));
    }

    @ApiOperation("新增品牌管理")
    @PreAuthorize("@permission.has('pms:brand:add')")
    @PostMapping("/add")
    public ResponseDTO<Void> add(@RequestBody BrandAddCommand brand) {
        service.addBrand(brand);
        return ResponseDTO.ok();
    }

    @ApiOperation("修改品牌管理")
    @PreAuthorize("@permission.has('pms:brand:edit')")
    @PutMapping("/update")
    public ResponseDTO<Void> edit(@RequestBody BrandUpdateCommand brand) {
        service.updateBrand(brand);
        return ResponseDTO.ok();
    }

    @ApiOperation("获取品牌管理详细信息")
    @PreAuthorize("@permission.has('pms:brand:query')")
    @GetMapping(value = "/{id}")
    public ResponseDTO<BrandDTO> getInfo(@NotNull @Positive @PathVariable Long id) {
        return ResponseDTO.ok(service.getBrandById(id));
    }

    @ApiOperation("删除品牌管理")
    @PreAuthorize("@permission.has('pms:brand:remove')")
	@DeleteMapping("/{ids}")
    public ResponseDTO<Void> remove(@PathVariable List<Long> ids) {
        BulkOperationCommand<Long> bulkDeleteCommand = new BulkOperationCommand<>(ids);
        service.deleteBrands(bulkDeleteCommand);
        return ResponseDTO.ok();
    }

}
