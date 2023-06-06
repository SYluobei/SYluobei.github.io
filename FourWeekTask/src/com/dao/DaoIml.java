package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.ColumnHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bean.Client;
import com.util.Util;

public class DaoIml implements Dao{
	Util util = new Util();
	QueryRunner qr = new QueryRunner(util.datasource);
	
	/**
	 * 1、查询所有客户信息
	 */
	@Override
	public List<Client> queryAllMasage() {
		String sql = "SELECT id,name,phonenum,score,faith,consumtime,address FROM client";
		try {
			List<Client> list = qr.query(sql, new BeanListHandler<>(Client.class));
			sql = "SELECT consumtime FROM client";
			List<Object> time = qr.query(sql, new ColumnListHandler<>());
			for(int i = 0;i<list.size();i++) {
				list.get(i).setDatetime(time.get(i).toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断客户编号是否存在
	 */
	@Override
	public boolean judgeExist(String id) {
		String sql = "SELECT id FROM client WHERE id = ?";
		Object params = id;
		try {
			String cid = qr.query(sql, params, new ScalarHandler<>());
			if(cid!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 2、添加一条客户数据
	 */
	@Override
	public void addClient(Client client) {
		String sql = "INSERT INTO client VALUES(?,?,?,?,?,?,?)";
		Object params[] = {client.getId(),client.getName(),client.getPhonenum(),client.getScore(),client.getFaith(),client.getDatetime(),client.getAddress()};
		try {
			 qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 3、修改上一条数据的(信用度、联系方式、和姓名)
	 */
	@Override
	public void changeFPN(int faith,String phonenum,String name) {
		String sql = "SELECT id FROM client ORDER BY id DESC LIMIT 1";
		try {
			String id = qr.query(sql, new ScalarHandler<>());
			sql = "UPDATE client SET faith = ?, phonenum = ?, name = ? WHERE id = ? ";
			Object params[] = {faith,phonenum,name,id};
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 4、删除指定客户信息
	 */
	@Override
	public void delClient(String id) {
		String sql = "DELETE FROM client WHERE id = ?";
		Object params = id;
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 5、查询指定客户信息
	 */
	@Override
	public Client queryClientById(String id) {
		String sql = "SELECT id,name,phonenum,score,faith,consumtime,address FROM client WHERE id = ?";
		try {
			Object param = id;
			Client client = qr.query(sql,param, new BeanHandler<>(Client.class));
			sql = "SELECT consumtime FROM client WHERE id = ?";
			Object time = qr.query(sql, param,new ScalarHandler<>());
			client.setDatetime(time.toString());
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 6、查询信用度高于85的客户有哪些
	 */
	@Override
	public List<Client> queryByName(String name, int faith) {
		String sql = "SELECT id,name,phonenum,score,faith,consumtime,address FROM client WHERE faith > ?";
		try {
			Object param = faith;
			List<Client> list = qr.query(sql, param,new BeanListHandler<>(Client.class));
			sql = "SELECT consumtime FROM client WHERE faith > ?";
			List<Object> time = qr.query(sql, param ,new ColumnListHandler<>());
			for(int i = 0;i<list.size();i++) {
				list.get(i).setDatetime(time.get(i).toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 9、查询消费记录最新的三条客户信息
	 */
	@Override
	public List<Client> queryBylasttime(int limit) {
		String sql = "SELECT id,name,phonenum,score,faith,consumtime,address FROM client ORDER BY consumtime DESC LIMIT ?";
		try {
			Object param = limit;
			List<Client> list = qr.query(sql,param, new BeanListHandler<>(Client.class));
			sql = "SELECT consumtime FROM client ORDER BY consumtime DESC LIMIT ?";
			List<Object> time = qr.query(sql, param ,new ColumnListHandler<>());
			for(int i = 0;i<list.size();i++) {
				list.get(i).setDatetime(time.get(i).toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)
	 */
	@Override
	public List<Client> queryByNNAC(Client client) {
		String sql = "SELECT * FROM client WHERE 1=1 ";
		String sql2 = "SELECT consumtime FROM client WHERE 1=1 ";
		List<Object> list1 = new ArrayList<>();
		if(!client.getName().isEmpty()) {
			sql += " AND name = ?";
			sql2 += " AND name = ?";
			list1.add(client.getName());
		}
		if(!client.getPhonenum().isEmpty()) {
			sql += " AND phonenum = ?";
			sql2 += " AND phonenum = ?";
			list1.add(client.getPhonenum());
		}
		if(!client.getAddress().isEmpty()) {
			sql += " AND address = ?";
			sql2 += " AND address = ?";
			list1.add(client.getAddress());
		}
		try {
			List<Client> list2 = qr.query(sql, list1.toArray(), new BeanListHandler<>(Client.class));
			List<Object> time = qr.query(sql2, list1.toArray() ,new ColumnListHandler<>());
			for(int i = 0;i<list2.size();i++) {
				list2.get(i).setDatetime(time.get(i).toString());
			}
			return list2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
