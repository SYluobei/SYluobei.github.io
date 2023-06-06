package com.servlet;

import com.mysql.cj.protocol.Resultset;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;

@WebServlet("/ServletB")
public class ServletB extends HttpServlet {
    /**
     * 重写doGet方法
     * 接受客服端发送的get请求
     * */
    @Override
    public void doGet(HttpServletRequest  request, HttpServletResponse response) throws UnsupportedEncodingException {
//        设置响应编码格式
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bookstore?serverTimezone=GMT";
            String user = "root";
            String password = "admin";
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("SELECT * from user");
            ResultSet rs = ps.executeQuery();

            //向页面响应table元素
            PrintWriter pw = response.getWriter();
            pw.write("<div id='div1' style='text-align:center;'><form action='AddServlet' method='post' accept-charset='UTF-8'>");
            pw.write("<input type='text' name='user_name' placeholder='请输入姓名'>");
            pw.write("<input type='password' name='pwd' placeholder='请输入密码'>");
            pw.write("<input type='submit' value='添加'>");
            pw.write("<br>");

//            String user_name = request.getParameter("user_name");
//            String pwd = request.getParameter("pwd");

            pw.write("</form></div>");
            pw.write("<div>");
            pw.write("<table border='1px' style='margin:auto;'>");
            while(rs.next()){
                pw.write("<tr>");
                pw.write("<td>"+rs.getString(2)+"</td>");
                pw.write("<td>"+rs.getString(4)+"</td>");
                pw.write("<td>"+rs.getString(5)+"</td>");
                pw.write("<td><a href='/webDay06/DeleteServlet?user_id="+rs.getString(1)+"'>删除</a></td>");
                pw.write("<td><form action='FixServlet' method='post' accept-charset='UTF-8'>" +
                        "<input type='text' name='user_name' placeholder='请输入修改后的姓名'>" +
                        "<input type='password' name='pwd' placeholder='请输入修改后的密码'>" +
                                "<input type='text' name='money' placeholder='请输入修改后的金额'>"+
                        "<input type='hidden' name='user_id' value='" + rs.getString(1) + "'>" +
                        "<input type='submit' value='修改'></form></td>");
                pw.write("</tr>");
            }
            pw.write("</table>");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *重写doPost方法
     * 接收客服端发送的post请求
     * request：请求对象
     * response：响应对象
     */
    @Override
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }
}
