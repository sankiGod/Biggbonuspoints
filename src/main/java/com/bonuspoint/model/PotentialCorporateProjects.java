package com.bonuspoint.model;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity()
@Table(name="bp_potential_corp_projects",uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"contact_person", "mobile_number", "email_id", "company_name"})
	    , @UniqueConstraint(columnNames = {"contact_person", "email_id", "company_name"})
	    , @UniqueConstraint(columnNames = {"contact_person", "mobile_number", "company_name"})})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true , ignoreUnknown = true)
public class PotentialCorporateProjects {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pc_Id")
	private long pcId;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
	@Column(name = "mobile_number")
	@Size(max=10, min=10 , message="Invalid Mobile Number")
	private String mobileNumber;

	@Column(name = "email_id")
	@Email(message="Invalid Email Address")
	private String emailId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Embedded
	private ProjectAddress address;
	
	@Column(name="is_approved")
	private boolean isApproved = false;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getPcId() {
		return pcId;
	}

	public void setPcId(long pcId) {
		this.pcId = pcId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
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

	public ProjectAddress getAddress() {
		return address;
	}

	public void setAddress(ProjectAddress address) {
		this.address = address;
	}
	
	
}
