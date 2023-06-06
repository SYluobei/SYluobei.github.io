package com.controller;

import java.util.List;

import com.bean.Client;
import com.service.Service;
import com.service.ServiceIml;

public class Controller {
	Service ser = new ServiceIml();

	/**
	 * 1、查询所有客户信息
	 */
	public List<Client> queryAllMasage() {
		return ser.queryAllMasage();
	}

	/**
	 * 判断客户编号是否存在
	 */
	public boolean judgeExist(String id) {
		return ser.judgeExist(id);
	}

	/**
	 * 2、添加一条客户数据
	 */
	public void addClient(Client client) {
		ser.addClient(client);
	}

	/**
	 * 3、修改上一条数据的(信用度、联系方式、和姓名)
	 * @param name 
	 * @param phonenum 
	 * @param faith 
	 */
	public void changeFPN(int faith, String phonenum, String name) {
		ser.changeFPN(faith,phonenum,name);
	}

	/**
	 * 4、删除指定客户信息
	 */
	public void delClient(String id) {
		ser.delClient(id);
	}

	/**
	 * 5、查询指定客户信息
	 */
	public Client queryClientById(String id) {
		return ser.queryClientById(id);
	}

	/**
	 * 6、查询姓名中包含 "卷" 字且信用度高于85的客户有哪些
	 * @param faith 
	 * @param name 
	 */
	public List<Client> queryByName(String name, int faith) {
		return ser.queryByName(name, faith);
	}

	/**
	 * 9、查询消费记录最新的三条客户信息
	 */
	public List<Client> queryBylasttime(int limit) {
		return ser.queryBylasttime(limit);
	}

	/**
	 * 10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)
	 */
	public List<Client> queryByNNAC(Client client) {
		return ser.queryByNNAC(client);
	}

}
