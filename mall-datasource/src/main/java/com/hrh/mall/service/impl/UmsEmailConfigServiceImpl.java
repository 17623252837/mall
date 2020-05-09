package com.hrh.mall.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hrh.mall.mapper.UmsEmailConfigMapper;
import com.hrh.mall.service.UmsEmailConfigService;
/**
 * @ProjectName:    mall-monomer 
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsEmailConfigServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}  
 * @Date:    2020/5/8 10:59
 * @Version:    1.0
 */
@Service
public class UmsEmailConfigServiceImpl implements UmsEmailConfigService{

    @Resource
    private UmsEmailConfigMapper umsEmailConfigMapper;

}
