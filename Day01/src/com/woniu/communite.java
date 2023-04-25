package com.woniu;
import java.util.Scanner;

public class communite {
	public static void main(String[] args) {
		System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
		System.out.println("*\t*\t*\t控制台计算器:\t*\t*\t*");
		System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
		System.out.println("请输入第一个数字：");
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		System.out.println("请输入第二个数字：");
		int num2 = scan.nextInt();
		System.out.println("请输入第一个字符：");
		String str = scan.next();
		if(str.equals("+")){
			System.out.println(num1+num2);
		}else if(str.equals("-")){
			System.out.println(num1-num2);
		}else if(str.equals("*")){
			System.out.println(num1*num2);
		}else if(str.equals("/")){
			System.out.println(num1/num2);
		}else {
			System.out.println("运算失败");
		}
	}
}
