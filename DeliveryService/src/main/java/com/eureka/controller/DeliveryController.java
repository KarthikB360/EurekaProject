package com.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.model.Delivery;
import com.eureka.service.DeliveryServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	DeliveryServices deliveryServices;

	@PostMapping("/createDelivery")
	public Delivery createDelivery(@RequestBody Delivery delivery) throws JsonProcessingException {
		return deliveryServices.saveDelivery(delivery);
	}

}
