package com.bonuspoint.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.CustomersBalanceTables;
import com.bonuspoint.model.MerchantsBalanceTables;
import com.bonuspoint.rest.service.BalanceTablesService;

@RestController
@RequestMapping("/balanceTables")
public class BalanceTablesController {

	@Autowired
	BalanceTablesService service;

	@GetMapping("/getCustomerTableByCustomerId/{customerID}/{projectID}")
	public ResponseEntity<CustomersBalanceTables> getCustomerTableByCustomerId(
			@PathVariable("customerID") String customerID, @PathVariable("projectID") String projectID) {
		CustomersBalanceTables ctable = service.getCustomerTableByCustomerIdAndProjectID(customerID, projectID);
		return new ResponseEntity<CustomersBalanceTables>(ctable, HttpStatus.OK);
	}

	@GetMapping("/getMerchantTableByMerchantId/{merchantID}")
	public ResponseEntity<MerchantsBalanceTables> getMerchantTableByMerchantId(@PathVariable String merchantID) {
		MerchantsBalanceTables mtable = service.getMerchantTableByMerchantId(merchantID);
		return new ResponseEntity<MerchantsBalanceTables>(mtable, HttpStatus.OK);
	}

	@GetMapping("/getCustomerTablesByProjectId/{projectID}")
	public ResponseEntity<List<CustomersBalanceTables>> getCustomerTablesByProjectId(@PathVariable String projectID) {
		List<CustomersBalanceTables> ctables = service.getCustomerTablesByProjectId(projectID);
		return new ResponseEntity<List<CustomersBalanceTables>>(ctables, HttpStatus.OK);
	}

	@GetMapping("/getMerchantTablesByProjectId/{projectID}")
	public ResponseEntity<List<MerchantsBalanceTables>> getMerchantTablesByProjectId(@PathVariable String projectID) {
		List<MerchantsBalanceTables> mtables = service.getMerchantTablesByProjectId(projectID);
		return new ResponseEntity<List<MerchantsBalanceTables>>(mtables, HttpStatus.OK);
	}
}
