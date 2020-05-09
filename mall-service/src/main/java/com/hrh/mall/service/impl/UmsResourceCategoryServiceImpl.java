package com.hrh.mall.service.impl;

import com.hrh.mall.domain.UmsResourceCategory;
import com.hrh.mall.mapper.UmsResourceCategoryMapper;
import com.hrh.mall.service.UmsResourceCategoryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsResourceCategoryServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService{

    @Resource
    private UmsResourceCategoryMapper umsResourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> listAll() {
        Example example = new Example(UmsResourceCategory.class);
        example.setOrderByClause("sort desc");
        return umsResourceCategoryMapper.selectByExample(example);
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return umsResourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(Math.toIntExact(id));
        return umsResourceCategoryMapper.updateByPrimaryKey(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        Example example = new Example(UmsResourceCategory.class);
        example.createCriteria().andEqualTo("id",id);
        return umsResourceCategoryMapper.deleteByExample(example);
    }
}
