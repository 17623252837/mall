package com.hrh.mall.service.impl;

import com.hrh.mall.mapper.UmsRoleResourceRelationMapper;
import com.hrh.mall.service.UmsRoleResourceRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsRoleResourceRelationServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
@Service
public class UmsRoleResourceRelationServiceImpl implements UmsRoleResourceRelationService{

    @Resource
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

}
