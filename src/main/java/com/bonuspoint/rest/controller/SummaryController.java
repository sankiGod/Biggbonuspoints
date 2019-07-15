package com.bonuspoint.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.CustomerSummary;
import com.bonuspoint.rest.service.SummaryService;

@RestController
@RequestMapping("/summary")
public class SummaryController {

	@Autowired
	SummaryService service;

	@GetMapping("/getAll")
	public List<CustomerSummary> getAll() {
		return service.getAll();
	}

	@GetMapping("/getByCustomer/{customerID}/{merchantID}")
	public ResponseEntity<CustomerSummary> getSummary(@PathVariable("customerID") String customerID,
			@PathVariable("merchantID") String merchantID) {
		CustomerSummary sum = service.getSummary(customerID, merchantID);
		return new ResponseEntity<CustomerSummary>(sum, HttpStatus.OK);
	}

	@GetMapping("/getByMobileNo/{mobileNumber}/{merchantID}")
	public ResponseEntity<CustomerSummary> getByMobileNo(@PathVariable String mobileNumber,
			@PathVariable("merchantID") String merchantID) {
		CustomerSummary sum = service.getByMobileNo(mobileNumber, merchantID);
		return new ResponseEntity<CustomerSummary>(sum, HttpStatus.OK);
	}

	@GetMapping("/getByCustomerAndProjctId/{customerID}/{projectID}")
	public ResponseEntity<CustomerSummary> getByCustomerAndProjectId(@PathVariable("customerID") String customerID,
			@PathVariable("projectID") String projectID) {
		CustomerSummary sum = service.getByCustomerAndProjectId(customerID, projectID);
		return new ResponseEntity<CustomerSummary>(sum, HttpStatus.OK);
	}

}
