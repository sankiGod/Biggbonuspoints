package com.bonuspoint.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;

@Service
public class BpAccountService {

	@Autowired
	CustomerMerchantBPAccountRepository repository;

	public List<CustomerMerchantBPAccount> getAllAccounts() {

		return repository.findAll();
	}

	public List<CustomerMerchantBPAccount> getAccountsByCustomerId(String customerID) {

		if (!repository.findByCustomerID(customerID).isEmpty()) {
			return repository.findByCustomerID(customerID);
		} else
			throw new ResourceNotFoundException("CustomerMerchantBPAccount", "customerID", customerID);
	}

	public List<CustomerMerchantBPAccount> getAccountsByMerchantId(String merchantID) {

		if (!repository.findByMerchantProjectID(merchantID).isEmpty()) {
			return repository.findByMerchantProjectID(merchantID);
		} else
			throw new ResourceNotFoundException("CustomerMerchantBPAccount", "merchantID", merchantID);
	}

	public CustomerMerchantBPAccount getByMerchantAndCustomerId(String customerID, String merchantID) {

		if (repository.findByCustomerIDAndMerchantProjectID(customerID, merchantID) != null) {
			return repository.findByCustomerIDAndMerchantProjectID(customerID, merchantID);
		} else
			throw new ResourceNotFoundException("CustomerMerchantBPAccount", "customerID & merchantID",
					customerID + merchantID);
	}

}
