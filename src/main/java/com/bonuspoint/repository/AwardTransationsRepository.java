package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.AwardTransactions;

@Repository
public interface AwardTransationsRepository extends JpaRepository<AwardTransactions, Long> {

	List<AwardTransactions> findByProjectID(String projectID);

	List<AwardTransactions> findByMerchantID(String merchantID);

	List<AwardTransactions> findByCustomerID(String customerID);

	List<AwardTransactions> findByMerchantIDAndCustomerID(String merchantID, String customerID);

	AwardTransactions findByRrn(String rrn);

	List<AwardTransactions> findFirst10ByCustomerIDOrderByCreatedAtDesc(String customerID);

	List<AwardTransactions> findByCustomerIDAndCreatedAtBetween(String customerID, Date createdAtStart,
			Date createdAtEnd);

	List<AwardTransactions> findByProjectIDAndCreatedAtBetween(String projectID, Date createdAtStart,
			Date createdAtEnd);

	List<AwardTransactions> findFirst10ByMerchantIDOrderByCreatedAtDesc(String merchantID);
	
	List<AwardTransactions> findFirst10ByMerchantIDAndTerminalIDOrderByCreatedAtDesc(String merchantID, String terminalID);

	List<AwardTransactions> findByMerchantIDAndCreatedAtBetween(String merchantID, Date createdAtStart,
			Date createdAtEnd);
	
	List<AwardTransactions> findFirst10ByMerchantIDAndCustomerIDOrderByCreatedAtDesc(String merchantID, String customerID);
	
	List<AwardTransactions> findFirst10ByProjectIDAndCustomerIDOrderByCreatedAtDesc(String projectID, String customerID);
}
