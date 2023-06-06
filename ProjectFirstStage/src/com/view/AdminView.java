package com.view;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.bean.User;
import com.controller.Controller;

public class AdminView {
	static Scanner scan = new Scanner(System.in);
	static Controller co = new Controller();
	
	
//	/**
//	 * test
//	 */
//	public static void main(String[] args) {
//		MainPage();
//	}
	
	
	/**
	 * 1.首页
	 * @param user_name 
	 */
	public static void MainPage(String user_name) {
		while(true) {
			System.out.println("===========================");
			System.out.println("**************************");
			System.out.println("*****欢迎登陆商城管理员界面*****");
			System.out.println("********1.添加用户********");
			System.out.println("********2.删除用户********");
			System.out.println("********3.修改用户********");
			System.out.println("********4.查看用户********");
			System.out.println("********5.多条件查询用户******");
			System.out.println("********6.返回主界面********");
			System.out.println("**************************");
			System.out.println("======请选择你要进行的操作======");
			int in = scan.nextInt();
			switch (in) {
			case 1:
				//1.1添加用户
				addUser();
				break;
			case 2:
				//1.2删除用户
				delUser(user_name);
				break;
			case 3:
				//1.3修改用户
				changeUser(user_name);
				break;
			case 4:
				//1.4查看所有用户
				showAllUser();
				break;
			case 5:
				//1.5多条件查询用户
				showByCofiUser();
				break;
			case 6:
				//1.6返回主界面
				System.out.println("返回主界面成功");
				return;
			default:
				System.out.println("操作暂未开发，敬请期待");
				break;
			}			
		}
	}

	/**
	 * 1.5多条件查询用户
	 */
	private static void showByCofiUser() {
		boolean isloop = true;
		String user_name = null;
		int permission = 1;
		while(isloop) {
			System.out.println("输入要查询的用户名");
			user_name = scan.nextLine();
			user_name = scan.nextLine();
			if(user_name.isEmpty()||co.judgeExist(user_name)) {
				isloop = false;
			}else System.out.println("输入的用户名不存在，重新输入");
		}
		System.out.println("输入要查询的密码：");
		String pwd = scan.nextLine();
		System.out.println("最大值和最小值都输入-1表示不限余额范围");
		System.out.println("输入要查询的余额最大值：");
		double maxmoney = scan.nextDouble();
		System.out.println("输入要查询的余额最小值：");
		double minmoney = scan.nextDouble();
		isloop = true;
		while(isloop) {
			System.out.println("输入要查询的权限级别(输入0不限级别)");
			permission = scan.nextInt();
			if(permission==0||(permission<=3&&permission>=1)) {
				isloop = false;
			}else System.out.println("输入的权限不存在，重新输入");
		}
		User user = new User(null, user_name, pwd, -1,permission);
		List<User> list = co.showByCofiUser(user,maxmoney,minmoney);
		System.out.println("查询结果如下：");
		System.out.println("用户编号\t\t\t\t\t用户名称\t密码\t余额\t\t权限号");
		list.forEach(System.out::println);
	}

	/**
	 * 1.3修改用户
	 * @param user_name2 
	 */
	private static void changeUser(String user_name2) {
		showAllUser();
		boolean isloop = true;
		String user_name = null;
		String new_name = null;
		int permission = 1;
		while(isloop) {
			System.out.println("输入要修改的用户名：");
			user_name = scan.next();
			if(user_name.equals(user_name2)) {
				System.out.println("不能修改自己");
			}else {
				if(co.judgeExist(user_name)) {
					isloop = false;
				}else System.out.println("输入的用户名不存在，重新输入");				
			}
		}
		isloop = true;
		while(isloop) {
			System.out.println("输入新用户名");
			scan.nextLine();
			new_name = scan.nextLine();
			if(new_name.isEmpty()||!co.judgeExist(new_name)) {
				isloop = false;
			}else System.out.println("输入的用户名已存在，重新输入");
		}
		System.out.println("输入新密码：");
		String pwd = scan.nextLine();
		System.out.println("输入新余额：(输入-1表示不修改)");
		double money = scan.nextDouble();
		isloop = true;
		while(isloop) {
			System.out.println("输入新权限(输入0表示不修改)");
			permission = scan.nextInt();
			if(permission==0||(permission<=3&&permission>=1)) {
				isloop = false;
			}else System.out.println("输入的权限不存在，重新输入");
		}
		User user = new User(null, new_name, pwd, money,permission);
		co.changeUser(user_name,user);
		System.out.println("修改成功");
		
	}
	/**
	 * 1.4查看所有用户
	 */
	private static void showAllUser() {
		List<User> list = co.showAllUser();
		System.out.println("用户编号\t\t\t\t\t用户名称\t密码\t余额\t权限号");
		list.forEach(System.out::println);
	}
	

	/**
	 * 1.2删除用户
	 * @param user_name2 
	 */
	private static void delUser(String user_name2) {
		boolean isloop = true;
		String user_name = null;
		while(isloop) {
			System.out.println("输入要删除的用户名：");
			user_name = scan.next();
			if(user_name.equals(user_name2)) {
				System.out.println("不能删除自己");
			}else {
				if(co.judgeExist(user_name)) {
					isloop = false;
				}else System.out.println("输入的用户名不存在，重新输入");
			}
		}
		co.delUser(user_name);
		System.out.println("删除成功");	
	}

	/**
	 * 1.1添加用户
	 */
	private static void addUser() {
		boolean isloop = true;
		String user_name = null;
		while(isloop) {
			System.out.println("输入要添加的用户名：");
			user_name = scan.next();
			if(!co.judgeExist(user_name)) {
				isloop = false;
			}else System.out.println("输入的用户名已存在，重新输入");
		}
		System.out.println("输入密码：");
		String pwd = scan.next();
		UUID user_id = UUID.randomUUID();
		int permission = 1;
		while(isloop) {
			System.out.println("输入权限");
			permission = scan.nextInt();
			if(permission<=3&&permission>=1) {
				isloop = false;
			}else System.out.println("输入的权限不存在，重新输入");
		}
		User user = new User(user_id.toString(), user_name, pwd, 0,permission);
		co.register(user);
		System.out.println("添加用户成功");
	}
}
