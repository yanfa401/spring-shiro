package com.xielei.springshiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xielei.springshiro.entity.User;
import com.xielei.springshiro.service.UserService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/10/08
 */
@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/getUser/{username}")
    public User getUser(@PathVariable("username") String userName){
        return userService.findByUserName(userName);
    }
}
