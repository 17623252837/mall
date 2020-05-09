package com.hrh.mall.service;

import com.hrh.mall.domain.UmsAdmin;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.dto.UmsAdminParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.service
 * @ClassName: UmsAdminService
 * @Author: HuRonghua
 * @Description: ${description}
 * @Date: 2020/4/24 11:09
 * @Version: 1.0
 */
public interface UmsAdminService {
    /**
     * 新增用户 大于0 增加成功
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 根据用户名获取用户
     *
     * @param userName 用户名
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(String userName);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdmin admin);

    /**
     * 跟新用户头像
     *
     * @param username 用户名
     * @param path     图片路径
     * @return
     */
    int modifyIcon(String username, String path);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);


    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 获取用户对应角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 根据id 获取用户信息
     * @param umsAdminId {@link UmsAdmin}
     * @return
     */
    UmsAdmin getUserInfoById(Long umsAdminId);

    /**
     * 更新用户信息
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    int update(UmsAdmin umsAdmin);
}
