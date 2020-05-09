package com.hrh.mall.service.impl;

import com.hrh.mall.mapper.UmsRolePermissionRelationMapper;
import com.hrh.mall.service.UmsRolePermissionRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsRolePermissionRelationServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
@Service
public class UmsRolePermissionRelationServiceImpl implements UmsRolePermissionRelationService{

    @Resource
    private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;

}
