package com.daily;

public class FindSurname {
	public static void main(String[] args) {
		String[] arr={"T-唐婉","W-王倩","S-苏小小","L-李强","T-唐果","T-唐欣"};
		//挑出姓氏首字母为T的人员
		System.out.println("姓氏首字母为T的人有：");
		for(String i : arr) {
			String arr2[] = i.split("-");
			if(arr2[0].equals("T")) {
				System.out.print(arr2[1]+" ");
			}
		}
		//挑出姓氏首字母在T-Z之间的人员
		System.out.println();
		System.out.println("姓氏首字母在T-Z的人有：");
		for(String i : arr) {
			String arr2[] = i.split("-");
			char a = arr2[0].charAt(0);
			if(a<='Z'&&a>='T') {
				System.out.print(arr2[1]+" ");
			}
		}
	}
}
