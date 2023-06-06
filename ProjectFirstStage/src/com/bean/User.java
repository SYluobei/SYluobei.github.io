package com.bean;

public class User {
	// 用户编号
	private String user_id;
	// 用户名称
	private String user_name;
	// 密码
	private String pwd;
	// 金额
	private double money;
	// 权限号
	private int permission;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return user_id + "\t" + user_name + "\t" + pwd + "\t" + money
				+ "\t" + permission;
	}
	public User(String user_id, String user_name, String pwd, double money, int permission) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.pwd = pwd;
		this.money = money;
		this.permission = permission;
	}
	public User() {
		super();
	}
	
}
