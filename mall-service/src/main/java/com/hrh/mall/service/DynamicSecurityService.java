package com.hrh.mall.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 动态权限
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.service
 * @ClassName: DynamicSecurityService
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/5/5 17:44
 * @Version: 1.0
 */
@Component
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
