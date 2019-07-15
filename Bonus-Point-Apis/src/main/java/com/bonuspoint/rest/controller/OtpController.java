package com.bonuspoint.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Otp;
import com.bonuspoint.rest.service.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {

	@Autowired
	OtpService service;

	@GetMapping("/sendRegistrationOtp/{mobileNumber}")
	public ResponseEntity<Otp> sendRegistrationOtp(@PathVariable String mobileNumber) {
		Otp otp = service.sendRegistrationOtp(mobileNumber);
		return new ResponseEntity<Otp>(otp, HttpStatus.CREATED);
	}
	
	@GetMapping("/sendPasswordChangeOtp/{mobileNumber}")
	public ResponseEntity<Otp> sendPasswordChangeOtp(@PathVariable String mobileNumber) {
		Otp otp = service.sendPasswordChangeOtp(mobileNumber);
		return new ResponseEntity<Otp>(otp, HttpStatus.CREATED);
	}
	
	@GetMapping("/sendRedeemOtp/{mobileNumber}")
	public ResponseEntity<Otp> sendRedeemOtp(@PathVariable String mobileNumber) {
		Otp otp = service.sendRedeemOtp(mobileNumber);
		return new ResponseEntity<Otp>(otp, HttpStatus.CREATED);
	}
}
