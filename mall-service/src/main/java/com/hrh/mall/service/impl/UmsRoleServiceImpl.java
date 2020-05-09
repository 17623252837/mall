package com.hrh.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.domain.UmsRoleMenuRelation;
import com.hrh.mall.domain.UmsRoleResourceRelation;
import com.hrh.mall.dto.UmsRoleExample;
import com.hrh.mall.mapper.UmsRoleMapper;
import com.hrh.mall.mapper.UmsRoleMenuRelationMapper;
import com.hrh.mall.mapper.UmsRoleResourceRelationMapper;
import com.hrh.mall.service.UmsRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsRoleServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService{

    @Resource
    private UmsRoleMapper umsRoleMapper;

    @Resource
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;

    @Resource
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

    @Override
    public UmsRole getRoleById(int id) {
        Example example = new Example(UmsRole.class);
        example.createCriteria().andEqualTo("umsRoleId",id);
        return umsRoleMapper.selectOneByExample(example);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return umsRoleMapper.getMenuList(adminId);
    }

    @Override
    public List<UmsRole> list() {
        return umsRoleMapper.selectAll();
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return umsRoleMapper.selectByExample(example);
    }

    @Override
    public int insert(UmsRole umsRole) {
        return umsRoleMapper.insert(umsRole);
    }

    @Override
    public int delete(List<Long> ids) {
        Example example = new Example(UmsRole.class);
        int del = 0;
        for(Long id :ids){
            example.createCriteria().andEqualTo("id",id);
            int delete = umsRoleMapper.deleteByExample(example);
            if(delete > 0) del++;
        }
        return del;
    }

    @Override
    public int update(UmsRole umsRole) {
        return umsRoleMapper.updateByPrimaryKey(umsRole);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return umsRoleMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 删除原有关系
        Example example = new Example(UmsRoleMenuRelation.class);
        example.createCriteria().andEqualTo("roleId",roleId);
        umsRoleMenuRelationMapper.deleteByExample(example);
        // 添加新关系
        menuIds.forEach(id -> {
            UmsRoleMenuRelation umsRoleMenuRelation = new UmsRoleMenuRelation();
            umsRoleMenuRelation.setRoleId(roleId);
            umsRoleMenuRelation.setMenuId(id);
            umsRoleMenuRelationMapper.insert(umsRoleMenuRelation);
        });
        return 0;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 删除原有的关系
        Example example = new Example(UmsRoleResourceRelation.class);
        example.createCriteria().andEqualTo("roleId",roleId);
        umsRoleResourceRelationMapper.deleteByExample(example);
        // 建立新关系
        resourceIds.forEach(id -> {
            UmsRoleResourceRelation umsRoleResourceRelation = new UmsRoleResourceRelation();
            umsRoleResourceRelation.setRoleId(roleId);
            umsRoleResourceRelation.setResourceId(id);
            umsRoleResourceRelationMapper.insert(umsRoleResourceRelation);
        });
        return 0;
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return umsRoleMapper.getResourceListByRoleId(roleId);
    }

    @Override
    public List<UmsRole> listRole(Long adminId) {
        return umsRoleMapper.getRoleList(adminId);
    }
}
