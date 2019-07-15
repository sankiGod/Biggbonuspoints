package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Employee;
import com.bonuspoint.model.EmployeeRoles;
import com.bonuspoint.rest.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/getAll")
	List<Employee> getAll() {
		return service.findAll();
	}

	@PostMapping("/create")
	ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		Employee emp = service.createEmployee(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}

	@PutMapping("/update/{eId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long eId, @Valid @RequestBody Employee employee) {
		Employee emp = service.updateEmployee(eId, employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@GetMapping("/getByEmployeeId/{employeeID}")
	public ResponseEntity<Employee> getByEmployeeId(@PathVariable String employeeID) {
		Employee emp = service.getByEmployeeId(employeeID);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@GetMapping("/getByEmailId/{emailId}")
	public ResponseEntity<Employee> getByEmailId(@PathVariable String emailId) {
		Employee emp = service.getByEmailId(emailId);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@GetMapping("/getByMobileNumber/{mobileNumber}")
	public ResponseEntity<Employee> getByMobileNumber(@PathVariable String mobileNumber) {
		Employee emp = service.getByMobileNumber(mobileNumber);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@GetMapping("/getRolesByEmployeeId/{employeeID}")
	public ResponseEntity<EmployeeRoles> getRolesByEmployeeId(@PathVariable String employeeID) {
		EmployeeRoles roles = service.getRolesByEmployeeId(employeeID);
		return new ResponseEntity<EmployeeRoles>(roles, HttpStatus.OK);
	}

	@PostMapping("/assignEmployeeRoles")
	public ResponseEntity<EmployeeRoles> assignEmployeeRole(@Valid @RequestBody EmployeeRoles roles) {
		EmployeeRoles role = service.assignEmployeeRoles( roles);
		return new ResponseEntity<EmployeeRoles>(role, HttpStatus.CREATED);
	}

}
