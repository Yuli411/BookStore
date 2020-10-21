package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.impl.ManagerService;
import com.atguigu.service.impl.ManagerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/25 00:27
 * @Description:
 */
public class ClientBookServlet extends BaseServlet {
    private ManagerService managerService = new ManagerServiceImpl();
    //处理分页
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo")== null? "1":request.getParameter("pageNo") ;
        Integer pageInt = Integer.valueOf(pageNo);
        String pageSize = request.getParameter("pageSize")== null?"4":request.getParameter("pageSize");

        //分页操作
        Page<Book> page = managerService.page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        page.setUrl("client/bookServlet?action=page");

        Integer total = page.getPageTotal();
        //把记录保存到request域中
        if (pageInt > total){
            //请求转发
            Page<Book> page1 = managerService.page(total, Integer.valueOf(pageSize));
            page1.setUrl("client/bookServlet?action=page");

            request.setAttribute("page",page1);
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
        }else if (pageInt<1){
            Page<Book> page1 = managerService.page(1, Integer.valueOf(pageSize));
            page1.setUrl("client/bookServlet?action=page");
            request.setAttribute("page",page1);
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
        } else {
            request.setAttribute("page",page);
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

        }
    }

    public void pagePrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer max = Integer.valueOf(request.getParameter("max"));

        Integer min = Integer.valueOf(request.getParameter("min"));

        String pageNo = request.getParameter("pageNo")==null? "1":request.getParameter("pageNo");
        Integer no = Integer.valueOf(pageNo);
        String pageSize = request.getParameter("pageSize")== null?"4":request.getParameter("pageSize");
        Integer size = Integer.valueOf(pageSize);

        Page<Book> bookPage = managerService.pagePrice(no, size, min, max);
        Integer total = bookPage.getPageTotal();
        bookPage.setUrl("client/bookServlet?action=pagePrice&min="+min+"&max="+max+"");

        //把记录保存到request域中
        if (no > total){
            //请求转发
            Page<Book> page1 = managerService.pagePrice(total,size,min,max);
            page1.setUrl("client/bookServlet?action=page");
            request.setAttribute("page",page1);
            request.setAttribute("min",min);
            request.setAttribute("max",max);
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
        }else if (no < 1){
            Page<Book> page1 = managerService.pagePrice(1, size, min, max);
            page1.setUrl("client/bookServlet?action=page");
            request.setAttribute("page",page1);
            request.setAttribute("min",min);
            request.setAttribute("max",max);
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
        } else {
            request.setAttribute("page",bookPage);
            request.setAttribute("min",min);
            request.setAttribute("max",max);
            request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

        }


    }


}
