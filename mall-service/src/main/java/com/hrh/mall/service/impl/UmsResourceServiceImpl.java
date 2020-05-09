package com.hrh.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.mapper.UmsResourceMapper;
import com.hrh.mall.service.UmsResourceService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsResourceServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService{

    @Resource
    private UmsResourceMapper umsResourceMapper;


    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(UmsResource.class);
        Example.Criteria criteria = example.createCriteria();
        if(categoryId!=null){
            criteria.andEqualTo("categoryId",categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andLike("name",'%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andLike("url",'%'+urlKeyword+'%');
        }
        return umsResourceMapper.selectByExample(example);
    }

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return umsResourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        return umsResourceMapper.updateByPrimaryKey(umsResource);
    }

    @Override
    public int delete(Long id) {
        Example example = new Example(UmsResource.class);
        example.createCriteria().andEqualTo("id",id);
        return umsResourceMapper.deleteByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return umsResourceMapper.selectAll();
    }

    @Override
    public List<UmsResource> getListByAdminId(Long adminId) {
        return umsResourceMapper.getListByAdminId(adminId);
    }
}
