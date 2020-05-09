package com.hrh.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.hrh.mall.domain.UmsAdmin;
import com.hrh.mall.domain.UmsAdminRoleRelation;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.dto.UmsAdminExample;
import com.hrh.mall.dto.UmsAdminParam;
import com.hrh.mall.mapper.UmsAdminMapper;
import com.hrh.mall.mapper.UmsAdminRoleRelationMapper;
import com.hrh.mall.mapper.UmsPermissionMapper;
import com.hrh.mall.mapper.UmsRoleMapper;
import com.hrh.mall.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:    mall-monomer
 * @Package:        com.hrh.mall.service.impl
 * @ClassName:      UmsAdminServiceImpl
 * @Author:     HuRonghua
 * @Description:  ${description}
 * @Date:    2020/4/24 11:09
 * @Version:    1.0
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService{
    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsRoleMapper umsRoleMapper;


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        // 初始化用户对象
        initUmsAdmin(umsAdmin);
        return umsAdminMapper.insert(umsAdmin);
    }

    @Override
    public UmsAdmin get(String userName) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",userName);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public int update(Long id,UmsAdmin umsAdmin) {
        umsAdmin.setId(id);
        UmsAdmin rawAdmin = umsAdminMapper.selectByPrimaryKey(id);
        if(rawAdmin.getPassword().equals(umsAdmin.getPassword())){
            //与原加密密码相同的不需要修改
            umsAdmin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(umsAdmin.getPassword())){
                umsAdmin.setPassword(null);
            }else{
                umsAdmin.setPassword(bCryptPasswordEncoder.encode(umsAdmin.getPassword()));
            }
        }
        return umsAdminMapper.updateByPrimaryKeySelective(umsAdmin);
    }

    @Override
    public int modifyIcon(String username, String path) {
        UmsAdmin umsAdmin = get(username);
        umsAdmin.setIcon(path);
        return umsAdminMapper.updateByPrimaryKey(umsAdmin);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return umsAdminMapper.selectByExample(example);
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = bCryptPasswordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public int delete(Long id) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(id);
        return umsAdminMapper.delete(umsAdmin);
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsRoleMapper.getRoleList(adminId);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        // 先删除原权限
        int i = umsRoleMapper.deleteRole(adminId);
        // 添加新权限
        roleIds.forEach(id -> umsRoleMapper.insertRole(adminId,id));
        return 0;
    }

    @Override
    public UmsAdmin getUserInfoById(Long umsAdminId) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(umsAdminId);
        return umsAdminMapper.selectByPrimaryKey(umsAdmin);
    }

    @Override
    public int update(UmsAdmin umsAdmin) {
        return umsAdminMapper.updateByPrimaryKey(umsAdmin);
    }

    /**
     * 初始化UmsAdmin
     * @param umsAdmin {@link UmsAdmin}
     */
    private void initUmsAdmin(UmsAdmin umsAdmin){
        // 创建时间，最后登陆时间
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        // 初始化状态 0 : 封禁
        if (umsAdmin.getStatus() == null) {
            umsAdmin.setStatus(0);
        }
        // 密码加密
        umsAdmin.setPassword(bCryptPasswordEncoder.encode(umsAdmin.getPassword()));
    }
}
