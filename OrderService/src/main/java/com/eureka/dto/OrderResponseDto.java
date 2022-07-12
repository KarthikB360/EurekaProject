package com.eureka.dto;

import com.eureka.model.Order;

public class OrderResponseDto {

	private Order order;

	private double amount;

	private String paymentTransactionId;

	private String deliveryTrackingId;

	private String status;

	public OrderResponseDto() {

	}

	public OrderResponseDto(Order order, double amount, String paymentTransactionId, String deliveryTrackingId,
			String status) {
		super();
		this.order = order;
		this.amount = amount;
		this.paymentTransactionId = paymentTransactionId;
		this.deliveryTrackingId = deliveryTrackingId;
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentTransactionId() {
		return paymentTransactionId;
	}

	public void setPaymentTransactionId(String paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}

	public String getDeliveryTrackingId() {
		return deliveryTrackingId;
	}

	public void setDeliveryTrackingId(String deliveryTrackingId) {
		this.deliveryTrackingId = deliveryTrackingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
