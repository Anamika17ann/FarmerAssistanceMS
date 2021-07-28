package com.farm.main.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.main.dao.ICustomerDao;
import com.farm.main.entity.Customer;
import com.farm.main.entity.Retailer;


@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerDao userDao;

	@Override
	public List<Customer> getAllUser() {
		// TODO Auto-generated method stub
		List<Customer> users = userDao.findAll();
		return users;

	}
	
	@Override
	public Optional<Customer> getCustomer(int customerId) {
		
		return userDao.findById(customerId);
	}

	@Override
	public Customer insertUser(Customer userReg) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println(userReg.getUserEmail());
		 * System.out.println(userReg.getUserName());
		 * System.out.println(userReg.getUserPassword());
		 * System.out.println(userReg.getUserPass());
		 * System.out.println(userReg.getUserRole());
		 */
		return userDao.save(userReg);

	}

	@Override
	public ResponseEntity<Object> validateUser(Map<String, String> logData) {
		List<Customer> users = userDao.findAll();
		for (Customer u : users) {
			if (u.getCustomerEmail().equals(logData.get("userEmail")) && u.getCustomerPassword().equals(logData.get("userPassword")) && u.getCustomerRole().equals(logData.get("userRole"))) {
				return new ResponseEntity<>(u, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.EXPECTATION_FAILED);

	}

	

}
