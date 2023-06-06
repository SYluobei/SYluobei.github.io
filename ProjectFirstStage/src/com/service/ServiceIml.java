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

public class ServiceIml implements Service{
	Dao dao = new DaoIml();
	
	/**
	 * 1.1登录
	 */
	@Override
	public boolean login(String user_name, String pwd) {
		return dao.login(user_name, pwd);
	}

	/**
	 *判断用户名是否重复 
	 */
	@Override
	public boolean judgeExist(String user_name) {
		return dao.judgeExist(user_name);
	}

	/**
	 * 1.2注册
	 */
	@Override
	public void register(User user) {
		dao.register(user);
	}

	/**
	 * 1.3充值
	 */
	@Override
	public void recharge(String user_name, double money) {
		dao.recharge(user_name,money);
	}

	/**
	 * 查询余额
	 */
	@Override
	public double queryMoney(String user_name) {
		return dao.queryMoney(user_name);
	}

	/**
	 * 获取用户权限类型
	 */
	@Override
	public int getPermission(String user_name) {
		return dao.getPermission(user_name);
	}

	/**
	 * 1.2删除用户（admin）
	 */
	@Override
	public void delUser(String user_name) {
		dao.delUser(user_name);
	}

	/**
	 * 查看所有用户
	 */
	@Override
	public List<User> showAllUser() {
		return dao.showAllUser();
	}

	/**
	 * 1.3修改用户（admin）
	 */
	@Override
	public void changeUser(String user_name, User user) {
		dao.changeUser(user_name, user);
	}

	/**
	 * 1.5多条件查询用户（admin）
	 */
	@Override
	public List<User> showByCofiUser(User user, double maxmoney, double minmoney) {
		return dao.showByCofiUser(user, maxmoney,minmoney);
	}

	/**
	 * 1.4查看所有书籍(Book)
	 */
	@Override
	public List<Book> showAllBook() {
		return dao.showAllBook();
	}

	//判断书名是否存在
	@Override
	public boolean judgeBookExist(String book_name) {
		return dao.judgeBookExist(book_name);
	}

	/**
	 * 1.1添加书籍
	 */
	@Override
	public void addBook(Book book) {
		dao.addBook(book);
	}

	/**
	 * 1.2删除书籍
	 */
	@Override
	public void delBook(String book_name) {
		dao.delBook(book_name);
	}

	/**
	 * 1.3修改书籍
	 */
	@Override
	public void changeBook(String book_name, Book book) {
		dao.changeBook(book_name,book);
	}

	/**
	 * 1.5多条件查询书籍
	 */
	@Override
	public List<Book> showByCofiBook(String book_name, double maxprice, double minprice, int max_num, int min_num) {
		return dao.showByCofiBook(book_name, maxprice, minprice, max_num,min_num);
	}

	/**
	 *判断库存是否足够
	 */
	@Override
	public boolean judegeBookNum(int remain_num,String book_name) {
		return dao.judegeBookNum(remain_num,book_name);
	}

	/**
	 * 获取指定书名的书籍信息
	 */
	@Override
	public Book getBook(String book_name) {
		return dao.getBook(book_name);
	}

	/**
	 * 商品信息保存
	 */
	@Override
	public void addGoods(Goods goods) {
		dao.addGoods(goods);
	}

	/**
	 * 获取goods信息集合
	 */
	@Override
	public List<Goods> getGoodsList(String user_name) {
		return dao.getGoodsList(user_name);
	}

	/**
	 * 9.修改库存，修改用户余额
	 */
	@Override
	public void tradeDown(String user_name, double sum,String order_id) {
		dao.tradeDown(user_name,sum,order_id);
	}

	/**
	 * 8.余额充足，录入订单表与订单关系表并打印订单信息
	 */
	@Override
	public Order getChangeOrder(String user_name,double sum,String remark,List<Goods> goodslist,String order_id) {
		return dao.getChangeOrder(user_name,sum,remark,goodslist,order_id);
	}

	/**
	 * 加入购物车
	 */
	@Override
	public void putShoopingCart(String user_name, List<Goods> goodslist) {
		dao.putShoopingCart(user_name, goodslist);
	}

	/**
	 * 1.2查看购物车
	 */
	@Override
	public List<ShoppingCart> queryShoppingCart(String user_name) {
		return dao.queryShoppingCart(user_name);
	}

	/**
	 * 根据用户名获取用户id
	 */
	@Override
	public String getUserId(String user_name) {
		return dao.getUserId(user_name);
	}

	/**
	 * 删除该用户的购物车
	 */
	@Override
	public void cleanCart(String user_name) {
		dao.cleanCart(user_name);
	}

	/**
	 * 查看订单
	 */
	@Override
	public List<Order> getOrder() {
		return dao.getOrder();
	}

	/**
	 * 通过用户id找到用户name
	 */
	@Override
	public String getNameById(String user_id) {
		return dao.getNameById(user_id);
	}

	/**
	 * 1.7.查看前三名重要的买家
	 */
	@Override
	public List<TopThree> getTopThree() {
		return dao.getTopThree();
	}

	/**
	 * 更新topthree表
	 */
	@Override
	public void refershTop() {
		dao.refershTop();
	}

	/**
	 * 1.8.显示本年某个月每周流水量
	 */
	@Override
	public Map<String,Double> showWeekIncome(String month) {
		return dao.showWeekIncome(month);
	}

	/**
	 * 获取库存为零的书籍名称
	 */
	@Override
	public List<String> getBookNameByRe() {
		return dao.getBookNameByRe();
	}

	/**
	 * 1.9.显示售出数量最少的三本书并设置优惠金额
	 */
	@Override
	public List<ThreeBook> getThreeBook() {
		return dao.getThreeBook();
	}

	/**
	 * 设置优惠金额
	 */
	@Override
	public void setPrice(String book_name, int discount) {
		dao.setPrice(book_name,discount);
	}

	/**
	 * 根据订单编号获取订单单项编号，再获取订单单项表
	 */
	@Override
	public List<Goods> getGoodsListByOrder(String order_id) {
		return dao.getGoodsListByOrder(order_id);
	}

	//获取OrderGoods中的订单单项号
	@Override
	public List<String> getOrderGoodsList() {
		return dao.getOrderGoodsList();
	}

}
