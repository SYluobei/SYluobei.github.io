package com.homework;

import java.util.Arrays;
import java.util.Scanner;

public class carTest {
	static Car cars[];
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
		System.out.println("汽车编号\t\t品牌\t款式\t售价\t\t类型\t车身结构");
		for(Car i : cars) {
			System.out.print(i.getCid()+"\t");
			System.out.print(i.getBrand()+"\t");
			System.out.print(i.getStyle()+"\t");
			System.out.print(i.getMoney()+"\t");
			System.out.print(i.getType()+"\t");
			System.out.println(i.getStructure());
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
		double price  = 0;
		while(priceLower) {
			System.out.println("请输入售价：");
			price = scan.nextDouble();
			double judgePrice = price;
			if(judgePrice<0) {
				System.out.println("您输入的售价不合法，请重新输入！");
			}else priceLower = false;
		}
		System.out.println("请输入类型：");
		String type = scan.next();
		System.out.println("请输入车身结构：");
		String struct = scan.next();
		Car car = new Car(num, brand, shape, price, type, struct);
//		car.setCid(num);
//		car.setBrand(brand);
//		car.setStyle(shape);
//		car.setMoney(price);
//		car.setType(type);
//		car.setStructure(struct);
		
		cars = Arrays.copyOf(cars, cars.length+1);
		cars[cars.length-1] = car;
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
		System.arraycopy(cars, index+1, cars, index, cars.length-1-index);
		cars = Arrays.copyOf(cars, cars.length-1);
		System.out.println("编号："+num+"的车辆信息删除成功！");
	}
	
	/**
	 * 4、查询所有的"中型"汽车信息。
	 */
	public static void checkAllMiddleCar(String type) {
		System.out.println("汽车编号\t\t品牌\t款式\t售价\t类型\t车身结构");
		for(Car i : cars) {
			if(i.getType().equals(type)) {
				System.out.print(i.getCid()+"\t");
				System.out.print(i.getBrand()+"\t");
				System.out.print(i.getStyle()+"\t");
				System.out.print(i.getMoney()+"\t");
				System.out.print(i.getType()+"\t");
				System.out.println(i.getStructure());
			}
		}
	}
	
	/**
	 * 5、计算出是"SUV"结构的汽车的平均价格。
	 */
	public static void calculateAveragePrice(String struct) {
		Double price = 0.0, sum = 0.0;
		int nums = 0;
		for(Car i : cars) {
			if(i.getStructure().equals(struct)) {
				price = i.getMoney();
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
		for(Car i : cars) {
			if(i.getBrand().equals(brand)) {
				price = i.getMoney();
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
		double price = 0;
		System.out.println("售价大于20万的汽车有：");
		for(Car i : cars) {
			price = i.getMoney();
			if(price>200000) {
				System.out.print("["+i.getBrand()+" "+i.getStyle()+"] ");
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
		for(Car i : cars) {
			if(num.equals(i.getCid())) {
				System.out.print(i.getCid()+"\t");
				System.out.print(i.getBrand()+"\t");
				System.out.print(i.getStyle()+"\t");
				System.out.print(i.getMoney()+"\t");
				System.out.print(i.getType()+"\t");
				System.out.println(i.getStructure());
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
		for(Car i : cars) {
			boolean find = false; 
			for(int j = 0; j<brand.length; j++) {
				if(i.getBrand().equals(brand[j])) {
					numBrand[j]++;
					find = true;
				}
			}
			if(!find) {
				brand = Arrays.copyOf(brand, brand.length+1);
				brand[brand.length-1] = i.getBrand();
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
		for(Car i : cars) {
			if(i.getCid().equals(num)) {
				numRepeat = true;
				index = allIndex;
				break;
			}
			allIndex++;
		}
		return numRepeat;		
	}
	/**
	 * 11、判断款式是否重复
	 */
	public static boolean judgeShapeRepeat(String shape) {
		boolean shapeRepeat = false;
		for(Car i : cars) {
			if(i.getStyle().equals(shape)) {
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
		for(Car i : cars) {
			if(nums==index) {
				System.out.print(i.getCid()+"\t");
				System.out.print(i.getBrand()+"\t");
				System.out.print(i.getStyle()+"\t");
				System.out.print(i.getMoney()+"\t");
				System.out.print(i.getType()+"\t");
				System.out.println(i.getStructure());
				break;
			}
			index++;
		}
	}
	static {
		Car c1 = new Car("20231122", "奔驰", "GLC", 400000, "中型", "SUV");
//		c1.setCid();
//		c1.setBrand("奔驰");
//		c1.setStyle();
//		c1.setMoney(400000);
//		c1.setType();
//		c1.setStructure("SUV");
		Car c2 = new Car("20231123", "比亚迪", "护卫舰07", 202800, "中型", "SUV");
//		c2.setCid("20231123");
//		c2.setBrand("比亚迪");
//		c2.setStyle("护卫舰07");
//		c2.setMoney(202800);
//		c2.setType("中型");
//		c2.setStructure("SUV");
		Car c3 = new Car("20231124","比亚迪", "汉",197800, "中型", "轿车");
//		c3.setCid("20231124");
//		c3.setBrand("比亚迪");
//		c3.setStyle("汉");
//		c3.setMoney(197800);
//		c3.setType("中型");
//		c3.setStructure("轿车");
		Car c4 = new Car("20231125","特斯拉", "MetalY",412211, "中型", "SUV");
//		c4.setCid("20231125");
//		c4.setBrand("特斯拉");
//		c4.setStyle("MetalY");
//		c4.setMoney(412211);
//		c4.setType("中型");
//		c4.setStructure("SUV");
		Car c5 = new Car("20231126","比亚迪", "海豚",116800, "小型", "轿车");
//		c5.setCid("20231126");
//		c5.setBrand("比亚迪");
//		c5.setStyle("海豚");
//		c5.setMoney(116800);
//		c5.setType("小型");
//		c5.setStructure("轿车");
		cars = new Car[] {c1, c2, c3, c4, c5};
	}
}
