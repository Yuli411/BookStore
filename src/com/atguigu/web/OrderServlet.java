package com.atguigu.web;


import com.atguigu.dao.impl.BookDao;
import com.atguigu.pojo.*;
import com.atguigu.service.impl.ManagerService;
import com.atguigu.service.impl.ManagerServiceImpl;
import com.atguigu.service.impl.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/2 21:05
 * @Description:
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    public void placeAnOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userid") != null){

            Cart cart = (Cart) session.getAttribute("cart");
            Integer userid = (Integer)session.getAttribute("userid");
            String newOrderNo = orderService.createNewOrder(cart, userid);
            session.setAttribute("newOrder",newOrderNo);
            response.sendRedirect("http://10.220.89.160:8080/book/pages/cart/checkout.jsp");


        }else {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }

    }

    public void modifyAnOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void queryMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userid")!=null){
            Integer userid = (Integer)session.getAttribute("userid");
            List<Order> orders = orderService.queryMyOrders(userid);
            request.setAttribute("myOrders",orders);
            request.getRequestDispatcher("//pages/order/order.jsp").forward(request,response);

        }else{
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

        }

    }

    public void queryAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    public void queryOrderDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");
        List<OrderItems> orderItems = orderService.orderDetails(orderid);
        request.setAttribute("orderItems",orderItems);
        request.getRequestDispatcher("").forward(request,response);
    }

    public void deliveryOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
    public void finishOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
