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

@Entity
@Table(name = "bp_corporate_accounts")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class CorporateAccounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ca_Id")
	private long caId;

	@Column(name = "project_ID", unique = true)
	private String projectID;

	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "corporate_bonus_point")
	private int corporateBonusPoint;

	@Column(name = "corporate_bonus_amount", precision=20, scale=2)
	private BigDecimal corporateBonusAmount;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public long getCaId() {
		return caId;
	}

	public void setCaId(long caId) {
		this.caId = caId;
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

	public int getCorporateBonusPoint() {
		return corporateBonusPoint;
	}

	public void setCorporateBonusPoint(int corporateBonusPoint) {
		this.corporateBonusPoint = corporateBonusPoint;
	}

	public BigDecimal getCorporateBonusAmount() {
		return corporateBonusAmount;
	}

	public void setCorporateBonusAmount(BigDecimal corporateBonusAmount) {
		this.corporateBonusAmount = corporateBonusAmount;
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
