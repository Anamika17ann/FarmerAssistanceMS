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
import org.springframework.web.bind.annotation.RestController;
import com.farm.main.dao.ICustomerDao;
import com.farm.main.entity.Customer;
import com.farm.main.entity.Retailer;
import com.farm.main.service.ICustomerService;
import com.farm.main.service.IRetailerService;

@RestController
@RequestMapping("/retailer")
public class RetailerController {
	private static final Logger logger = LoggerFactory.getLogger(RetailerController.class);
	@Autowired
	private IRetailerService iRetailerService;
	@Autowired
	private ICustomerService iCustomerService;

	@GetMapping("/home")
	public String welcome() {
		return "<h1 align=center>Welcome to Retailer Home Page</h1>";
	}

	@GetMapping
	public ResponseEntity<List<Retailer>> getRetailers() {

		try {
			List<Retailer> retailers = iRetailerService.getRetailers();

			if (retailers.isEmpty()) {
				logger.info("getting empty reatailer list");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info("successful in getting all retailers : " + retailers);
			return new ResponseEntity<>(retailers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error while getting retailers : " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{retailerId}")
	public ResponseEntity<Optional<Retailer>> getRetailer(@PathVariable int retailerId) {

		try {
			Optional<Retailer> retailer = iRetailerService.getRetailer(retailerId);

			if (!retailer.isPresent()) {
				logger.info("getting empty reatailer");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info("successful in getting retailer " + retailer);
			return new ResponseEntity<>(retailer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error while getting retailers " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/addRetailer")
	public ResponseEntity<Retailer> addRetailer(@RequestBody Retailer retailer) {
		System.out.println("sub ret" + retailer);
		/*
		 * try { Optional<Customer> retailerFound=
		 * iCustomerService.getCustomer(retailer.getRetailerId());
		 * if(!retailerFound.isPresent()) { logger.info("getting empty reatailer ");
		 * return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
		 */try {
			Retailer added = iRetailerService.addRetailer(retailer);
			logger.info("successful in adding retailer : " + retailer);
			return new ResponseEntity<>(added, HttpStatus.CREATED);// 201

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error while getting retailers : " + e);
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@DeleteMapping("/{retailerId}")
    public ResponseEntity<String> deleteFarmer(@PathVariable int retailerId) {
        return new ResponseEntity<String>(iRetailerService.deleteRetailer(retailerId),HttpStatus.OK);
        
    }

	@PutMapping("/{retailerId}")
	public ResponseEntity<Object> updateFarmer(@RequestBody Retailer retailer, @PathVariable int retailerId) {
		try {
			Optional<Retailer> retailerFound = iRetailerService.getRetailer(retailerId);
			if (retailerFound.isPresent()) {
				Retailer updateRetailer = iRetailerService.updateRetailer(retailer);
				logger.info("Retailer Record updated with Id : " + retailerId);
				return ResponseEntity.ok(updateRetailer);
			} else {
				logger.info("Retailer Record NOT updated with Id : " + retailerId);
				return new ResponseEntity<>("Record NOT updated with Id : " + retailerId, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			logger.error("error while updating retailer " + e);
			return new ResponseEntity<>("Exception while Record uploading : " + retailer,
					HttpStatus.EXPECTATION_FAILED);
		}

	}
}