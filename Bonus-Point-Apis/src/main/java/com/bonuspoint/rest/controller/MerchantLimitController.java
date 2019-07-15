package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.MerchantLimit;
import com.bonuspoint.model.MerchantTopupLimit;
import com.bonuspoint.rest.service.MerchantTopupLimitService;

@RestController
@RequestMapping("/admin/limit")
public class MerchantLimitController {

	@Autowired
	MerchantTopupLimitService service;
	
	@PostMapping("/create")
	public ResponseEntity<MerchantTopupLimit> createLimit(@Valid @RequestBody MerchantTopupLimit limit){
		
		MerchantTopupLimit lim = service.createLimit(limit);
		
		return new ResponseEntity<MerchantTopupLimit>(lim, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public List<MerchantTopupLimit> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getLimits")
	public List<MerchantLimit> getLimits(){
		return service.getLimits();
	}
	
	@GetMapping("/getTransactionByMerchantId/{merchantID}")
	public ResponseEntity<List<MerchantTopupLimit>> getTransactionByMerchantId(@PathVariable String merchantID){
		List<MerchantTopupLimit> trans = service.getTransactionByMerchantId(merchantID);
		return new ResponseEntity<List<MerchantTopupLimit>>(trans, HttpStatus.OK);
	}
	
	@GetMapping("/getLimitByMerchantId/{merchantID}")
	public ResponseEntity<MerchantLimit> getLimitByMerchantId(@PathVariable String merchantID){
		MerchantLimit lim = service.getLimitByMerchantId(merchantID);
		return new ResponseEntity<MerchantLimit>(lim, HttpStatus.OK);
	}
}
