package com.bonuspoint.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonuspoint.model.AccountInfo;
import com.bonuspoint.rest.service.AccountInfoService;
import com.bonuspoint.web.service.AccountWebService;

@Controller
public class AccountWebController {

	@Autowired
	AccountWebService service;

	@Autowired
	AccountInfoService aiservice;

	@PostMapping("update_account_info")
	public String updateAccountInfo(@RequestParam("account_number") String accountNumber,
			@RequestParam("account_type") String accountType, @RequestParam("payable_to") String payableTo,
			@RequestParam("ifsc_code") String ifscCode, @RequestParam("bank_name") String bankName,
			@RequestParam("branch_name") String branchName, @RequestParam("city") String city,
			@RequestParam("pincode") String pincode, ModelMap model) {

		AccountInfo info = new AccountInfo();
		info.setAccountNumber(accountNumber);
		info.setAccountType(accountType);
		info.setBankName(bankName);
		info.setBranchName(branchName);
		info.setCity(city);
		info.setIfscCode(ifscCode);
		info.setPayableTo(payableTo);
		info.setPincode(pincode);

		String message = service.updateAccount(info);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Account Updated");
			AccountInfo info1 = aiservice.getInfo();
			model.addAttribute("account", info1);
			return "bp_account_info";
		} else {
			model.put("errorMessage", message);
			AccountInfo info1 = aiservice.getInfo();
			model.addAttribute("account", info1);
			return "bp_account_info";
		}
	}

}
