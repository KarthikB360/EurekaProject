package com.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.dto.OrderRequestDto;
import com.eureka.dto.OrderResponseDto;
import com.eureka.service.OrderServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderServices orderServices;

	@PostMapping("/createOrder")
	public OrderResponseDto createOrder(@RequestBody OrderRequestDto request) throws JsonProcessingException {

		return orderServices.saveOrder(request);
	}

}
