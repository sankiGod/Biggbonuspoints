package com.bonuspoint.web.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.AccountInfo;
import com.bonuspoint.repository.AccountInfoRepository;

@Service
public class AccountWebService {

	@Autowired
	AccountInfoRepository repository;

	public String updateAccount(AccountInfo accInfo) {
		AccountInfo acc = repository.findById(1l).orElse(new AccountInfo());

		acc.setAccountNumber(accInfo.getAccountNumber());
		acc.setAccountType(accInfo.getAccountType());
		acc.setBankName(accInfo.getBankName());
		acc.setBranchName(accInfo.getBranchName());
		acc.setCity(accInfo.getCity());
		acc.setIfscCode(accInfo.getIfscCode());
		acc.setPayableTo(accInfo.getPayableTo());
		acc.setPincode(accInfo.getPincode());

		try {
			repository.save(acc);
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
