package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmployeeID(String employeeID);
	
	Employee findByMobileNumber(String mobileNumber);
	
	Employee findByEmailId(String emailId);
	
	Employee findByUsername(String username);
	
}
