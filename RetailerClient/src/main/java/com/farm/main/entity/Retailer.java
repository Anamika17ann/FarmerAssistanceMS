package com.farm.main.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
//import javax.persistence.AttributeOverride;
//import javax.persistence.AttributeOverrides;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_retailer")
public class Retailer {

	@Id
	private int retailerId;

	public enum Gender {
		male, female
	}

	@Enumerated(EnumType.STRING)
	private Gender gender;
	private long retailerPhone;
	private int retailerShopNum;
	private String shopName;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "AddressRef", joinColumns = @JoinColumn(name = "retailerId"), inverseJoinColumns = @JoinColumn(name = "addressId"))
	private List<Address> retailerAddress;

	/*
	 * private Address retailerHomeAddress;
	 * 
	 * @AttributeOverrides({
	 * 
	 * @AttributeOverride(name = "street", column = @Column( name = "shopStreet")),
	 * 
	 * @AttributeOverride(name = "city", column = @Column(name = "shopCity")),
	 * 
	 * @AttributeOverride(name = "state", column = @Column(name = "shopState")),
	 * 
	 * @AttributeOverride(name = "pincode", column = @Column(name = "shopPincode"))
	 * }) private Address retailerShopAddress;
	 */

	public int getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(int retailerId) {
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

	/*
	 * public Address getRetailerHomeAddress() { return retailerHomeAddress; }
	 * public void setRetailerHomeAddress(Address retailerHomeAddress) {
	 * this.retailerHomeAddress = retailerHomeAddress; }
	 * 
	 * public Address getRetailerShopAddress() { return retailerShopAddress; }
	 * public void setRetailerShopAddress(Address retailerShopAddress) {
	 * this.retailerShopAddress = retailerShopAddress; }
	 * 
	 * @Override public String toString() { return "Retailer [rId=" + rId +
	 * ", reatailerPhone=" + reatailerPhone + ", retailerShopNum=" + retailerShopNum
	 * + ", retailerHomeAddress=" + retailerHomeAddress + ", retailerShopAddress=" +
	 * retailerShopAddress + "]"; }
	 * 
	 * @Override public String toString() { return "Retailer [rId=" + rId +
	 * ", reatailerPhone=" + reatailerPhone + ", retailerShopNum=" + retailerShopNum
	 * + ", retailerHomeAddress=" + retailerHomeAddress + "]"; }
	 */

}
