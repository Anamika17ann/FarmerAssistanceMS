package com.farm.main.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farm.main.entity.Product;
import com.farm.main.services.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService iProductService;

	/*
	 * @Autowired private MailService ms;
	 */
	// ********************get all products*********************//
	@GetMapping("/list")
	public ResponseEntity<Object> getAllProducts() {
		try {
			List<Product> products = iProductService.getAllProducts();
			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ********************get product using id*********************//
	@GetMapping("/byId/{productId}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("productId") Long productId) {

		try {
			Optional<Product> product = iProductService.getProduct(productId);

			if (product.equals(null)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ********************add product *********************//
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		try {
			Product added = iProductService.addProduct(product);
			return new ResponseEntity<>(added, HttpStatus.CREATED);// 201
		} catch (Exception e) {
			return new ResponseEntity<>(product, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product,
			@PathVariable("productId") Long productId) {
		return iProductService.updateProduct(product, productId);

	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
		try {
			iProductService.deleteProduct(productId);
			return new ResponseEntity<>("Record Deleted...with id : " + productId, HttpStatus.OK);//200
		} catch (Exception e) {

			return new ResponseEntity<>("Record not found with id : " + productId, HttpStatus.EXPECTATION_FAILED);
		}
	}
}
