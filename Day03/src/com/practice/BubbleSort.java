package com.practice;

import java.util.Scanner;

public class BubbleSort {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] arr = new int[10];
		int a = 0;
		for(int i = 0; i<arr.length; i++) {
			System.out.println("请输入第"+(i+1)+"个数");
			arr[i] = scan.nextInt();
		}
		for(int j = arr.length-1; j>0; j--) {			
			for(int i = 0; i<j; i++) {
				if(arr[i]>arr[i+1]) {
					a = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = a;
				}
			}
		}
		for(int i : arr) {
			System.out.print(i+" ");
		}
	}
}
