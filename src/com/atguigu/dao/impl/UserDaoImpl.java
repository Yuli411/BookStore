package com.atguigu.dao.impl;

import com.atguigu.pojo.User;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 18:14
 * @Description:
 */
public class   UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public User queryUserByName(String name) {
        String sql = "select * from t_user where username=?";
        return (User) queryForOne(User.class,sql,name);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        String sql = "select id,username,password,email from t_user where username=? and password=?";
        return (User) queryForOne(User.class,sql,name,password);
    }
}
