package com.bonuspoint.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.DocumentUploads;
import com.bonuspoint.repository.DocumentUploadRepository;

@Service
public class DocumentUploadService {

	@Autowired
	DocumentUploadRepository repository;

	public DocumentUploads getPaths(String userType) {
		if (repository.findByUserType(userType) != null) {
			return repository.findByUserType(userType);
		} else
			throw new ResourceNotFoundException("DocumentUploads", "User Type", userType);
	}

}
