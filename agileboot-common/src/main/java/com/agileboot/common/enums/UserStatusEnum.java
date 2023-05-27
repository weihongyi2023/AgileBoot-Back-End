package com.agileboot.common.enums;

/**
 * @author : hongyi.wei
 * @version : 1.0
 * @description : UserStatusEnum
 * @date : 2023/5/27 12:58
 */
public enum UserStatusEnum {

    ON(1,"正常"),
    OFF(2,"停用"),
    LIMIT(3,"冻结");

    UserStatusEnum(Integer status, String description){
        this.status = status;
        this.description = description;
    }

    /**
     * 状态
     */
    private Integer status;

    private String description;

    public int getStatus() {
        return status;
    }

}
