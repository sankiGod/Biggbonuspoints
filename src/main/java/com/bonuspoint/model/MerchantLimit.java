package com.bonuspoint.model;

import java.math.BigDecimal;
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
@Table(name = "bp_merchant_limit")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class MerchantLimit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ml_Id")
	private long mlId;

	@Column(name = "merchant_ID", unique = true)
	private String merchantID;

	@Column(name = "merchant_limit", precision = 20, scale = 2)
	private BigDecimal merchantLimit;
	
	@Column(name = "due_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getMlId() {
		return mlId;
	}

	public void setMlId(long mlId) {
		this.mlId = mlId;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public BigDecimal getMerchantLimit() {
		return merchantLimit;
	}

	public void setMerchantLimit(BigDecimal merchantLimit) {
		this.merchantLimit = merchantLimit;
	}

	
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
