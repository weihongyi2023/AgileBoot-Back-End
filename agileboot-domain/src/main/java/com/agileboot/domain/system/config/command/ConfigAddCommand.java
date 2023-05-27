package com.agileboot.domain.system.config.command;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : ConfigAddCommand
 * @date : 2023/5/27 13:15
 */
@Data
@Schema
public class ConfigAddCommand {

    @ApiModelProperty("配置名称")
    @NotNull
    @NotEmpty
    private String configName;

    @ApiModelProperty("配置键名")
    @NotNull
    @NotEmpty
    private String configKey;

    @ApiModelProperty("配置值")
    @NotNull
    @NotEmpty
    private String configValue;

    @ApiModelProperty("是否允许修改")
    @NotNull
    private Boolean isAllowChange;

    @ApiModelProperty("备注")
    @NotNull
    @NotEmpty
    private String remark;
}
