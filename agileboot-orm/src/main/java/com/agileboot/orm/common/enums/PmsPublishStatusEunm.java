package com.agileboot.orm.common.enums;

import com.agileboot.orm.common.CssTag;
import com.agileboot.orm.common.annotations.Dictionary;
import com.agileboot.orm.common.interfaces.DictionaryEnum;

/**
 * description: PublishStatusEunm
 *              商品上下架状态枚举类
 * date: 2023/6/10 12:41
 * author: hongyi.wei
 * version: 1.0
 */
@Dictionary(name = "pms_publish_status")
public enum PmsPublishStatusEunm implements DictionaryEnum<Integer> {
    /**
     * 开关状态
     */
    ON(1, "上架", CssTag.PRIMARY),
    OFF(0, "下架", CssTag.DANGER);

    private final int value;
    private final String description;
    private final String cssTag;

    PmsPublishStatusEunm(int value, String description, String cssTag) {
        this.value = value;
        this.description = description;
        this.cssTag = cssTag;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String cssTag() {
        return cssTag;
    }
}
