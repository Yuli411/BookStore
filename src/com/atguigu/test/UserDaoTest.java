package com.atguigu.test;

import com.atguigu.dao.impl.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/5 18:32
 * @Description:
 */
public class UserDaoTest {
    @Test
    public void test1(){
        UserDao userDao = new UserDaoImpl();
        User admin = userDao.queryUserByName("admin");
        System.out.println(admin);

    }
    @Test
    public void test2(){
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByNameAndPassword("admin", "admin");
        System.out.println(user);
    }
    @Test
    public void test3(){
        UserDao userDao = new UserDaoImpl();
        int isSaved = userDao.saveUser(new User(null, "tom", "123456", "tom@123.com"));
        if (isSaved != -1){
            System.out.println("存储成功");
        }else {
            System.out.println("存储失败");
        }
    }


}
