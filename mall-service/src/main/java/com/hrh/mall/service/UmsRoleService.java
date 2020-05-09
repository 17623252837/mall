package com.hrh.mall.service;

import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.domain.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.service
 * @ClassName: UmsRoleService
 * @Author: HuRonghua
 * @Description: ${description}
 * @Date: 2020/4/24 11:11
 * @Version: 1.0
 */
public interface UmsRoleService {
    /**
     * 根据用户权限id获取完整权限
     *
     * @return
     */
    UmsRole getRoleById(int id);


    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取所有角色列表
     */
    List<UmsRole> list();

    /**
     * 分页获取角色列表
     */
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);


    /**
     * 新增角色列表
     * @param umsRole
     * @return
     */
    int insert(UmsRole umsRole);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 更新
     * @param umsRole
     * @return
     */
    int update(UmsRole umsRole);

    /**
     * 获取角色相关菜单
     */
    List<UmsMenu> listMenu(Long roleId);


    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

    /**
     * 获取角色相关资源
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 获取用户拥有角色
     */

    List<UmsRole> listRole(Long adminId);
}
