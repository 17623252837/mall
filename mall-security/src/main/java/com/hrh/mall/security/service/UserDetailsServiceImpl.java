package com.hrh.mall.security.service;

import com.google.common.collect.Lists;
import com.hrh.mall.domain.UmsAdmin;
import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.service.UmsAdminService;
import com.hrh.mall.service.UmsResourceService;
import com.hrh.mall.service.UmsRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 获取用户角色信息
 *
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.service
 * @ClassName: UserDetailsServiceImpl
 * @Author: HuRonghua
 * @Description: 自定义认证授权
 * @Date: 2020/3/20 22:41
 * @Version: 1.0
 */

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UmsAdminService umsAdminService;

    @Resource
    private UmsRoleService roleService;

    @Resource
    private UmsResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

       UmsAdmin umsAdmin = umsAdminService.get(s);
        // 账号存在
        if (umsAdmin != null) {
           //  List<UmsRole> umsRoles = roleService.listRole(umsAdmin.getId());
            List<UmsResource> listByAdminId = resourceService.getListByAdminId(umsAdmin.getId());
            if(listByAdminId != null && listByAdminId.size() > 0) {
                listByAdminId.forEach(resource -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resource.getName());
                    grantedAuthorities.add(grantedAuthority);
                });
            }
            else {
                    GrantedAuthority grantedAuthority =new SimpleGrantedAuthority("USER");
                    grantedAuthorities.add(grantedAuthority);
            }

            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorities);
        }
        return null;
    }
}
