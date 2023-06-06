package com.bean;

public class ThreeBook {
	//书籍名
	private String book_name;
	//售出数量
	private int sel_num;
	//单价
	private double price;
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public int getSel_num() {
		return sel_num;
	}
	public void setSel_num(int sel_num) {
		this.sel_num = sel_num;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return  book_name + "\t" + sel_num + "\t"+price;
	}
	public ThreeBook(String book_name, int sel_num,double price) {
		super();
		this.book_name = book_name;
		this.sel_num = sel_num;
		this.price = price;
	}
	public ThreeBook() {
		super();
	}
	
}
