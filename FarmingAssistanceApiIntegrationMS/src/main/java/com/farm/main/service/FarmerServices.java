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

import com.farm.main.Entity.FarmerEntity;
import com.farm.main.Entity.Login;
import com.farm.main.exception.MyFarmingException;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class FarmerServices {

	@Autowired
	RegisterLoginServices registerservices;

	// ********************** add farmer **************************** //
	public ResponseEntity<String> addFarmer(FarmerEntity request, RestTemplate restTemplate, String username,
			String password) throws MyFarmingException, URISyntaxException, JSONException {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		Login login = new Login(username, password);
		Long custId = (Long) registerservices.getCustomerId(login, restTemplate);
		String role = registerservices.getCustomerRole(login, restTemplate);
		if (custId == request.getFarmerId() && role.equals("farmer")) {
			//System.out.println("inside loop");
			final String baseUrl = "http://localhost:8081/farmer/addFarmer";
			URI uri = new URI(baseUrl);
			HttpEntity<FarmerEntity> entity = new HttpEntity<>(request, header);
			//System.out.println(entity.toString());
			ResponseEntity<String> result = restTemplate.postForEntity(uri, entity, String.class);
			return result;
		} else
			return new ResponseEntity<>("Farmer Id not found", HttpStatus.NOT_FOUND);
	}

	// ********************** get farmer List **************************** //
	public String getFarmers(RestTemplate restTemplate) {
		return restTemplate.getForObject("http://localhost:8081/farmer/findFarmerList", String.class);
	}

	// ********************** get farmer by id **************************** //

	public FarmerEntity getFarmer(Long farmerId, RestTemplate restTemplate) {
		return restTemplate.getForObject(
				"http://localhost:8081/farmer/getFarmerById?farmerId=" + farmerId, FarmerEntity.class);
	}

	// ********************** update farmer by id **************************** //
	public ResponseEntity<String> updateFarmer(FarmerEntity farmer, Long farmerId, RestTemplate restTemplate,
			String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<FarmerEntity> httpEntity = new HttpEntity<>(farmer, headers);
		return restTemplate.exchange("http://localhost:8081/farmer/updateFarmer/" + farmerId,
				HttpMethod.PUT, httpEntity, String.class, farmerId);
	}

	// ********************** delete farmer by id **************************** //
	public ResponseEntity<String> deleteFarmer(Long farmerId, RestTemplate restTemplate, String username, String password)
			throws  URISyntaxException, JSONException, MyFarmingException {
		HttpHeaders headers = new HttpHeaders();
		Login login = new Login(username, password);
		Long custId = (Long) registerservices.getCustomerId(login, restTemplate);
		String role = registerservices.getCustomerRole(login, restTemplate);
		if (custId == farmerId && role == "farmer") {
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Long> httpEntity = new HttpEntity<>(farmerId, headers);
			return restTemplate.exchange("http://localhost:8081/farmer/deleteFarmer/" + farmerId,
					HttpMethod.DELETE, httpEntity, String.class);
		} else
			return new ResponseEntity<>("Farmers record not deleted with id " + farmerId, HttpStatus.UNAUTHORIZED);
	}

}
