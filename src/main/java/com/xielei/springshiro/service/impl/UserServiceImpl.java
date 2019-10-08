package com.xielei.springshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xielei.springshiro.entity.User;
import com.xielei.springshiro.mapper.UserMapper;
import com.xielei.springshiro.service.UserService;

/**
 * 描述：User Service 实现
 *
 * @author xielei
 * @date 2019/10/07
 */

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
