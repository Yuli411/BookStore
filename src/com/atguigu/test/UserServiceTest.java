package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 19:20
 * @Description:
 */
class UserServiceTest {
    UserService userService = new UserServiceImpl();
    User user = new User(null,"wuhua","123456","wuhua@123.com");

    @org.junit.jupiter.api.Test
    void register() {
        int register = userService.register(user);
        System.out.println(register);
    }

    @org.junit.jupiter.api.Test
    void login() {
//        User user = userService.login(new User(null,"wuhua","123456","wuhua@123.com"));
//        System.out.println(user);

    }

    @org.junit.jupiter.api.Test
    void exsitUsername() {
        boolean wuhua = userService.exsitUsername("wuhua");
        System.out.println(wuhua);
    }
}