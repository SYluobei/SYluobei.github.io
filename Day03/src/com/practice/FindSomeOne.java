package com.practice;

public class FindSomeOne {
	public static void main(String[] args) {
		 String str="嬴渠梁，魏鞅，嬴驷，公孙衍，嬴荡，魏冉，嬴政";
		 int y = 0;
		 String[] arr = str.split("，");
		 System.out.println("一共有"+arr.length+"人");
		 System.out.println("第三个人是"+arr[2]);
		 System.out.println("姓魏的人有：");
		 for(String i:arr) {
			 if(i.startsWith("魏")) {
				 System.out.print(i+" ");
			 }
			 if(i.startsWith("嬴")) y++;
		 }
		 System.out.println();
		 System.out.println("姓赢的人有"+y+"个");
	}
}
