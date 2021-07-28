package com.farm.main.controller;

import java.net.URISyntaxException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.farm.main.Entity.CustomerRequest;
import com.farm.main.Entity.FarmerEntity;
import com.farm.main.Entity.Login;
import com.farm.main.Entity.Orders;
import com.farm.main.Entity.ProductRequest;
import com.farm.main.Entity.Retailer;
import com.farm.main.exception.MyFarmingException;
import com.farm.main.service.FarmerServices;
import com.farm.main.service.ProductNOrderServices;
import com.farm.main.service.RegisterLoginServices;
import com.farm.main.service.RetailerServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/farmersAssistance")
public class FarmController {

	private static final Logger logger = LoggerFactory.getLogger(FarmController.class);

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RegisterLoginServices registerLoginService;

	@Autowired
	ProductNOrderServices productNOrderServices;

	@Autowired
	RetailerServices retailerServices;

	@Autowired
	FarmerServices farmerServices;

	// --------------register a new customer can be a retailer of farmer-------
	@PostMapping(value = "/register")
	public ResponseEntity<String> RegisterUser(@RequestBody CustomerRequest request)
			throws URISyntaxException, JSONException {
		try {
			ResponseEntity<String> response = registerLoginService.RegisterUser(request, restTemplate);
			logger.info("User Registered Successfully :" + response);
			return new ResponseEntity<>("User Registered Successfully :" + response, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("User Registration failed :" + request + HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>("User Registration failed :" + request, HttpStatus.BAD_REQUEST);
		}
	}

	// -------------------------------get all products---------------
	@GetMapping(value = "/productsList")
	public ResponseEntity<String> getProductList(@RequestParam String username, @RequestParam String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// jwt authentication and login validation
		if (validcode == 200) {
			String response = productNOrderServices.getProduct(restTemplate); // get list of product
			logger.info("get Product Response :" + response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			logger.error("get Product Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------get product by id-----------------
	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<String> getProductById(@RequestParam String username, @RequestParam String password,
			@PathVariable("productId") Long productId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.getProductById(productId, restTemplate);
			logger.info("get Product Response :" + response);
			return response;
		} else {
			logger.error("get Product Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------add product----------------------
	@PostMapping(value = "/product/add/{userName}/{password}")
	public ResponseEntity<String> addProduct(@RequestBody ProductRequest request,
			@PathVariable("userName") String userName, @PathVariable("password") String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.addProduct(request, restTemplate);
			logger.info("add Product Response :" + response);
			return response;
		} else {
			logger.error("add Product Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------------update product---------------
	@PutMapping(value = "/product/updateProduct/{userName}/{password}/{productId}")
	public ResponseEntity<String> updateProduct(@RequestBody ProductRequest request,
			@PathVariable("userName") String userName, @PathVariable("password") String password,
			@PathVariable("productId") Long productId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.updateProduct(request, productId, userName,
					password, restTemplate);
			logger.info("update Product Response :" + response);
			return response;
		} else {
			logger.error("update Product Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------------update product---------------
	@PutMapping(value = "/product/updateOrder/{userName}/{password}/{orderId}/{productId}")
	public ResponseEntity<String> updateOrder(@RequestBody Orders request, @PathVariable("userName") String userName,
			@PathVariable("password") String password, @PathVariable("orderId") Long orderId,
			@PathVariable("productId") Long productId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.updateOrder(request, productId, userName, password,
					orderId, restTemplate);
			logger.info("update Order Response :" + response);
			return response;
		} else {
			logger.error("update Order Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ---------------------add Retailer Details-----------------
	@PostMapping(value = "/retailer/addDetails/{userName}/{password}") // http://localhost:8083/farmersAssistance/productsList
	public ResponseEntity<String> addRetailerDetails(@RequestBody Retailer request,
			@PathVariable("userName") String userName, @PathVariable("password") String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);// jwt authentication and validation by
																			// username and // password
		if (validcode == 200) {
			ResponseEntity<String> response = retailerServices.addRetailer(request, restTemplate, userName, password);
			logger.info("add Product Response :" + response);
			return response;
		} else {
			logger.error("add Product Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -----------------add farmer details------------
	@PostMapping(value = "/farmer/addDetails/{userName}/{password}")
	public ResponseEntity<String> addFarmerDetails(@RequestBody FarmerEntity request,
			@PathVariable("userName") String userName, @PathVariable("password") String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);
		System.out.println(userName + " " + password);
		if (validcode == 200) {
			System.out.println("inside farmerServices");
			ResponseEntity<String> response = farmerServices.addFarmer(request, restTemplate, userName, password);
			logger.info("add farmer Response :" + response);
			return response;
		} else {
			logger.error("add farmer Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -----------------------add orders by product id---------------------------
	@PostMapping(value = "/orders/add/{userName}/{password}/{productId}")
	public ResponseEntity<String> addOrders(@RequestBody Orders request, @PathVariable("userName") String userName,
			@PathVariable("password") String password, @PathVariable("productId") Long productId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);// jwt authentication and validation by
																			// username and // password
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.addOrder(request, productId, userName, password,
					restTemplate);
			logger.info("add Order Success :" + response);
			return response;
		} else {
			logger.error("add Order Failure : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -------get Orders List--------------------
	@GetMapping(value = "/ordersList")
	public ResponseEntity<String> getOrdersList(@RequestParam String username, @RequestParam String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// jwt authentication and login validation
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.getOrdersList(restTemplate); // get list of product

			logger.info("get Order Response :" + response);
			return ResponseEntity.ok("get Order Response :" + response);
		} else {
			logger.error("get Order Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------get order by id-----------------
	@GetMapping(value = "/order/{orderId}")
	public ResponseEntity<String> getOrderById(@RequestParam String username, @RequestParam String password,
			@PathVariable("orderId") Long orderId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.getOrderById(orderId, restTemplate);
			logger.info("get order Response :" + response);
			return response;
		} else {
			logger.error("get order Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --------------delete order request---------------
	@DeleteMapping(value = "/order/delete/{orderId}")
	public ResponseEntity<String> deleteOrderById(@RequestParam String username, @RequestParam String password,
			@PathVariable("orderId") Long orderId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.deleteOrder(orderId, restTemplate,
					login.getUsername(), login.getPassword());
			logger.info("get order Response :" + response);
			return response;
		} else {
			logger.error("get order Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --------------delete product request---------------
	@DeleteMapping(value = "/product/delete/{productId}")
	public ResponseEntity<String> deleteProductById(@RequestParam String username, @RequestParam String password,
			@PathVariable("productId") Long productId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = productNOrderServices.deleteProduct(productId, restTemplate,
					login.getUsername(), login.getPassword());
			logger.info("Delete product Response :" + response);
			return response;
		} else {
			logger.error("Delete product Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -----------------update farmer details------------
	@PutMapping(value = "/farmer/updateDetails/{userName}/{password}/{farmerId}")
	public ResponseEntity<String> updateFarmerDetails(@RequestBody FarmerEntity request,
			@PathVariable("userName") String userName, @PathVariable("password") String password,
			@PathVariable("farmerId") Long farmerId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);
		if (validcode == 200) {
			ResponseEntity<String> response = farmerServices.updateFarmer(request, farmerId, restTemplate, userName,
					password);
			logger.info("add Farmer Details Response :" + response);
			return response;
		} else {
			logger.error("add Farmer Details Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -------get Farmers List--------------------
	@GetMapping(value = "/farmersList")
	public ResponseEntity<String> getFarmersList(@RequestParam String username, @RequestParam String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// jwt authentication and login validation
		if (validcode == 200) {
			String response = farmerServices.getFarmers(restTemplate);
			logger.info("get Order Response :" + response);
			return ResponseEntity.ok("get Order Response :" + response);
		} else {
			logger.error("get Order Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------get farmer by id-----------------
	@GetMapping(value = "/farmer/{farmerId}")
	public ResponseEntity<String> getFarmerById(@RequestParam String username, @RequestParam String password,
			@PathVariable("farmerId") Long farmerId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			FarmerEntity response = farmerServices.getFarmer(farmerId, restTemplate);
			logger.info("get farmer Response :" + response);
			return ResponseEntity.ok("get Order Response :" + response);
		} else {
			logger.error("get farmer Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -----------------update Retailer details------------
	@PutMapping(value = "/retailer/updateDetails/{userName}/{password}/{retailerId}")
	public ResponseEntity<String> updateRetailerDetails(@RequestBody Retailer request,
			@PathVariable("userName") String userName, @PathVariable("password") String password,
			@PathVariable("retailerId") Long retailerId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(userName, password);
		int validcode = registerLoginService.validate(login, restTemplate);
		if (validcode == 200) {
			ResponseEntity<Retailer> response = retailerServices.updateRetailer(request, retailerId, restTemplate,
					userName, password);
			logger.info("add Retailer Details Response :" + response);
			return ResponseEntity.ok("add Retailer Details Response :" + response);
		} else {
			logger.error("add Retailer Details Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// -------get Retailer List--------------------
	@GetMapping(value = "/RetailersList")
	public ResponseEntity<String> getRetailersList(@RequestParam String username, @RequestParam String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// jwt authentication and login validation
		if (validcode == 200) {
			String response = retailerServices.getRetailers(restTemplate);
			logger.info("get Order Response :" + response);
			return ResponseEntity.ok("get Order Response :" + response);
		} else {
			logger.error("get Order Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------get Retailer by id-----------------
	@GetMapping(value = "/Retailer/{retailerId}")
	public ResponseEntity<String> getRetailerById(@RequestParam String username, @RequestParam String password,
			@PathVariable("retailerId") Long retailerId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			FarmerEntity response = farmerServices.getFarmer(retailerId, restTemplate);
			logger.info("get farmer Response :" + response);
			return ResponseEntity.ok("get Order Response :" + response);
		} else {
			logger.error("get farmer Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --------------delete retailer request---------------
	@DeleteMapping(value = "/retailer/delete/{retailerId}")
	public ResponseEntity<String> deleteRetailerById(@RequestParam String username, @RequestParam String password,
			@PathVariable("retailerId") Long retailerId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = retailerServices.deleteRetailer(retailerId, restTemplate, username,
					password);
			logger.info("Delete retailer record Response :" + response);
			return response;
		} else {
			logger.error("Delete retailer record Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// --------------delete farmer request---------------
	@DeleteMapping(value = "/farmer/delete/{farmerId}")
	public ResponseEntity<String> deleteFarmerById(@RequestParam String username, @RequestParam String password,
			@PathVariable("farmerId") Long farmerId)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = farmerServices.deleteFarmer(farmerId, restTemplate, username, password);
			logger.info("Delete farmer record Response :" + response);
			return response;
		} else {
			logger.error("Delete farmer record response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// ------------------------get Retailer by id-----------------
	@GetMapping(value = "/userdetail")
	public ResponseEntity<String> getuserDetailById(@RequestParam String username, @RequestParam String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		int validcode = registerLoginService.validate(login, restTemplate);// login validation
		if (validcode == 200) {
			ResponseEntity<String> response = registerLoginService.getCustomerDetail(login, restTemplate);
			logger.info("get user role Response :" + response);
			return response;
		} else {
			logger.error("get user role Response : user not found" + HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
