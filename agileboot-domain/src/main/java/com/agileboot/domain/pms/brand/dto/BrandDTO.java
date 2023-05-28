package com.agileboot.domain.pms.brand.dto;

import com.agileboot.orm.pms.entity.BrandEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 品牌管理 数据视图对象
 * 
 * @author zcc
 */
@Data
@Schema(name = "BrandDTO", description = "品牌信息")
public class BrandDTO {

    private Long id;

    private String name;

    private Integer sort;

    private Integer showStatus;

    private String logo;


    public BrandDTO(BrandEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            sort = entity.getSort();
            showStatus = entity.getShowStatus();
            logo = entity.getLogo();
        }
    }

}
