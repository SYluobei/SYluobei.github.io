package com.exam;

import java.util.Arrays;
import java.util.Scanner;

public class MessageManage {
	static String[] hotels= {"20230111-李朝宗-男-15067682233-30899","20230112-杨星阑-男-15067682255-26000",
			"20230113-唐碗儿-女-15067682266-18899","20230114-杨婉柔-女-15367682277-60000"};
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		boolean isLoop = true;
		while(isLoop) {
			mainList();
			int in = scan.nextInt();
			if(in == 1) {
//				1、查看所有账户信息(10分)
				checkAll();
			}else if(in == 2) {
//				2、新增一条账户信息(10分)
				addMessage();
				System.out.println("新信息已成功录入！");
			}else if(in == 3) {
//				3、手动输入下标，根据下标修改账户姓名(10分)
				fixName();
			}else if(in == 4) {
//				4、输入一个下标，删除该下标的账户信息(10分)
				delMessage();							
			}else if(in == 5) {
//				5、查询指定编号的账户信息(10分)
				String id = checkId2();	//检查输入的id是否存在;		
				checkById(id);
			}else if(in == 6) {
//				6、查询所有性别是"女"的账户信息(10分)
				String sex ="女";
				checkWoman(sex);
			}else if(in == 7) {
//				7、找出所有姓"杨"的账户信息(10分)
				String firstName = "杨";
				checkYang(firstName);														
			}else if(in == 8) {
//				8、找出余额最高的那个账户信息(10分)
				checkById(checkMaxRemain());	//checkMaxRemain输出最大余额的账号ID，checkById根据id输出账号信息			
			}else if(in == 9) {
//				9、找出电话号码以"150"开头的账户信息(10分)
				checkPhoneNumber();				
			}else if(in == 10) {
//				10、统计处不同性别的人数，并且显示出这个性别的所有账户信息(10分)
				communitySex();
			}else if(in == 11) {
//				11、退出
				isLoop = false;
			}else System.out.println("输入的操作暂未开放，请重新输入！");												
		}
	}
	/**
	 * 0.主菜单显示
	 */
	public static void mainList() {
		System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
		System.out.println("*\t*\t*\t欢迎来到账户信息系统！\t*\t*");
		System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
		System.out.println("按1.查看所有账户信息");
		System.out.println("按2.新增一条账户信息");
		System.out.println("按3.手动输入下标，根据下标修改账户姓名");
		System.out.println("按4.输入一个下标，删除该下标的账户信息");
		System.out.println("按5.查询指定编号的账户信息");
		System.out.println("按6.查询所有性别是\"女\"的账户信息");
		System.out.println("按7.找出所有姓\"杨\"的账户信息");
		System.out.println("按8.找出余额最高的那个账户信息");
		System.out.println("按9.找出电话号码以\"150\"开头的账户信息");
		System.out.println("按10.统计处不同性别的人数，并且显示出这个性别的所有账户信息");
		System.out.println("按11.退出");
		System.out.println("请输入想使用功能所对应的数字；");
	}
	
	/**
	 * 1.查看所有账户信息
	 */
	public static void checkAll() {
		System.out.println("账号ID\t\t用户名\t性别\t联系电话\t\t余额");
		for(String i : hotels) {
			String arr[] = i.split("-");
			System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]);
		}
	}
	
	/**
	 * 2.新增一条账户信息
	 */
	public static void addMessage() {
		boolean isRepeat = true;
		System.out.println("请输入您想添加的人员及其信息");
		String id = checkId();		
		System.out.println("请输入用户名：");
		String name = scan.next();
		System.out.println("请输入性别：");
		String sex = scan.next();
		System.out.println("请输入联系电话：");
		String number = scan.next();
		System.out.println("请输入余额：");
		String remain = scan.next();
		hotels = Arrays.copyOf(hotels, hotels.length+1);
		hotels[hotels.length-1] = id+"-"+name+"-"+sex+"-"+number+"-"+remain;		
	}
	
	/**
	 * 3.手动输入下标，根据下标修改账户姓名
	 */
	public static void fixName() {
		int index = inIndex();
		System.out.println("请输入一个名字：");
		String name = scan.next();
		for(String i : hotels) {
			String arr[] = i.split("-");
			arr[1] = name;
			hotels[index] = arr[0]+"-"+arr[1]+"-"+arr[2]+"-"+arr[3]+"-"+arr[4];
		}
		System.out.println("下标 "+index+" 处的账户姓名修改成功！");	
	}
	
	/**
	 * 4.输入一个下标，删除该下标的账户信息
	 */
	public static void delMessage() {
		int index = inIndex();
		System.arraycopy(hotels, index+1, hotels, index, hotels.length-1-index);
		hotels = Arrays.copyOf(hotels, hotels.length-1);
		System.out.println("下标 "+index+" 处的账户信息删除成功！");	
	}
	
	/**
	 * 5.查询指定编号的账户信息
	 */
	public static void checkById(String id){
		System.out.println("账号ID\t\t用户名\t性别\t联系电话\t\t余额");
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(id.equals(arr[0])){
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]);
			}
		}
	}
	
	/**
	 * 6.查询所有性别是"女"的账户信息
	 */
	public static void checkWoman(String sex) {
		System.out.println("所有性别为"+sex+"的信息如下：");
		System.out.println("账号ID\t\t用户名\t性别\t联系电话\t\t余额");
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(sex.equals(arr[2])){
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]);
			}
		}
	}
	
	/**
	 * 7.找出所有姓"杨"的账户信息(
	 */
	public static void checkYang(String firstName) {
		System.out.println("所有姓"+firstName+"的信息如下：");
		System.out.println("账号ID\t\t用户名\t性别\t联系电话\t\t余额");
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[1].startsWith(firstName)){
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]);
			}
		}
	}
	
	/**
	 * 8.找出余额最高的那个账户信息
	 */
	public static String checkMaxRemain() {
		int maxremain = 0;
		String id = "";
		System.out.println("余额最高的账户信息如下：");
		for(String i : hotels) {
			String arr[] = i.split("-");
			int remain = Integer.parseInt(arr[4]);
			if(remain>maxremain){
				maxremain = remain;
				id = arr[0];
			}
		}
		return id;
	}
	
	/**
	 * 9.找出电话号码以"150"开头的账户信息
	 */
	public static void checkPhoneNumber() {
		System.out.println("电话号码以\"150\"开头的账户信息如下：");
		System.out.println("账号ID\t\t用户名\t性别\t联系电话\t\t余额");
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[3].startsWith("150")){
				System.out.println(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]);
			}
		}
	}
	
	/**
	 * 10.统计处不同性别的人数，并且显示出这个性别的所有账户信息
	 */
	public static void communitySex() {

		int man = 0, woman = 0;
		for(String i : hotels) {
			String arr[] = i.split("-");
			if(arr[2].equals("男")){
				man++;
			}
			if(arr[2].equals("女")){
				woman++;
			}
			
		}
		System.out.println("男性人数为："+man);
		checkWoman("男");
		System.out.println("女性人数为："+woman);
		checkWoman("女");
	}
	
	
	/**
	 * 11.输入一个下标,并判断是否合法
	 */
	public static int inIndex() {
		boolean isIllegal = true;
		int index = 0;
		while(isIllegal) {
			System.out.println("请输入一个下标：");
			index = scan.nextInt();
			if(index<0||index>=hotels.length) {
				System.out.println("您输入的下标超出范围，请重新输入！");
			}else isIllegal = false;			
		}
		return index;
	}
	/**
	 * 12.输入一个编号，并查看是否重复
	 */
	public static String checkId() {
		boolean isRepeat = true;
		String id = "";
		while(isRepeat) {
			System.out.println("请输入账号ID：");
			id = scan.next();
			for(String i : hotels) {
				String arr2[] = i.split("-");
				if(arr2[0].equals(id)) {
					isRepeat = true;
					System.out.println("您输入的账号ID已重复，请重新输入");
					break;
				}else isRepeat = false;
			}
		}
		return id;
	}
	/**
	 * 13.输入一个编号，并查看是否存在
	 */
	public static String checkId2() {
		boolean isNotExist = true;
		String id = "";
		int notExist = 0;
		while(isNotExist) {
			System.out.println("请输入账号ID：");
			id = scan.next();
			for(String i : hotels) {
				String arr2[] = i.split("-");
				if(arr2[0].equals(id)) {
					isNotExist = false;
					break;
				}else {
					notExist++;
				}
				if(notExist==hotels.length) {
					notExist = 0;
					System.out.println("您输入的账号ID不存在，请重新输入");					
				}
			}
		}
		return id;
	}
}
