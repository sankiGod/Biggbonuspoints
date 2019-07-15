package com.bonuspoint.rest.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.Customer;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.repository.OtpRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;
import com.bonuspoint.util.GeneratePassword;
import com.bonuspoint.util.SendSMS;

import okhttp3.Response;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	@Autowired
	OtpRepository orepository;

	public List<Customer> getAllCustomers() {

		return repository.findAll();
	}

	public Customer checkCusotmer(Customer c) {
		try {
			Customer custDetails = repository.save(c);
			repository.delete(custDetails);
			custDetails.setcId(0);
			return custDetails;
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}

	}

	public Customer createCustomer(@Valid Customer customerDetails) {

		Customer customer = repository.save(customerDetails);
		customer.setCustomerID(GenerateID.generateCustID(customer.getcId()));
		customer.setOtp(customerDetails.getOtp());

		String message = "You have been registered successfully with Biggbonuspoints. User ID "
				+ customer.getCustomerID() + " PWD " + customer.getPassword() + " PIN " + customer.getPin();

		Response response;

		try {
			response = SendSMS.sendSMS(customer.getMobileNumber(), message);
			response.close();

			try {
				return repository.save(customer);
			} catch (DataIntegrityViolationException e) {
				String[] msg = e.getRootCause().toString().split(":");
				String[] errMsg = msg[1].split("for");
				throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
			} catch (ConstraintViolationException e) {
				String[] msg = e.getMessage().split("=");
				String[] errMsg = msg[1].split(",");
				throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
			}

		} catch (IOException e) {
			throw new CustomErrorException("Customer", "Not Able To Send Details To Customer",
					ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
		}

	}

	public Customer findByCustomerID(String customerID) {
		if (repository.findByCustomerID(customerID) != null) {
			return repository.findByCustomerID(customerID);
		} else
			throw new ResourceNotFoundException("Customer", "customerID", customerID);
	}

	public Customer findByMobileNumber(String mobileNo) {
		if (repository.findByMobileNumber(mobileNo) != null) {
			return repository.findByMobileNumber(mobileNo);
		} else
			throw new ResourceNotFoundException("Customer", "mobileNo", mobileNo);
	}

	public Customer updateCustomer(long cid, @Valid Customer c) {

		if (repository.existsById(cid)) {
			try {
				return repository.save(c);
			} catch (DataIntegrityViolationException e) {
				String[] msg = e.getRootCause().toString().split(":");
				String[] errMsg = msg[1].split("for");
				throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
			} catch (ConstraintViolationException e) {
				String[] msg = e.getMessage().split("=");
				String[] errMsg = msg[1].split(",");
				throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
			}
		} else
			throw new ResourceNotFoundException("Customer", "cId", cid);
	}

	public Customer findByEmailId(String emailId) {
		if (repository.findByEmailId(emailId) != null) {
			return repository.findByEmailId(emailId);
		} else
			throw new ResourceNotFoundException("Customer", "emailId", emailId);
	}

	public Customer changePassword(String customerID, String oldPassword, String newPassword) {
		if (repository.findByCustomerID(customerID) != null) {

			Customer customer = repository.findByCustomerID(customerID);
			if (customer.getPassword().equalsIgnoreCase(oldPassword)) {
				customer.setPassword(newPassword);
				return repository.save(customer);
			} else
				throw new CustomErrorException("Customer", ErrorCodes.INCORRECT_PASSWORD.getDescription(),
						ErrorCodes.INCORRECT_PASSWORD.getCode());
		} else
			throw new ResourceNotFoundException("Customer", "customerID", customerID);
	}

	public Customer changeMobileNo(String customerID, String mobileNumber) {
		if (repository.findByCustomerID(customerID) != null) {
			try {
				Customer customer = repository.findByCustomerID(customerID);
				customer.setMobileNumber(mobileNumber);
				return repository.save(customer);
			} catch (DataIntegrityViolationException e) {
				String[] msg = e.getRootCause().toString().split(":");
				String[] errMsg = msg[1].split("for");
				throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
			} catch (ConstraintViolationException e) {
				String[] msg = e.getMessage().split("=");
				String[] errMsg = msg[1].split(",");
				throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
			}
		} else
			throw new ResourceNotFoundException("Customer", "customerID", customerID);

	}

	public Customer createCustomerFromMerchant(@Valid Customer c) {

		try {
			c.setPassword(GeneratePassword.generatePassword(8));
			c.setPin(GenerateID.generatePin(4));
			Customer custDetails = repository.save(c);

			custDetails.setCustomerID(GenerateID.generateCustID(custDetails.getcId()));

			String message = "You have been registered successfully with Biggbonuspoints. User ID "
					+ custDetails.getCustomerID() + " PWD " + custDetails.getPassword() + " PIN "
					+ custDetails.getPin();
			Response response;

			response = SendSMS.sendSMS(custDetails.getMobileNumber(), message);
			response.close();

			return repository.save(custDetails);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Customer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		} catch (IOException e) {
			throw new CustomErrorException("Customer", "Not Able To Send Details To Customer",
					ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
		}

	}

	public Customer changePin(long cid, int oldPin, int newPin) {
		Customer customer = repository.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "cid", cid));
		if (customer.getPin() == oldPin) {
			customer.setPin(newPin);
			return repository.save(customer);
		} else
			throw new CustomErrorException("Customer", "Incorrect Pin", ErrorCodes.INCORRECT_PASSWORD.getCode());
	}

	public void deleteCustomer(String mobileNumber) {
		if (repository.findByMobileNumber(mobileNumber) != null) {
			repository.deleteByMobileNumber(mobileNumber);
		} else
			throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
	}

	public void delete() {
		repository.deleteByCustomerID(null);
		repository.deleteByCustomerID("");

	}

}
