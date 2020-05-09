package com.hrh.mall.service;

import com.hrh.mall.domain.UmsResource;

import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service
 * @ClassName:      UmsResourceService
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
public interface UmsResourceService{
    /**
     * 分页查询资源
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 添加资源
     */
    int create(UmsResource umsResource);


    /**
     * 修改资源
     */
    int update(Long id, UmsResource umsResource);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 查询所有资源
     */
    List<UmsResource> listAll();

    /**
     * 根据id获取用户所有资源
     * @param adminId 用户id
     * @return
     */
    List<UmsResource> getListByAdminId(Long adminId);
}
