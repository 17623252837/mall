package com.hrh.mall.service;

import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.dto.UmsMenuNode;

import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service
 * @ClassName:      UmsMenuService
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:10
 * @Version:    1.0
 */
public interface UmsMenuService{
    /**
     * 树形结构返回所有菜单列表
     */
    List<UmsMenuNode> treeList();

    /**
     * 分页查询后台菜单
     */
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 创建后台菜单
     */
    int create(UmsMenu umsMenu);

    /**
     * 修改后台菜单
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * 根据ID获取菜单详情
     */
    UmsMenu getItem(Long id);

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id, Integer hidden);

    /**
     * 根据ID删除菜单
     */
    int delete(Long id);

}
