package com.bean;

public class ShoppingCart {
	// 单项商品的总价
	private int cart_id;
	// 单项商品的总价
	private double goods_sum;
	// 购买数量
	private int buy_num;
	// 书名
	private String book_name;
	// 用户编号
	private String user_id;
	
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public double getGoods_sum() {
		return goods_sum;
	}
	public void setGoods_sum(double goods_sum) {
		this.goods_sum = goods_sum;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return cart_id+"\t"+goods_sum+"\t"+buy_num+"\t"+book_name+"\t"+ user_id;
	}
	public ShoppingCart(double goods_sum, int buy_num, String book_name, String user_id) {
		super();
		this.cart_id = cart_id;
		this.goods_sum = goods_sum;
		this.buy_num = buy_num;
		this.book_name = book_name;
		this.user_id = user_id;
	}
	public ShoppingCart() {
		super();
	}
	
}
