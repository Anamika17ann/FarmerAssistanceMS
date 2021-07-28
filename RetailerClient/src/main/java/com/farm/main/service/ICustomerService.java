package com.farm.main.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.farm.main.entity.Customer;
import com.farm.main.entity.Retailer;


public interface ICustomerService {
	
	
	public List<Customer> getAllUser();	// get all users

	public Customer insertUser(Customer userReg); // create user

	public ResponseEntity<Object> validateUser(Map<String, String> logData);

	public Optional<Customer> getCustomer(int customerId);

	
}
