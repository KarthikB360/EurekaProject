package com.eureka.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.model.Delivery;
import com.eureka.repository.GenericRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DeliveryServices {

	@Autowired
	GenericRepository genericRepository;

	Logger logger = LoggerFactory.getLogger(Delivery.class);

	@Transactional
	public Delivery saveDelivery(Delivery delivery) throws JsonProcessingException {
		logger.info("DELIVERY_SERVICE Request: {}", new ObjectMapper().writeValueAsString(delivery));

		delivery.setTrackingId(UUID.randomUUID().toString());
		return genericRepository.save(delivery);
	}
}
