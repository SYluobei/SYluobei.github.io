package com.woniuxy.dao;


import com.woniuxy.entity.User;

import java.math.BigDecimal;

public interface IUserDao {
    //登陆用方法
    User findByAccount(String account);
    User findByUid(int uid);
    boolean subMoney(int id, BigDecimal money);

    boolean addMoney(String cardId, BigDecimal money);
}
