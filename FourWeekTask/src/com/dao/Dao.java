package com.dao;

import java.util.List;

import com.bean.Client;
import com.util.Util;

public interface Dao {
	Util util = new Util();

	/**
	 * 1、查询所有客户信息
	 */
	List<Client> queryAllMasage();

	/**
	 * 判断客户编号是否存在
	 */
	boolean judgeExist(String id);

	/**
	 * 2、添加一条客户数据
	 */
	void addClient(Client client);

	/**
	 * 3、修改上一条数据的(信用度、联系方式、和姓名)
	 * @param name 
	 * @param phonenum 
	 * @param faith 
	 */
	void changeFPN(int faith, String phonenum, String name);

	/**
	 * 4、删除指定客户信息
	 */
	void delClient(String id);

	/**
	 * 5、查询指定客户信息
	 */
	Client queryClientById(String id);

	/**
	 * 6、查询姓名中包含 "卷" 字且信用度高于85的客户有哪些
	 * @param faith 
	 * @param name 
	 */
	List<Client> queryByName(String name, int faith);

	/**
	 * 9、查询消费记录最新的三条客户信息
	 */
	List<Client> queryBylasttime(int limit);

	/**
	 * 10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)
	 */	
	List<Client> queryByNNAC(Client client);
}
