package com.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.bean.Book;
import com.bean.Goods;
import com.bean.Order;
import com.bean.ShoppingCart;
import com.controller.Controller;

public class BuyerView {
	static Scanner scan = new Scanner(System.in);
	static Controller co = new Controller();
	
//	/**
//	 * test
//	 */
//	public static void main(String[] args) {
//		MainPage("张飞");
//	}
	
	/**
	 * 1.首页
	 */
	public static void MainPage(String user_name) {
		while(true) {
			// 1、用户进入买家页面展示所有书籍信息
			showAllBook();
			System.out.println("===========================");
			System.out.println("**************************");
			System.out.println("*****欢迎登陆书籍商城界面*****");
			System.out.println("********1.购买书籍");
			System.out.println("********2.查看购物车");
			System.out.println("********3.查看购买记录");
			System.out.println("********4.查看余额");
			System.out.println("********5.返回主界面");
			System.out.println("**************************");
			System.out.println("======请选择你要进行的操作======");
			int in = scan.nextInt();
			switch (in) {
			case 1:
				//1.1、用户根据书籍名称购买书籍
				buyBook(user_name);
				break;
			case 2:
				//1.2查看购物车
				queryShoppingCart(user_name);
				break;
			case 3:
				//1.3.查看购买记录
				queryBuyLog(user_name);
				break;
			case 4:
				//1.4.查看余额
				System.out.println("余额为 "+co.queryMoney(user_name)+" 元");
				break;
			case 5:
				//1.5返回主界面
				System.out.println("返回主界面成功");
				return;
			default:
				System.out.println("操作暂未开发，敬请期待");
				break;
			}			
		}
	}
	
	/**
	 * 1.3.查看购买记录
	 */
	private static void queryBuyLog(String user_name) {
		System.out.println();
		System.out.println("您的购买记录如下：");
		List<Goods> list = co.getGoodsList(user_name);
		System.out.println("------------------------------------------------------------------");
		System.out.println("订单单项号\t\t\t\t\t用户编号\t\t\t\t\t商品编号\t\t\t\t\t商品名称\t购买数量\t商品单价\t总价");
		list.forEach(System.out::println);
		System.out.println("------------------------------------------------------------------");
		System.out.println();
	}

	/**
	 * 1.2查看购物车
	 */
	private static void queryShoppingCart(String user_name) {
		List<ShoppingCart> list = co.queryShoppingCart(user_name);
		if(!list.isEmpty()) {
			System.out.println("-----------------------------------------------------");
			System.out.println("您的购物车中有下面这些商品：");
			System.out.println("购物车编号\t总价\t购买数量\t书籍名称\t用户编号");
			list.forEach(System.out::println);
			System.out.println("-----------------------------------------------------");
			System.out.println();
			System.out.println("===========================");
			System.out.println("**************************");
			System.out.println("********1.结算");
			System.out.println("********2.清空购物车");
			System.out.println("********3.返回上一个界面");
			System.out.println("**************************");
			System.out.println("======请选择你要进行的操作======");
			int in = scan.nextInt();
			switch (in) {
			case 1:
				//1.1结算
				List<Goods> goodslist = co.getGoodsList(user_name);
				List<String> olist = co.getOrderGoodsList();	//获取OrderGoods中的订单单项号
				List<Goods> glist = new ArrayList<>();
				for(Goods i:goodslist) {
					for(int j = 0;j<olist.size();j++) {
						if(i.getGoods_id().equals(olist.get(j))) {
							break;
						}
						if(j==olist.size()-1) {
							glist.add(i);
						}
					}
				}
				pay(user_name, glist);
				co.cleanCart(user_name);	//删除该用户的购物车
				break;
			case 2:
				//1.2清空购物车
				co.cleanCart(user_name);
				System.out.println("购物车已清空");
				break;
			case 3:
				//1.3返回上一个界面
				System.out.println("返回上一个界面成功");
				return;
			default:
				System.out.println("操作暂未开发，敬请期待");
				break;
			}			
		}else System.out.println("您的购物车为空");
	}

	/**
	 * 2、用户根据书籍名称购买书籍
	 */
	private static void buyBook(String user_name) {
		boolean firstloop = true;
		List<Goods> goodslist = new ArrayList<>();
		while(firstloop) {
			boolean isloop = true;
			String book_name = null;
			String user_id = co.getUserId(user_name);	//根据用户名获取用户id
			//3、判断用户输入的书籍编号是否存在，如果不存在则进行重新购买
			while(isloop) {		
				System.out.println("请输入想要购买的书籍名");
				book_name = scan.next();
				if(co.judgeBookExist(book_name)) {
					isloop = false;
				}else System.out.println("输入的书籍不存在，请重新输入");
			}
			Book book = co.getBook(book_name);
			//4、如果书籍编号存在，提示用户输入购买数量。购买数量不能超过库存量，否则显示库存不足!重新进入购买
			int buy_num = 0;
			isloop = true;
			while(isloop) {
				System.out.println("请输入购买数量");
				buy_num = scan.nextInt();
				if(buy_num>0&&judegeBookNum(buy_num,book_name)) {
					isloop = false;
				}else if(buy_num<0) {
					System.out.println("输入的数量不合理，请重新输入");
				}else {
					System.out.println("抱歉，本书的库存不足，请重新输入一个更小的数量");
					System.out.println("如有购买大量书籍的需求，请联系商家");
				}
			}
			//5、如果书籍编号和数量正确则把该条商品信息保存下来
			double goods_sum = buy_num*book.getPrice();
			UUID goods_id = UUID.randomUUID();
			Goods goods = new Goods(goods_id.toString(),user_id,book.getBook_id(), book_name, buy_num, book.getPrice(), goods_sum);
			co.addGoods(goods);
			goodslist.add(goods);
			//实现多次选购商品(如果用户已经添加选择一本书籍后，还可以购买第二本，第三本等等....)
			System.out.println("是否继续选购？（是：1/否：0）");
			int in = scan.nextInt();
			if(in==0) {
				firstloop = false;
			}
		}
		System.out.println("付账结算请按1");
		System.out.println("加入购物车请按2");
		int in = scan.nextInt();
		if(in==1) {
			pay(user_name, goodslist);	//结算			
		}else if(in==2) {
			putShoopingCart(user_name, goodslist);	//加入购物车
		}
	}

	/**
	 * 加入购物车
	 */
	private static void putShoopingCart(String user_name, List<Goods> goodslist) {
		co.putShoopingCart(user_name, goodslist);
		System.out.println("订单已加入购物车");
	}

	/**
	 * 付账
	 */
	private static void pay(String user_name, List<Goods> goodslist) {
		double sum = 0;
		boolean payisloop = true;
		for(Goods i : goodslist) {
			sum += i.getGoods_sum();
		}
		while(payisloop) {			
			double remain = co.queryMoney(user_name);
			if(sum>remain) {
				System.out.println("余额不足，是否充值？（是：1/否：0）");
				int chose = scan.nextInt();
				switch (chose) {
				case 0:
					System.out.println("欢迎您下次光临");
					return;
				case 1:
					//充值
					recharge(user_name);
					break;						
				default:
					System.out.println("输入有误");
					break;
				}
			}else {
				System.out.println("请填入需要提醒商家的备注：");
				String remark = scan.nextLine();
				remark = scan.nextLine();
				UUID order_id = UUID.randomUUID();
				//9.修改库存，修改用户余额
				tradeDown(user_name,sum,order_id.toString());
				//8.余额充足，录入订单表与订单关系表并打印订单信息
				printTrade(user_name,sum,remark,goodslist,order_id.toString());
				return;
			}				
		}
	}

	/**
	 * 8.余额充足，录入订单表与订单关系表并打印订单信息
	 * @param order_id 
	 */
	private static void printTrade(String user_name, double sum, String remark, List<Goods> goodslist, String order_id) {
		Order order = co.getChangeOrder(user_name,sum,remark,goodslist,order_id);
		System.out.println("请收好您的订单，欢迎下次光临！");
		System.out.println("====================打印订单信息====================");
		System.out.println();
		System.out.println("订单号："+order.getOrder_id());		
		System.out.println();
		System.out.println("----------------------------购买商品详情如下--------------------------");
		List<Goods> list = co.getGoodsListByOrder(order.getOrder_id());
		System.out.println("订单单项号\t\t\t\t\t用户编号\t\t\t\t\t商品编号\t\t\t\t\t商品名称\t购买数量\t商品单价\t总价");
		list.forEach(System.out::println);
		System.out.println("------------------------------------------------------------------");
		System.out.println();
		System.out.println("\t\t\t\t\t订单总价："+order.getSum()+"元");
		System.out.println();
		System.out.println("\t\t\t\t\t日期："+order.getDate());
		System.out.println("------------------------------------------------------------------");
	}

	/**
	 * 9.修改库存，修改用户余额
	 * @param order_id 
	 */
	private static void tradeDown(String user_name, double sum, String order_id) {
		co.tradeDown(user_name, sum,order_id);
	}

	//充值
	private static void recharge(String user_name) {
		boolean isloop = true;
		double money = 0.0;
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
		System.out.println("自动支付中");
	}

	/**
	 *判断库存是否足够
	 */
	private static boolean judegeBookNum(int remain_num,String book_name) {
		return co.judegeBookNum(remain_num,book_name);
	}

	/**
	 * 1、用户进入买家页面展示所有书籍信息
	 */
	private static void showAllBook() {
		List<Book> list = co.showAllBook();
		System.out.println();
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("书籍编号\t\t\t\t\t书籍名称\t单价\t库存量");
		list.forEach(System.out::println);
		System.out.println("-------------------------------------------------------------------------");
		System.out.println();
	}
	

}
