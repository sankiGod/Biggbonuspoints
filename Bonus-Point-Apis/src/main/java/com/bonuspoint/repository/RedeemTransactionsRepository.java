package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.RedeemTransactions;

@Repository
public interface RedeemTransactionsRepository extends JpaRepository<RedeemTransactions, Long> {

	List<RedeemTransactions> findByProjectID(String projectID);

	List<RedeemTransactions> findByMerchantID(String merchantID);

	List<RedeemTransactions> findByCustomerID(String customerID);

	List<RedeemTransactions> findByMerchantIDAndCustomerID(String merchantID, String customerID);

	RedeemTransactions findByRrn(String rrn);

	List<RedeemTransactions> findFirst10ByCustomerIDOrderByCreatedAtDesc(String customerID);

	List<RedeemTransactions> findByCustomerIDAndCreatedAtBetween(String customerID, Date createdAtStart,
			Date createdAtEnd);

	List<RedeemTransactions> findByProjectIDAndCreatedAtBetween(String projectID, Date createdAtStart,
			Date createdAtEnd);

	List<RedeemTransactions> findFirst10ByMerchantIDOrderByCreatedAtDesc(String merchantID);
	
	List<RedeemTransactions> findFirst10ByMerchantIDAndTerminalIDOrderByCreatedAtDesc(String merchantID, String terminalID);

	List<RedeemTransactions> findByMerchantIDAndCreatedAtBetween(String merchantID, Date createdAtStart,
			Date createdAtEnd);

	List<RedeemTransactions> findFirst10ByMerchantIDAndCustomerIDOrderByCreatedAtDesc(String merchantID, String customerID);

	List<RedeemTransactions> findFirst10ByProjectIDAndCustomerIDOrderByCreatedAtDesc(String projectID, String customerID);

}
