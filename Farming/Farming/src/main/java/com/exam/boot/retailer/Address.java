package com.exam.boot.retailer;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String location;
	private String city;
	private String state;
	private int pincode;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "Address [location=" + location + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
	
	
	

}
