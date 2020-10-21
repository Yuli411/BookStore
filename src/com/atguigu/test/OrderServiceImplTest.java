package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CarteItem;
import com.atguigu.service.impl.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 20:34
 * @Description:
 */
class OrderServiceImplTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    void createNewOrder() {
        Cart cart = new Cart();
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        String newOrder = orderService.createNewOrder(cart, 2);
        System.out.println(newOrder);


    }

    @Test
    void queryMyOrders() {

    }

    @Test
    void orderDetails() {
    }

    @Test
    void queryAllOrders() {
    }

    @Test
    void delivery() {
    }

    @Test
    void received() {
    }
}