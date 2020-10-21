package com.atguigu.service.impl;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItems;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.*;
import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 20:04
 * @Description:
 */
public interface OrderService {
    public String createNewOrder(Cart cart, Integer uerid);
    public java.util.List<Order> queryMyOrders(Integer userId);
    public List<OrderItems> orderDetails(String orderId);
    public List<Order> queryAllOrders();
    public int Delivery(String orderId);
    public int received(String orderId);

}
