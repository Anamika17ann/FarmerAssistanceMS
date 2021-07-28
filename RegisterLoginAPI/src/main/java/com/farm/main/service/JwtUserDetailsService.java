package com.farm.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farm.main.dao.ICustomerDao;
import com.farm.main.entity.Customer;
import com.farm.main.entity.CustomerRequest;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private ICustomerDao farmerDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer farmer = farmerDao.findByUserName(username);
		if (farmer == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(farmer.getUsername(), farmer.getPassword(),
				new ArrayList<>());
	}

	public Customer getCustomerByUsername(String username) {
		Customer farmer = farmerDao.findByUserName(username);
		if (farmer == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return farmer;
		
	}
	
	public Customer save(CustomerRequest user) {
		Customer newfarmer = new Customer();
		newfarmer.setUsername(user.getUsername());
		Date date = new Date();
		newfarmer.setCustomerRegDate(date);
		newfarmer.setPassword(bcryptEncoder.encode(user.getPassword()));
		newfarmer.setConfirmPassword(bcryptEncoder.encode(user.getPassword()));
		newfarmer.setEmailId(user.getEmailId());
		newfarmer.setFullName(user.getFullName());
		newfarmer.setCustomerRole(user.getCustomerRole());
		return farmerDao.save(newfarmer);
	}

	public Optional<Customer> getCustomerBydId(Long customerId) {
		return farmerDao.findById(customerId);
	}
}