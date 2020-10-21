package com.atguigu.filter;

import com.atguigu.utils.JdbcUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther: Yhurri
 * @Date: 2020/6/3 23:45
 * @Description:
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.调用下一个filter
        //2.调用资源
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtil.commitAndClose();
        }catch (Exception e){
            JdbcUtil.rollback();
            e.getStackTrace();            //把异常抛给tomcat服务器获得500

            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
