package com.bonuspoint.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.CorporateAccounts;
import com.bonuspoint.rest.service.CorporateAccountsService;

@RestController
@RequestMapping("/corporateAccounts")
public class CorporateAccountsController {
	
	@Autowired
	CorporateAccountsService service;
	
	@GetMapping("/getAll")
	public List<CorporateAccounts> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getByProjectId/{projectID}")
	public ResponseEntity<CorporateAccounts> getByProjectid(@PathVariable String projectID){
		CorporateAccounts acc = service.getByProjectId(projectID);
		return new ResponseEntity<CorporateAccounts>(acc, HttpStatus.OK);
	}

}
