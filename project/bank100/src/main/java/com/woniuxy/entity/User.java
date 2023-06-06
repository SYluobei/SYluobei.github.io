package com.woniuxy.entity;

import java.math.BigDecimal;

public class User {
    private int id;         //id
    private String account;     //账号
    private String password;     //密码
    private String cardId;      //卡号
    private BigDecimal money;  //余额

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getCardId() {
        return cardId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", cardId='" + cardId + '\'' +
                ", money=" + money +
                '}';
    }

    public User(int id, String account, String password, String cardId, BigDecimal money) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.cardId = cardId;
        this.money = money;
    }

    public User() {
        super();
    }

}
