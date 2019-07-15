package com.bonuspoint.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.CorporateAccounts;
import com.bonuspoint.repository.CorporateAccountsRepository;

@Service
public class CorporateAccountsService {

	@Autowired
	CorporateAccountsRepository repository;

	public List<CorporateAccounts> getAll() {
		return repository.findAll();
	}

	public CorporateAccounts getByProjectId(String projectID) {
		if(repository.findByProjectID(projectID)!=null) {
			return repository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("CorporateAccounts", "projectID", projectID);
	}
	
	
}
