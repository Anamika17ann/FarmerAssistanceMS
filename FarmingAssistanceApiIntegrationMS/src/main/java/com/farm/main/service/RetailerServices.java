package com.farm.main.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.farm.main.Entity.Login;
import com.farm.main.Entity.ProductRequest;
import com.farm.main.Entity.Retailer;
import com.farm.main.exception.MyFarmingException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class RetailerServices {
	@Autowired
	RegisterLoginServices registerservices;

	public ResponseEntity<String> addRetailer(Retailer request, RestTemplate restTemplate, String username,
			String password) throws URISyntaxException, JSONException, RestClientException, JsonProcessingException, MyFarmingException {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		Login login = new Login(username, password);
		Long custId = (Long) registerservices.getCustomerId(login, restTemplate);
		String role = registerservices.getCustomerRole(login, restTemplate);
		if (custId == request.getRetailerId() && role == "retailer") {
			final String baseUrl = "http://localhost:8085/api/assistance/retailer/addRetailer";
			URI uri = new URI(baseUrl);
			HttpEntity<Retailer> entity = new HttpEntity<>(request, header);
			ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
			return result;
		} else
			return new ResponseEntity<>("Retailer Id not found", HttpStatus.NOT_FOUND);
	}

	// ********************** get all retailer **************************** //
	public String getRetailers(RestTemplate restTemplate) {
		return restTemplate.getForObject("http://localhost:8085/api/assistance/retailer", String.class);
	}

	// ********************** get retailer by id **************************** //
	public Retailer getRetailer(Long retailerId, RestTemplate restTemplate) {
		return restTemplate.getForObject("http://localhost:8085/api/assistance/retailer/" + retailerId, Retailer.class);
	}

	// ********************** update retailer by id **************************** //
	public ResponseEntity<Retailer> updateRetailer(Retailer retailer, Long retailerId, RestTemplate restTemplate,
			String username, String password)
			throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Login login = new Login(username, password);
		Long custId = (Long) registerservices.getCustomerId(login, restTemplate);
		String role = registerservices.getCustomerRole(login, restTemplate);
		if (custId == retailerId && role == "retailer") {
			HttpEntity<Retailer> httpEntity = new HttpEntity<>(retailer, headers);
			return restTemplate.exchange("http://localhost:8085/api/assistance/retailer/" + retailerId, HttpMethod.PUT,
					httpEntity, Retailer.class);
		} else
			return new ResponseEntity<>(retailer, HttpStatus.NOT_MODIFIED);
	}

	public ResponseEntity<String> deleteRetailer(Long retailerId, RestTemplate restTemplate, String username,
			String password) throws RestClientException, JsonProcessingException, URISyntaxException, JSONException, MyFarmingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Login login = new Login(username, password);
		Long custId = (Long) registerservices.getCustomerId(login, restTemplate);
		String role = registerservices.getCustomerRole(login, restTemplate);
		if (custId == retailerId && role == "retailer") {
			HttpEntity<Retailer> httpEntity = new HttpEntity<>(headers);
			return restTemplate.exchange("http://localhost:8085/api/assistance/retailer/" + retailerId,
					HttpMethod.DELETE, httpEntity, String.class);
		} else
			return new ResponseEntity<>("Retailer record not deleted with id :"+retailerId, HttpStatus.NOT_MODIFIED);
	}

}
