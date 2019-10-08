package com.xielei.springshiro.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 描述：已废弃,用HashedCredentialMatcher自带 MD5加密
 *
 * @author xielei
 * @date 2019/10/07
 */
@Deprecated
public class MD5CredentialMatcher extends SimpleCredentialsMatcher {
    
    /**
     * 用于页面传入的 password和数据库中的 password比对
     * @param token 页面传入的令牌
     * @param info
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取页面传入 password
        String password = new String(usernamePasswordToken.getPassword());
        /* TODO 加盐
        Md5Hash md5Hash = new Md5Hash(password);
        md5Hash.setSalt();
        */
        //获取数据库中 password
        String dbPassword = (String) info.getCredentials();
        return this.equals(password, dbPassword);
    }
}
