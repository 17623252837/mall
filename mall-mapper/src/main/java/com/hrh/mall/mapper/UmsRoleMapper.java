package com.hrh.mall.mapper;

import com.hrh.mall.domain.UmsMenu;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.domain.UmsRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.mapper
 * @ClassName:      UmsRoleMapper
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:11
 * @Version:    1.0
 */
public interface UmsRoleMapper extends MyMapper<UmsRole> {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取对应权限
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(@Param("adminId") long adminId);

    /**
     * 删除对应权限
     * @param adminId
     * @return
     */
    int deleteRole(@Param("adminId") long adminId);

    /**
     * 增加权限
     * @param adminId
     * @param roleId
     * @return
     */
    int insertRole(@Param("adminId") long adminId,@Param("roleId") long roleId);


}
