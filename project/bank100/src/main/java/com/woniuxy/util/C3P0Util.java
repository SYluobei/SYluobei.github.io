package com.woniuxy.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

/*c3p0工具类，自动resources下寻找名字叫c3p0-config.xml文件来读取，然后链接数据库*/
public class C3P0Util {
    private static final ComboPooledDataSource DATASOURCE = new ComboPooledDataSource();

    public static ComboPooledDataSource getDataSource(){
        return DATASOURCE;
    }

    public static Connection getConnection(){
        try {
            Connection connection = DATASOURCE.getConnection();
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return null;
    }

    public static void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("链接关闭异常");
            }
        }
    }
}