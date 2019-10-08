package com.xielei.springshiro.config;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.xielei.springshiro.config.shiro.AuthRealm;

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
     *      密码校验规则HashedCredentialsMatcher
     *      这个类是为了对密码进行编码的 ,
     *      防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     *      这个类也负责对form里输入的密码进行编码
     *      处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
   /* @Bean("MD5CredentialMatcher")
    public MD5CredentialMatcher getCredentialMatcher() {
        return new MD5CredentialMatcher();
    }*/
    
    @Bean("MD5CredentialMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        matcher.setHashAlgorithmName("MD5");
        //加密次数
        matcher.setHashIterations(1);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }
    
    /**
     * 自定义realm
     * @param matcher
     * @return
     */
    @Bean("authRealm")
    public AuthRealm getAuthRealm(@Qualifier("MD5CredentialMatcher") HashedCredentialsMatcher matcher) {
        // 自定义realm
        AuthRealm authRealm = new AuthRealm();
        // 设置 自定义的密码比较器
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }
    
    /**
     * 定义安全管理器securityManager,注入自定义的realm
     * @param authRealm
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }
    
    /**
     * 定义shiroFilter过滤器并注入securityManager
     * @param securityManager
     * @return
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置securityManager
        bean.setSecurityManager(securityManager);
    
        //设置登录页面
        bean.setLoginUrl("/login.html");
        //登录成功跳转的页面
        bean.setSuccessUrl("/index.html");
        //未授权跳转的页面
        bean.setUnauthorizedUrl("/unauthorized.html");
    
        //设置权限是否需要拦截
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /**
         * authc => 需要认证
         * anon => 不需要认证
         */
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/login", "anon");
        //需要登录访问的资源 , 一般将/**放在最下边
        filterChainDefinitionMap.put("/**", "authc");
    
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    
    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    
    /**
     * 配置shiro跟spring的关联
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    
}
