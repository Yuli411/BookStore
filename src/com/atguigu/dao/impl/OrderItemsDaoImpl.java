package com.atguigu.dao.impl;

import com.atguigu.pojo.OrderItems;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 18:21
 * @Description:
 */
public class OrderItemsDaoImpl extends BaseDao implements OrderItemsDao {
    @Override
    public int saveOrderItems(OrderItems orderItems) {
        String sql = "insert into t_orderitems(name,count,price,totalPrice,order_id) values(?,?,?,?,?)";
        return update(sql,orderItems.getName(),orderItems.getCount(),orderItems.getPrice(),orderItems.getTotalPrice(),orderItems.getOrder_id());
    }

    @Override
    public List<OrderItems> queryOrderDetailsById(String orderID) {
        String sql = "select * from t_orderitems where order_id=?";
        return  queryList(OrderItems.class,sql,orderID);

    }
}
