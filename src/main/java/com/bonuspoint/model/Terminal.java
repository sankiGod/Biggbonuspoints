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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bp_terminal")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class Terminal {

	@Id
	@Column(name = "t_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tId;

	@Column(name = "project_ID")
	private String projectID;

	@Column(name = "merchant_ID")
	private String merchantID;

	@Column(name = "terminal_ID", unique = true)
	private String terminalID;

	@Column(name = "password")
	private String password;

	@Column(name = "pin")
	private int pin;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Terminal() {
		super();
	}

	public Terminal(String projectID, String merchantID) {
		super();
		this.projectID = projectID;
		this.merchantID = merchantID;
	}

	public long gettId() {
		return tId;
	}

	public void settId(long tId) {
		this.tId = tId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
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

	@Override
	public String toString() {
		return "Terminal [tId=" + tId + ", projectID=" + projectID + ", merchantID=" + merchantID + ", terminalID="
				+ terminalID + ", password=" + password + ", pin=" + pin + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
