package com.atguigu.test;

import com.atguigu.dao.impl.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 18:39
 * @Description:
 */
class OrderDaoImplTest {

    @Test
    void saveOrder() {
        Order order = new com.atguigu.pojo.Order("123456787",new Date(),new BigDecimal(100),0,3);
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(order);

    }


    @Test
    void queryAllOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.queryAllOrders();
        for (Order order:orders){
            System.out.println(order.getUser_id());
        }
        System.out.println(orders);
    }

    @Test
    void queryMyOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        List<Order> orders = orderDao.queryMyOrders(2);
        System.out.println(orders);
    }

    @Test
    void changeOrderStatus() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.changeOrderStatus(1,"123456789");
    }
}