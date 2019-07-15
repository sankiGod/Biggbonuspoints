package com.bonuspoint.web.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.Email;
import com.bonuspoint.repository.EmailRepository;

@Service
public class EmailWebService {

	@Autowired
	EmailRepository repository;

	public Email getEmails() {
		return repository.findById(1l).orElse(new Email());
	}

	public String updateEmailSettings(String customerEmailID, String merchantEmailID) {

		Email email = repository.findById(1l).orElse(new Email());
		email.setCustomerEmailID(customerEmailID);
		email.setMerchantEmailID(merchantEmailID);
		try {
			repository.save(email);
			return "Success";
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			return errMsg[0];
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			return errMsg[0];
		}
	}

}
