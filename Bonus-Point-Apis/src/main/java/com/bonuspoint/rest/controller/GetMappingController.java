package com.bonuspoint.rest.controller;

import java.math.BigDecimal;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Map;
import com.bonuspoint.rest.service.MappingService;

@RestController
@RequestMapping("/map")
public class GetMappingController {

	@Autowired
	MappingService service;
	
	@GetMapping("/getAmount/{merchantID}")
	public ResponseEntity<Map> getBp(@PathVariable String merchantID ,@PathParam("bonusPoint") int bonusPoint){
		Map map = service.getBp(merchantID, bonusPoint);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
	@GetMapping("/getAccrued/{merchantID}")
	public ResponseEntity<Map> getAccrued(@PathVariable String merchantID, @PathParam("purchaseAmount") BigDecimal purchaseAmount){
		Map map = service.getAccrued(merchantID, purchaseAmount);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
}
