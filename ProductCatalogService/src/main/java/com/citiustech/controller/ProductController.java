package com.citiustech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.model.Product;
import com.citiustech.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/saveProduct")
	public Product save(@RequestBody Product product) throws JsonProcessingException {
		Product saveProduct = productService.save(product);
		return saveProduct;
	}

	@GetMapping("/get-all-products")
	public List<Product> getAllProducts() throws JsonProcessingException {
		List<Product> products = productService.getAll();
		return products;
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") int id) throws JsonProcessingException {
		Product product = productService.getById(id);
		return product;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) throws JsonProcessingException {
		productService.delete(id);
		return "Product Deleted!...";
	}

	@PutMapping("/updateProduct")
	public Product update(@RequestBody Product product) throws JsonProcessingException {
		Product newProduct = new Product(product);
		Product updateProduct = productService.save(newProduct);
		return updateProduct;
	}

	@GetMapping("/name")
	public Product getProductByName(@RequestParam String name) throws JsonProcessingException {
		Product product = productService.getByName(name);
		return product;
	}

}
