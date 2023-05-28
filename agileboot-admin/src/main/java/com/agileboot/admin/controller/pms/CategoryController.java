package com.agileboot.admin.controller.pms;

import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.pms.category.CategoryApplicationService;
import com.agileboot.domain.pms.category.command.CategoryAddCommand;
import com.agileboot.domain.pms.category.command.CategoryUpdateCommand;
import com.agileboot.domain.pms.category.dto.CategoryDTO;
import com.agileboot.domain.pms.category.query.CategoryQuery;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 商品分类Controller
 */

@Slf4j
@Validated
@RestController
@RequestMapping("/pms/category")
@RequiredArgsConstructor
@Tag(name = "商品分类接口API", description = "商品分类接口列表")
public class CategoryController {
    @NonNull
    private CategoryApplicationService service;

    @ApiOperation("查询商品分类列表")
    @PreAuthorize("@permission.has('pms:category:list')")
    @PostMapping("/list")
    public ResponseDTO<PageDTO<CategoryDTO>> list(@RequestBody(required = false) CategoryQuery query) {
        PageDTO<CategoryDTO> list = service.getCategoryList(query);
        return ResponseDTO.ok(list);
    }

    @ApiOperation("新增商品分类")
    @PreAuthorize("@permission.has('pms:category:add')")
    @PostMapping("/add")
    public ResponseDTO<Void> add(@RequestBody CategoryAddCommand brand) {
        service.addCategory(brand);
        return ResponseDTO.ok();
    }

    @ApiOperation("修改商品分类")
    @PreAuthorize("@permission.has('pms:category:edit')")
    @PutMapping("/update")
    public ResponseDTO<Void> edit(@RequestBody CategoryUpdateCommand brand) {
        service.updateCategory(brand);
        return ResponseDTO.ok();
    }

    @ApiOperation("获取商品分类详细信息")
    @PreAuthorize("@permission.has('pms:category:query')")
    @GetMapping(value = "/{id}")
    public ResponseDTO<CategoryDTO> getInfo(@NotNull @Positive @PathVariable Long id) {
        return ResponseDTO.ok(service.getCategoryById(id));
    }

    @ApiOperation("删除商品分类")
    @PreAuthorize("@permission.has('pms:category:remove')")
    @DeleteMapping("/{ids}")
    public ResponseDTO<Void> remove(@PathVariable List<Long> ids) {
        BulkOperationCommand<Long> bulkDeleteCommand = new BulkOperationCommand<>(ids);
        service.deleteCategorys(bulkDeleteCommand);
        return ResponseDTO.ok();
    }
    
    
}
