package com.daily;

import java.util.Scanner;

public class JudgePrimeNum {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		//手动输入一个正整数，判断是否为素数，判断操作封装为方法
		boolean isloop = true;
		while(isloop) {
			System.out.println("请输入一个数字：");
			int in = scan.nextInt();
			if(in<=1) {
				System.out.println("输入的数字超出范围，请重新输入");
			}else {				
				if(judgePrimeNum(in)) {
					System.out.println(in+"为素数");
				}else {
					System.out.println(in+"不为素数");
				}
			}			
		}
	}
	private static boolean judgePrimeNum(int in) {
		boolean isNum = true;
		for(int j = in-1; j>1; j--) {
			if(in%j==0) {
				isNum = false;
				break;
			}
		}
		return isNum;
	}
}
