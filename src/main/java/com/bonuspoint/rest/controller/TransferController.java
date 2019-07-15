package com.bonuspoint.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.rest.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	TransferService service;

	@PostMapping("/create/custom/{customerID}")
	public List<CustomerMerchantBPAccount> transferCustom(@PathVariable String customerID,
			@RequestParam("fromMerchantID") String fromMerchantID, @RequestParam("toMerchantID") String toMerchantID,
			@RequestParam("bonusPoint") int bonusPoint) {

		return service.transferCustom(customerID, fromMerchantID, toMerchantID, bonusPoint);

	}

	@PostMapping("/create/all/{customerID}")
	public List<CustomerMerchantBPAccount> transferAll(@PathVariable String customerID,
			@RequestParam("fromMerchantID") String fromMerchantID, @RequestParam("toMerchantID") String toMerchantID) {

		return service.transferAll(customerID, fromMerchantID, toMerchantID);

	}

}
