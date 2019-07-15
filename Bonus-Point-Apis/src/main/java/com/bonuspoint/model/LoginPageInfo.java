package com.bonuspoint.model;

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
import javax.validation.constraints.Email;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_login_page_info")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class LoginPageInfo {

	@Id
	@Column(name = "lp_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long lpId;

	@Column(name = "sales_contact_name")
	private String salesContactName;

	@Column(name = "sales_mobile_number")
	private String salesMobileNumber;

	@Column(name = "sales_email_id")
	@Email(message="Invalid Email ID")
	private String salesEmailId;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getLpId() {
		return lpId;
	}

	public void setLpId(long lpId) {
		this.lpId = lpId;
	}

	public String getSalesContactName() {
		return salesContactName;
	}

	public void setSalesContactName(String salesContactName) {
		this.salesContactName = salesContactName;
	}

	public String getSalesMobileNumber() {
		return salesMobileNumber;
	}

	public void setSalesMobileNumber(String salesMobileNumber) {
		this.salesMobileNumber = salesMobileNumber;
	}

	public String getSalesEmailId() {
		return salesEmailId;
	}

	public void setSalesEmailId(String salesEmailId) {
		this.salesEmailId = salesEmailId;
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
