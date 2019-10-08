package com.xielei.springshiro.config.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.xielei.springshiro.entity.Permission;
import com.xielei.springshiro.entity.Role;
import com.xielei.springshiro.entity.User;
import com.xielei.springshiro.service.UserService;

/**
 * 描述：自定义 Realm
 *
 * @author xielei
 * @date 2019/10/07
 */
public class AuthRealm extends AuthorizingRealm {
    
    @Autowired
    private UserService userService;
    
    /**
     * 授权时候调用该方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissionSet = new HashSet<>();
        // 1.获取用户信息
        Collection collection = principals.fromRealm(this.getClass().getName());
        User userWeb = (User) principals.getPrimaryPrincipal();
        User user = (User) collection.iterator().next();
        // 2.根据用户,获取角色列表
        Set<Role> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                // 3.根据角色获取权限列表
                Set<Permission> permissions = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissions)) {
                    for (Permission permission : permissions) {
                        // 4.添加到权限集合中
                        permissionSet.add(permission.getName());
                    }
                }
            }
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }
    
    /**
     * 认证登录时候用的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // token携带了用户信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        // 获取前端输入的用户名
        String username = usernamePasswordToken.getUsername();
        // 根据用户名查询数据库中对应的记录
        User user = userService.findByUserName(username);
        // 盐值
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, this.getClass().getName());
    }
}
