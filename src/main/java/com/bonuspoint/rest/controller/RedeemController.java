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

import com.bonuspoint.model.RedeemTransactions;
import com.bonuspoint.rest.service.RedeemTransactionService;

@RestController
@RequestMapping("/redeem")
@Transactional(rollbackFor = Throwable.class)
public class RedeemController {

	@Autowired
	RedeemTransactionService service;

	@GetMapping("/getAll")
	public List<RedeemTransactions> getAll() {
		return service.getAll();
	}

	@PostMapping("/create")
	public ResponseEntity<RedeemTransactions> create(@Valid @RequestBody RedeemTransactions redeem) {
		RedeemTransactions redeems = service.create(redeem);
		return new ResponseEntity<RedeemTransactions>(redeems, HttpStatus.CREATED);
	}

	@GetMapping("/getByCustomerId/{customerID}")
	public List<RedeemTransactions> getTransactionsByCustomerId(@PathVariable String customerID) {
		return service.getByCustomerId(customerID);
	}

	@GetMapping("/getByMerchantId/{merchantID}")
	public List<RedeemTransactions> getTransactionsByMerchantId(@PathVariable String merchantID) {
		return service.getByMerchantId(merchantID);
	}

	@GetMapping("/get10RecentTransactionsByCustomerId/{customerID}")
	public ResponseEntity<List<RedeemTransactions>> get10RecentTransactionsByCustomerId(
			@PathVariable String customerID) {
		List<RedeemTransactions> ats = service.get10RecentTransactionsByCustomerId(customerID);
		return new ResponseEntity<List<RedeemTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/get10RecentTransactionsByMerchantId/{merchantID}")
	public ResponseEntity<List<RedeemTransactions>> get10RecentTransactionsByMerchantId(
			@PathVariable String merchantID) {
		List<RedeemTransactions> ats = service.get10RecentTransactionsByMerchantId(merchantID);
		return new ResponseEntity<List<RedeemTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/get10RecentTransactionsByMerchantIdAndTerminalId/{merchantID}/{terminalID}")
	public ResponseEntity<List<RedeemTransactions>> get10RecentTransactionsByMerchantIdAndTerminalId(
			@PathVariable("merchantID") String merchantID, @PathVariable("terminalID") String terminalID) {
		List<RedeemTransactions> ats = service.get10RecentTransactionsByMerchantIdAndTerminalId(merchantID, terminalID);
		return new ResponseEntity<List<RedeemTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/getTransactionsByCustomerIdInRange/{customerID}")
	public ResponseEntity<List<RedeemTransactions>> getTransactionsByCustomerIdInRange(@PathVariable String customerID,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date createdStartDate = sdf.parse(startDate);
		Date createdEndDate = sdf.parse(endDate);
		List<RedeemTransactions> ats = service.getTransactionsByCustomerIdInRange(customerID, createdStartDate,
				createdEndDate);
		return new ResponseEntity<List<RedeemTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/getTransactionsByMerchantIdInRange/{merchantID}")
	public ResponseEntity<List<RedeemTransactions>> getTransactionsByMerchantIdInRange(@PathVariable String merchantID,
			@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date createdStartDate = sdf.parse(startDate);
		Date createdEndDate = sdf.parse(endDate);
		List<RedeemTransactions> ats = service.getTransactionsByMerchantIdInRange(merchantID, createdStartDate,
				createdEndDate);
		return new ResponseEntity<List<RedeemTransactions>>(ats, HttpStatus.OK);
	}

	@GetMapping("/getByMerchantAndCustomerId/{merchantID}/{customerID}")
	public ResponseEntity<List<RedeemTransactions>> getByMerchantAndCustomerId(
			@PathVariable("merchantID") String merchantID, @PathVariable("customerID") String customerID) {
		List<RedeemTransactions> redeems = service.getByMerchantAndCustomerId(merchantID, customerID);
		return new ResponseEntity<List<RedeemTransactions>>(redeems, HttpStatus.OK);
	}

	@GetMapping("/getByFirst10MerchantAndCustomerId/{merchantID}/{customerID}")
	public ResponseEntity<List<RedeemTransactions>> getByFirst10MerchantAndCustomerId(
			@PathVariable("merchantID") String merchantID, @PathVariable("customerID") String customerID) {
		List<RedeemTransactions> awards = service.getByFirst10MerchantAndCustomerId(merchantID, customerID);
		return new ResponseEntity<List<RedeemTransactions>>(awards, HttpStatus.OK);
	}

	@GetMapping("/getByFirst10ProjectAndCustomerId/{projectID}/{customerID}")
	public ResponseEntity<List<RedeemTransactions>> getByFirst10ProjectAndCustomerId(
			@PathVariable("projectID") String projectID, @PathVariable("customerID") String customerID) {
		List<RedeemTransactions> awards = service.getByFirst10ProjectAndCustomerId(projectID, customerID);
		return new ResponseEntity<List<RedeemTransactions>>(awards, HttpStatus.OK);
	}
}
