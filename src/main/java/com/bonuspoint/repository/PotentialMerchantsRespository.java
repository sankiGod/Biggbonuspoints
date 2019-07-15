package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.PotentialMerchants;

public interface PotentialMerchantsRespository extends JpaRepository<PotentialMerchants, Long> {

	PotentialMerchants findByMobileNumber(String mobileNumber);
	
	PotentialMerchants findByEmailId(String emailId);
	
	List<PotentialMerchants> findByIsApprovedAndMerchantType(boolean status, String type);
	
	List<PotentialMerchants> findByIsApproved(boolean status);
	
	List<PotentialMerchants> findByMerchantType(String type);
}
