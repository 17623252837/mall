package com.hrh.mall.service;

import com.hrh.mall.domain.UmsResourceCategory;

import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service
 * @ClassName:      UmsResourceCategoryService
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
public interface UmsResourceCategoryService{

    /**
     * 获取所有资源分类
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * 修改资源分类
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 删除资源分类
     */
    int delete(Long id);
}
