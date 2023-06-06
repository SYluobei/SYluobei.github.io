package com.woniuxy.service.impl;

import com.woniuxy.dao.IUserDao;
import com.woniuxy.dao.impl.IUserDaoImpl;
import com.woniuxy.entity.User;
import com.woniuxy.service.UserService;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {
    IUserDao dao = new IUserDaoImpl();
    @Override
    public User findByAccount(String account) {
        //调用dao层的代码获取想要的数据
        User user = dao.findByAccount(account);
        return user;
    }

    @Override
    public User findByUid(int uid) {
        User user = dao.findByUid(uid);
        return user;
    }

    @Override
    public boolean transfer(int id, String cardId, BigDecimal money) {
        //得到当前用户信息的
        User user = dao.findByUid(id);
        if(money.compareTo(user.getMoney())<=0){
            boolean result = dao.subMoney(id, money);
            if(result){
                //加钱
                result = dao.addMoney(cardId, money);
                if(result){
                    return true;
                }
            }
        }
        return false;
    }
}
