package com.xielei.springshiro.entity;

import lombok.Data;

/**
 * 描述：权限
 *
 * @author xielei
 * @date 2019/10/07
 */
@Data
public class Permission {
    
    private Integer pid;
    
    private String name;
    
    private String url;
}
