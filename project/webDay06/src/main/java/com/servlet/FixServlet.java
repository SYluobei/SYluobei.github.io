package com.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

@WebServlet("/FixServlet")
public class FixServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String pwd = request.getParameter("pwd");
        String money1 = request.getParameter("money");
        double money = Double.parseDouble(money1);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=GMT";
            String user = "root";
            String password = "admin";
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("update user set user_name=?,pwd=?,money=? where user_id=?");
            ps.setObject(1,user_name);
            ps.setObject(2,pwd);
            ps.setObject(3,money);
            ps.setObject(4,user_id);
            int num = ps.executeUpdate();
            if(num>0){
                response.sendRedirect("/webDay06/ServletB");
            }else {
                response.getWriter().write("<h1>修改失败！<h1>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
