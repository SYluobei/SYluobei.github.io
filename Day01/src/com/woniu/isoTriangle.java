package com.woniu;

public class isoTriangle {
	public static void main(String[] args) {
		int a = 10;
		for(int i = 1; i<a; i+=2) {
			for(int j = (a-i)/2; j>=1; j--) {
				System.out.print("    ");
			}
			for(int b = i; b>0; b--) System.out.print("*   ");
			System.out.println("");
		}
	}
}
