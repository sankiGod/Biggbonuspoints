package com.bonuspoint.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Feedback;
import com.bonuspoint.rest.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbackService service;
	
	@PostMapping("/create")
	public ResponseEntity<Feedback> create(@Valid @RequestBody Feedback fb){
		Feedback feed = service.create(fb);
		return new ResponseEntity<Feedback>(feed,HttpStatus.CREATED);
	}
}
