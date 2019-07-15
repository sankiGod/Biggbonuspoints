package com.bonuspoint.web.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.LoginPageInfo;
import com.bonuspoint.repository.LoginPageInfoRepository;

@Service
public class LoginPageInfoService {

	@Autowired
	LoginPageInfoRepository repository;

	public LoginPageInfo getInfo() {
		return repository.findById(1l).orElse(new LoginPageInfo());
	}

	public String updateLoginPageInfo(String salesContactName, String salesMobileNumber, String salesEmailId) {
		LoginPageInfo info = repository.findById(1l).orElse(new LoginPageInfo());
		info.setSalesContactName(salesContactName);
		info.setSalesMobileNumber(salesMobileNumber);
		info.setSalesEmailId(salesEmailId);
		try {
			repository.save(info);
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
