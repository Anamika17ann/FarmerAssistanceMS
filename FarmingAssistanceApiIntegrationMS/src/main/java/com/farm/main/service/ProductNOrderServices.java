package com.farm.main.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.farm.main.Entity.Login;
import com.farm.main.Entity.MailEntity;
import com.farm.main.Entity.Orders;
import com.farm.main.Entity.ProductRequest;
import com.farm.main.Entity.Retailer;
import com.farm.main.exception.MyFarmingException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ProductNOrderServices {

	@Autowired
	RegisterLoginServices registerservices;

	// ---------get list of product---------
	public String getProduct(RestTemplate restTemplate) {
		return restTemplate.getForObject("http://localhost:8087/product/list", String.class);
	}

	// --------------get product by product Id-----------
	public ResponseEntity<String> getProductById(Long productId, RestTemplate restTemplate) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = null;
		entity = new HttpEntity<String>(header);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/product/byId/" + productId,
				HttpMethod.GET, entity, String.class);
		return response;
	}

	// --------------add product---------------------
	public ResponseEntity<String> addProduct(ProductRequest request, RestTemplate restTemplate)
			throws URISyntaxException, JSONException {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		final String baseUrl = "http://localhost:8087/product/add";
		URI uri = new URI(baseUrl);
		HttpEntity<ProductRequest> entity = new HttpEntity<>(request, header);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
		return result;
	}

	// ----------------update product------------------------
	public ResponseEntity<String> updateProduct(ProductRequest request, Long productId, String username,
			String password, RestTemplate restTemplate)
			throws URISyntaxException, JSONException, MyFarmingException {
		ResponseEntity<String> productById = getProductById(productId, restTemplate);
		JSONObject jsonObject = new JSONObject(productById.getBody());
		Login login = new Login(username, password);
		Long customerIdbyId = (Long) jsonObject.get("customerId");
		ResponseEntity<String> customerDetail = registerservices.getCustomerDetail(login, restTemplate);
		JSONObject jsonObject2 = new JSONObject(customerDetail.getBody());
		Long custId = (Long) jsonObject2.get("customerId");
		if (custId == customerIdbyId) {
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			final String baseUrl = "http://localhost:8087/product/update/" + productId;
			URI uri = new URI(baseUrl);
			HttpEntity<ProductRequest> entity = new HttpEntity<>(request, header);
			ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);
			return result;
		} else {
			return null;
		}
	}

	public ResponseEntity<String> addOrder(Orders request, Long productId, String username, String password,
			RestTemplate restTemplate)
			throws URISyntaxException, JSONException, MyFarmingException, RestClientException, JsonProcessingException {
		Login login = new Login(username, password);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		final String baseUrl = "http://localhost:8087/orders/product/" + productId + "/orders";
		URI uri = new URI(baseUrl);
		HttpEntity<Orders> entity = new HttpEntity<>(request, header); // sellersId
		ResponseEntity<String> customerDetail = registerservices.getCustomerDetail(login, restTemplate);
		System.out.println("customerDetail:" + customerDetail);
		JSONObject jobj = new JSONObject(customerDetail.getBody());
		String buyerEmail = jobj.getString("emailId");
		ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
		JSONObject jsonObject = new JSONObject(result.getBody());
		Long sellersId = jsonObject.getLong("sellersId");
		ResponseEntity<String> customerById = registerservices.getCustomerById(login, restTemplate, sellersId);
		System.out.println("customerById::" + customerById);
		JSONObject jobj1 = new JSONObject(customerById.getBody());
		String sellersMail = jobj1.getString("emailId");
		String[] toEmail = { sellersMail, buyerEmail };
		MailEntity mailentity = new MailEntity();
		if (result.getStatusCodeValue() == 200) {
			mailentity.setToEmail(toEmail);
			mailentity.setSubject("Farmer Assistant Service : Orders Information");
			mailentity.setMessage(result.getBody());
			registerservices.SendMail(mailentity, restTemplate, username, password);
		}
		return result;
	}

	public ResponseEntity<String> getOrdersList(RestTemplate restTemplate) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = null;
		entity = new HttpEntity<String>(header);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/orders/orderList",
				HttpMethod.GET, entity, String.class);
		return response;
	}

	public ResponseEntity<String> getOrderById(Long orderId, RestTemplate restTemplate) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = null;
		entity = new HttpEntity<String>(header);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/orders/" + orderId,
				HttpMethod.GET, entity, String.class);
		return response;
	}

	public ResponseEntity<String> updateOrder(Orders request, Long productId, String username, String password,
			Long orderId, RestTemplate restTemplate)
			throws URISyntaxException, JSONException, RestClientException, JsonProcessingException, MyFarmingException {
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		Login login = new Login(username, password);
		ResponseEntity<String> getOrderById = getOrderById(orderId, restTemplate);
		JSONObject jobj3 = new JSONObject(getOrderById.getBody());
		Long buyersId = jobj3.getLong("buyersId");
		Long sellerId = jobj3.getLong("sellersId");
		ResponseEntity<String> customerDetail = registerservices.getCustomerById(login, restTemplate, buyersId);
		JSONObject jobj = new JSONObject(customerDetail.getBody());
		Long custId = jobj.getLong("customerId");
		if (custId == buyersId || custId == sellerId) { // only seller or buyer can update
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			final String baseUrl = "http://localhost:8087/orders/" + orderId;
			URI uri = new URI(baseUrl);
			HttpEntity<Orders> entity = new HttpEntity<>(request, header);
			String buyerEmail = jobj.getString("emailId");
			System.out.println("buyerEmail::" + buyerEmail);
			ResponseEntity<String> result = restTemplate.exchange("http://localhost:8087/orders/" + orderId,
					HttpMethod.PUT, entity, String.class, orderId);
			ResponseEntity<String> customerById = registerservices.getCustomerById(login, restTemplate, sellerId);
			JSONObject jobj1 = new JSONObject(customerById.getBody());
			String sellersMail = jobj1.getString("emailId");
			String[] toEmail = { sellersMail, buyerEmail };
			MailEntity mailentity = new MailEntity();
			if (result.getStatusCodeValue() == 200) {
				System.out.println("sending mail to" + sellersMail);
				mailentity.setToEmail(toEmail);
				mailentity.setSubject("Farmer Assistant Service : Orders updated Information");
				mailentity.setMessage(result.getBody());
				registerservices.SendMail(mailentity, restTemplate, username, password);
			}
			return result;
		}

		else
			return new ResponseEntity<>("Records cannot be updated user not found", HttpStatus.NOT_MODIFIED);
	}

	public ResponseEntity<String> deleteProduct(Long productId, RestTemplate restTemplate, String username,
			String password) throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		ResponseEntity<String> productById = getProductById(productId, restTemplate);
		JSONObject jsonObject = new JSONObject(productById.getBody());
		Login login = new Login(username, password);
		Long customerIdbyId = (Long) jsonObject.get("customerId");
		ResponseEntity<String> customerDetail = registerservices.getCustomerDetail(login, restTemplate);
		JSONObject jsonObject2 = new JSONObject(customerDetail.getBody());
		Long custId = (Long) jsonObject2.get("customerId");
		if (custId == customerIdbyId) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Retailer> httpEntity = new HttpEntity<>(headers);
			return restTemplate.exchange("http://localhost:8087/product/delete/" + productId, HttpMethod.DELETE,
					httpEntity, String.class);
		} else
			return new ResponseEntity<>("Product record not deleted with id :" + productId, HttpStatus.UNAUTHORIZED);
	}

	public ResponseEntity<String> deleteOrder(Long orderId, RestTemplate restTemplate, String username, String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		Login login = new Login(username, password);
		ResponseEntity<String> getOrderById = getOrderById(orderId, restTemplate);
		JSONObject jobj3 = new JSONObject(getOrderById);
		Long buyersId = jobj3.getLong("buyersId");
		Long sellerId = jobj3.getLong("sellersId");
		ResponseEntity<String> customerDetail = registerservices.getCustomerDetail(login, restTemplate);
		JSONObject jobj = new JSONObject(customerDetail);
		Long custId = jobj.getLong("customerId");
		if (custId == buyersId || custId == sellerId) { // only seller or buyer can update
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Retailer> httpEntity = new HttpEntity<>(headers);
			return restTemplate.exchange("http://localhost:8087/orders/" + orderId, HttpMethod.DELETE, httpEntity,
					String.class);
		} else
			return new ResponseEntity<>("Order record not deleted with id :" + orderId, HttpStatus.UNAUTHORIZED);
	}

}
