package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.CorporateMerchantsBalanceTables;

public interface CorporateMerchantsBalanceTablesRepository
		extends JpaRepository<CorporateMerchantsBalanceTables, Long> {

	CorporateMerchantsBalanceTables findByMerchantID(String merchantID);

	List<CorporateMerchantsBalanceTables> findByProjectID(String projectID);
}
