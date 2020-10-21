package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.impl.ManagerService;
import com.atguigu.service.impl.ManagerServiceImpl;
import com.atguigu.utils.WebUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/16 23:55
 * @Description:
 */
public class BookServlet extends BaseServlet {
    private ManagerService managerService = new ManagerServiceImpl();

    public void addBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Book book = new Book();



        try {
            WebUtil.copyParamToBean(request.getParameterMap(),book);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        managerService.addBook(book);

        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        pageNo += 1;
        response.sendRedirect(request.getContextPath()+"/manager/bookQuery?action=page&pageNo="+pageNo);        //此处必须用重定向 因为浏览器会记录下最后一次请求 一旦刷新会重复最后一次请求的所有内容，当使用请求转发的时候最后一次请求是
        //表单提交后addbook，所以会重复表单提交
        //注意重定向地址
    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Book book = new Book();
        try {
            WebUtil.copyParamToBean(request.getParameterMap(),book);
            book.setId(Integer.valueOf(request.getParameter("id")));

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        managerService.modifyBook(book);
        String pageNo = request.getParameter("pageNo");
        response.sendRedirect(request.getContextPath()+"/manager/bookQuery?action=page&pageNo="+pageNo);


    }
    public void deleteBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String id = request.getParameter("id");
        managerService.deleteBook(Integer.valueOf(id));
        String pageNo = request.getParameter("pageNo");

        response.sendRedirect(request.getContextPath()+"/manager/bookQuery?action=page&pageNo="+pageNo);
    }
    public void list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Book> books = managerService.queryAllBooks();
        request.setAttribute("list",books);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/manager/book_manager.jsp");
        requestDispatcher.forward(request,response);
    }

    public void showUpdateBookInfo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String id = request.getParameter("id");
        Book book = managerService.queryBookById(Integer.valueOf(id));
        request.setAttribute("willBeModifiedBook",book);

        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);

    }

    /**
     * 处理分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取请求的分页信息 包括每页记录数 当前页码
        String pageNo = request.getParameter("pageNo")== null? "1":request.getParameter("pageNo") ;
        Integer pageInt = Integer.valueOf(pageNo);
        String pageSize = request.getParameter("pageSize")== null?"4":request.getParameter("pageSize");

        //分页操作
        Page<Book> page = managerService.page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        page.setUrl("manager/bookQuery?action=page");
        Integer total = page.getPageTotal();
        //把记录保存到request域中
        if (pageInt > total){
            //请求转发
            Page<Book> page1 = managerService.page(total, Integer.valueOf(pageSize));
            page1.setUrl("manager/bookQuery?action=page");
            request.setAttribute("page",page1);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        }else if (pageInt<1){
            Page<Book> page1 = managerService.page(1, Integer.valueOf(pageSize));
            page1.setUrl("manager/bookQuery?action=page");
            request.setAttribute("page",page1);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        } else {
            request.setAttribute("page",page);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

        }
    }

}
