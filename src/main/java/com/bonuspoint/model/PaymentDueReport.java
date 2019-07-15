package com.bonuspoint.model;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentDueReport {

	private String merchantID;

	private String projectID;

	private String contactPerson;

	private String mobileNumber;

	private String emailId;

	private Date dueDate;

	private BigDecimal merchantLimit;

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getMerchantLimit() {
		return merchantLimit;
	}

	public void setMerchantLimit(BigDecimal merchantLimit) {
		this.merchantLimit = merchantLimit;
	}

}
