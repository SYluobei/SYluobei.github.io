package com.woniu;
import java.util.Scanner;

public class month {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一个月份：");
		int a = scan.nextInt();
		if(a==12||a==1||a==2) {
			System.out.println("冬季");
		}else if(a<=5&&a>=3) {
			System.out.println("春季");
		}else if(a>=6&&a<=8) {
			System.out.println("夏季");
		}else if(a>=9&&a<=11) {
			System.out.println("秋季");
		}else System.out.println("输入错误");
	}
}
