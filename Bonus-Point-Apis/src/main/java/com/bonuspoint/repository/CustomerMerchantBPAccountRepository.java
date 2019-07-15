package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.CustomerMerchantBPAccount;

@Repository
public interface CustomerMerchantBPAccountRepository extends JpaRepository<CustomerMerchantBPAccount, Long> {
	
	CustomerMerchantBPAccount findByCustomerIDAndMerchantProjectID(String customerID, String merchantProjectID);
	
	List<CustomerMerchantBPAccount> findByCustomerID(String customerID);
	
	List<CustomerMerchantBPAccount> findByMerchantProjectID(String merchantProjectID);

}
