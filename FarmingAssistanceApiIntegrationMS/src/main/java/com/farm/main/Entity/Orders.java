package com.farm.main.Entity;

import java.util.Date;

public class Orders {

	private String productWeight;

	private Long buyersId;

	private String status;

	public Orders() {

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBuyersId() {
		return buyersId;
	}

	public void setBuyersId(Long buyersId) {
		this.buyersId = buyersId;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	@Override
	public String toString() {
		return "Orders [productWeight=" + productWeight + ", buyersId=" + buyersId + ", status=" + status + "]";
	}

}
