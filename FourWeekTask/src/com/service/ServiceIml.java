package com.service;

import java.util.List;

import com.bean.Client;
import com.dao.Dao;
import com.dao.DaoIml;

public class ServiceIml implements Service{
	Dao dao = new DaoIml();

	/**
	 * 1、查询所有客户信息
	 */
	@Override
	public List<Client> queryAllMasage() {
		return dao.queryAllMasage();
	}

	/**
	 * 判断客户编号是否存在
	 */
	@Override
	public boolean judgeExist(String id) {
		return dao.judgeExist(id);
	}

	/**
	 * 2、添加一条客户数据
	 */
	@Override
	public void addClient(Client client) {
		dao.addClient(client);
	}

	/**
	 * 3、修改上一条数据的(信用度、联系方式、和姓名)
	 */
	@Override
	public void changeFPN(int faith,String phonenum,String name) {
		dao.changeFPN(faith,phonenum,name);
	}

	/**
	 * 4、删除指定客户信息
	 */
	@Override
	public void delClient(String id) {
		dao.delClient(id);
	}

	/**
	 * 5、查询指定客户信息
	 */
	@Override
	public Client queryClientById(String id) {
		return dao.queryClientById(id);
	}

	/**
	 * 6、查询姓名中包含 "卷" 字且信用度高于85的客户有哪些
	 */
	@Override
	public List<Client> queryByName(String name, int faith) {
		return dao.queryByName(name, faith);
	}

	/**
	 * 9、查询消费记录最新的三条客户信息
	 */
	@Override
	public List<Client> queryBylasttime(int limit) {
		return dao.queryBylasttime(limit);
	}

	/**
	 * 10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)
	 */
	@Override
	public List<Client> queryByNNAC(Client client) {
		return dao.queryByNNAC(client);
	}
}
