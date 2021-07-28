package com.farm.main.Entity;

import java.util.List;
import java.util.Set;

public class Retailer {

	private Long retailerId;

	public enum Gender {
		male, female
	}

	private Gender gender;
	private long retailerPhone;
	private int retailerShopNum;
	private String shopName;
	private List<Address> retailerAddress;

	public Long getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}

	public long getRetailerPhone() {
		return retailerPhone;
	}

	public void setRetailerPhone(long retailerPhone) {
		this.retailerPhone = retailerPhone;
	}

	public int getRetailerShopNum() {
		return retailerShopNum;
	}

	public void setRetailerShopNum(int retailerShopNum) {
		this.retailerShopNum = retailerShopNum;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Address> getRetailerAddress() {
		return retailerAddress;
	}

	public void setRetailerAddress(List<Address> retailerAddress) {
		this.retailerAddress = retailerAddress;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Override
	public String toString() {
		return "Retailer [retailerId=" + retailerId + ", gender=" + gender + ", retailerPhone=" + retailerPhone
				+ ", retailerShopNum=" + retailerShopNum + ", shopName=" + shopName + ", retailerAddress="
				+ retailerAddress + "]";
	}



}
