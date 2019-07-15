package com.bonuspoint.rest.controller;


import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.PotentialCorporateProjects;
import com.bonuspoint.model.PotentialMerchants;
import com.bonuspoint.rest.service.PotentialService;

@RestController
@RequestMapping("/potentials")
public class PotentialController {

	@Autowired
	PotentialService service;
	
	@PostMapping("/project/create")
	public ResponseEntity<PotentialCorporateProjects> createProject(@Valid @RequestBody PotentialCorporateProjects project){
		PotentialCorporateProjects projects = service.createProject(project);
		return new ResponseEntity<PotentialCorporateProjects>(projects, HttpStatus.CREATED);
	}
	
	@PostMapping("/merchant/create")
	public ResponseEntity<PotentialMerchants> createMerchant(@Valid @RequestBody PotentialMerchants merchant){
		PotentialMerchants merchants = service.createMerchant(merchant);
		return new ResponseEntity<PotentialMerchants>(merchants, HttpStatus.CREATED);
	}
	
	@PutMapping("/project/approve")
	public ResponseEntity<PotentialCorporateProjects> approveProject(@PathParam("mobileNumber") String mobileNumber){
		service.approveProject(mobileNumber);
		return new ResponseEntity<PotentialCorporateProjects>(HttpStatus.OK);
	}
	
	@PutMapping("/merchant/approve")
	public ResponseEntity<PotentialMerchants> approveMerchant(@PathParam("mobileNumber") String mobileNumber){
		service.approveMerchant(mobileNumber);
		return new ResponseEntity<PotentialMerchants>(HttpStatus.OK);
	}
	
	@GetMapping("/project")
	public ResponseEntity<PotentialCorporateProjects> getProject(@PathParam("mobileNumber") String mobileNumber){
		PotentialCorporateProjects projects = service.getProject(mobileNumber);
		return new ResponseEntity<PotentialCorporateProjects>(projects, HttpStatus.OK);
	}
	
	@GetMapping("/merchant")
	public ResponseEntity<PotentialMerchants> getMerchant(@PathParam("mobileNumber") String mobileNumber){
		PotentialMerchants merchants = service.getMerchant(mobileNumber);
		return new ResponseEntity<PotentialMerchants>(merchants, HttpStatus.OK);
	}
}
