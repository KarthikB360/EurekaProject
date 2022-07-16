package com.citiustech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SearchService {

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(Product.class);

//	@Value("${microservice.product-catalog-service.endpoints.doPayment.uri}")
	private String productCatalogEndpointUrl = "http://PRODUCT-CATALOG-SERVICE/product";

	public Product getById(Long id) throws JsonProcessingException {
		Product product = restTemplate.getForObject(productCatalogEndpointUrl + "/" + id, Product.class);

		logger.info("SEARCH_SERVICE Request: {}", new ObjectMapper().writeValueAsString(product));
		return product;
	}

	public Product getByName(String name) throws JsonProcessingException {
		Product product = restTemplate.getForObject(productCatalogEndpointUrl + "/name?name=" + name, Product.class);

		logger.info("SEARCH_SERVICE Request: {}", new ObjectMapper().writeValueAsString(product));
		return product;
	}

}
