package com.atguigu.test;

import com.atguigu.dao.impl.OrderItemsDao;
import com.atguigu.dao.impl.OrderItemsDaoImpl;
import com.atguigu.pojo.OrderItems;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 19:23
 * @Description:
 */
class OrderItemsDaoImplTest {

    @Test
    void saveOrderItems() {
        OrderItems orderItems = new OrderItems(null,"数据结构与算法",2,new BigDecimal(20),new BigDecimal(40),"123456789");
        OrderItemsDao orderItemsDao = new OrderItemsDaoImpl();
        orderItemsDao.saveOrderItems(orderItems);
    }

    @Test
    void queryOrderDetailsById() {
        OrderItemsDao orderItemsDao = new OrderItemsDaoImpl();
        List orderItems = orderItemsDao.queryOrderDetailsById("123456789");
        System.out.println(orderItems);
    }
}