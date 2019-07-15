package com.bonuspoint.rest.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Customer;
import com.bonuspoint.rest.service.CustomerService;

@RestController
@RequestMapping("/customers")
@Transactional
public class CustomerController {

	@Autowired
	CustomerService service;

	@GetMapping("/getAll")
	public List<Customer> getAllCustomers() {
		return service.getAllCustomers();
	}

	@PostMapping("/check")
	public ResponseEntity<Customer> checkCusotmer(@Valid @RequestBody Customer c) {
		Customer cust = service.checkCusotmer(c);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}

	@PostMapping("/createCustomer")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customerDetails) {
		Customer cust = service.createCustomer(customerDetails);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@GetMapping("/getByCustomerId/{customerID}")
	public ResponseEntity<Customer> getByCustomerID(@PathVariable String customerID) {
		Customer cust = service.findByCustomerID(customerID);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@GetMapping("/getByMobileNo/{mobileNo}")
	public ResponseEntity<Customer> getByMobileNo(@PathVariable String mobileNo) {
		Customer cust = service.findByMobileNumber(mobileNo);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@PutMapping("/update/{cid}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable long cid, @Valid @RequestBody Customer c) {
		Customer cust = service.updateCustomer(cid, c);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@GetMapping("/getByEmailId/{emailId}")
	public ResponseEntity<Customer> getByEmailId(@PathVariable String emailId) {
		Customer cust = service.findByEmailId(emailId);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@PutMapping("/changeMobileNumber/{customerID}")
	public ResponseEntity<Customer> changeMobileNumber(@PathVariable String customerID,
			@PathParam("mobileNumber") String mobileNumber) {
		Customer cust = service.changeMobileNo(customerID, mobileNumber);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);

	}

	@PutMapping("/changePassword/{customerID}")
	public ResponseEntity<Customer> changePassword(@PathVariable String customerID,
			@PathParam("oldPassword") String oldPassword, @PathParam("newPassword") String newPassword) {
		Customer cust = service.changePassword(customerID, oldPassword, newPassword);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@PostMapping("/createCustomerFromMerchant")
	public ResponseEntity<Customer> createCustomerFromMerchant(@Valid @RequestBody Customer c) {
		Customer cust = service.createCustomerFromMerchant(c);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}

	@PutMapping("/changePin/{cid}")
	public ResponseEntity<Customer> changePin(@PathVariable long cid, @PathParam("oldPin") int oldPin,
			@PathParam("newPin") int newPin) {
		Customer cust = service.changePin(cid, oldPin, newPin);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@DeleteMapping("/{mobileNumber}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable String mobileNumber) {
		service.deleteCustomer(mobileNumber);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Customer> delete() {
		service.delete();
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}

}
