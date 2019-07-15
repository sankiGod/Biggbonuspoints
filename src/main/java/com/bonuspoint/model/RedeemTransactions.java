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
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_redeem_transactions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class RedeemTransactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rt_Id")
	private long rtId;

	@Column(name = "project_ID")
	private String projectID;

	@Column(name = "merchant_ID")
	private String merchantID;

	@Column(name = "terminal_ID")
	private String terminalID;

	@Column(name = "customer_ID")
	private String customerID;

	@Column(name = "mobile_number")
	@Size(max = 10, min = 10, message = "Invalid Mobile Number")
	private String mobileNumber;

	@Column(name = "redeem_code")
	private String redeemCode;

	@Column(name = "purchase_amount", precision = 20, scale = 2)
	private BigDecimal purchaseAmount;

	@Column(name = "redeem_points_accrued")
	private int redeemPointsAccrued;

	@Column(name = "redeem_amount", precision = 20, scale = 2)
	private BigDecimal redeemAmount;

	@Column(name = "rrn")
	private String rrn;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getRtId() {
		return rtId;
	}

	public void setRtId(long rtId) {
		this.rtId = rtId;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getTerminalID() {
		return terminalID;
	}

	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRedeemCode() {
		return redeemCode;
	}

	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}

	public int getRedeemPointsAccrued() {
		return redeemPointsAccrued;
	}

	public void setRedeemPointsAccrued(int redeemPointsAccrued) {
		this.redeemPointsAccrued = redeemPointsAccrued;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public BigDecimal getRedeemAmount() {
		return redeemAmount;
	}

	public void setRedeemAmount(BigDecimal redeemAmount) {
		this.redeemAmount = redeemAmount;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
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
