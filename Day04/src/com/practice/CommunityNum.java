package com.practice;

public class CommunityNum {
	public static void main(String[] args) {
		int[] arr1 = {-3,12,60,99,24,77,-1};
		int a = 0, b = 0, sum = 0, min = arr1[0];
		int a1 = 0, evensum = 0;
		double alsum = 0, sum10 = 0;
		// 计算其中正数的个数,负数的个数,所有正数的总和.
		for(int i : arr1) {
			if(i>10) {
				sum10+=i;
				a1++;
			}
			if(i>=0) {
				a++;
				sum+=i;
			}else {
				b++;
			}
			if(min>i) {
				i = min;
			}
			if(i%2==0) {
				evensum+=i;
			}
			alsum+=i;
		}
		System.out.println("正数个数为："+a+"个，负数的个数为："+b+"所有正数的总和为："+sum) ;
		// 获得其中最小的成员并输出
		System.out.println("最小的成员为："+min);
		// 计算所有成员的平均值
		System.out.println("所有成员的平均值为："+(alsum/arr1.length));
		// 计算其中>10的成员的平均值
		System.out.println("其中大于10的成员的平均值为："+(sum10/a1));
		//计算所有偶数总和并输出
		System.out.println("所有偶数和为："+evensum);
	}
}
