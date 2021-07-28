package com.exam.boot.retailer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetailerController {
	
	@GetMapping
	public String welcome() {
		return "<h1 align=center>Welcome to Retailer Home Page</h1>";
	}

}
