package com.spring.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.entity.Cart;
import com.spring.entity.Status;
import com.spring.entity.User;
import com.spring.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	@Lazy
	RestTemplate restTemplate;

//	@Value("${microservice.cart-service.endpoints.addCart.uri}")
	String cartEndpointUrl = "http://CART-SERVICE/cart/addToCart";

	Logger logger = LoggerFactory.getLogger(User.class);

	@PostMapping("/register")
	public Status registerUser(@RequestBody User newUser) throws JsonProcessingException {
		logger.info("USER_SERVICE Request: {}", new ObjectMapper().writeValueAsString(newUser));
		List<User> users = userRepository.findAll();
		System.out.println("New user: " + newUser.toString());

		for (User user : users) {
			System.out.println("Registered user: " + newUser.toString());

			if (user.equals(newUser)) {
				System.out.println("User Already exists!");
				return Status.USER_ALREADY_EXISTS;
			}
		}

		userRepository.save(newUser);
		logger.info("USER_SERVICE response: {}", new ObjectMapper().writeValueAsString(newUser));
		return Status.SUCCESS;
	}

	@PostMapping("/login")
	public Status loginUser(@RequestBody User user) throws JsonProcessingException {
		logger.info("USER_SERVICE Request: {}", new ObjectMapper().writeValueAsString(user));
		Optional<User> optionalUser = userRepository.findById(user.getId());

		if (optionalUser.isPresent()) {
			user.setLoggedIn(true);
			user = userRepository.save(user);

			Cart cart = new Cart();
			cart.setUserId((int) user.getId());
			Cart cartResponse = restTemplate.postForObject(cartEndpointUrl, cart, Cart.class);
			logger.info("USER_SERVICE Response: {}", new ObjectMapper().writeValueAsString(user));

			return Status.SUCCESS;
		}

		return Status.FAILURE;
	}

	@PostMapping("/logout")
	public Status logUserOut(@RequestBody User user) throws JsonProcessingException {
		logger.info("USER_SERVICE Request: {}", new ObjectMapper().writeValueAsString(user));

		List<User> users = userRepository.findAll();

		for (User other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(false);
				userRepository.save(user);
				return Status.SUCCESS;
			}
		}

		return Status.FAILURE;
	}

	@DeleteMapping("/all")
	public Status deleteUsers() {
		userRepository.deleteAll();
		return Status.SUCCESS;
	}
}
