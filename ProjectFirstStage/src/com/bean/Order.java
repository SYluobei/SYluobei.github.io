package com.bean;

public class Order {
	// 订单号
	private String order_id;
	// 订单总价
	private double sum;
	// 订单日期
	private String date;
	// 备注
	private String remark;
	// 用户id
	private String user_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return order_id + "\t" + sum + "\t" + date + "\t" + remark + "\t"
				+ user_id;
	}
	public Order(String order_id, double sum, String date, String remark, String user_id) {
		super();
		this.order_id = order_id;
		this.sum = sum;
		this.date = date;
		this.remark = remark;
		this.user_id = user_id;
	}
	public Order() {
		super();
	}
	
}
