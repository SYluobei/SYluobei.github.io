package com.controller;

import java.util.List;
import java.util.Map;

import com.bean.Book;
import com.bean.Goods;
import com.bean.Order;
import com.bean.ShoppingCart;
import com.bean.ThreeBook;
import com.bean.TopThree;
import com.bean.User;
import com.service.Service;
import com.service.ServiceIml;

public class Controller {
	Service ser = new ServiceIml();

	/**
	 * 1.1登录
	 */
	public boolean login(String user_name, String pwd) {
		return ser.login(user_name, pwd);
	}

	/**
	 *判断用户名是否重复 
	 */
	public boolean judgeExist(String user_name) {
		return ser.judgeExist(user_name);
	}

	/**
	 * 1.2注册
	 */
	public void register(User user) {
		ser.register(user);
	}

	/**
	 * 1.3充值
	 */
	public void recharge(String user_name, double money) {
		ser.recharge(user_name,money);
	}

	/**
	 * 查询余额
	 */
	public double queryMoney(String user_name) {
		return ser.queryMoney(user_name);
	}

	/**
	 * 获取用户权限类型
	 */
	public int getPermission(String user_name) {
		return ser.getPermission(user_name);
	}

	/**
	 * 1.2删除用户（admin）
	 */
	public void delUser(String user_name) {
		ser.delUser(user_name);
	}

	/**
	 * 查看所有用户
	 */
	public List<User> showAllUser() {
		return ser.showAllUser();
	}

	/**
	 * 1.3修改用户（admin）
	 */
	public void changeUser(String user_name, User user) {
		ser.changeUser(user_name,user);
	}

	/**
	 * 1.5多条件查询用户（admin）
	 */
	public List<User> showByCofiUser(User user, double maxmoney, double minmoney) {
		return ser.showByCofiUser(user, maxmoney,minmoney);
	}

	/**
	 * 1.4查看所有书籍(Book)
	 */
	public List<Book> showAllBook() {
		return ser.showAllBook();
	}

	//判断书名是否存在
	public boolean judgeBookExist(String book_name) {
		return ser.judgeBookExist(book_name);
	}

	/**
	 * 1.1添加书籍
	 */
	public void addBook(Book book) {
		ser.addBook(book);
	}

	/**
	 * 1.2删除书籍
	 */
	public void delBook(String book_name) {
		ser.delBook(book_name);
	}

	/**
	 * 1.3修改书籍
	 */
	public void changeBook(String book_name, Book book) {
		ser.changeBook(book_name,book);
	}

	/**
	 * 1.5多条件查询书籍
	 */
	public List<Book> showByCofiBook(String book_name, double maxprice, double minprice, int max_num, int min_num) {
		return ser.showByCofiBook(book_name, maxprice, minprice, max_num,min_num);
	}

	/**
	 *判断库存是否足够
	 */
	public boolean judegeBookNum(int remain_num, String book_name) {
		return ser.judegeBookNum(remain_num,book_name);
	}

	/**
	 * 获取指定书名的书籍信息
	 */
	public Book getBook(String book_name) {
		return ser.getBook(book_name);
	}

	/**
	 * 商品信息保存
	 */
	public void addGoods(Goods goods) {
		ser.addGoods(goods);
	}

	/**
	 * 获取goods信息集合
	 */
	public List<Goods> getGoodsList(String user_name) {
		return ser.getGoodsList(user_name);
	}

	/**
	 * 9.修改库存，修改用户余额
	 * @param order_id 
	 */
	public void tradeDown(String user_name, double sum, String order_id) {
		ser.tradeDown(user_name,sum,order_id);
	}

	/**
	 * 8.余额充足，录入订单表与订单关系表并打印订单信息
	 * @param order_id 
	 */
	public Order getChangeOrder(String user_name, double sum, String remark, List<Goods> goodslist, String order_id) {
		return ser.getChangeOrder(user_name,sum,remark, goodslist,order_id);
	}

	/**
	 * 加入购物车
	 */
	public void putShoopingCart(String user_name, List<Goods> goodslist) {
		ser.putShoopingCart(user_name, goodslist);
	}

	/**
	 * 1.2查看购物车
	 */
	public List<ShoppingCart> queryShoppingCart(String user_name) {
		return ser.queryShoppingCart(user_name);
	}

	/**
	 * 根据用户名获取用户id
	 */
	public String getUserId(String user_name) {
		return ser.getUserId(user_name);
	}

	/**
	 * 删除该用户的购物车
	 */
	public void cleanCart(String user_name) {
		ser.cleanCart(user_name);
	}

	/**
	 * 查看订单
	 */
	public List<Order> getOrder() {
		return ser.getOrder();
	}

	/**
	 * 通过用户id找到用户name
	 */
	public String getNameById(String user_id) {
		return ser.getNameById(user_id);
	}

	/**
	 * 1.7.查看前三名重要的买家
	 */
	public List<TopThree> getTopThree() {
		return ser.getTopThree();
	}
	/**
	 * 更新topthree表
	 */
	public void refershTop() {
		ser.refershTop();
	}

	/**
	 * 1.8.显示本年某个月每周流水量
	 */
	public Map<String, Double> showWeekIncome(String month) {
		return ser.showWeekIncome(month);
	}

	/**
	 * 获取库存为零的书籍名称
	 */
	public List<String> getBookNameByRe() {
		return ser.getBookNameByRe();
	}

	/**
	 * 1.9.显示售出数量最少的三本书并设置优惠金额
	 */
	public List<ThreeBook> getThreeBook() {
		return ser.getThreeBook();
	}

	/**
	 * 设置优惠金额
	 */
	public void setPrice(String book_name, int discount) {
		ser.setPrice(book_name,discount);
	}

	/**
	 * 根据订单编号获取订单单项编号，再获取订单单项表
	 */
	public List<Goods> getGoodsListByOrder(String order_id) {
		return ser.getGoodsListByOrder(order_id);
	}

	//获取OrderGoods中的订单单项号
	public List<String> getOrderGoodsList() {
		return ser.getOrderGoodsList();
	}

}
