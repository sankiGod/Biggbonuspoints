package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.EmployeeRoles;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoles, Long> {

	EmployeeRoles findByEmployeeID(String employeeID);
}
