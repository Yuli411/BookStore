package com.atguigu.dao.impl;

import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 18:07
 * @Description:
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
        return update(sql, order.getOrder_id(), order.getCreate_time(), order.getPrice(), order.getStatus(), order.getUser_id());
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql = "select * from t_order";
        return queryList(Order.class, sql);
    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql = "select * from t_order where user_id=?";
        return queryList(Order.class,sql,userId);
    }

    @Override
    public int changeOrderStatus(Integer status,String orderId) {
        String sql = "update t_order set status=? where order_id=? ";
        return update(sql,status,orderId);
    }
}
