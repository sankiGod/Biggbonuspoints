package com.bonuspoint.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_email")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Email {

	@Id
	@Column(name = "email_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long emailID;

	@Column(name = "customer_email_Id")
	@javax.validation.constraints.Email(message = "Invalid Email Address")
	private String customerEmailID;

	@Column(name = "merchants_email_Id")
	@javax.validation.constraints.Email(message = "Invalid Email Address")
	private String merchantEmailID;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getEmailID() {
		return emailID;
	}

	public void setEmailID(long emailID) {
		this.emailID = emailID;
	}

	public String getCustomerEmailID() {
		return customerEmailID;
	}

	public void setCustomerEmailID(String customerEmailID) {
		this.customerEmailID = customerEmailID;
	}

	public String getMerchantEmailID() {
		return merchantEmailID;
	}

	public void setMerchantEmailID(String merchantEmailID) {
		this.merchantEmailID = merchantEmailID;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
