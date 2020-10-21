package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CarteItem;
import com.atguigu.service.impl.ManagerService;
import com.atguigu.service.impl.ManagerServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/30 15:14
 * @Description:
 */
public class CartServlet extends BaseServlet {
    private Cart cart = new Cart();

    private ManagerService managerService = new ManagerServiceImpl();

    public void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        Book book = managerService.queryBookById(Integer.valueOf(id));
        CarteItem carteItem = new CarteItem(book.getId(),book.getName(),1,
                new BigDecimal(book.getPrice()),new BigDecimal(book.getPrice()));

        cart.addItem(carteItem);
        session.setAttribute("cart",cart);

        session.setAttribute("lastItem",carteItem);
//        Map<String,Object> map = new HashMap<>();
//        map.put("total",cart.getItemsTotal());
//        map.put("lastName",carteItem.getName());
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(map);
//        response.getWriter().write(jsonString);
        response.sendRedirect(request.getHeader("referer"));

    }

    public void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        cart.deleteItem(Integer.valueOf(id));
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

    public void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cart.clean();
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    public void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        cart.updateItemsCount(Integer.valueOf(id),Integer.valueOf(count));
        response.sendRedirect(request.getHeader("referer"));
    }




}
