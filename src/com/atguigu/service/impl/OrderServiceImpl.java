package com.atguigu.service.impl;

import com.atguigu.dao.impl.*;
import com.atguigu.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 20:12
 * @Description:
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemsDao orderItemsDao = new OrderItemsDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createNewOrder(Cart cart, Integer userid) {
        String orderid = System.currentTimeMillis()+""+userid;
        Order order = new Order(orderid,new Date(),cart.getPriceTotal(),0,userid);
        orderDao.saveOrder(order);
        Map<Integer, CarteItem> items = cart.getItems();
        for (Map.Entry entry:items.entrySet()){
            CarteItem cartItem = (CarteItem) entry.getValue();
            OrderItems orderItems = new OrderItems(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderid);
            orderItemsDao.saveOrderItems(orderItems);
            //gengg
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setStock(book.getStock()-cartItem.getCount());
            book.setSales(book.getSales()+cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clean();
        return orderid;

    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<OrderItems> orderDetails(String orderId) {
        return orderItemsDao.queryOrderDetailsById(orderId);
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderDao.queryAllOrders();
    }

    @Override
    public int Delivery(String orderId) {
        return orderDao.changeOrderStatus(1,orderId);
    }

    @Override
    public int received(String orderId) {
        return orderDao.changeOrderStatus(2,orderId);
    }
}
