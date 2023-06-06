package com.view;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import com.bean.Client;
import com.controller.Controller;

public class View {
	static Controller co = new Controller();
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			/**
			 * 首页
			 */
			showMainPage();
			int in = scan.nextInt();
			switch (in) {
			case 1:
				//	1、查询所有客户信息(5分)
				queryAllMasage();
				break;
			case 2:
				//	2、添加一条客户数据(积分和信用度默认为0)（10分）
				addClient();
				break;
			case 3:
				//	3、修改上一条数据的(信用度、联系方式、和姓名)（10分）
				changeFPN();
				break;
			case 4:
				//	4、删除指定客户信息（10分）
				delClient();
				break;
			case 5:
				//	5、查询指定客户信息(5分)
				queryClientById();
				break;
			case 6:
				//	6、查询姓名中包含 "卷" 字且信用度高于85的客户有哪些?（10分）
				queryByName();
				break;
			case 7:
				//	7、查询 时间在"2023-05-19" 在高新区有消费记录的客户有哪些?（10分）
				queryBytime();
				break;
			case 8:
				//	8、查询积分在30 - 60区间的客户信息?（10分）
				queryByscore();
				break;
			case 9:
				//	9、查询消费记录最新的三条客户信息?（10分）
				queryBylasttime();
				break;
			case 10:
				//	10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)（20分）
				queryByNNAC();
				break;
			case 0:
				System.out.println("退出成功");
				return;
			default:
				System.out.println("操作暂未开发，敬请期待");
				break;
			}
		}
		
	}
	
	/**
	 * 10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)
	 */
	private static void queryByNNAC() {
		System.out.println("请输入要查询的姓名：");
		String name = scan.nextLine();
		name = scan.nextLine();
		System.out.println("请输入要查询的联系方式：");
		String phonenum = scan.nextLine();
		System.out.println("请输入要查询的地址：");
		String address = scan.nextLine();
		System.out.println("请输入要查询的消费时间（哪天范围内）：");
		String consumdate = scan.nextLine();
		Client client = new Client(null, name, phonenum, 0, 0, consumdate, address);
		List<Client> list = co.queryByNNAC(client);
		if(!client.getDatetime().isEmpty()) {
			list = list.stream().filter(i->i.getDatetime().startsWith(consumdate)).collect(Collectors.toList());
		}
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		list.forEach(System.out::println);
	}

	/**
	 * 9、查询消费记录最新的三条客户信息
	 */
	private static void queryBylasttime() {
		int limit = 3;
		List<Client> list = co.queryBylasttime(limit);
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		list.forEach(System.out::println);
	}

	/**
	 * 8、查询积分在30 - 60区间的客户信息
	 */
	private static void queryByscore() {
		List<Client> list = co.queryAllMasage();
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		list.stream().filter(i->i.getScore()<=60&&i.getScore()>=30).forEach(System.out::println);
	}

	/**
	 * 7、查询 时间在"2023-05-19" 在高新区有消费记录的客户有哪些
	 */
	private static void queryBytime() {
		List<Client> list = co.queryAllMasage();
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		list.stream().filter(i->i.getDatetime().startsWith("2023-05-19")&&i.getAddress().contains("高新区")).forEach(System.out::println);
	}

	/**
	 * 6、查询姓名中包含 "卷" 字且信用度高于85的客户有哪些
	 */
	private static void queryByName() {
		String name = "卷";
		int faith = 85;
		List<Client> list = co.queryByName(name,faith);
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		list.stream().filter(i->i.getName().startsWith(name)).forEach(System.out::println);
	}

	/**
	 * 5、查询指定客户信息
	 */
	private static void queryClientById() {
		boolean isloop = true;
		String id = null;
		while(isloop) {
			System.out.println("输入要查询的客户编号：");
			id = scan.next();
			if(co.judgeExist(id)) {
				isloop = false;
			}else System.out.println("输入的客户编号不存在，重新输入");
		}
		Client client = co.queryClientById(id);
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		System.out.println(client);
	}

	/**
	 * 4、删除指定客户信息
	 */
	private static void delClient() {
		boolean isloop = true;
		String id = null;
		while(isloop) {
			System.out.println("输入要删除的客户编号：");
			id = scan.next();
			if(co.judgeExist(id)) {
				isloop = false;
			}else System.out.println("输入的客户编号不存在，重新输入");
		}
		co.delClient(id);
		System.out.println("删除成功");
	}
	/**
	 * 3、修改上一条数据的(信用度、联系方式、和姓名)
	 */
	private static void changeFPN() {
		System.out.println("请输入新的信用度");
		int faith = scan.nextInt();
		System.out.println("请输入新的联系方式");
		String phonenum = scan.next();
		System.out.println("请输入新的姓名");
		String name = scan.next();
		co.changeFPN(faith,phonenum,name);
		System.out.println("修改成功");
	}

	/**
	 * 2、添加一条客户数据
	 */
	private static void addClient() {
		boolean isloop = true;
		String id = null;
		while(isloop) {
			System.out.println("输入要添加的客户编号：");
			id = scan.next();
			if(!co.judgeExist(id)) {
				isloop = false;
			}else System.out.println("输入的客户编号已存在，重新输入");
		}
		System.out.println("请输入客户姓名：");
		String name = scan.next();
		System.out.println("请输入联系方式：");
		String phonenum = scan.next();
		int score = 0, faith = 0;
		isloop = true;
		while(isloop) {
			System.out.println("输入积分");
			score = scan.nextInt();
			if(score>=0&&score<=10000) {
				isloop = false;
			}else System.out.println("输入的积分超出范围，重新输入");
		}
		isloop = true;
		while(isloop) {
			System.out.println("输入信用度");
			faith = scan.nextInt();
			if(faith<=100&&faith>=0) {
				isloop = false;
			}else System.out.println("输入信用度超出范围，重新输入");
		}
		System.out.println("请输入联系地址：");
		String address = scan.next();
		LocalDateTime now = LocalDateTime.now(); 
		Client client = new Client(id, name, phonenum, score, faith, now.toString(), address);
		co.addClient(client);
		System.out.println("添加客户成功");
	}

	/**
	 * 1、查询所有客户信息
	 */
	private static void queryAllMasage() {
		List<Client> list = co.queryAllMasage();
		System.out.println("客户编号\t姓名\t联系方式\t\t积分\t信用度\t消费时间\t\t\t联系地址");
		list.forEach(System.out::println);
	}
	/**
	 * 首页
	 */
	private static void showMainPage() {
		System.out.println("=======================================================");
		System.out.println("*******************************************************");
		System.out.println("*******************欢迎登陆客户系统*************************");
		System.out.println("******1、查询所有客户信息");
		System.out.println("******2、添加一条客户数据");
		System.out.println("******3、修改上一条数据的信用度、联系方式、和姓名");
		System.out.println("******4、删除指定客户信息");
		System.out.println("******5、查询指定客户信息");
		System.out.println("******6、查询姓名中包含 \"卷\" 字且信用度高于85的客户有哪些");
		System.out.println("******7、查询 时间在\"2023-05-19\" 在高新区有消费记录的客户有哪些");
		System.out.println("******8、查询积分在30 - 60区间的客户信息");
		System.out.println("******9、查询消费记录最新的三条客户信息");
		System.out.println("******10、多条件查询(参考字段: 姓名、联系方式、地址、和消费时间)");
		System.out.println("******0、退出系统");
		System.out.println("******************************************************");
		System.out.println("====================请选择你要进行的操作====================");
	}
}
