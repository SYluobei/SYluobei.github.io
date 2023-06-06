package com.woniuxy.dao.impl;

import com.woniuxy.dao.IUserDao;
import com.woniuxy.entity.User;
import com.woniuxy.util.C3P0Util;

import java.math.BigDecimal;
import java.sql.*;

public class IUserDaoImpl implements IUserDao {

    @Override
    public User findByAccount(String account) {
        Connection connection = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            connection = C3P0Util.getConnection();
            String sql = "select * from user where account = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,account);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String acc = resultSet.getString("account");
                String cardId = resultSet.getString("cardId");
                String pwd = resultSet.getString("password");
                BigDecimal money = resultSet.getBigDecimal("money");
                user = new User(id,acc,pwd,cardId,money);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public User findByUid(int uid) {
        Connection connection = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            connection = C3P0Util.getConnection();
            String sql = "select * from user where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,uid);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String acc = resultSet.getString("account");
                String cardId = resultSet.getString("cardId");
                String pwd = resultSet.getString("password");
                BigDecimal money = resultSet.getBigDecimal("money");
                user = new User(id,acc,pwd,cardId,money);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public boolean subMoney(int id, BigDecimal money) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = C3P0Util.getConnection();
            String sql = "update user set money = money - ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,money);
            ps.setObject(2,id);
            int row = ps.executeUpdate();
            if(row==1){
                return true;
            }else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean addMoney(String cardId, BigDecimal money) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = C3P0Util.getConnection();
            String sql = "update user set money = money + ? where cardId = ?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,money);
            ps.setObject(2,cardId);
            int row = ps.executeUpdate();
            if(row==1){
                return true;
            }else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
