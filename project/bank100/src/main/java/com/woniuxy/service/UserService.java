package com.woniuxy.service;

import com.woniuxy.entity.User;

import java.math.BigDecimal;

public interface UserService {
    User findByAccount(String account);
    User findByUid(int uid);
    boolean transfer(int id, String cardId, BigDecimal money);
}
