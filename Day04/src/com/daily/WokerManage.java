package com.daily;

import java.util.Arrays;
import java.util.Scanner;

public class WokerManage {
	static String[] arr = { "100-司马懿-男-11000", "101-貂蝉-女-18000", "102-孙权-男-12000", 
			"103-黄忠-男-20000", "104-孙尚香-女-13000", "105-诸葛亮-男-15000","106-西门吹雪-男-16000"};
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		boolean isloop = true;
		while(isloop) {			
			//主菜单显示
			mainList(); 
			int in = scan.nextInt();
			if(in == 1) {				
				//按1.查询完整名单
				findList(); 
			}else if(in == 2) {				
				//按2.新增人员
				addMan(); 
			}else if(in == 3) {				
				//按3.输入下标和姓名替换该下标对应的元素
				inIndex(); 
			}else if(in == 4) {				
				//按4.根据下标删除元素				
				delIndex(); 
			}else if(in == 5) {
				//按5.输入关键字进行模糊匹配
				keyMatch();
			}else if(in == 6) {
				//按6.找出工资15000-20000之间的人
				findSal();			
			}else if(in ==7) {
				//按7.找出工资最高和最低的人
				findMaxMin();				
			}else if(in ==8) {
				//按8.找人性别为女的所有人员；
				findWoman();				
			}else {
				System.out.println("此功能尚未开放！");
				System.out.println("请重新输入！");
				System.out.println();
			}
			
		}
	}
	public static void mainList() {
		//主菜单显示
		System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
		System.out.println("*\t*\t*\t欢迎来到员工管理系统！\t*\t*");
		System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
		System.out.println("按1.查询完整名单");
		System.out.println("按2.新增人员");
		System.out.println("按3.输入下标和姓名替换该下标对应的元素");
		System.out.println("按4.根据下标删除元素");
		System.out.println("按5.输入关键字进行模糊匹配");
		System.out.println("按6.找出工资15000-20000之间的人");
		System.out.println("按7.找出工资最高和最低的人");
		System.out.println("按8.找人性别为女的所有人员；");
		System.out.println("请输入想使用功能所对应的数字；");
	}
	public static void findList() {
		//按1.查询完整名单
		System.out.println("工号\t姓名\t性别\t工资");
		for(String i : arr) {
			String arr2[] = i.split("-");
			System.out.println(arr2[0]+"\t"+arr2[1]+"\t"+arr2[2]+"\t"+arr2[3]);
			System.out.println();
		}		
	}
	public static void addMan() {
		Scanner scan = new Scanner(System.in);
		//按2.新增人员
		boolean a2 = true;
		String num2 = "";
		System.out.println("请输入您想添加的人员及其信息");
		while(a2==true) {
			System.out.println("请输入工号：");
			num2 = scan.next();
			for(String i : arr) {
				String arr2[] = i.split("-");
				if(arr2[0].equals(num2)) {
					a2 = true;
					System.out.println("您输入的工号已重复，请重新输入");
					break;
				}else a2 = false;
			}
		}
		System.out.println("请输入姓名：");
		String name2 = scan.next();
		System.out.println("请输入性别：");
		String sex2 = scan.next();
		System.out.println("请输入工资：");
		String sal2 = scan.next();
		arr = Arrays.copyOf(arr, arr.length+1);
		arr[arr.length-1] = num2+"-"+name2+"-"+sex2+"-"+sal2;		
	}
	public static void inIndex() {
		//按3.输入下标和姓名替换该下标对应的元素
		Scanner scan = new Scanner(System.in);
		boolean a3 = true;
		int index3 = 0;
		int j3 = 0;//记录下标
		while(a3==true) {
			System.out.println("请输入要替换元素的下标:");
			index3 = scan.nextInt();
			if(index3<0||index3>=arr.length) {
				System.out.println("您所输入的下标不合法，请重新输入");
			}else a3=false;
		}
		System.out.println("请输入要替换元素的姓名：");
		String name3 = scan.next();
		for(String i : arr) {
			String arr2[] = i.split("-");
			if(j3 == index3) {
				arr2[1] = name3;
				System.out.println("您所替换的姓名为："+arr2[1]);
				arr[j3] = arr2[0]+"-"+arr2[1]+"-"+arr2[2]+"-"+arr2[3];
			}
			j3++;
		}		
	}
	public static void delIndex() {
		//按4.根据下标删除元素
		boolean a4 = true;
		int index4 = 0;
		int j4 = 0;//记录下标
		while(a4==true) {
			System.out.println("请输入要删除元素的下标:");
			index4 = scan.nextInt();
			if(index4<0||index4>=arr.length) {
				System.out.println("您所输入的下标不合法，请重新输入");
			}else a4=false;
		}
		for(String i : arr) {
			String arr2[] = i.split("-");
			if(j4 == index4) {
				System.out.println("您所删除的姓名为："+arr2[1]);
				System.arraycopy(arr, j4+1, arr, j4, arr.length-1-j4);
				arr = Arrays.copyOf(arr, arr.length-1);
			}
			j4++;
		}		
	}
	public static void keyMatch() {
		//按5.输入关键字进行模糊匹配
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入想要匹配的关键字：");
		String key5 = scan.next();
		System.out.println("您输入的关键字匹配的数据有：");
		System.out.println("工号\t姓名\t性别\t工资");
		for(int i = 0; i<arr.length; i++) {
			if(arr[i].contains(key5)) {
				String arr2[] = arr[i].split("-");
				System.out.println(arr2[0]+"\t"+arr2[1]+"\t"+arr2[2]+"\t"+arr2[3]);
			}				
		}		
	}
	public static void findSal() {
		//按6.找出工资15000-20000之间的人
		System.out.println("工资在15000-20000之间的人有：");
		System.out.println("工号\t姓名\t性别\t工资");
		for(String i : arr) {
			String arr2[] = i.split("-");
			int a6 = Integer.parseInt(arr2[3]);
			if(a6>=15000&&a6<=20000) {
				System.out.println(arr2[0]+"\t"+arr2[1]+"\t"+arr2[2]+"\t"+arr2[3]);
				System.out.println();
			}
		}		
	}
	public static void findMaxMin() {
		//按7.找出工资最高和最低的人
		int maxsal7 = 0, minsal7 = 99999;
		String maxname = "", minname = "";
		for(String i : arr) {
			String arr2[] = i.split("-");
			int a6 = Integer.parseInt(arr2[3]);
			if(maxsal7<a6) {
				maxsal7 = a6;
				maxname = arr2[1];
			}
			if(minsal7>a6) {
				minsal7 = a6;
				minname = arr2[1];
			}
		}
		System.out.println("工资最高的人是："+maxname+"，他的工资为："+maxsal7);
		System.out.println("工资最低的人是："+minname+"，他的工资为："+minsal7);		
	}
	public static void findWoman() {
		//按8.找人性别为女的所有人员；
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入您想找的女生的姓名：");
		String name8 = scan.next();
		boolean a8 = true;
		for(String i : arr) {
			String arr2[] = i.split("-");
			if(name8.equals(arr2[1])&&arr2[2].equals("女")) {
				System.out.println("您所找的女生信息如下：");
				System.out.println("工号\t姓名\t性别\t工资");
				System.out.println(arr2[0]+"\t"+arr2[1]+"\t"+arr2[2]+"\t"+arr2[3]);
				System.out.println();
				a8 = false;
			}
		}
		if(a8==true) {
			System.out.println("对不起，找不到您所输入的信息");
			System.out.println();
		}
		
	}
}
