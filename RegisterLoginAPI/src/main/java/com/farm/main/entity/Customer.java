package com.farm.main.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.type.TrueFalseType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "tbl_customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 6325307718376939175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "customer_name")
	private String fullName;

	@Column(name = "customer_email_id")
	private String emailId;

	@Column(name = "password")
	private String password;
	@Transient
	@Column(name = "confirm_password")
	private String confirmPassword;

	@Column(name = "customer_role")
	private String customerRole;

	@Column(name = "user_name", unique = true)
	private String userName;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "reg_date")
	private Date customerRegDate;

	@Column(name = "last_updated")
	private Date lastUpdatedDate;

	public Customer(Long customerId, String fullName, String emailId, String password, String confirmPassword,
			String customerRole, String userName, Date customerRegDate, Date lastUpdatedDate) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.customerRole = customerRole;
		this.userName = userName;
		this.customerRegDate = customerRegDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Customer(String password, String username) {
		super();
		this.password = password;
		this.userName = username;
	}

	public Customer(String fullName, String emailId, String password, String confirmPassword, String customerRole,
			String username, Date customerRegDate) {
		super();
		this.fullName = fullName;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.customerRole = customerRole;
		this.userName = username;
		this.customerRegDate = customerRegDate;
	}

	public Customer() { // TODO Auto-generated constructor stub
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public Date getCustomerRegDate() {
		return customerRegDate;
	}

	public void setCustomerRegDate(Date customerRegDate) {
		this.customerRegDate = customerRegDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", fullName=" + fullName + ", emailId=" + emailId + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", customerRole=" + customerRole + ", userName="
				+ userName + ", customerRegDate=" + customerRegDate + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}

}
