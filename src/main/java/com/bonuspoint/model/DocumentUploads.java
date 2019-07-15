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
@Table(name="bp_doc_uploads")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class DocumentUploads {

	@Id
	@Column(name="doc_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long docId;
	
	@Column(name="privacy_policy_path")
	private String privacyPolicyPath;
	
	@Column(name="terms_and_conditions_path")
	private String termsAndConditionsPath;
	
	@Column(name="user_guide_path")
	private String userGuidePath;
	
	@Column(name="user_type")
	private String userType;
	
	@Column(name="update_counter")
	private long updateCounter = 0;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public long getDocId() {
		return docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public String getPrivacyPolicyPath() {
		return privacyPolicyPath;
	}

	public void setPrivacyPolicyPath(String privacyPolicyPath) {
		this.privacyPolicyPath = privacyPolicyPath;
	}

	public String getTermsAndConditionsPath() {
		return termsAndConditionsPath;
	}

	public void setTermsAndConditionsPath(String termsAndConditionsPath) {
		this.termsAndConditionsPath = termsAndConditionsPath;
	}

	public String getUserGuidePath() {
		return userGuidePath;
	}

	public void setUserGuidePath(String userGuidePath) {
		this.userGuidePath = userGuidePath;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public long getUpdateCounter() {
		return updateCounter;
	}

	public void setUpdateCounter(long updateCounter) {
		this.updateCounter = updateCounter;
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
