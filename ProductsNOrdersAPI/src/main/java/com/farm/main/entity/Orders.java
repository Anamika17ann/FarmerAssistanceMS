package com.farm.main.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_orders_new")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	private Long buyersId;

	private Long sellersId;

	private String productName;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date postedDate;

	private String productWeight;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date lastUpdatedDate;

	@PrePersist
	private void onCreate() {
		lastUpdatedDate = new Date();
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Product product;

	public Orders() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getBuyersId() {
		return buyersId;
	}

	public void setBuyersId(Long buyersId) {
		this.buyersId = buyersId;
	}

	public Long getSellersId() {
		return sellersId;
	}

	public void setSellersId(Long sellersId) {
		this.sellersId = sellersId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", buyersId=" + buyersId + ", sellersId=" + sellersId + ", status="
				+ status + ", postedDate=" + postedDate + ", productWeight=" + productWeight + ", lastUpdatedDate="
				+ lastUpdatedDate + ", product=" + product + "]";
	}

}
