package com.farm.main.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farm.main.config.JwtTokenUtil;
import com.farm.main.entity.Customer;
import com.farm.main.entity.CustomerRequest;
import com.farm.main.entity.JwtRequest;
import com.farm.main.entity.JwtResponse;
import com.farm.main.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@RequestBody CustomerRequest user) throws Exception {
		try {
			Customer customer = userDetailsService.getCustomerByUsername(user.getUsername());
			System.out.println("customer::" + customer);
			return new ResponseEntity<>("Already present username" + user.getUsername(), HttpStatus.ALREADY_REPORTED);
		} catch (Exception e) {
			try {
			return new ResponseEntity<>(userDetailsService.save(user).toString(), HttpStatus.CREATED);
			}catch(Exception e1) {
				return new ResponseEntity<>("Record not registered", HttpStatus.BAD_REQUEST);
			}
		}

	}

	@PostMapping("/validate")
	public ResponseEntity<String> validate() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getCustomerByUsername")
	public Customer getIdByUsername(@RequestParam String username) {
		Customer farmer = userDetailsService.getCustomerByUsername(username);
		return farmer;
	}
	
	@GetMapping("/getCustomerById")
	public Optional<Customer> getIdById(@RequestParam Long customerId) {
		Optional<Customer> farmer = userDetailsService.getCustomerBydId(customerId);
		return  farmer;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}