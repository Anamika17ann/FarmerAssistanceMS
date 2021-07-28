package com.farm.main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_supplier")
public class SupplierEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="supplier_id")
	private Long supplierId;

	
	@Column(name="supplier_name")
	private String supplierName;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email_id")
	private String email_Id;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="location")
	private String address;
	
	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_image")
	private String productImage;

	@Column(name = "reg_date")
	private Date regDate;

	public SupplierEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierEntity(Long supplierId, String supplierName, String userName, String password, String email_Id,
			String mobileNumber, String address, String productName, String productImage, Date regDate) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.userName = userName;
		this.password = password;
		this.email_Id = email_Id;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.productName = productName;
		this.productImage = productImage;
		this.regDate = regDate;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "SupplierEntity [supplierId=" + supplierId + ", supplierName=" + supplierName + ", userName=" + userName
				+ ", password=" + password + ", email_Id=" + email_Id + ", mobileNumber=" + mobileNumber + ", address="
				+ address + ", productName=" + productName + ", productImage=" + productImage + ", regDate=" + regDate
				+ "]";
	}
	
	
	

}
