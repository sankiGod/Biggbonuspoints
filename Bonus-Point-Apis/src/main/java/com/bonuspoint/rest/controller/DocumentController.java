package com.bonuspoint.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.DocumentUploads;
import com.bonuspoint.rest.service.DocumentUploadService;

@RestController
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	DocumentUploadService service;

	@GetMapping("/getPaths/{userType}")
	public ResponseEntity<DocumentUploads> getPaths(@PathVariable String userType){
		DocumentUploads uploads = service.getPaths(userType);
		return new ResponseEntity<DocumentUploads>(uploads, HttpStatus.OK);
	}
	
}
