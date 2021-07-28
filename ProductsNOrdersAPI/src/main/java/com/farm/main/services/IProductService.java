package com.farm.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.main.entity.Product;

@Service
public interface IProductService {

	public List<Product> getAllProducts();

	public Optional<Product> getProduct(Long productId);

	public Product addProduct(Product product);

	public ResponseEntity<Object> updateProduct(Product product,Long productId);

	public void deleteProduct(Long productId);
	
	
	
	
}
