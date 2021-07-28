package com.farm.main.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.farm.main.Entity.CustomerRequest;
import com.farm.main.Entity.FarmerEntity;
import com.farm.main.Entity.Login;
import com.farm.main.Entity.MailEntity;
import com.farm.main.exception.MyFarmingException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class RegisterLoginServices {

	private static final Logger logger = LoggerFactory.getLogger(RegisterLoginServices.class);

	public String authenticate(Login login, RestTemplate restTemplate)
			throws MyFarmingException, URISyntaxException, JSONException {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		final String baseUrl = "http://localhost:9001/authenticate";
		URI uri = new URI(baseUrl);
		HttpEntity<Login> request = new HttpEntity<>(login, header);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		System.out.println(result.getStatusCodeValue());
		JSONObject jsonObject = new JSONObject(result.getBody());
		String token = jsonObject.getString("token");
		return token;

	}

	public int validate(Login login, RestTemplate restTemplate)
			throws MyFarmingException, URISyntaxException, JSONException {
		String accessToken = authenticate(login, restTemplate);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set("Authorization", "Bearer " + accessToken);
		HttpEntity<Login> request = new HttpEntity<>(login, header);
		final String baseUrl = "http://localhost:9001/validate";
		URI uri = new URI(baseUrl); // To read the URL.
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class); // getting response from
																								// url by passing all
																								// the headers and
																								// parameters
		int status_code = result.getStatusCodeValue();// getting the status code value
		return status_code;
	}

	public ResponseEntity<String> getCustomerDetail(Login login, RestTemplate restTemplate)
			throws MyFarmingException, URISyntaxException, JSONException {
		String accessToken = authenticate(login, restTemplate);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set("Authorization", "Bearer " + accessToken);
		HttpEntity<String> request = new HttpEntity<>(header);
		final String baseUrl = "http://localhost:9001/getCustomerByUsername?username=" + login.getUsername();
		URI uri = new URI(baseUrl); // To read the URL.
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
		return result;
	}

	public String getCustomerRole(Login login, RestTemplate restTemplate)
			throws MyFarmingException, URISyntaxException, JSONException {
		ResponseEntity<String> customerDetail = getCustomerDetail(login, restTemplate);
		JSONObject jsonObject2 = new JSONObject(customerDetail.getBody());
		String role = jsonObject2.getString("customerRole");
		return role;
	}
	
	public Long getCustomerId(Login login, RestTemplate restTemplate)
			throws MyFarmingException, URISyntaxException, JSONException {
		ResponseEntity<String> customerDetail = getCustomerDetail(login, restTemplate);
		JSONObject jsonObject2 = new JSONObject(customerDetail.getBody());
		Long role = jsonObject2.getLong("customerId");
		return role;
	}

	public ResponseEntity<String> RegisterUser(CustomerRequest request, RestTemplate restTemplate)
			throws MyFarmingException, URISyntaxException {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		final String baseUrl = "http://localhost:9001/register";
		URI uri = new URI(baseUrl);
		HttpEntity<CustomerRequest> entity = new HttpEntity<>(request, header);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
		return result;
	}
	
	public ResponseEntity<String> SendMail(MailEntity mailentity, RestTemplate restTemplate, String username, String password)
			throws URISyntaxException, RestClientException, JsonProcessingException, JSONException {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		final String baseUrl = "http://localhost:8082/mailer/mailservice";
		URI uri = new URI(baseUrl);
		HttpEntity<MailEntity> entity = new HttpEntity<>(mailentity, header);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
		return result;
	}
	
	public ResponseEntity<String> getCustomerById(Login login, RestTemplate restTemplate, Long CustomerId)
			throws MyFarmingException, URISyntaxException, JSONException {
		String accessToken = authenticate(login, restTemplate);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set("Authorization", "Bearer " + accessToken);
		HttpEntity<String> request = new HttpEntity<>(header);
		final String baseUrl = "http://localhost:9001/getCustomerById?customerId=" + CustomerId;
		URI uri = new URI(baseUrl); // To read the URL.
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
		return result;
	}
}
