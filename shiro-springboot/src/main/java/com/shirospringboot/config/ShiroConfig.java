package com.shirospringboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1、创建realm对象，需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //2、DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getdefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //3、shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getdefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //实现shiro的内置过滤器
        /**
         * anon:无需认证就可以访问
         * authc:必须认证了才能访问
         * user:必须拥有记住我功能才能用
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限
         */

        //拦截
        Map<String, String> filter = new LinkedHashMap<>();

        //授权，正常的情况下，未授权跳转到未授权页面
        filter.put("/user/add", "perms[user:add]");
        filter.put("/user/update", "perms[user:update]");


        filter.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filter);

        //设置登录的请求
        bean.setLoginUrl("/toLogin");

        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    // 整合ShiroDialect： 用来整合 Shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

}
