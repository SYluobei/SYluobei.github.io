package com.bean;

public class Client {
	// 客户编号
	private String id;
	// 客户姓名
	private String name;
	// 联系方式
	private String phonenum;
	// 积分
	private int score;
	// 信用度
	private int faith;
	// 消费时间
	private String datetime;
	// 联系地址
	private String address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getFaith() {
		return faith;
	}
	public void setFaith(int faith) {
		this.faith = faith;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return id + "\t" + name + "\t" + phonenum + "\t" + score + "\t"
				+ faith + "\t" + datetime + "\t" + address;
	}
	public Client(String id, String name, String phonenum, int score, int faith, String datetime, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phonenum = phonenum;
		this.score = score;
		this.faith = faith;
		this.datetime = datetime;
		this.address = address;
	}
	public Client() {
		super();
	}
	
}
