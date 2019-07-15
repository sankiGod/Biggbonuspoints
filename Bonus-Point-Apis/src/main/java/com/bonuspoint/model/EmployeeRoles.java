package com.bonuspoint.model;

import java.io.Serializable;
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
@Table(name="bp_employee_roles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class EmployeeRoles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rId;
	
	@Column(name="employee_ID", unique= true)
	private String employeeID;
	
	@Column(name="project_onboarding")
	private boolean projectOnboarding = false;
	
	@Column(name="merchant_onboarding")
	private boolean merchantOnboarding = false;
	
	@Column(name="merchant_send_credentials")
	private boolean merchantSendCredentials = false;
	
	@Column(name="merchant_map_bonus_point")
	private boolean merchantMapBonusPoint = false;
	
	@Column(name="merchant_top_up_limit")
	private boolean merchantTopUpLimit = false;
	
	@Column(name="merchant_status")
	private boolean merchantStatus = false;
	
	@Column(name="merchant_login_page_info")
	private boolean merchantLoginPageInfo = false;
	
	@Column(name="merchant_send_notification")
	private boolean merchantSendNotification = false;
	
	@Column(name="merchant_received_offers")
	private boolean merchantReceivedOffers = false;
	
	@Column(name="modify_customer_details")
	private boolean modifyCustomerDetails = false;
	
	@Column(name="customer_send_notification")
	private boolean customerSendNotification = false;
	
	@Column(name="create_employee")
	private boolean createEmployee = false;
	
	@Column(name="assign_employee_roles")
	private boolean assignEmployeeRoles = false;
	
	@Column(name="payment_details")
	private boolean paymentDetails = false;
	
	@Column(name="account_info")
	private boolean accountInfo = false;
	
	@Column(name="reports")
	private boolean reports = false;
	
	@Column(name="helpdesk_action")
	private boolean helpdeskAction = false;
	
	@Column(name="email_settings")
	private boolean emailSettings = false;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public long getrId() {
		return rId;
	}

	public void setrId(long rId) {
		this.rId = rId;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public boolean isProjectOnboarding() {
		return projectOnboarding;
	}

	public void setProjectOnboarding(boolean projectOnboarding) {
		this.projectOnboarding = projectOnboarding;
	}

	public boolean isMerchantOnboarding() {
		return merchantOnboarding;
	}

	public void setMerchantOnboarding(boolean merchantOnboarding) {
		this.merchantOnboarding = merchantOnboarding;
	}

	public boolean isMerchantSendCredentials() {
		return merchantSendCredentials;
	}

	public void setMerchantSendCredentials(boolean merchantSendCredentials) {
		this.merchantSendCredentials = merchantSendCredentials;
	}

	public boolean isMerchantMapBonusPoint() {
		return merchantMapBonusPoint;
	}

	public void setMerchantMapBonusPoint(boolean merchantMapBonusPoint) {
		this.merchantMapBonusPoint = merchantMapBonusPoint;
	}

	public boolean isMerchantTopUpLimit() {
		return merchantTopUpLimit;
	}

	public void setMerchantTopUpLimit(boolean merchantTopUpLimit) {
		this.merchantTopUpLimit = merchantTopUpLimit;
	}

	public boolean isMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(boolean merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public boolean isMerchantLoginPageInfo() {
		return merchantLoginPageInfo;
	}

	public void setMerchantLoginPageInfo(boolean merchantLoginPageInfo) {
		this.merchantLoginPageInfo = merchantLoginPageInfo;
	}

	public boolean isMerchantSendNotification() {
		return merchantSendNotification;
	}

	public void setMerchantSendNotification(boolean merchantSendNotification) {
		this.merchantSendNotification = merchantSendNotification;
	}

	public boolean isMerchantReceivedOffers() {
		return merchantReceivedOffers;
	}

	public void setMerchantReceivedOffers(boolean merchantReceivedOffers) {
		this.merchantReceivedOffers = merchantReceivedOffers;
	}

	public boolean isModifyCustomerDetails() {
		return modifyCustomerDetails;
	}

	public void setModifyCustomerDetails(boolean modifyCustomerDetails) {
		this.modifyCustomerDetails = modifyCustomerDetails;
	}

	public boolean isCustomerSendNotification() {
		return customerSendNotification;
	}

	public void setCustomerSendNotification(boolean customerSendNotification) {
		this.customerSendNotification = customerSendNotification;
	}

	public boolean isCreateEmployee() {
		return createEmployee;
	}

	public void setCreateEmployee(boolean createEmployee) {
		this.createEmployee = createEmployee;
	}

	public boolean isAssignEmployeeRoles() {
		return assignEmployeeRoles;
	}

	public void setAssignEmployeeRoles(boolean assignEmployeeRoles) {
		this.assignEmployeeRoles = assignEmployeeRoles;
	}

	public boolean isPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(boolean paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public boolean isAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(boolean accountInfo) {
		this.accountInfo = accountInfo;
	}

	public boolean isReports() {
		return reports;
	}

	public void setReports(boolean reports) {
		this.reports = reports;
	}

	public boolean isHelpdeskAction() {
		return helpdeskAction;
	}

	public void setHelpdeskAction(boolean helpdeskAction) {
		this.helpdeskAction = helpdeskAction;
	}

	public boolean isEmailSettings() {
		return emailSettings;
	}

	public void setEmailSettings(boolean emailSettings) {
		this.emailSettings = emailSettings;
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
