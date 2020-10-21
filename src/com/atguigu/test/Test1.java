package com.atguigu.test;

import com.atguigu.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/14 01:29
 * @Description:
 */
public class Test1 {
    public void testCopy(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();


    }
}
