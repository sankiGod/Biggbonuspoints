package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustomerID(String customerID);

	Customer findByMobileNumber(String mobileNumber);

	Customer findByEmailId(String emailId);
	
	Customer findByAadharNumber(String aadharNumber);
	
	Customer findByPanNumber(String panNumber);

	void deleteByMobileNumber(String mobileNumber);
	
	void deleteByCustomerID(String CID);

}
