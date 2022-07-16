package com.citiustech.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.entity.Payment;
import com.citiustech.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	Logger logger = LoggerFactory.getLogger(Payment.class);

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		logger.info("PAYMENT_SERVICE Request: {}", new ObjectMapper().writeValueAsString(payment));

		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepository.save(payment);
	}

	public String paymentProcessing() {
		return new Random().nextBoolean() ? "success" : "failed";
	}
}
