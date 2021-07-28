package com.farm.main.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class SupplierRequestEntity {

	
	private MultipartFile file;

	private String supplierName;

	private String password;

	private String userName;

	private String email_Id;

	private String mobileNumber;

	private String address;

	private String productName;

	private String productImage;

	private Date regDate;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "SupplierRequestEntity [file=" + file + ", supplierName=" + supplierName + ", password=" + password
				+ ", userName=" + userName + ", email_Id=" + email_Id + ", mobileNumber=" + mobileNumber + ", address="
				+ address + ", productName=" + productName + ", productImage=" + productImage + ", regDate=" + regDate
				+ "]";
	}

	
	
}
