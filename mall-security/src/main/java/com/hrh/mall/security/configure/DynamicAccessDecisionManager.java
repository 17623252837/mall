package com.hrh.mall.security.configure;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.security.configure
 * @ClassName: DynamicAccessDecisionManager
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/5/5 18:02
 * @Version: 1.0
 */
@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 当接口未被配置资源时直接放行
        if (CollUtil.isEmpty(collection)) {
            return;
        }
        Iterator<ConfigAttribute> iterator = collection.iterator();

        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needAuthority = configAttribute.getAttribute();
            String[] split = needAuthority.split(":");
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (split[1].trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }

         throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
