package com.farm.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "product_quantity")
	private float productQuantity;

	@Column(name = "product_price_per_kg")
	private float productPricePerKg;

	@Column(name = "customer_role")
	private String customerRole;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public float getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(float productQuantity) {
		this.productQuantity = productQuantity;
	}

	public float getProductPricePerKg() {
		return productPricePerKg;
	}

	public void setProductPricePerKg(float productPricePerKg) {
		this.productPricePerKg = productPricePerKg;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", customerId=" + customerId
				+ ", productQuantity=" + productQuantity + ", productPricePerKg=" + productPricePerKg
				+ ", customerRole=" + customerRole + "]";
	}

}
