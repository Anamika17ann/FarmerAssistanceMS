package com.farm.main.Entity;

import java.io.Serializable;
import java.util.List;

public class FarmerEntity implements Serializable {

	public enum Gender {
		female, male
	}

	private static final long serialVersionUID = 6325307718376939175L;

	private List<Address> address;

	private Long farmerId;

	private Gender gender;

	private String mobileNumber;

	private String productDetails;

	private String productName;

	public List<Address> getAddress() {
		return address;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public Gender getGender() {
		return gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public String getProductName() {
		return productName;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "FarmerEntity [farmerId=" + farmerId + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", productName=" + productName + "]";
	}

}
