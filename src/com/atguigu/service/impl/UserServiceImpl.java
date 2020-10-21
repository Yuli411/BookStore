package com.atguigu.service.impl;

import com.atguigu.dao.impl.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 19:06
 * @Description:
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();
    @Override
    public int register(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(String username,String password) {
        return userDao.queryUserByNameAndPassword(username, password);

    }

    @Override
    public boolean exsitUsername(String username) {
        User user = userDao.queryUserByName(username);
        if (user != null){
            return true;
        }else
            return false;
    }
}
