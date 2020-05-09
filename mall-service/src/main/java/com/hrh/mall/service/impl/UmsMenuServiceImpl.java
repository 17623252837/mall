package com.hrh.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.dto.UmsMenuExample;
import com.hrh.mall.dto.UmsMenuNode;
import com.hrh.mall.mapper.UmsMenuMapper;
import com.hrh.mall.mapper.UmsRoleMapper;
import com.hrh.mall.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsMenuServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService{
    @Resource
    private UmsMenuMapper umsMenuMapper;

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = umsMenuMapper.selectByExample(new UmsMenuExample());

        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsMenu.class);
        example.setOrderByClause("sort desc");
        example.createCriteria().andEqualTo("parentId",parentId);
        return umsMenuMapper.selectByExample(example);
    }

    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return umsMenuMapper.insert(umsMenu);
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return umsMenuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return umsMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return umsMenuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public int delete(Long id) {
        return umsMenuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改菜单层级
     */
    private void updateLevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        } else {
            //有父菜单时选择根据父菜单level设置
            UmsMenu parentMenu = umsMenuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     */
    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
