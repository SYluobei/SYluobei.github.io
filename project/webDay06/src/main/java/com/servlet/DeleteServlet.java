package com.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
//    重写doGet
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String user_id = request.getParameter("user_id");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=GMT";
            String user = "root";
            String password = "admin";
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("delete from user where user_id = ?");
            ps.setString(1,user_id);
            int num = ps.executeUpdate();
            if(num>0){
                response.sendRedirect("/webDay06/ServletB");
            }else {
                response.getWriter().write("<h1>删除失败！</h1>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
//    重写doPost
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        doGet(request, response);
    }
}
