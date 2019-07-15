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

import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.rest.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	NotificationService service;
	
	@PostMapping("/send")
	public ResponseEntity<NotificationHistory> send(@Valid @RequestBody NotificationHistory not){
		NotificationHistory notifcation = service.send(not);
		return new ResponseEntity<NotificationHistory>(notifcation, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public List<NotificationHistory> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getByCustomerId/{customerID}")
	public List<NotificationHistory> getByCustomerId(@PathVariable String customerID){
		return service.getByCustomerId(customerID);
	}
	
	@GetMapping("/getByMerchantId/{merchantID}")
	public List<NotificationHistory> getByMerchantId(@PathVariable String merchantID){
		return service.getByMerchantId(merchantID);
	}
}
