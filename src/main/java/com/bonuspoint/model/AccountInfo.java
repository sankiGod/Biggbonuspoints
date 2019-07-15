package com.bonuspoint.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_account_info")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true, ignoreUnknown = true)
public class AccountInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ai_Id")
	private long aiId;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "payable_to")
	private String payableTo;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "city")
	private String city;
	
	@Column(name="pincode")
	private String pincode;

	public long getAiId() {
		return aiId;
	}

	public void setAiId(long aiId) {
		this.aiId = aiId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPayableTo() {
		return payableTo;
	}

	public void setPayableTo(String payableTo) {
		this.payableTo = payableTo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
}
