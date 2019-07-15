package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.CustomerSummary;

public interface SummaryRepository extends JpaRepository<CustomerSummary, Long> {

	CustomerSummary findByCustomerIDAndMerchantID(String customerID, String merchantID);

	CustomerSummary findByMobileNumberAndMerchantID(String mobileNumber , String merchantID);
}
