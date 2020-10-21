package com.atguigu.dao.impl;

import com.atguigu.pojo.User;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 18:07
 * @Description:如果返回null说明没有这个用户
 * 或者用户名密码错误
 */


public interface UserDao {
    public User queryUserByName(String name);
    public int saveUser(User user);
    public User queryUserByNameAndPassword(String name,String password);

}
