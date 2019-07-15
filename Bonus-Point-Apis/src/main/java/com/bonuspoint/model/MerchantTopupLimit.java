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

/**
 * @author Anki
 *
 */
@Entity
@Table(name = "bp_merchant_topup_limit")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class MerchantTopupLimit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mtl_Id")
	private long mtlId;

	@Column(name = "project_ID")
	private String projectID;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "merchant_ID")
	private String merchantID;

	@Column(name = "agreed_topup_amount", precision = 20, scale = 2)
	private BigDecimal agreedTopupAmount;

	@Column(name = "actual_topup_paid", precision = 20, scale = 2)
	private BigDecimal actualTopupPaid;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "cheque_number")
	private String chequeNumber;

	@Column(name = "cheque_date")
	@Temporal(TemporalType.DATE)
	private Date chequeDate;

	@Column(name = "cheque_clearance_date")
	@Temporal(TemporalType.DATE)
	private Date chequeClearanceDate;

	@Column(name = "transaction_ID", unique = true)
	private String transactionID;

	@Column(name = "city")
	private String city;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getMtlId() {
		return mtlId;
	}

	public void setMtlId(long mtlId) {
		this.mtlId = mtlId;
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

	public BigDecimal getAgreedTopupAmount() {
		return agreedTopupAmount;
	}

	public void setAgreedTopupAmount(BigDecimal agreedTopupAmount) {
		this.agreedTopupAmount = agreedTopupAmount;
	}

	public BigDecimal getActualTopupPaid() {
		return actualTopupPaid;
	}

	public void setActualTopupPaid(BigDecimal actualTopupPaid) {
		this.actualTopupPaid = actualTopupPaid;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public Date getChequeClearanceDate() {
		return chequeClearanceDate;
	}

	public void setChequeClearanceDate(Date chequeClearanceDate) {
		this.chequeClearanceDate = chequeClearanceDate;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "MerchantTopupLimit [mtlId=" + mtlId + ", projectID=" + projectID + ", projectName=" + projectName
				+ ", merchantID=" + merchantID + ", agreedTopupAmount=" + agreedTopupAmount + ", actualTopupPaid="
				+ actualTopupPaid + ", bankName=" + bankName + ", chequeNumber=" + chequeNumber + ", chequeDate="
				+ chequeDate + ", chequeClearanceDate=" + chequeClearanceDate + ", transactionID=" + transactionID
				+ ", city=" + city + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
