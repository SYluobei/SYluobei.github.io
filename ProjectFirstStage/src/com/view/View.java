package com.view;

import java.util.Scanner;
import java.util.UUID;

import com.bean.User;
import com.controller.Controller;

public class View {
	static Controller co = new Controller();
	static Scanner scan = new Scanner(System.in);
	static AdminView admin = new AdminView();
	static BuyerView buyer = new BuyerView();
	static SellerView seller = new SellerView();
	public static void main(String[] args) {
		while(true){
			/**
			 * 1.首页展示
			 */
			showMainPage();
			int in = scan.nextInt();
			switch (in) {
			case 1:
				//1.1登录
				login();
				break;
			case 2:
				//1.2注册
				register();
				break;
			case 3:
				//1.3充值
				recharge();
				break;
			case 4:
				System.out.println("退出商城成功");
				return;
			default:
				System.out.println("操作暂未开发，敬请期待");
				break;
			}
		}
		
	}
	
	/**
	 * 1.3充值
	 */
	private static void recharge() {
		boolean isloop = true;
		String user_name = null;
		double money = 0.0;
		while(isloop) {
			System.out.println("请输入要充值的用户名：");
			user_name = scan.next();
			if(co.judgeExist(user_name)) {
				isloop = false;
			}else System.out.println("输入的用户名不存在，请重新输入");
		}
		isloop = true;
		while(isloop) {
			System.out.println("请输入要充值的金额：");
			money = scan.nextDouble();
			money += co.queryMoney(user_name);	//查询余额
			if(money>0&&money<1000000) {
				isloop = false;
			}else System.out.println("输入的金额超出范围，请重新输入");
		}
		co.recharge(user_name,money);
		System.out.println("充值成功");
		System.out.println("您的余额为："+money+"元");
	}
	/**
	 * 1.2注册
	 */
	private static void register() {
		boolean isloop = true;
		String user_name = null;
		while(isloop) {
			System.out.println("请输入用户名：");
			user_name = scan.next();
			if(!co.judgeExist(user_name)) {
				isloop = false;
			}else System.out.println("输入的用户名已存在，请重新输入");
		}
		System.out.println("请输入密码：");
		String pwd = scan.next();
		UUID user_id = UUID.randomUUID();
		User user = new User(user_id.toString(), user_name, pwd, 0,1);
		co.register(user);
		System.out.println("恭喜注册成功！");		
	}
	/**
	 * 1.1登录
	 */
	private static void login() {
//		boolean isloop = true;
		int isloop = 3;
		while(isloop>0) {
			System.out.println("请输入用户名：");
			String user_name = scan.next();
			System.out.println("请输入密码：");
			String pwd = scan.next();
			if(co.login(user_name,pwd)) {
				isloop = 3;
				int permission = 1;
				permission = co.getPermission(user_name);
				/**
				 * 登录成功后根据权限进入不同界面
				 */
				switch (permission) {
				case 1:
					//买家
					buyer.MainPage(user_name);
					isloop = -1;
					break;
				case 2:
					//卖家
					seller.MainPage();
					isloop = -1;
					break;
				case 3:
					//管理员
					admin.MainPage(user_name);
					isloop = -1;
					break;

				default:
					System.out.println("系统出错了！");
					break;
				}
			}else {
				System.out.println("输入的用户名或密码不正确");
				if((isloop-1)==0) {
					System.out.println("三次输入错误，将退回主界面");
				}else {
					System.out.println("您还有 "+(isloop-1)+" 次输入用户名和密码的机会");
				}
				isloop--;
				if(isloop==0) continue;
				System.out.println("输入1注册新账号");
				System.out.println("输入任意数字，重新输入用户名和密码");
				int in = scan.nextInt();
				switch (in) {
				case 1:
					register();
					isloop = 3;
					System.out.println("可以登录了！");
					break;
				default:	
					break;
				}
			}
		}		
	}
	/**
	 * 1.首页展示
	 */
	private static void showMainPage() {
		System.out.println("===========================");
		System.out.println("**************************");
		System.out.println("*******欢迎登陆商城系统*******");
		System.out.println("*********1.登录*********");
		System.out.println("*********2.注册*********");
		System.out.println("*********3.充值*********");
		System.out.println("*********4.退出*********");
		System.out.println("**************************");
		System.out.println("======请选择你要进行的操作======");
	}
}
