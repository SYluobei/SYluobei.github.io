package com.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/ServletA")
public class ServletA implements Servlet {

    public void init(ServletConfig config) throws ServletException {
        System.out.println("----init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("----service");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("----destroy");
    }
}
