package com.spring.entity;

public class Cart {

	private int cartId;
	private int userId;
	private int qty;
	private Long price;

	public Cart() {

	}

	public Cart(int cartId, int userId, int qty, Long price) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.qty = qty;
		this.price = price;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
