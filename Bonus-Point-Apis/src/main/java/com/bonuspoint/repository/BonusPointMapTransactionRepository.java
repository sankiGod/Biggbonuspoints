package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.BonusPointMapTransactions;

public interface BonusPointMapTransactionRepository extends JpaRepository<BonusPointMapTransactions, Long> {

	List<BonusPointMapTransactions> findFirst10ByMerchantIDOrderByCreatedAtDesc(String merchantID);
	
	List<BonusPointMapTransactions> findFirst10ByProjectIDOrderByCreatedAtDesc(String projectID);
}
