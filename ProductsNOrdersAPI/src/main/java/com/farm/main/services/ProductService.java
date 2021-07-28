package com.farm.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.main.dao.IProductDao;
import com.farm.main.entity.Product;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDao iProductDao;
	
	@Override
	public List<Product> getAllProducts() {
		return iProductDao.findAll();
	}

	@Override
	public Optional<Product> getProduct(Long productId) {
		return iProductDao.findById(productId);
	}

	@Override
	public Product addProduct(Product product) {
		Product add = iProductDao.save(product);
		return add;
	}

	@Override
	public ResponseEntity<Object> updateProduct(Product product, Long productId) {
				try {
			Optional<Product> productFound = iProductDao.findById( productId);
			if (productFound.isPresent()) {
				product.setProductId(productId);
				Product updatedProduct = iProductDao.save(product);
				return ResponseEntity.ok(updatedProduct);
			} else {
				return new ResponseEntity<>("Record NOT updated with Id : " + product, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Exception while Record uploading : " + product, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public void deleteProduct(Long productId) {
		iProductDao.deleteById((long) productId);
	}

}
