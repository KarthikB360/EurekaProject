package com.eureka.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.eureka.dto.Delivery;
import com.eureka.dto.OrderResponseDto;
import com.eureka.dto.Payment;
import com.eureka.model.Order;
import com.eureka.repository.GenericRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RefreshScope
public class OrderServices {

	@Autowired
	GenericRepository genericRepository;

	@Autowired
	@Lazy
	RestTemplate restTemplate;

//	@Value("${microservice.delivery-service.endpoints.createDelivery.uri}")
	String deliveryEndpointUrl = "http://DELIVERY-SERVICE/delivery/createDelivery";

//	@Value("${microservice.payment-service.endpoints.doPayment.uri}")
	private String paymentEndpointUrl = "http://PAYMENT-SERVICE/payment/doPayment";

	Logger logger = LoggerFactory.getLogger(OrderServices.class);

	@Transactional
//	@CircuitBreaker(name = "order-service", fallbackMethod = "orderServiceFallback")
	public Order saveOrder(Order order) throws JsonProcessingException {
		logger.info("ORDER_SERVICE Request: {}", new ObjectMapper().writeValueAsString(order));

		order.setOrderDate(LocalDate.now());

		order = genericRepository.save(order);

		Payment payment = new Payment();
		payment.setAmount(order.getAmount());
		payment.setOrderId(order.getId());

		Payment paymentResponse = restTemplate.postForObject(paymentEndpointUrl, payment, Payment.class);

		Delivery delivery = new Delivery();
		delivery.setOrderId(order.getId());
		delivery.setUserId(order.getUserId());

		Delivery deliveryResponse = restTemplate.postForObject(deliveryEndpointUrl, delivery, Delivery.class);

//		String status = (paymentResponse.getPaymentStatus() == "success" && !deliveryResponse.getTrackingId().isEmpty())
//				? "Order Successfull"
//				: "Order Failed";
//		OrderResponseDto response = new OrderResponseDto(order, paymentResponse.getAmount(),
//				paymentResponse.getTransactionId(), deliveryResponse.getTrackingId(), status);
//		logger.info("ORDER_SERVICE Response: {}", new ObjectMapper().writeValueAsString(response));
		return order;
	}

	public OrderResponseDto orderServiceFallback() {
		OrderResponseDto response = new OrderResponseDto();
		response.setStatus("Order Failed");
		return response;
	}

}
