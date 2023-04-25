package com.daily;

public class morningTest {
	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 4, 5, 6, 7};
		String str = "asGHIUIUOsakljoiSsdedHJS";

		System.out.println("数据组中的元素之和为：");
		System.out.println(sumList(arr));
		findUpCase(str);
	}
	
	public static int sumList(int arr[]) {
		int sum = 0;
		for(int i : arr) {
			sum+=i;
		}
		return sum;
	}
	
	public static void findUpCase(String str) {
		System.out.println("字符串中的大写字母有：");
		for(int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if(a<='Z'&&a>='A') {
				System.out.print(a+" ");
			}
		}
	}
}


