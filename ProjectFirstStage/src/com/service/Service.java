package com.service;

import java.util.List;
import java.util.Map;

import com.bean.Book;
import com.bean.Goods;
import com.bean.Order;
import com.bean.ShoppingCart;
import com.bean.ThreeBook;
import com.bean.TopThree;
import com.bean.User;
import com.dao.Dao;
import com.dao.DaoIml;

public interface Service {
	Dao dao = new DaoIml();

	/**
	 * 1.1登录
	 */
	boolean login(String user_name, String pwd);

	/**
	 *判断用户名是否重复 
	 */
	boolean judgeExist(String user_name);

	/**
	 * 1.2注册
	 */
	void register(User user);

	/**
	 * 1.3充值
	 */
	void recharge(String user_name, double money);

	/**
	 * 查询余额
	 */
	double queryMoney(String user_name);

	/**
	 * 获取用户权限类型
	 */
	int getPermission(String user_name);

	/**
	 * 1.2删除用户（admin）
	 */
	void delUser(String user_name);

	/**
	 * 查看所有用户
	 */
	List<User> showAllUser();

	/**
	 * 1.3修改用户（admin）
	 */
	void changeUser(String user_name, User user);

	/**
	 * 1.5多条件查询用户（admin）
	 */
	List<User> showByCofiUser(User user, double maxmoney, double minmoney);

	/**
	 * 1.4查看所有书籍(Book)
	 */
	List<Book> showAllBook();

	//判断书名是否存在
	boolean judgeBookExist(String book_name);

	/**
	 * 1.1添加书籍
	 */
	void addBook(Book book);

	/**
	 * 1.2删除书籍
	 */
	void delBook(String book_name);

	/**
	 * 1.3修改书籍
	 */
	void changeBook(String book_name, Book book);

	/**
	 * 1.5多条件查询书籍
	 */
	List<Book> showByCofiBook(String book_name, double maxprice, double minprice, int max_num, int min_num);

	/**
	 *判断库存是否足够
	 */
	boolean judegeBookNum(int remain_num, String book_name);

	/**
	 * 获取指定书名的书籍信息
	 */
	Book getBook(String book_name);

	/**
	 * 商品信息保存
	 */
	void addGoods(Goods goods);

	/**
	 * 获取goods信息集合
	 */
	List<Goods> getGoodsList(String user_name);

	/**
	 * 9.修改库存，修改用户余额
	 * @param order_id 
	 */
	void tradeDown(String user_name, double sum, String order_id);

	/**
	 * 8.余额充足，录入订单表与订单关系表并打印订单信息
	 * @param order_id 
	 */
	Order getChangeOrder(String user_name, double sum, String remark, List<Goods> goodslist, String order_id);

	/**
	 * 加入购物车
	 */
	void putShoopingCart(String user_name, List<Goods> goodslist);

	/**
	 * 1.2查看购物车
	 */
	List<ShoppingCart> queryShoppingCart(String user_name);

	/**
	 * 根据用户名获取用户id
	 */
	String getUserId(String user_name);

	/**
	 * 删除该用户的购物车
	 */
	void cleanCart(String user_name);

	/**
	 * 查看订单
	 */
	List<Order> getOrder();

	/**
	 * 通过用户id找到用户name
	 */
	String getNameById(String user_id);

	/**
	 * 1.7.查看前三名重要的买家
	 */
	List<TopThree> getTopThree();

	/**
	 * 更新topthree表
	 */
	void refershTop();

	/**
	 * 1.8.显示本年某个月每周流水量
	 */
	Map<String, Double> showWeekIncome(String month);

	/**
	 * 获取库存为零的书籍名称
	 */
	List<String> getBookNameByRe();

	/**
	 * 1.9.显示售出数量最少的三本书并设置优惠金额
	 */
	List<ThreeBook> getThreeBook();

	/**
	 * 设置优惠金额
	 */
	void setPrice(String book_name, int discount);

	/**
	 * 根据订单编号获取订单单项编号，再获取订单单项表
	 */
	List<Goods> getGoodsListByOrder(String order_id);

	//获取OrderGoods中的订单单项号
	List<String> getOrderGoodsList();

}
