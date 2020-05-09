package com.hrh.mall.service.impl;

import com.hrh.mall.mapper.UmsPermissionMapper;
import com.hrh.mall.service.UmsPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsPermissionServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService{

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

}
