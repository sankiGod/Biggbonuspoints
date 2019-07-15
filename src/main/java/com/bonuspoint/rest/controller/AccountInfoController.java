package com.bonuspoint.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.AccountInfo;
import com.bonuspoint.rest.service.AccountInfoService;

@RestController
@RequestMapping("/account")
public class AccountInfoController {

	@Autowired
	AccountInfoService service;

	@GetMapping("/getInfo")
	public ResponseEntity<AccountInfo> getInfo() {
		AccountInfo pd = service.getInfo();
		return new ResponseEntity<AccountInfo>(pd, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<AccountInfo> create(@Valid @RequestBody AccountInfo paymentDetails) {
		AccountInfo pd = service.create(paymentDetails);
		return new ResponseEntity<AccountInfo>(pd, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<AccountInfo> update(@Valid @RequestBody AccountInfo paymentDetails) {
		AccountInfo pd = service.update(paymentDetails);
		return new ResponseEntity<AccountInfo>(pd, HttpStatus.OK);
	}
}
