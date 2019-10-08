package com.xielei.springshiro.entity;

import java.util.Set;

import lombok.Data;

/**
 * 描述：用户
 *
 * @author xielei
 * @date 2019/10/07
 */
@Data
public class User {
    
    private Integer uid;
    
    private String userName;
    
    private String password;
    
    private String salt;
    
    private Set<Role> roles;
}
