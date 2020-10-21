package com.atguigu.dao.impl;

import com.atguigu.pojo.Order;
import jdk.net.SocketFlow;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 17:58
 * @Description:
 */
public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryAllOrders();
    public List<Order> queryMyOrders(Integer orderId);
    public int changeOrderStatus(Integer status, String orderId);



}
