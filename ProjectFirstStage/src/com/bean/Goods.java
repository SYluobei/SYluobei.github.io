package com.bean;

public class Goods {
	//订单单项号
	private String goods_id;
	//用户编号
	private String user_id;
	// 商品编号
	private String book_id;
	// 商品名称
	private String book_name;
	// 购买数量
	private int buy_num;
	// 商品单价
	private double price;
	// 单项商品的总价
	private double goods_sum;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getGoods_sum() {
		return goods_sum;
	}
	public void setGoods_sum(double goods_sum) {
		this.goods_sum = goods_sum;
	}
	
	@Override
	public String toString() {
		return goods_id + "\t" + user_id + "\t" + book_id + "\t" + book_name + "\t"
				+ buy_num + "\t" + price + "\t" + goods_sum;
	}
	
	public Goods(String goods_id, String user_id, String book_id, String book_name, int buy_num, double price, double goods_sum) {
		super();
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.book_name = book_name;
		this.buy_num = buy_num;
		this.price = price;
		this.goods_sum = goods_sum;
	}
	public Goods() {
		super();
	}
	
}
