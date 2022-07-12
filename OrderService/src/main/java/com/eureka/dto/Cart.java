package com.eureka.dto;

public class Cart {

	private int id;

	private double price;

	private int qty;

	public Cart() {

	}

	public Cart(int id, double price, int qty) {
		super();
		this.id = id;
		this.price = price;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
