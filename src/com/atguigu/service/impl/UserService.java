package com.atguigu.service.impl;

import com.atguigu.pojo.User;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 19:03
 * @Description:
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    public int register(User user);

    /**
     * 用户登陆
     * @param
     * @return
     */
    public User login(String username, String password);


    /**
     * username是否存在
     * @param username
     */

    public boolean exsitUsername(String username);
}
