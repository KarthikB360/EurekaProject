package com.citiustech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.model.Cart;
import com.citiustech.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@PostMapping("/addToCart")
	public Cart addToCart(@RequestBody Cart request) throws JsonProcessingException {

		return cartService.addCart(request);
	}

}
