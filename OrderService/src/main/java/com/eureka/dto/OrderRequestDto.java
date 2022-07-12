package com.eureka.dto;

public class OrderRequestDto {

	private Cart cart;
	
	private User user;
	
	public OrderRequestDto() {

	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
