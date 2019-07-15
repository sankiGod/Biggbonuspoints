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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_project")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true , ignoreUnknown = true)
public class Project {
	@Id
	@Column(name = "p_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_ID")
	private String projectID;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "mobile_number", unique = true )
	@Size(max=10, min=10 , message="Invalid Mobile Number")
	private String mobileNumber;

	@Column(name = "email_id", unique = true)
	@Email(message="Invalid Email Address")
	private String emailId;

	@Column(name = "abbrv")
	private String abbrv;

	@Column(name = "legal_name")
	private String legalName;

	@Embedded
	private ProjectAddress address;

	@Column(name = "is_transfer_allowed_within_project")
	private boolean isTransferAllowedWithinProject; 
	
	@Column(name = "is_corporate")
	private boolean isCorporate;
	
	@Column(name="project_type")
	private String projectType;
	
	@Column(name = "logo")
	private String logo;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

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

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public String getAbbrv() {
		return abbrv;
	}

	public void setAbbrv(String abbrv) {
		this.abbrv= abbrv;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public ProjectAddress getAddress() {
		return address;
	}

	public void setAddress(ProjectAddress address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean getIsTransferAllowedWithinProject() {
		return isTransferAllowedWithinProject;
	}

	public void setIsTransferAllowedWithinProject(boolean isTransferAllowedWithinProject) {
		this.isTransferAllowedWithinProject = isTransferAllowedWithinProject;
	}

	public boolean getIsCorporate() {
		return isCorporate;
	}

	public void setIsCorporate(boolean isCorporate) {
		this.isCorporate = isCorporate;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Override
	public String toString() {
		return "Project [pId=" + pId + ", projectName=" + projectName + ", projectID=" + projectID + ", contactPerson="
				+ contactPerson + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", abbrv=" + abbrv
				+ ", legalName=" + legalName + ", address=" + address + ", isTransferAllowedWithinProject="
				+ isTransferAllowedWithinProject + ", isCorporate=" + isCorporate + ", projectType=" + projectType
				+ ", logo=" + logo + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
