package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Terminal;
import com.bonuspoint.rest.service.TerminalService;

@RestController
@RequestMapping("/terminals")
public class TerminalController {

	@Autowired
	TerminalService service;
	
	@GetMapping("/getAll")
	public List<Terminal> getAllTerminals(){
		return service.getAll();
	}
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Terminal> create(@Valid Terminal terminal){
		Terminal term = service.create(terminal);
		return new ResponseEntity<Terminal>(term,HttpStatus.CREATED);
	}
	
	@GetMapping("/getByTerminalId/{terminalID}")
	public ResponseEntity<Terminal> findByTerminalId(@PathVariable String terminalID){
		Terminal term = service.getByTerminalId(terminalID);
		return new ResponseEntity<Terminal>(term,HttpStatus.OK);
	}
	
	@GetMapping("/getByMerchantId/{merchantID}")
	public List<Terminal> findByMerchantId(@PathVariable String merchantID){
		return service.getByMerchantId(merchantID);
	}
	
	@GetMapping("/getByProjectId/{projectID}")
	public List<Terminal> findByProjectId(@PathVariable String projectID){
		return service.getByProjectId(projectID);
	}
	
}
