package com.daily;

public class CommunityWorker {
	public static void main(String[] args) {
		int[] arr={6,3,1,8,12,18,9,10};
		int sum = 0, max = 0, min = 999;
		for(int i : arr) {
			sum+=i;
			if(i>max) max = i;
			if(i<min) min = i;
		}
		System.out.println("所有成员的总和为："+sum);
		System.out.println("最大成员为："+max);
		System.out.println("最小成员为："+min);
	}
}
