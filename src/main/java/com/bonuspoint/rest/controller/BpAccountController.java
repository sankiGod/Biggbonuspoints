package com.bonuspoint.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.rest.service.BpAccountService;

@RestController
@RequestMapping("/account")
public class BpAccountController {

	@Autowired
	BpAccountService service;

	@GetMapping("/getAll")
	public List<CustomerMerchantBPAccount> getAllAccounts() {
		return service.getAllAccounts();
	}

	@GetMapping("/getByCustomerId/{customerID}")
	public List<CustomerMerchantBPAccount> getAccountsByCustomerId(@PathVariable String customerID) {
		return service.getAccountsByCustomerId(customerID);
	}

	@GetMapping("/getByMerchantId/{merchantID}")
	public List<CustomerMerchantBPAccount> getAccountsByMerchantId(@PathVariable String merchantID) {
		return service.getAccountsByMerchantId(merchantID);
	}

	@GetMapping("/getByMerchantAndCustomerId")
	public ResponseEntity<CustomerMerchantBPAccount> getByMerchantAndCustomerId(
			@PathParam("customerID") String customerID, @PathParam("merchantID") String merchantID) {
		CustomerMerchantBPAccount acc = service.getByMerchantAndCustomerId(customerID, merchantID);
		return new ResponseEntity<CustomerMerchantBPAccount>(acc, HttpStatus.OK);
	}
	
	@GetMapping("/getByProjectAndCustomerId")
	public ResponseEntity<CustomerMerchantBPAccount> getByProjectAndCustomerId(
			@PathParam("customerID") String customerID, @PathParam("projectID") String projectID) {
		CustomerMerchantBPAccount acc = service.getByMerchantAndCustomerId(customerID, projectID);
		return new ResponseEntity<CustomerMerchantBPAccount>(acc, HttpStatus.OK);
	}

}
