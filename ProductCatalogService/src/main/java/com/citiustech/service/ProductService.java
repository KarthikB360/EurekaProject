package com.citiustech.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Product;
import com.citiustech.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RefreshScope
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

//	@Value("${microservice.inventory-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL = "http://INVENTORY-SERVICE/inventory";

	Logger logger = LoggerFactory.getLogger(Product.class);

	public Product save(Product product) throws JsonProcessingException {
		logger.info("PRODUCT_SERVICE Request: {}", new ObjectMapper().writeValueAsString(product));

		return productRepository.save(product);
	}

	public List<Product> getAll() throws JsonProcessingException {
		List<Product> products = productRepository.findAll();
		logger.info("PRODUCT_SERVICE get all products request: {}", new ObjectMapper().writeValueAsString(products));

		return products;
	}

	public Product getById(int id) throws JsonProcessingException {
		Product product = null;
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isEmpty()) {
			product = restTemplate.getForObject(ENDPOINT_URL + "/" + id, Product.class);
		} else {
			product = optionalProduct.get();
		}
		logger.info("PRODUCT_SERVICE get product request: {}", new ObjectMapper().writeValueAsString(product));

		return product;
	}

	public void delete(int id) throws JsonProcessingException {
		Product product = getById(id);
		logger.info("PRODUCT_SERVICE delete product request: {}", new ObjectMapper().writeValueAsString(product));

		productRepository.delete(product);
	}

	public Product getByName(String name) throws JsonProcessingException {
		Product product = productRepository.findByName(name);
		if (product == null) {
			product = restTemplate.getForObject(ENDPOINT_URL + "/name?name=" + name, Product.class);
		}
		logger.info("PRODUCT_SERVICE get product request: {}", new ObjectMapper().writeValueAsString(product));

		return product;
	}

}
