package com.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

import com.bean.Book;
import com.bean.Order;
import com.bean.ThreeBook;
import com.bean.TopThree;
import com.bean.User;
import com.controller.Controller;

public class SellerView {
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
	 */
	public static void MainPage() {
		List<String> booklist = co.getBookNameByRe();	//获取库存为零的书籍名称
		if(!booklist.isEmpty()) {	//判断是否需要补货
			booklist.forEach(i->System.out.println(i+"库存为零，请及时补货"));
		}
		while(true) {
			System.out.println("============================");
			System.out.println("****************************");
			System.out.println("******欢迎登陆商城卖家界面******");
			System.out.println("********1.添加书籍");
			System.out.println("********2.删除书籍");
			System.out.println("********3.修改书籍");
			System.out.println("********4.查看书籍");
			System.out.println("********5.多条件查询书籍");
			System.out.println("********6.查看买家备注");
			System.out.println("********7.查看前三名重要的买家");
			System.out.println("********8.显示本年某个月每周流水量");
			System.out.println("********9.显示售出数量最少的三本书并设置优惠金额");
			System.out.println("********10.返回主界面");
			System.out.println("****************************");
			System.out.println("=======请选择你要进行的操作=======");
			int in = scan.nextInt();
			switch (in) {
			case 1:
				//1.1添加书籍
				addBook();
				break;
			case 2:
				//1.2删除书籍
				delBook();
				break;
			case 3:
				//1.3修改书籍
				changeBook();
				break;
			case 4:
				//1.4查看所有书籍
				showAllBook();
				break;
			case 5:
				//1.5条件查询书籍
				showByCofiBook();
				break;
			case 6:
				//1.6查看买家备注
				showaRemark();
				break;
			case 7:
				//1.7.查看前三名重要的买家
				showTopThree();
				break;
			case 8:
				//1.8.显示本月每周流水量
				showWeekIncome();
				break;
			case 9:
				//1.9.显示售出数量最少的三本书并设置优惠金额
				showThreeBook();
				break;
			case 10:
				//1.10返回主界面
				System.out.println("返回主界面成功");
				return;
			default:
				System.out.println("操作暂未开发，敬请期待");
				break;
			}			
		}
	}
	
	/**
	 * 1.9.显示售出数量最少的三本书并设置优惠金额
	 */
	private static void showThreeBook() {
		System.out.println("售出数量最少的三本书数据如下：");
		List<ThreeBook> list = co.getThreeBook();
		System.out.println("书籍名\t售出数量\t单价");
		list.forEach(System.out::println);
		System.out.println("是否降价处理？（是1/否任意数字）");
		int in = scan.nextInt();
		switch (in) {
		case 1:
			//设置优惠金额
			for(ThreeBook i:list) {
				System.out.println("设置"+i.getBook_name()+"的优惠金额：");
				int discount = scan.nextInt();
				boolean isloop = true;
				while(isloop) {
					if(discount<=i.getPrice()&&discount>=0) {
						isloop = false;
					}else {
						System.out.println("优惠金额设置不合理");
					}
				}
				co.setPrice(i.getBook_name(),discount);
			}
			System.out.println("设置成功");
			break;

		default:
			break;
		}
	}

	/**
	 * 1.8.显示本年某个月每周流水量
	 */
	private static void showWeekIncome() {
		System.out.println("请输入想要查询的月份：(格式为01、12等)");		
//		String month = "05";
		String month = scan.next();
		System.out.println(month+"月流水数据如下：");
		Map<String,Double> map = co.showWeekIncome(month);
		map.keySet().forEach(i->{
			System.out.print(i+" ");
			double sum = map.get(i);
			while(sum>0) {
				if(sum>10000) {
					System.out.print("$");
					sum-=10000;
				}else if(sum>1000) {
					System.out.print("￥");
					sum-=1000;					
				}else {
					System.out.print("*");
					sum-=100;
				}
			}
			System.out.println(" "+map.get(i)+"元");
		});
	}

	/**
	 * 1.7.查看前三名重要的买家
	 */
	private static void showTopThree() {
		System.out.println("是否要更新排名信息？（1是/0否）");
		int in = scan.nextInt();
		List<TopThree> list = new ArrayList<>();
		if(in==1) {
			co.refershTop();	//更新topthree表
		}
		System.out.println("前三买家信息如下：");
		System.out.println("用户编号\t\t\t\t\t购买总额\t购买次数");
		list = co.getTopThree();
		list.forEach(System.out::println);

	}

	/**
	 * 1.6查看买家备注
	 */
	private static void showaRemark() {
		List<Order> list = co.getOrder();	//查看订单
		System.out.println("买家备注如下：");
		for(Order i : list) {
			if(!i.getRemark().isEmpty()) {
				String user_name = co.getNameById(i.getUser_id());
				System.out.print(i.getDate()+"   "+user_name+"说：");
				System.out.println(i.getRemark());
			}
		}
		System.out.println("---------------------------------------");
	}
	/**
	 * 1.5多条件查询书籍
	 */
	private static void showByCofiBook() {
		boolean isloop = true;
		String book_name = null;
		isloop = true;
		while(isloop) {
			System.out.println("输入要查询的书籍名");
			book_name = scan.nextLine();
			book_name = scan.nextLine();
			if(book_name.isEmpty()||co.judgeBookExist(book_name)) {
				isloop = false;
			}else System.out.println("输入的书籍不存在，重新输入");
		}
		double maxprice = 0.0;
		double minprice = 0.0;
		isloop = true;
		while(isloop) {
			System.out.println("最高单价和最低单价都输入-1表示不查询");
			System.out.println("输入要查询的最高单价");
			maxprice = scan.nextDouble();
			System.out.println("输入要查询的最低单价");
			minprice = scan.nextDouble();
			if((maxprice<=1000&&minprice>0&&maxprice>=minprice)||(minprice==-1&&maxprice==-1)) {
				isloop = false;
			}else System.out.println("输入的单价范围不合理，重新输入");
		}
		int max_num = 0;
		int min_num = 0;
		isloop = true;
		while(isloop) {
			System.out.println("最高单价和最低单价都输入0表示不查询");
			System.out.println("输入要查询的最高库存量");
			max_num = scan.nextInt();
			System.out.println("输入要查询的最低库存量");
			min_num = scan.nextInt();
			if((max_num<=10000&&min_num>=0&&max_num>=min_num)||(max_num==0&&min_num==0)) {
				isloop = false;
			}else System.out.println("输入的库存量范围不合理，重新输入");
		}
		List<Book> list = co.showByCofiBook(book_name,maxprice,minprice,max_num,min_num);
		System.out.println("查询结果如下：");
		System.out.println("书籍编号\t\t\t\t\t书籍名称\t单价\t库存量");
		list.forEach(System.out::println);
	}
	/**
	 * 1.3修改书籍
	 */
	private static void changeBook() {
		showAllBook();
		boolean isloop = true;
		String book_name = null;
		String new_name = null;
		while(isloop) {
			System.out.println("输入要修改的书籍名：");
			book_name = scan.next();
			if(co.judgeBookExist(book_name)) {
				isloop = false;
			}else System.out.println("输入的书籍名不存在，重新输入");
		}
		isloop = true;
		while(isloop) {
			System.out.println("输入新书籍名");
			new_name = scan.nextLine();
			new_name = scan.nextLine();
			if(new_name.isEmpty()||!co.judgeBookExist(new_name)) {
				isloop = false;
			}else System.out.println("输入的书籍已存在，重新输入");
		}
		double price = 0.0;
		isloop = true;
		while(isloop) {
			System.out.println("输入新单价(输入-1表示不修改)");
			price = scan.nextDouble();
			if((price<=1000&&price>0)||price==-1) {
				isloop = false;
			}else System.out.println("输入的单价不合理，重新输入");
		}
		int remain_num = 0;
		isloop = true;
		while(isloop) {
			System.out.println("输入新库存量(输入0表示不修改)");
			remain_num = scan.nextInt();
			if(remain_num<=10000&&remain_num>=0) {
				isloop = false;
			}else System.out.println("输入的库存量超出范围，重新输入");
		}
		Book book = new Book(null, new_name, price, remain_num);
		co.changeBook(book_name,book);
		System.out.println("修改成功");
		
	}
	/**
	 * 1.4查看所有书籍
	 */
	private static void showAllBook() {
		List<Book> list = co.showAllBook();
		System.out.println("书籍编号\t\t\t\t\t书籍名称\t单价\t库存量");
		list.forEach(System.out::println);
	}
	

	/**
	 * 1.2删除书籍
	 */
	private static void delBook() {
		boolean firstloop = true;
		while(firstloop) {
			boolean isloop = true;
			String book_name = null;
			while(isloop) {
				System.out.println("输入要删除的书籍名：");
				book_name = scan.next();
				if(co.judgeBookExist(book_name)) {
					isloop = false;
				}else System.out.println("输入的书籍名不存在，重新输入");
			}
			co.delBook(book_name);
			System.out.println("删除成功");	
			System.out.println("是否继续删除？（是：1/否：0）");
			int in = scan.nextInt();
			if(in==0) {
				firstloop = false;
			}
		}
	}

	/**
	 * 1.1添加书籍
	 */
	private static void addBook() {
		boolean firstloop = true;
		while(firstloop) {
			boolean isloop = true;
			String book_name = null;
			while(isloop) {
				System.out.println("输入要添加的书名：");
				book_name = scan.next();
				if(!co.judgeBookExist(book_name)) {
					isloop = false;
				}else System.out.println("输入的书名已存在，重新输入");
			}
			UUID book_id = UUID.randomUUID();
			double price = 0.0;
			isloop = true;
			while(isloop) {
				System.out.println("输入此书单价");
				price = scan.nextDouble();
				if(price<=1000&&price>0) {
					isloop = false;
				}else System.out.println("输入的单价不合理，重新输入");
			}
			int remain_num = 0;
			isloop = true;
			while(isloop) {
				System.out.println("输入库存量");
				remain_num = scan.nextInt();
				if(remain_num<=10000&&remain_num>0) {
					isloop = false;
				}else System.out.println("输入的库存量超出范围，重新输入");
			}
			Book book = new Book(book_id.toString(), book_name, price, remain_num);
			co.addBook(book);
			System.out.println("添加书籍成功");
			System.out.println("是否继续添加？（是：1/否：0）");
			int in = scan.nextInt();
			if(in==0) {
				firstloop = false;
			}
		}
	}
}
