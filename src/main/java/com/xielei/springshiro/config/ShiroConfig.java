package com.xielei.springshiro.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.xielei.springshiro.config.shiro.AuthRealm;
import com.xielei.springshiro.config.shiro.MD5CredentialMatcher;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/10/07
 */
@SpringBootConfiguration
public class ShiroConfig {
    
    /**
     * 校验规则
     */
    @Bean("MD5CredentialMatcher")
    public MD5CredentialMatcher getCredentialMatcher() {
        return new MD5CredentialMatcher();
    }
    
    /**
     * 自定义realm
     * @param md5CredentialMatcher
     * @return
     */
    @Bean("authRealm")
    public AuthRealm getAuthRealm(@Qualifier("MD5CredentialMatcher") MD5CredentialMatcher md5CredentialMatcher) {
        // 自定义realm
        AuthRealm authRealm = new AuthRealm();
        // 设置 自定义的密码比较器
        authRealm.setCredentialsMatcher(md5CredentialMatcher);
        return authRealm;
    }
    
    @Bean("securityManager")
    public SecurityManager getSecurityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }
    
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");
    
        //设置权限是否需要拦截
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/login", "anon");
    
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    
}
