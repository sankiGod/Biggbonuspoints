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
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_customer_summary", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"customer_ID", "merchant_ID", "mobile_number"})})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class CustomerSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cs_Id")
	private long csId;

	@Column(name = "customer_ID")
	private String customerID;
	
	@Column(name="merchant_ID")
	private String merchantID;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="mobile_number")
	private String mobileNumber;

	@Column(name = "total_bonus_points")
	private int totalBonusPoints = 0;

	@Column(name = "total_bonus_amount", precision = 20, scale = 2)
	private BigDecimal totalBonusAmount = new BigDecimal(0);

	@Column(name = "total_bonus_points_awarded")
	private int totalBonusPointsAwarded = 0;

	@Column(name = "total_bonus_amount_awarded", precision = 20, scale = 2)
	private BigDecimal totalBonusAmountAwarded = new BigDecimal(0);

	@Column(name = "total_bonus_points_redeemed")
	private int totalBonusPointsRedeemed = 0;

	@Column(name = "total_bonus_amount_redeemed", precision = 20, scale = 2)
	private BigDecimal totalBonusAmountRedeemed = new BigDecimal(0);

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public int getTotalBonusPoints() {
		return totalBonusPoints;
	}

	public void setTotalBonusPoints(int totalBonusPoints) {
		this.totalBonusPoints = totalBonusPoints;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getTotalBonusPointsAwarded() {
		return totalBonusPointsAwarded;
	}

	public void setTotalBonusPointsAwarded(int totalBonusPointsAwarded) {
		this.totalBonusPointsAwarded = totalBonusPointsAwarded;
	}

	public int getTotalBonusPointsRedeemed() {
		return totalBonusPointsRedeemed;
	}

	public void setTotalBonusPointsRedeemed(int totalBonusPointsRedeemed) {
		this.totalBonusPointsRedeemed = totalBonusPointsRedeemed;
	}

	public BigDecimal getTotalBonusAmount() {
		return totalBonusAmount;
	}

	public void setTotalBonusAmount(BigDecimal totalBonusAmount) {
		this.totalBonusAmount = totalBonusAmount;
	}

	public BigDecimal getTotalBonusAmountAwarded() {
		return totalBonusAmountAwarded;
	}

	public void setTotalBonusAmountAwarded(BigDecimal totalBonusAmountAwarded) {
		this.totalBonusAmountAwarded = totalBonusAmountAwarded;
	}

	public BigDecimal getTotalBonusAmountRedeemed() {
		return totalBonusAmountRedeemed;
	}

	public void setTotalBonusAmountRedeemed(BigDecimal totalBonusAmountRedeemed) {
		this.totalBonusAmountRedeemed = totalBonusAmountRedeemed;
	}

	public long getCsId() {
		return csId;
	}

	public void setCsId(long csId) {
		this.csId = csId;
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
