package com.bonuspoint.rest.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.model.AccountInfo;
import com.bonuspoint.repository.AccountInfoRepository;
import com.bonuspoint.util.ErrorCodes;

@Service
public class AccountInfoService {

	@Autowired
	AccountInfoRepository repository;

	public AccountInfo getInfo() {
		if (!repository.findAll().isEmpty()) {
			List<AccountInfo> accs = repository.findAll();
			return accs.get(0);
		} else
			return new AccountInfo();
	}

	public AccountInfo create(@Valid AccountInfo paymentDetails) {
		if (!repository.findAll().isEmpty()) {
			List<AccountInfo> accs = repository.findAll();
			return accs.get(0);
		} else
			return repository.save(paymentDetails);
	}

	public AccountInfo update(@Valid AccountInfo accInfo) {

		AccountInfo acc = repository.findById(1l)
				.orElseThrow(() -> new CustomErrorException("AccountInfo", "Account Info Not Yet Entered", ErrorCodes.RESOURCE_NOT_FOUND.getCode()));

		acc.setAccountNumber(accInfo.getAccountNumber());
		acc.setAccountType(accInfo.getAccountType());
		acc.setBankName(accInfo.getBankName());
		acc.setBranchName(accInfo.getBranchName());
		acc.setCity(accInfo.getCity());
		acc.setIfscCode(accInfo.getIfscCode());
		acc.setPayableTo(accInfo.getPayableTo());
		acc.setPincode(accInfo.getPincode());

		return repository.save(acc);
	}
}
