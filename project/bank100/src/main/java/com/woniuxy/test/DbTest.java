package com.woniuxy.test;

import com.woniuxy.dao.IUserDao;
import com.woniuxy.dao.impl.IUserDaoImpl;
import com.woniuxy.entity.User;

public class DbTest {
    public static void main(String[] args) {
        IUserDao iUserDao = new IUserDaoImpl();
        User user = iUserDao.findByAccount("ikun");
        System.out.println(user);
    }
}
