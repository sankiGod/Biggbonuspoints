package com.bonuspoint.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.AwardTransactions;
import com.bonuspoint.rest.service.AwardTransactionService;

@RestController
@RequestMapping("/award")
@Transactional(rollbackFor = Throwable.class)
public class AwardController {

	@Autowired
	AwardTransactionService service;

	@GetMapping("/getAll")
	public List<AwardTransactions> getAll() {
		return service.getAllTransactions();
	}

	@PostMapping("/create")
	public ResponseEntity<AwardTransactions> create(@Valid @RequestBody AwardTransactions award) {
		AwardTransactions awards = service.create(award);
		return new ResponseEntity<AwardTransactions>(awards, HttpStatus.CREATED);
	}

	@GetMapping("/getByCustomerId/{customerID}")
	public List<AwardTransactions> getTransactionsByCustomerId(@PathVariable String customerID) {
		return service.getTransactionsByCustomerId(customerID);
	}

	@GetMapping("/getByMerchantId/{merchantID}")
	public List<AwardTransactions> getTransactionsByMerchantId(@PathVariable String merchantID) {
		return service.getTransactionsByMerchantId(merchantID);
	}

	@GetMapping("/get10RecentTransactionsByCustomerId/{customerID}")
	public ResponseEntity<List<AwardTransactions>> get10RecentTransactionsByCustomerId(
			@PathVariable String customerID) {
		List<AwardTransactions> ats = service.get10RecentTransactionsByCustomerId(customerID);
		return new ResponseEntity<List<AwardTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/get10RecentTransactionsByMerchantId/{merchantID}")
	public ResponseEntity<List<AwardTransactions>> get10RecentTransactionsByMerchantId(
			@PathVariable String merchantID) {
		List<AwardTransactions> ats = service.get10RecentTransactionsByMerchantId(merchantID);
		return new ResponseEntity<List<AwardTransactions>>(ats, HttpStatus.OK);
	}
	
	@GetMapping("/get10RecentTransactionsByMerchantIdAndTerminalId/{merchantID}/{terminalID}")
	public ResponseEntity<List<AwardTransactions>> get10RecentTransactionsByMerchantIdAndTerminalId(
			@PathVariable("merchantID") String merchantID, @PathVariable("terminalID") String terminalID) {
		List<AwardTransactions> ats = service.get10RecentTransactionsByMerchantIdAndTerminalId(merchantID, terminalID);
		return new ResponseEntity<List<AwardTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/getTransactionsByCustomerIdInRange/{customerID}")
	public ResponseEntity<List<AwardTransactions>> getTransactionsByCustomerIdInRange(@PathVariable String customerID,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date createdStartDate = sdf.parse(startDate);
		Date createdEndDate = sdf.parse(endDate);
		List<AwardTransactions> ats = service.getTransactionsByCustomerIdInRange(customerID, createdStartDate,
				createdEndDate);
		return new ResponseEntity<List<AwardTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/getTransactionsByMerchantIdInRange/{merchantID}")
	public ResponseEntity<List<AwardTransactions>> getTransactionsByMerchantIdInRange(@PathVariable String merchantID,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date createdStartDate = sdf.parse(startDate);
		Date createdEndDate = sdf.parse(endDate);
		List<AwardTransactions> ats = service.getTransactionsByMerchantIdInRange(merchantID, createdStartDate,
				createdEndDate);
		return new ResponseEntity<List<AwardTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/getByMerchantAndCustomerId/{merchantID}/{customerID}")
	public ResponseEntity<List<AwardTransactions>> getByMerchantAndCustomerId(
			@PathVariable("merchantID") String merchantID, @PathVariable("customerID") String customerID) {
		List<AwardTransactions> awards = service.getByMerchantAndCustomerId(merchantID, customerID);
		return new ResponseEntity<List<AwardTransactions>>(awards, HttpStatus.OK);
	}

	@GetMapping("/getByFirst10MerchantAndCustomerId/{merchantID}/{customerID}")
	public ResponseEntity<List<AwardTransactions>> getByFirst10MerchantAndCustomerId(
			@PathVariable("merchantID") String merchantID, @PathVariable("customerID") String customerID) {
		List<AwardTransactions> awards = service.getByFirst10MerchantAndCustomerId(merchantID, customerID);
		return new ResponseEntity<List<AwardTransactions>>(awards, HttpStatus.OK);
	}

	@GetMapping("/getByFirst10ProjectAndCustomerId/{projectID}/{customerID}")
	public ResponseEntity<List<AwardTransactions>> getByFirst10ProjectAndCustomerId(
			@PathVariable("projectID") String projectID, @PathVariable("customerID") String customerID) {
		List<AwardTransactions> awards = service.getByFirst10ProjectAndCustomerId(projectID, customerID);
		return new ResponseEntity<List<AwardTransactions>>(awards, HttpStatus.OK);
	}
}
