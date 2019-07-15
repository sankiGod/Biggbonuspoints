package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.CustomersBalanceTables;

public interface CustomerBalanceTableRepository extends JpaRepository<CustomersBalanceTables, Long> {

	List<CustomersBalanceTables> findByProjectID(String projectID);

	CustomersBalanceTables findByCustomerIDAndProjectID(String customerID, String projectID);
	
	List<CustomersBalanceTables> findByUpdatedAtBetween(Date startDate, Date endDate);
}
