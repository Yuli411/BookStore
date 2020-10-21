package com.atguigu.dao.impl;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItems;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 18:04
 * @Description:
 */
public interface OrderItemsDao {
    public int saveOrderItems(OrderItems orderItems);
    public List<OrderItems> queryOrderDetailsById(String orderID);

}
