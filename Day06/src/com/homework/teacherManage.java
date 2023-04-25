package com.homework;

/**
* 数据转换成对象
*/
class Teacher{ //一个java文件中可以定义多个类，但只能有一个前标为public并且与文件名相同
    //定义类的属性
	String name;
	String pos;
}
public class teacherManage {
    static Teacher arr[]; //定义一个Teacher类型的数组
	public static void main(String[] args) {
		for(Teacher i : arr) {
			System.out.println(i.name+"是"+i.pos); //调用对象的属性							
		}
	}
    static{
        Teacher t1 = new Teacher();  //创建一个Teacher对象
        t1.name = "马老师";          //设置对象的属性
		t1.pos = "金牌讲师";	
        Teacher t2 = new Teacher(); 
		t2.name = "刀老师";
		t2.pos = "银牌讲师";	
        arr = new Teacher[] {t1, t2};
    }
}