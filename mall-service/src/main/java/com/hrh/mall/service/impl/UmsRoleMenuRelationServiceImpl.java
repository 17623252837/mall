package com.hrh.mall.service.impl;

import com.hrh.mall.mapper.UmsRoleMenuRelationMapper;
import com.hrh.mall.service.UmsRoleMenuRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsRoleMenuRelationServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
@Service
public class UmsRoleMenuRelationServiceImpl implements UmsRoleMenuRelationService{

    @Resource
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;

}
