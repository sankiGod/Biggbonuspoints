package com.bonuspoint.rest.service;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.repository.NotificationRepository;
import com.bonuspoint.util.ErrorCodes;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository repository;

	public List<NotificationHistory> getAll() {
		return repository.findAll();
	}

	public List<NotificationHistory> getByCustomerId(String customerID) {
		if (!repository.findByCustomerID(customerID).isEmpty()) {
			return repository.findByCustomerID(customerID);
		} else
			throw new ResourceNotFoundException("NotificationHistory", "customerID", customerID);
	}

	public List<NotificationHistory> getByMerchantId(String merchantID) {
		if (!repository.findByMerchantID(merchantID).isEmpty()) {
			return repository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("NotificationHistory", "merchantID", merchantID);
	}

	public NotificationHistory send(@Valid NotificationHistory not) {
		try {
			return repository.save(not);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Notification", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Notification", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

}
