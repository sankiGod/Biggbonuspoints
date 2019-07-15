package com.bonuspoint.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_merchant")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Merchant {

	@Id
	@Column(name = "m_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mId;

	@Column(name = "project_ID")
	private String projectID;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "mobile_number", unique = true)
	@Size(max = 10, min = 10, message = "Invalid Mobile Number")
	private String mobileNumber;

	@Column(name = "email_id", unique = true)
	@Email(message = "Invalid Email Address")
	private String emailId;

	@Column(name = "gst_number", unique = true)
	private String gstNumber;

	@Column(name = "aadhar_number", unique = true)
	@Size(max = 12, min = 12, message = "Invalid Aadhar Number")
	private String aadharNumber;

	@Column(name = "pan_number", unique = true)
	@Size(max = 10, min = 10, message = "Invalid Pan Number")
	private String panNumber;

	@Column(name = "merchant_ID", unique = true)
	private String merchantID;

	@Column(name = "shop_name", unique = true)
	private String shopName;

	@Column(name = "legal_name", unique = true)
	private String legalName;

	@Column(name = "logo")
	private String logo;

	@Embedded
	private MerchantAddress address;

	@Column(name = "status")
	private boolean status = true;

	@OneToMany
	@JoinColumn(name = "merchant_terminal_ID")
	private List<Terminal> terminals = new ArrayList<Terminal>();

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getmId() {
		return mId;
	}

	public void setmId(long mId) {
		this.mId = mId;
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
		if (!mobileNumber.isEmpty())
			this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		if (!emailId.isEmpty())
			this.emailId = emailId;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		if (!gstNumber.isEmpty())
			this.gstNumber = gstNumber;

	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		if (!aadharNumber.isEmpty())
			this.aadharNumber = aadharNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		if (!panNumber.isEmpty())
			this.panNumber = panNumber;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public MerchantAddress getAddress() {
		return address;
	}

	public void setAddress(MerchantAddress address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public List<Terminal> getTerminals() {
		return terminals;
	}

	public void setTerminals(List<Terminal> terminals) {
		this.terminals = terminals;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
