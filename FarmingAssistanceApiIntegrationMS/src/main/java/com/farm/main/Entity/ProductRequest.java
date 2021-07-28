package com.farm.main.Entity;

public class ProductRequest {

	private String productName;

	private Long customerId;

	private float productQuantity;

	private float productPricePerKg;

	private String customerRole;

	public ProductRequest(String productName, Long customerId, float productQuantity, float productPricePerKg,
			String customerRole) {
		super();
		this.productName = productName;
		this.customerId = customerId;
		this.productQuantity = productQuantity;
		this.productPricePerKg = productPricePerKg;
		this.customerRole = customerRole;
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
		return "ProductRequest [productName=" + productName + ", customerId=" + customerId + ", productQuantity="
				+ productQuantity + ", productPricePerKg=" + productPricePerKg + ", customerRole=" + customerRole + "]";
	}

}
