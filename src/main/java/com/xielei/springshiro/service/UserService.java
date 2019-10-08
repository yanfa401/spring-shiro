package com.xielei.springshiro.service;

import org.apache.ibatis.annotations.Param;

import com.xielei.springshiro.entity.User;

/**
 * 描述： 用户 Service
 *
 * @author xielei
 * @date 2019/10/07
 */
public interface UserService {
    
    User findByUserName(String userName);
}
