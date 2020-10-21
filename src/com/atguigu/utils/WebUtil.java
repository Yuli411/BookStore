package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/14 01:21
 * @Description:
 */
public class WebUtil {
//    public static void copyParamToBean(HttpServletRequest req,Object bean) throws InvocationTargetException, IllegalAccessException {
//        BeanUtils.populate(bean,req.getParameterMap());
//    }
    //把表单参数注入bean
    public static <T> T copyParamToBean(Map map, T bean) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(bean,map);
        return bean;
    }
}
