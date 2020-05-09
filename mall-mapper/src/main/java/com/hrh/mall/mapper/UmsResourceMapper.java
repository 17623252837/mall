package com.hrh.mall.mapper;

import com.hrh.mall.domain.UmsResource;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.mapper
 * @ClassName:      UmsResourceMapper
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
public interface UmsResourceMapper extends MyMapper<UmsResource> {
    /**
     * 根据id获取用户所有资源
     * @param adminId 用户id
     * @return
     */
    List<UmsResource> getListByAdminId(Long adminId);
}
