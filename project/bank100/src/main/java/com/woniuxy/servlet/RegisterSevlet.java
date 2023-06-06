package com.woniuxy.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="RegisterServlet", value = "/register")
public class RegisterSevlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        //设置响应编码方式
        response.setContentType("text/html; charset=utf-8");
        System.out.println(request.getParameter("name"));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request, response);
    }
}
