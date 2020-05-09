package com.hrh.mall.config;

import com.hrh.mall.security.configure.DynamicAccessDecisionManager;
import com.hrh.mall.security.configure.WebSecurityConfiguration;
import com.hrh.mall.security.service.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.config
 * @ClassName: SecurutyConfig
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/5/6 20:21
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 配置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }
}
