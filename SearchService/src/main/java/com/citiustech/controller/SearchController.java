package com.citiustech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.model.Product;
import com.citiustech.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("/name")
	public ResponseEntity<Product> getProductByName(@RequestParam String name) throws JsonProcessingException {
		Product product = searchService.getByName(name);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws JsonProcessingException {
		Product product = searchService.getById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
