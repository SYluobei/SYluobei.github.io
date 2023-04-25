package com.woniu;
import java.util.Scanner;

public class findMax {
	public static void main(String[] args) {
		int i = 0, sum = 0;
		System.out.println("请输入一个含有四个数字的数组：");
 		Scanner scan = new Scanner(System.in);
 		String str = scan.next().toString();
		String[] arr = str.split(",");
		int a[] = new int [arr.length];
		for( i = 0; i<arr.length; i++) {
			if(a[i]%2 == 0) {
				sum += a[i];
			}
		}
		System.out.println("给出的数组中偶数和为"+sum);
	}
}
