package com.farm.main.entity;

//import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Embeddable
@Entity
@Table(name = "tbl_retailer_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	//private int cId;
	private String street;
	private String city;
	private String state;
	private int pincode;

	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}

	
	
	

}
