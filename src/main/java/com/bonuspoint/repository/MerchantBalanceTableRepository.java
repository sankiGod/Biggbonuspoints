package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.MerchantsBalanceTables;

public interface MerchantBalanceTableRepository extends JpaRepository<MerchantsBalanceTables, Long> {

	List<MerchantsBalanceTables> findByProjectID(String projectID);

	MerchantsBalanceTables findByMerchantID(String merchantID);
	
	List<MerchantsBalanceTables> findByUpdatedAtBetween(Date startDate, Date endDate);

}
