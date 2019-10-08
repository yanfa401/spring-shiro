package com.xielei.springshiro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xielei.springshiro.entity.User;
import com.xielei.springshiro.mapper.UserMapper;
import com.xielei.springshiro.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringShiroApplicationTests {
    
//    @Autowired
//    private UserService userService;
    
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User admin = userMapper.findByUserName("admin");
        System.out.println(admin);
    }

}
