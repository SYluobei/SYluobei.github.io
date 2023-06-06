package com.woniuxy.servlet;

import com.woniuxy.service.UserService;
import com.woniuxy.service.impl.UserServiceImpl;
import com.woniuxy.util.C3P0Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

@WebServlet(name="TransferServlet",value = "/transfer")
public class TransferServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        //设置响应编码方式
        response.setContentType("text/html; charset=utf-8");
        int uid = (Integer) request.getSession().getAttribute("uid");

        String cardId = request.getParameter("cardId");
        BigDecimal money = new BigDecimal(request.getParameter("money"));

//        System.out.println(uid);
//        System.out.println(cardId);
//        System.out.println(money);
        boolean tranfer = userService.transfer(uid,cardId,money);
        try {
            response.getWriter().write(tranfer+"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        doGet(request, response);
    }
}
