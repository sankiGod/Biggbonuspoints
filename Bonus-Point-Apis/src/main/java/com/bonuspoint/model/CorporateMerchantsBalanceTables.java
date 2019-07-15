package com.bonuspoint.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "bp_corporate_merchants_balance_table")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class CorporateMerchantsBalanceTables {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cmb_Id")
	private long cmbId;

	@Column(name = "project_ID")
	private String projectID;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "merchant_ID", unique = true)
	private String merchantID;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "mobile_number", unique = true)
	@Size(max = 10, min = 10, message = "Invalid Mobile Number")
	private String mobileNumber;

	@Column(name = "prevous_day_balance", precision = 20, scale = 2)
	private BigDecimal previousDayBalance = new BigDecimal(0);
	
	@Column(name = "total_points_awarded")
	private int totalPointsAwarded = 0;

	@Column(name = "total_amount_awarded", precision = 20, scale = 2)
	private BigDecimal totalAmountAwarded = new BigDecimal(0);

	@Column(name = "total_points_redeemed")
	private int totalPointsRedeemed = 0;

	@Column(name = "totalAmountRedeemed", precision = 20, scale = 2)
	private BigDecimal totalAmountRedeemed = new BigDecimal(0);

	@Column(name = "final_points")
	private int finalPoints = 0;

	@Column(name = "final_amount", precision = 20, scale = 2)
	private BigDecimal finalAmount = new BigDecimal(0);
	
	@Column(name = "current_balance", precision = 20, scale = 2)
	private BigDecimal currentBalance = new BigDecimal(0);

	@Embedded
	private MerchantAddress address;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getCmbId() {
		return cmbId;
	}

	public void setCmbId(long cmbId) {
		this.cmbId = cmbId;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public BigDecimal getPreviousDayBalance() {
		return previousDayBalance;
	}

	public void setPreviousDayBalance(BigDecimal previousDayBalance) {
		this.previousDayBalance = previousDayBalance;
	}

	public int getTotalPointsAwarded() {
		return totalPointsAwarded;
	}

	public void setTotalPointsAwarded(int totalPointsAwarded) {
		this.totalPointsAwarded = totalPointsAwarded;
	}

	public BigDecimal getTotalAmountAwarded() {
		return totalAmountAwarded;
	}

	public void setTotalAmountAwarded(BigDecimal totalAmountAwarded) {
		this.totalAmountAwarded = totalAmountAwarded;
	}

	public int getTotalPointsRedeemed() {
		return totalPointsRedeemed;
	}

	public void setTotalPointsRedeemed(int totalPointsRedeemed) {
		this.totalPointsRedeemed = totalPointsRedeemed;
	}

	public BigDecimal getTotalAmountRedeemed() {
		return totalAmountRedeemed;
	}

	public void setTotalAmountRedeemed(BigDecimal totalAmountRedeemed) {
		this.totalAmountRedeemed = totalAmountRedeemed;
	}

	public int getFinalPoints() {
		return finalPoints;
	}

	public void setFinalPoints(int finalPoints) {
		this.finalPoints = finalPoints;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public MerchantAddress getAddress() {
		return address;
	}

	public void setAddress(MerchantAddress address) {
		this.address = address;
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
