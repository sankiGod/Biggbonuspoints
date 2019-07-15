package com.bonuspoint.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.CustomersBalanceTables;
import com.bonuspoint.model.MerchantsBalanceTables;
import com.bonuspoint.repository.CustomerBalanceTableRepository;
import com.bonuspoint.repository.MerchantBalanceTableRepository;

@Service
public class BalanceTablesService {

	@Autowired
	CustomerBalanceTableRepository cbrepository;

	@Autowired
	MerchantBalanceTableRepository mbrepository;

	public CustomersBalanceTables getCustomerTableByCustomerIdAndProjectID(String customerID , String projectID) {
		if (cbrepository.findByCustomerIDAndProjectID(customerID,projectID) != null) {
			return cbrepository.findByCustomerIDAndProjectID(customerID,projectID);
		} else
			throw new ResourceNotFoundException("CustomerBalanceTable", "customerID", customerID);
	}

	public MerchantsBalanceTables getMerchantTableByMerchantId(String merchantID) {
		if (mbrepository.findByMerchantID(merchantID) != null) {
			return mbrepository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("MerchantsBalanceTable", "merchantID", merchantID);
	}

	public List<CustomersBalanceTables> getCustomerTablesByProjectId(String projectID) {
		if (!cbrepository.findByProjectID(projectID).isEmpty()) {
			return cbrepository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("CustomerBalanceTable", "projectID", projectID);
	}

	public List<MerchantsBalanceTables> getMerchantTablesByProjectId(String projectID) {
		if (!mbrepository.findByProjectID(projectID).isEmpty()) {
			return mbrepository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("MerchantsBalanceTable", "projectID", projectID);
	}

}
