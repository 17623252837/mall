package com.hrh.mall.security.configure;

import com.hrh.mall.domain.UmsResource;
import com.hrh.mall.security.component.RestAuthenticationEntryPoint;
import com.hrh.mall.security.component.RestfulAccessDeniedHandler;
import com.hrh.mall.service.DynamicSecurityService;
import com.hrh.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.business.configure
 * @ClassName: WebSecurityConfiguration
 * @Author: HuRonghua
 * @Description: 认证服务器安全配置
 * @Date: 2020/3/20 22:13
 * @Version: 1.0
 */

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private UmsResourceService resourceService;

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义认证授权
        auth.userDetailsService(userDetailsService());
    }
    /**
     * 用于支持 password 模式
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 排除不拦截接口路径.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        web.ignoring().antMatchers("/user/login",
                                               "/swagger-ui.html",
                                               "/swagger-resources/**",
                                               "/swagger/**",
                                               "/**/v2/api-docs",
                                               "/webjars/springfox-swagger-ui/**",
                                               "/v2/**",
                                               "/webjars/**",
                                               "/favicon.ico",
                                               "/doc.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();

        // 允许跨域处理
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();

        // 任何请求需要提供身份认证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护和session的使用
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                .and()
                .addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);

                ;
        // 添加资源
        if(dynamicSecurityService != null){
          registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }

    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> umsResources = resourceService.listAll();
                umsResources.forEach(resource -> {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                });
                return map;
            }
        };
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}
