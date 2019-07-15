package com.bonuspoint.rest.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.Employee;
import com.bonuspoint.model.EmployeeRoles;
import com.bonuspoint.repository.EmployeeRepository;
import com.bonuspoint.repository.EmployeeRoleRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	EmployeeRoleRepository erepository;

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public Employee createEmployee(@Valid Employee employee) {

		try {
			Employee empDetails = repository.save(employee);
			empDetails.setEmployeeID(GenerateID.generateEmployeeID(empDetails.geteId()));
			return repository.save(empDetails);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Employee", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		}

	}

	public Employee updateEmployee(long eId, @Valid Employee employee) {
		if (repository.existsById(eId)) {
			return repository.save(employee);
		} else
			throw new ResourceNotFoundException("Employee", "eId", eId);

	}

	public Employee getByEmployeeId(String employeeID) {
		if (repository.findByEmployeeID(employeeID) != null) {
			return repository.findByEmployeeID(employeeID);
		} else
			throw new ResourceNotFoundException("Employee", "employeeID", employeeID);
	}

	public Employee getByEmailId(String emailId) {
		if (repository.findByEmailId(emailId) != null) {
			return repository.findByEmailId(emailId);
		} else
			throw new ResourceNotFoundException("Employee", "emailId", emailId);
	}

	public Employee getByMobileNumber(String mobileNumber) {
		if (repository.findByMobileNumber(mobileNumber) != null) {
			return repository.findByMobileNumber(mobileNumber);
		} else
			throw new ResourceNotFoundException("Employee", "mobileNumber", mobileNumber);
	}

	public Employee getByUsername(String username) {
		if (repository.findByUsername(username) != null) {
			return repository.findByUsername(username);
		} else
			throw new ResourceNotFoundException("Employee", "mobileNumber", username);
	}

	public EmployeeRoles getRolesByEmployeeId(String employeeID) {
		if (erepository.findByEmployeeID(employeeID) != null) {
			return erepository.findByEmployeeID(employeeID);
		} else
			throw new ResourceNotFoundException("EmployeeRoles", "employeeID", employeeID);
	}

	public EmployeeRoles assignEmployeeRoles(@Valid EmployeeRoles roles) {

		return erepository.save(roles);
	}

}
