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
@Table(name="bp_bonusPointMapTransactions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdTime", "updatedTime","createdDate","updatedDate"}, 
        allowGetters = true)
public class BonusPointMapTransactions {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bpmt_Id")
	private long bpmtId;
	
	@Column(name="project_ID")
	private String projectID;
	
	@Column(name="merchant_ID")
	private String merchantID;
	
	@Column(name="currency")
	private String currency = "INR";
	
	@Column(name="mapped_by")
	private String mappedBy;
	
	@Column(name="new_BP_per_100")
	private int newBpPer100 ;
	
	@Column(name="new_amount_per_BP", precision=20, scale=2)
	private BigDecimal newAmountPerBP;
	
	@Column(name="prev_BP_per_100")
	private int prevBpPer100 ;
	
	@Column(name="prev_amount_per_BP", precision=20, scale=2)
	private BigDecimal prevAmountPerBP;
	
	@Column(name="corp_BP_per_100")
	private int corpBpPer100 ;
	
	@Column(name="corp_amount_per_BP", precision=20, scale=2)
	private BigDecimal corpAmountPerBP;
	
	@Column(name="mapping_type")
	private String mappingType;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public long getBpmtId() {
		return bpmtId;
	}

	public void setBpmtId(long bpmtId) {
		this.bpmtId = bpmtId;
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

	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	public int getNewBpPer100() {
		return newBpPer100;
	}

	public void setNewBpPer100(int newBpPer100) {
		this.newBpPer100 = newBpPer100;
	}

	public BigDecimal getNewAmountPerBP() {
		return newAmountPerBP;
	}

	public void setNewAmountPerBP(BigDecimal newAmountPerBP) {
		this.newAmountPerBP = newAmountPerBP;
	}

	public int getPrevBpPer100() {
		return prevBpPer100;
	}

	public void setPrevBpPer100(int prevBpPer100) {
		this.prevBpPer100 = prevBpPer100;
	}

	public BigDecimal getPrevAmountPerBP() {
		return prevAmountPerBP;
	}

	public void setPrevAmountPerBP(BigDecimal prevAmountPerBP) {
		this.prevAmountPerBP = prevAmountPerBP;
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

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
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
