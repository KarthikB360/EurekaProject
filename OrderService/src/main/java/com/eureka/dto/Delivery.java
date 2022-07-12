package com.eureka.dto;

public class Delivery {

	private int id;

	private String trackingId;

	private int orderId;

	private int userId;

	public Delivery() {

	}

	public Delivery(int id, String trackingId, int orderId, int userId) {
		super();
		this.id = id;
		this.trackingId = trackingId;
		this.orderId = orderId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
