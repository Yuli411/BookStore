package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtil;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/14 00:04
 * @Description:
 */
public class UserServlet extends BaseServlet {


    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserService userService = new UserServiceImpl();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            session.setAttribute("userid",user.getId());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/login_success.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            //需要在此处回显一些信息 并且提示错误信息
            req.setAttribute("username", req.getParameter("username"));
            req.setAttribute("msg", "用户名或密码错误！");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        String token = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
        session.removeAttribute("KAPTCHA_SESSION_KEY");
        UserService userService = new UserServiceImpl();
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String code = req.getParameter("code");
        User user = new User();
        try {
            user = WebUtil.copyParamToBean(req.getParameterMap(),user);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (token != null && req.getParameter("code").equalsIgnoreCase(token)) {
            //正确 检查用户名是否可用
            boolean isExsit = userService.exsitUsername(user.getUsername());
            if (!isExsit) {
                //可用 注册成功
                userService.register(user);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist_success.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                //不可用 返回注册页面
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("code", req.getParameter("code"));
                req.setAttribute("username", req.getParameter("username"));
                req.setAttribute("email", req.getParameter("email"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
                requestDispatcher.forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误！");
            //表单回显
            req.setAttribute("code", req.getParameter("code"));
            req.setAttribute("username", req.getParameter("username"));
            req.setAttribute("email", req.getParameter("email"));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/user/regist.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().invalidate();
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }


    protected void existUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        UserService userService = new UserServiceImpl();
        boolean isExist = userService.exsitUsername(username);
        Map<String, Boolean> map = new HashMap<>();
        map.put("isExist",isExist);
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        resp.getWriter().write(jsonString);

    }
}
