package com.exam.boot.retailer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Retailer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rId;
	private Address address;
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Retailer [rId=" + rId + ", address=" + address + "]";
	}
	
}
