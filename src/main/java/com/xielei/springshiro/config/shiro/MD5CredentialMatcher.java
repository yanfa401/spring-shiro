package com.xielei.springshiro.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/10/07
 */
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
        Md5Hash md5Hash = new Md5Hash(password);
        //获取数据库中 password
        String dbPassword = (String) info.getCredentials();
        return this.equals(password, dbPassword);
    }
}
