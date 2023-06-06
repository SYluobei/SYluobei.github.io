package com.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String user_name = request.getParameter("user_name");
        String pwd = request.getParameter("pwd");
        double money = 0;
        int permission = 1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=GMT";
            String user = "root";
            String password = "admin";
            UUID uuid = UUID.randomUUID();
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("insert into user values (?,?,?,?,?)");
            ps.setObject(1,uuid.toString());
            ps.setObject(2,user_name);
            ps.setObject(3,pwd);
            ps.setObject(4,money);
            ps.setObject(5,permission);
            int num = ps.executeUpdate();
            if(num>0){
                response.sendRedirect("/webDay06/ServletB");
            }else {
                response.getWriter().write("<h1>添加失败！<h1>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        doGet(request, response);
    }
}
