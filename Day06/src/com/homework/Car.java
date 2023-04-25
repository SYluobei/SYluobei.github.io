package com.homework;

public class Car {
	private String cid;
	private String brand;
	private String style; 
	private double money;
	private String type;
	private String structure;
	@Override
	public String toString() {
		return "Car [cid=" + cid + ", brand=" + brand + ", style=" + style + ", money=" + money + ", type=" + type
				+ ", structure=" + structure + "]";
	}
	
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCid() {
		return cid;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrand() {
		return brand;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyle() {
		return style;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getMoney() {
		return money;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getStructure() {
		return structure;
	}

	public Car(String cid, String brand, String style, double money, String type, String structure) {
		this.cid = cid;
		this.brand = brand;
		this.style = style;
		this.money = money;
		this.type = type;
		this.structure = structure;
	}

}
