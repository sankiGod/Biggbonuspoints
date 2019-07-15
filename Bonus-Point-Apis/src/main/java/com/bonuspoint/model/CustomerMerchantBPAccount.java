package com.bonuspoint.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bp_customer_merchant_BP_account", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"customer_ID", "merchant_or_project_ID"})})
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class CustomerMerchantBPAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cm_Id")
	private long cmaId;
	
	@Column(name="customer_ID")
	private String customerID;
	
	@Column(name = "merchant_or_project_ID")
	private String merchantProjectID;
	
	@Column(name="shop_name")
	private String shopName;
	
	@Column(name="bonus_points")
	private int bonusPoints;
	
	@Column(name="bonus_point_amount", precision=20, scale=2)
	private BigDecimal bonusPointAmount;
	
	@Column(name="logo")
	@Lob
	private String logo;
	
	@Column(name="type")
	private String type;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public long getCmaId() {
		return cmaId;
	}

	public void setCmaId(long cmaId) {
		this.cmaId = cmaId;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getMerchantProjectID() {
		return merchantProjectID;
	}

	public void setMerchantProjectID(String merchantProjectID) {
		this.merchantProjectID = merchantProjectID;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public BigDecimal getBonusPointAmount() {
		return bonusPointAmount;
	}

	public void setBonusPointAmount(BigDecimal bonusPointAmount) {
		this.bonusPointAmount = bonusPointAmount;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
