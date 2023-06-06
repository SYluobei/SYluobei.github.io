package com.bean;

public class TopThree {
	//用户编号
	private String user_id;
	//购买总额
	private double sum_money;
	//购买次数
	private int buy_times;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public double getSum_money() {
		return sum_money;
	}
	public void setSum_money(double sum_money) {
		this.sum_money = sum_money;
	}
	public int getBuy_times() {
		return buy_times;
	}
	public void setBuy_times(int buy_times) {
		this.buy_times = buy_times;
	}
	@Override
	public String toString() {
		return user_id + "\t" + sum_money + "\t" + buy_times;
	}
	public TopThree(String user_id, double sum_money, int buy_times, String level) {
		super();
		this.user_id = user_id;
		this.sum_money = sum_money;
		this.buy_times = buy_times;
	}
	public TopThree() {
		super();
	}
}
