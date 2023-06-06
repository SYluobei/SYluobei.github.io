package com.bean;

public class OrderGoods {
	//用户编号
	private String user_id;
	//订单号
	private String order_id;
	//订单单项号
	private String goods_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	@Override
	public String toString() {
		return "OrderGoods [user_id=" + user_id + ", order_id=" + order_id + ", goods_id=" + goods_id + "]";
	}
	public OrderGoods(String user_id, String order_id, String goods_id) {
		super();
		this.user_id = user_id;
		this.order_id = order_id;
		this.goods_id = goods_id;
	}
	public OrderGoods() {
		super();
	}
	
}
