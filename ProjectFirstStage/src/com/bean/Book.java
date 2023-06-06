package com.bean;

public class Book {
	//书籍编号
	private String book_id;
	//书籍名称
	private String book_name;
	//单价
	private double price;
	//库存量
	private int remain_num;
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getRemain_num() {
		return remain_num;
	}
	public void setRemain_num(int remain_num) {
		this.remain_num = remain_num;
	}
	@Override
	public String toString() {
		return book_id + "\t" + book_name + "\t" + price + "\t"+ remain_num;
	}
	public Book(String book_id, String book_name, double price, int remain_num) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.price = price;
		this.remain_num = remain_num;
	}
	public Book() {
		super();
	}

	
}
