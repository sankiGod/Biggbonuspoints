package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

	Merchant findByMerchantID(String merchantID);
	
	List<Merchant> findByProjectID(String projectID);
	
	Merchant findByMobileNumber(String mobileNumber);
	
	Merchant findByEmailId(String emailId);
	
	Merchant findByShopName(String shopName);
	
	List<Merchant> findByStatus(boolean status);
	
	List<Merchant> findByStatusAndUpdatedAtBetween(boolean status, Date startDate, Date endDate);
	
}
