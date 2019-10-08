package com.xielei.springshiro.entity;

import java.util.Set;

import lombok.Data;

/**
 * 描述：角色
 *
 * @author xielei
 * @date 2019/10/07
 */
@Data
public class Role {
    
    private Integer rid;
    
    private String roleName;
    
    private Set<Permission> permissions;
}
