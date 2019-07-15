package com.bonuspoint.web.service;

/*import java.io.IOException;*/
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.Employee;
import com.bonuspoint.model.EmployeeRoles;
import com.bonuspoint.repository.EmployeeRepository;
import com.bonuspoint.repository.EmployeeRoleRepository;
import com.bonuspoint.util.GenerateID;
import com.bonuspoint.util.GeneratePassword;
/*import com.bonuspoint.util.SendSMS;

import okhttp3.Response;*/

@Service
public class EmployeeWebService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	EmployeeRoleRepository erepository;

	public String addEmployee(Employee employee) {
		try {
			employee.setPassword(GeneratePassword.generatePassword(8));
			Employee empDetails = repository.save(employee);
			empDetails.setEmployeeID(GenerateID.generateEmployeeID(empDetails.geteId()));
			repository.save(empDetails);

			/*
			 * String messageLine1 = "Biggbonuspoints Credentials "; String messageLine2 =
			 * "UName : " + empDetails.getUsername(); String messageLine3 = "PWD : " +
			 * empDetails.getPassword();
			 * 
			 * String fullMessage = messageLine1 + "\n" + messageLine2 + "\n" + messageLine3
			 * ;
			 * 
			 * Response response = SendSMS.sendSMS(empDetails.getMobileNumber(),
			 * fullMessage); response.close();
			 */

			return "Success";
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			return errMsg[0];
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			return errMsg[0];
		} /*
			 * catch (IOException e) { return e.toString(); }
			 */

	}

	public Employee getEmployee(String username) {
		if (repository.findByUsername(username) != null) {
			return repository.findByUsername(username);
		} else
			return new Employee();
	}

	public Employee getByEId(long eId) {
		return repository.findById(eId).orElse(new Employee());
	}

	public String editEmployee(Employee employee) {
		if (repository.findByEmployeeID(employee.getEmployeeID()) != null) {
			try {

				Employee empDetails = repository.findByEmployeeID(employee.getEmployeeID());
				empDetails.setAadharNumber(employee.getAadharNumber());
				empDetails.setAddress(employee.getAddress());
				empDetails.setEmailId(employee.getEmailId());
				empDetails.setEmployeeName(employee.getEmployeeName());
				empDetails.setMobileNumber(employee.getMobileNumber());
				empDetails.setPanNumber(employee.getPanNumber());
				empDetails.setUsername(employee.getUsername());
				repository.save(empDetails);
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
		} else {
			return "Internal Error";

		}
	}

	public String assignRoles(String employeeID, String[] roles) {

		EmployeeRoles erole = null;
		if (erepository.findByEmployeeID(employeeID) != null) {
			erole = erepository.findByEmployeeID(employeeID);
		} else {
			erole = new EmployeeRoles();
		}

		erole.setEmployeeID(employeeID);

		List<String> checkedRoles = Arrays.asList(roles);

		if (checkedRoles.contains("project_onboarding"))
			erole.setProjectOnboarding(true);
		else
			erole.setProjectOnboarding(false);
		if (checkedRoles.contains("merchant_onboarding"))
			erole.setMerchantOnboarding(true);
		else
			erole.setMerchantOnboarding(false);
		if (checkedRoles.contains("merchant_send_credentials"))
			erole.setMerchantSendCredentials(true);
		else
			erole.setMerchantSendCredentials(false);
		if (checkedRoles.contains("merchant_map_bonus_point"))
			erole.setMerchantMapBonusPoint(true);
		else
			erole.setMerchantMapBonusPoint(false);
		if (checkedRoles.contains("merchant_top_up_limit"))
			erole.setMerchantTopUpLimit(true);
		else
			erole.setMerchantTopUpLimit(false);
		if (checkedRoles.contains("merchant_status"))
			erole.setMerchantStatus(true);
		else
			erole.setMerchantStatus(false);
		if (checkedRoles.contains("merchant_login_page_info"))
			erole.setMerchantLoginPageInfo(true);
		else
			erole.setMerchantLoginPageInfo(false);
		if (checkedRoles.contains("merchant_send_notification"))
			erole.setMerchantSendNotification(true);
		else
			erole.setMerchantSendNotification(false);
		if (checkedRoles.contains("merchant_received_offers"))
			erole.setMerchantReceivedOffers(true);
		else
			erole.setMerchantReceivedOffers(false);
		if (checkedRoles.contains("modify_customer_details"))
			erole.setModifyCustomerDetails(true);
		else
			erole.setModifyCustomerDetails(false);
		if (checkedRoles.contains("customer_send_notification"))
			erole.setCustomerSendNotification(true);
		else
			erole.setCustomerSendNotification(false);
		if (checkedRoles.contains("create_employee"))
			erole.setCreateEmployee(true);
		else
			erole.setCreateEmployee(false);
		if (checkedRoles.contains("assign_employee_roles"))
			erole.setAssignEmployeeRoles(true);
		else
			erole.setAssignEmployeeRoles(false);
		if (checkedRoles.contains("payment_details"))
			erole.setPaymentDetails(true);
		else
			erole.setPaymentDetails(false);
		if (checkedRoles.contains("account_info"))
			erole.setAccountInfo(true);
		else
			erole.setAccountInfo(false);
		if (checkedRoles.contains("reports"))
			erole.setReports(true);
		else
			erole.setReports(false);
		if (checkedRoles.contains("helpdesk_action"))
			erole.setHelpdeskAction(true);
		else
			erole.setHelpdeskAction(false);
		if (checkedRoles.contains("email_settings"))
			erole.setEmailSettings(true);
		else
			erole.setEmailSettings(false);

		try {
			erepository.save(erole);
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

	public String changePassword(String employeeID, String oldPassword, String newPassword) {
		if (repository.findByEmployeeID(employeeID) != null) {
			Employee employee = repository.findByEmployeeID(employeeID);
			try {
				if (employee.getPassword().equals(oldPassword)) {
					employee.setPassword(newPassword);
					repository.save(employee);
					return "Success";
				} else {
					return "Old Password Incorrect!";
				}
			} catch (DataIntegrityViolationException e) {
				String[] msg = e.getRootCause().toString().split(":");
				String[] errMsg = msg[1].split("for");
				return errMsg[0];
			} catch (ConstraintViolationException e) {
				String[] msg = e.getMessage().split("=");
				String[] errMsg = msg[1].split(",");
				return errMsg[0];
			}

		} else
			return "Internal Error, Please Try Again Later";
	}

}
