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
@Table(name="bp_bonusPointMap")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdTime", "updatedTime","createdDate","updatedDate"}, 
        allowGetters = true)
public class BonusPointMap {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bpm_Id")
	private long bpmId;
	
	@Column(name="project_ID")
	private String projectID;
	
	@Column(name="merchant_ID", unique= true)
	private String merchantID;
	
	@Column(name="currency")
	private String currency = "INR";
	
	@Column(name="BP_per_100")
	private int bpPer100 ;
	
	@Column(name="amount_per_BP", precision=20, scale=2)
	private BigDecimal amountPerBP;
	
	@Column(name="corp_BP_per_100")
	private int corpBpPer100 ;
	
	@Column(name="corp_amount_per_BP", precision=20, scale=2)
	private BigDecimal corpAmountPerBP;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public long getBpmId() {
		return bpmId;
	}

	public void setBpmId(long bpmId) {
		this.bpmId = bpmId;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getBpPer100() {
		return bpPer100;
	}

	public void setBpPer100(int bpPer100) {
		this.bpPer100 = bpPer100;
	}

	public BigDecimal getAmountPerBP() {
		return amountPerBP;
	}

	public void setAmountPerBP(BigDecimal amountPerBP) {
		this.amountPerBP = amountPerBP;
	}

	public int getCorpBpPer100() {
		return corpBpPer100;
	}

	public void setCorpBpPer100(int corpBpPer100) {
		this.corpBpPer100 = corpBpPer100;
	}

	public BigDecimal getCorpAmountPerBP() {
		return corpAmountPerBP;
	}

	public void setCorpAmountPerBP(BigDecimal corpAmountPerBP) {
		this.corpAmountPerBP = corpAmountPerBP;
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
