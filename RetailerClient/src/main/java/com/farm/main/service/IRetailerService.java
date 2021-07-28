package com.farm.main.service;

import java.util.List;
import java.util.Optional;

import com.farm.main.entity.Retailer;


public interface IRetailerService {

	public List<Retailer> getRetailers();

	public Optional<Retailer> getRetailer(int retailerId);

	public Retailer addRetailer(Retailer retailer);

	public Retailer updateRetailer(Retailer retailer);

	public String deleteRetailer(int retailerId);

	
	

}
