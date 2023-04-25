package com.practice;

public class CommunityFruit {
	public static void main(String[] args) {
		String[] arr2 = { "猕猴桃", "香蕉", "榴莲", "新疆青提","八月炸","砂糖橘","苹果"};
		// 获得2字名的水果,并输出.
		System.out.println("两字名的水果有：");
		for(String a : arr2) {
			if(a.length()==2) {
				System.out.print(a+" ");
			}
		}
		// 3字名的水果有多少个?
		int b = 0;
		for(String a : arr2) {
			if(a.length()==3) {
				b++;
			}
		}
		System.out.println();
		System.out.println("三字名的水果有"+b+"个");
	}
}
