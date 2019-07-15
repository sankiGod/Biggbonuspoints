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

import com.bonuspoint.model.Offers;
import com.bonuspoint.rest.service.OfferService;

@RestController
@RequestMapping("/offers")
public class OfferController {

	@Autowired
	OfferService service;

	@PostMapping("/create")
	public ResponseEntity<Offers> createOffer(@Valid @RequestBody Offers offer) {
		Offers offr = service.createOffer(offer);
		return new ResponseEntity<Offers>(offr, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Offers>> getAll() {
		List<Offers> offers = service.getAll();
		return new ResponseEntity<List<Offers>>(offers, HttpStatus.OK);
	}

	@GetMapping("/getAllByMerchantId/{merchantID}")
	public ResponseEntity<List<Offers>> getByMerchantID(@PathVariable String merchantID) {
		List<Offers> offers = service.getByMerchantID(merchantID);
		return new ResponseEntity<List<Offers>>(offers, HttpStatus.OK);
	}

	@GetMapping("/getApprovedByMerchantId/{merchantID}")
	public ResponseEntity<List<Offers>> getApprovedByMerchantId(@PathVariable String merchantID) {
		List<Offers> offers = service.getApprovedByMerchantId(merchantID);
		return new ResponseEntity<List<Offers>>(offers, HttpStatus.OK);
	}

	@PutMapping("/approveOffer")
	public ResponseEntity<Offers> approveOffer(@PathParam("offerCode") String offerCode) {
		Offers offr = service.approveOffer(offerCode);
		return new ResponseEntity<Offers>(offr, HttpStatus.OK);
	}

	@GetMapping("/getOffersForCustomerId/{customerID}")
	public ResponseEntity<List<Offers>> getOffersForCustomerId(@PathVariable String customerID) {
		List<Offers> offers = service.getOffersForCustomerId(customerID);
		return new ResponseEntity<List<Offers>>(offers, HttpStatus.OK);
	}
}
