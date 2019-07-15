package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

import com.bonuspoint.model.Merchant;
import com.bonuspoint.rest.service.MerchantService;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

	@Autowired
	MerchantService service;

	@GetMapping("/getAll")
	public List<Merchant> getAllMerchants() {
		return service.getAllMerchants();
	}

	@GetMapping("/getAllActive")
	public List<Merchant> getAllActiveMerchants() {
		return service.getAllActiveMerchants();
	}

	@PostMapping("/create")
	public ResponseEntity<Merchant> createMerchants(@Valid @RequestBody Merchant m) {
		Merchant mer = service.createMerchant(m);
		return new ResponseEntity<Merchant>(mer, HttpStatus.CREATED);
	}

	@GetMapping("/getByMerchantId/{merchantID}")
	public ResponseEntity<Merchant> getByMerchantID(@PathVariable String merchantID) {
		Merchant mer = service.findByMerchantID(merchantID);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@GetMapping("/getByProjectId/{projectID}")
	public List<Merchant> getByProjectID(@PathVariable String projectID) {
		return service.findByProjectID(projectID);
	}

	@GetMapping("/getByMobileNo/{mobileNo}")
	public ResponseEntity<Merchant> getByMobileNumber(@PathVariable String mobileNo) {
		Merchant mer = service.findByMobileNumber(mobileNo);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@PutMapping("/update/{mid}")
	public ResponseEntity<Merchant> updateMerchant(@PathVariable long mid, @Valid @RequestBody Merchant m) {
		Merchant mer = service.updateMerchant(mid, m);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@GetMapping("/disableMerchant/{merchantID}")
	public ResponseEntity<Merchant> disableMerchant(@PathVariable String merchantID) {
		Merchant mer = service.disableMerchant(merchantID);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@GetMapping("/enableMerchant/{merchantID}")
	public ResponseEntity<Merchant> enableMerchant(@PathVariable String merchantID) {
		Merchant mer = service.enableMerchant(merchantID);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@GetMapping("/getStatus/{merchantID}")
	public boolean getStatus(@PathVariable String merchantID) {
		return service.getStatus(merchantID);
	}

	@PutMapping("/changeMobileNumber/{merchantID}")
	public ResponseEntity<Merchant> changeMobileNo(@PathVariable String merchantID,
			@PathParam("mobileNumber") String mobileNumber) {
		Merchant mer = service.changeMobileNo(merchantID, mobileNumber);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@PutMapping("/changePassword/{merchantID}/{terminalID}")
	public ResponseEntity<Merchant> changePassword(@PathVariable("merchantID") String merchantID,
			@PathVariable("terminalID") String terminalID, @PathParam("oldPassword") String oldPassword,
			@PathParam("newPassword") String newPassword) {
		Merchant mer = service.changePassword(merchantID, terminalID, oldPassword, newPassword);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}

	@PutMapping("/changePin/{mid}/{terminalID}")
	public ResponseEntity<Merchant> changePin(@PathVariable("mid") long mid,
			@PathVariable("terminalID") String terminalID, @PathParam("oldPin") int oldPin,
			@PathParam("newPin") int newPin) {
		Merchant mer = service.changePin(mid, terminalID, oldPin, newPin);
		return new ResponseEntity<Merchant>(mer, HttpStatus.OK);
	}
}
