package com.homework;

import java.util.Arrays;
import java.util.Scanner;

public class carManage {
	static String[] hotels= {"20231122-奔驰-GLC-400000-中型-SUV","20231123-比亚迪-护卫舰07-202800-中型-SUV",
			"20231124-比亚迪-汉-197800-中型-轿车","20231125-特斯拉-Model Y-261900-中型-SUV","20231126-比亚迪-海豚-116800-小型-轿车"};
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		boolean isloop = true;
		while(isloop) {
			mainList(); //显示主菜单
			int in = scan.nextInt();
			switch (in) {
			case 1:
//		1、查看所有车辆信息。
				System.out.println("所有的车辆信息如下：");
				checkAllCar();				
				break;
			case 2:
//		2、新增一条车辆信息。(要求:车辆编号和款式不能重复)
				addCar();				
				break;
			case 3:
//		3、根据编号删除指定的车辆。
				delByNum();				
				break;
			case 4:
//		4、查询所有的"中型"汽车信息。
				String type = "中型";
				System.out.println(type+"汽车信息如下：");
				checkAllMiddleCar(type);				
				break;
			case 5:
//		5、计算出是"SUV"结构的汽车的平均价格。
				String struct = "SUV";
				calculateAveragePrice(struct);				
				break;
			case 6:
//		6、找出"比亚迪"品牌中价格最高的车辆信息。
				String brand = "比亚迪";
				findMaxPrice(brand);				
				break;
			case 7:
//		7、有哪些汽车售价超过"20万"。
				findOverTw();
				break;
			case 8:
//		8、根据编号查询车辆信息。
				checkByNum();				
				break;
			case 9:
//		9、上述数据中车辆有几种品牌，不同的品牌的车辆分别有几辆？
				checkBrand();				
				break;
			case 0:
//		0、退出系统
				isloop = out(isloop);
				System.out.println("退出成功！");
				break;
			default:
				System.out.println("您输入的 "+in+"号指令超出范围，请重新输入！");
				break;
			}
			
		}
	}
	/**
	 * 显示主菜单
	 */
	public static void mainList() {
		System.out.println("*\t*\t*\t*\t*\t*\t*");
		System.out.println("*\t*\t欢迎来到车辆信息系统！\t*\t*");
		System.out.println("*\t*\t*\t*\t*\t*\t*");
		System.out.println("按1、查看所有车辆信息。");
		System.out.println("按2、新增一条车辆信息。(要求:车辆编号和款式不能重复)");
		System.out.println("按3、根据编号删除指定的车辆。");
		System.out.println("按4、查询所有的\"中型\"汽车信息。");
		System.out.println("按5、计算出是\"SUV\"结构的汽车的平均价格。");
		System.out.println("按6、找出\"比亚迪\"品牌中价格最高的车辆信息。");
		System.out.println("按7、查询有哪些汽车售价超过\"20万\"。");
		System.out.println("按8、根据编号查询车辆信息。");
		System.out.println("按9、显示上述数据中车辆有几种品牌，不同的品牌的车辆分别有几辆？");
		System.out.println("按0、退出系统");
		System.out.println("请输入操作指令：");
	}
	/**
	 * 0、退出系统
	 */
	public static boolean out(boolean isloop) {
		isloop = false;
		return isloop;
	}
	
	/**
	 * 1、查看所有车辆信息。
	 */
	public static void checkAllCar() {
		System.out.println("汽车编号\t\t品牌\t款式\t售价\t类型\t车身结构");
		for(String i : hotels) {
			String arr[] = i.split("-");
			System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]+"\t"+arr[5]);
		}
	}
	
	/**
	 * 2、新增一条车辆信息。(要求:车辆编号和款式不能重复)
	 */
	public static void addCar() {
		boolean numRepeat = true;
		boolean shapeRepeat = true;
		boolean priceLower = true;
		String num = "";
		while(numRepeat) {
			System.out.println("请输入汽车编号：");
			num = scan.next();
			numRepeat = judgeNumRepeat(num, 0);
			if(numRepeat==true) {
				System.out.println("您输入的车辆编号重复，请重新输入！");
			}
		}		
		System.out.println("请输入品牌：");
		String brand = scan.next();
		String shape = "";
		while(shapeRepeat) {
			System.out.println("请输入款式：");
			shape = scan.next();
			shapeRepeat = judgeShapeRepeat(shape);
			if(shapeRepeat==true) {
				System.out.println("您输入的款式重复，请重新输入！");
			}
		}		
		String price = "";
		while(priceLower) {
			System.out.println("请输入售价：");
			price = scan.next();
			int judgePrice = Integer.parseInt(price);
			if(judgePrice<0) {
				System.out.println("您输入的售价不合法，请重新输入！");
			}else priceLower = false;
		}
		System.out.println("请输入类型：");
		String type = scan.next();
		System.out.println("请输入车身结构：");
		String struct = scan.next();
		hotels = Arrays.copyOf(hotels, hotels.length+1);
		hotels[hotels.length-1] = num+"-"+brand+"-"+shape+"-"+price+"-"+type+"-"+struct;
		System.out.println("信息添加成功！");
	}
	
	/**
	 * 3、根据编号删除指定的车辆。
	 */
	public static void delByNum() {
		boolean numRepeat = false;
		int index = 0;
		String num = "";
		while(!numRepeat) {
			System.out.println("请输入汽车编号：");
			num = scan.next();
			numRepeat = judgeNumRepeat(num, index);
			if(numRepeat==false) {
				System.out.println("您输入的车辆编号不存在，请重新输入！");	
			}		
		}
		System.arraycopy(hotels, index+1, hotels, index, hotels.length-1-index);
		hotels = Arrays.copyOf(hotels, hotels.length-1);
		System.out.println("编号："+num+"的车辆信息删除成功！");
	}
	
	/**
	 * 4、查询所有的"中型"汽车信息。
	 */
	public static void checkAllMiddleCar(String type) {
		System.out.println("汽车编号\t\t品牌\t款式\t售价\t类型\t车身结构");
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[4].equals(type)) {
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]+"\t"+arr[5]);
			}
		}
	}
	
	/**
	 * 5、计算出是"SUV"结构的汽车的平均价格。
	 */
	public static void calculateAveragePrice(String struct) {
		Double price = 0.0, sum = 0.0;
		int nums = 0;
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[5].equals(struct)) {
				price = Double.parseDouble(arr[3]);
				sum+=price;
				nums++;
			}
		}
		System.out.println(struct+"结构的汽车平均价格为："+(sum/nums));
	}
	
	/**
	 * 6、找出"比亚迪"品牌中价格最高的车辆信息。
	 */
	public static void findMaxPrice(String brand) {
		Double price = 0.0, maxPrice = 0.0;
		int nums = 0, index1 = 0;
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[1].equals(brand)) {
				price = Double.parseDouble(arr[3]);
				if(price>maxPrice) {
					maxPrice = price;
					nums = index1;
				}
			}
			index1++;
		}	
		System.out.println(brand+"品牌中价格最高的车辆信息如下：");
		System.out.println("汽车编号\t\t品牌\t款式\t售价\t类型\t车身结构");
		outByIndex(nums);
	}
	
	/**
	 * 7、有哪些汽车售价超过"20万"。
	 */
	public static void findOverTw() {
		int price = 0;
		System.out.println("售价大于20万的汽车有：");
		for(String i : hotels) {
			String arr[] = i.split("-");
			price = Integer.parseInt(arr[3]);
			if(price>200000) {
				System.out.print("["+arr[1]+" "+arr[2]+"] ");
			}
		}
		System.out.println();
	}
	
	/**
	 * 8、根据编号查询车辆信息。
	 */
	public static void checkByNum() {
		boolean numRepeat = false;
		int index = 0;
		String num = "";
		while(!numRepeat) {
			System.out.println("请输入汽车编号：");
			num = scan.next();
			numRepeat = judgeNumRepeat(num, index);
			if(numRepeat==false) {
				System.out.println("您输入的车辆编号不存在，请重新输入！");	
			}		
		}
		System.out.println("编号为 "+num+" 的车辆信息如下：");
		System.out.println("汽车编号\t\t品牌\t款式\t售价\t类型\t车身结构");
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(num.equals(arr[0])) {
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]+"\t"+arr[5]);
			}
		}
		System.out.println("编号："+num+"的车辆信息删除成功！");
	}
	
	/**
	 * 9、上述数据中车辆有几种品牌，不同的品牌的车辆分别有几辆？
	 */
	public static void checkBrand() {
		int numBrand[] = {};
		String brand[] = {};
		for(String i : hotels) {
			boolean find = false; 
			String arr[] = i.split("-");
			for(int j = 0; j<brand.length; j++) {
				if(arr[1].equals(brand[j])) {
					numBrand[j]++;
					find = true;
				}
			}
			if(!find) {
				brand = Arrays.copyOf(brand, brand.length+1);
				brand[brand.length-1] = arr[1];
				numBrand = Arrays.copyOf(numBrand, numBrand.length+1);				
				numBrand[numBrand.length-1] = 1;
			}
		}
		System.out.println("数据库中车辆有"+brand.length+"种品牌");
		for(int i = 0; i< brand.length; i++) {
			System.out.println(brand[i]+"的车辆有"+numBrand[i]+"辆");
		}
	}
	/**
	 * 10、判断编号是否重复
	 */
	public static boolean judgeNumRepeat(String num, int index) {
		boolean numRepeat = false;
		int allIndex = 0;
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[0].equals(num)) {
				numRepeat = true;
				index = allIndex;
				break;
			}
			allIndex++;
		}
//		System.out.println(numRepeat); //测试
		return numRepeat;
		
	}
	/**
	 * 11、判断款式是否重复
	 */
	public static boolean judgeShapeRepeat(String shape) {
		boolean shapeRepeat = false;
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[2].equals(shape)) {
				shapeRepeat = true;
			}
		}
		return shapeRepeat;
	}
	
	/**
	 * 12、根据下标输出车辆信息
	 */
	public static void outByIndex(int nums) {
		int index = 0;
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(nums==index) {
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]+"\t"+arr[5]);
				break;
			}
			index++;
		}
	}
}
