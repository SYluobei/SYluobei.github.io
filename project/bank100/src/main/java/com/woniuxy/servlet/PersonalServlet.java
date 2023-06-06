package com.woniuxy.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.entity.User;
import com.woniuxy.service.UserService;
import com.woniuxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name="PersonalServlet",value = "/personal")
public class PersonalServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取用户的id
        int uid = (Integer) request.getSession().getAttribute("uid");
        User user = userService.findByUid(uid);
//        System.out.println("uid:"+uid);
        //设置响应编码方式
//        response.setContentType("text/html; charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        String name = request.getParameter("name");
//        UserService userService = new UserServiceImpl();
//        User user = userService.findByAccount(name);
//        User user = new User(1001, "张三", "123", "1001", new BigDecimal("100"));
        //将user对象转换为json格式字符串
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(mapper.writeValueAsString(user));
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}