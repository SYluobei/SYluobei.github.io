package com.daily;

public class CommunityStriing {

	public static void main(String[] args) {
		String str="jAsdfK12HJ75";
		//一共由多少字符组成?
		//其中数字有几个?大写字母有几个?小写字母有几个?		
		char num[] = new char[str.length()];
		str.getChars(0, str.length(), num, 0);
		int nums = 0, upcase = 0, lowcase = 0;
		System.out.println("一共由"+str.length()+"个字符组成");
		for(int i = 0; i<num.length; i++) {
			if(num[i]<='Z'&&num[i]>='A') {
				upcase++;
			}else if(num[i]<='z'&&num[i]>='a') {
				lowcase++;
			}else if(num[i]<='9'&&num[i]>=0) {
				nums++;
			}
		}
		System.out.println("大写字母有"+upcase+"个");
		System.out.println("小写字母有"+lowcase+"个");
		System.out.println("数字有"+nums+"个");
	}

}
