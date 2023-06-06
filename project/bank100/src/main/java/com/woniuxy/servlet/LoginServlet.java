package com.woniuxy.servlet;

import com.woniuxy.entity.User;
import com.woniuxy.service.UserService;
import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码方式
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(name+password);
        User user = userService.findByAccount(name);
        if(user!=null&&name.equals(user.getAccount())&&password.equals(user.getPassword())){
            System.out.println("登陆成功，跳转到主页");
//            request.getRequestDispatcher("main.html").forward(request,response);
            response.getWriter().write("true");
            //获取session,该对象是由服务器创建并由request传进来的
            HttpSession session = request.getSession();
            System.out.println("login:"+session);
            //将用户信息放入session，目的是让服务器知道谁登陆了
            session.setAttribute("uid",user.getId());
        }else{
            System.out.println("登陆失败");
//            request.getRequestDispatcher("index.html").forward(request,response);

            response.getWriter().write("fasle");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }


}
