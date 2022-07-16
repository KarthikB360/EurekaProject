package com.citiustech.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.citiustech.entity.Product;
import com.citiustech.repository.InventoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RefreshScope
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	Logger logger = LoggerFactory.getLogger(Product.class);

	public Product save(Product product) throws JsonProcessingException {
		logger.info("INVENTORY_SERVICE Request: {}", new ObjectMapper().writeValueAsString(product));

		return inventoryRepository.save(product);
	}

	public List<Product> getAll() throws JsonProcessingException {
		List<Product> products = inventoryRepository.findAll();
		logger.info("INVENTORY_SERVICE get all products request: {}", new ObjectMapper().writeValueAsString(products));

		return products;
	}

	public Product getById(Long id) throws JsonProcessingException {
		Product product = inventoryRepository.findById(id).orElse(null);
		logger.info("INVENTORY_SERVICE get product request: {}", new ObjectMapper().writeValueAsString(product));

		return product;
	}

	public void delete(Long id) throws JsonProcessingException {
		Product product = getById(id);
		logger.info("INVENTORY_SERVICE delete product request: {}", new ObjectMapper().writeValueAsString(product));

		inventoryRepository.delete(product);
	}

	public Product getByName(String name) throws JsonProcessingException {
		Product product = inventoryRepository.findByName(name);
		logger.info("PRODUCT_SERVICE get product request: {}", new ObjectMapper().writeValueAsString(product));

		return product;
	}

}
