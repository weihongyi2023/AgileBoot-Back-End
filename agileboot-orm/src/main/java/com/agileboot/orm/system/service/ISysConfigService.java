package com.agileboot.orm.system.service;

import com.agileboot.orm.system.entity.SysConfigEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-09
 */
public interface ISysConfigService extends IService<SysConfigEntity> {

    /**
     * 通过key获取配置
     * @param key 配置对应的key
     * @return
     */
    String getConfigValueByKey(String key);

    /**
     * 通过ke获取配置
     * @param key
     * @return
     */
    SysConfigEntity getConfigByKey(String key);

    /**
     * 校验配置Key 是否重复
     * @param configKey
     * @return
     */
    boolean isConfigKeyDuplicated(String configKey);
}
