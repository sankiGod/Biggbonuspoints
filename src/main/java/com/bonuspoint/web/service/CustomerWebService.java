package com.bonuspoint.web.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bonuspoint.exception.FileStorageException;
import com.bonuspoint.model.Customer;
import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.model.DocumentUploads;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.property.FileStorageProperties;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.repository.DocumentUploadRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.NotificationRepository;

@Service
public class CustomerWebService {

	@Autowired
	CustomerRepository repository;

	@Autowired
	MerchantRepository mrepository;

	@Autowired
	NotificationRepository nrepository;

	@Autowired
	CustomerMerchantBPAccountRepository accrepository;

	@Autowired
	DocumentUploadRepository drepository;

	private final Path fileStorageLocation;

	@Autowired
	public CustomerWebService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public List<Customer> getAllCustomers() {

		return repository.findAll();
	}

	public String editCustomer(String customerID, String emailId, String aadharNumber, String customerName,
			String mobileNumber, String panNumber) {

		if (repository.findByCustomerID(customerID) != null) {
			Customer cust = repository.findByCustomerID(customerID);
			cust.setEmailId(emailId);
			cust.setAadharNumber(aadharNumber);
			cust.setCustomerName(customerName);
			cust.setMobileNumber(mobileNumber);
			cust.setPanNumber(panNumber);

			try {
				repository.save(cust);
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

		} else
			return "Customer With This Customer ID Not Found";

	}

	public String sendToAll(String notificationType, String title, String description) {

		List<Customer> customers = repository.findAll();
		NotificationHistory not = null;

		try {
			for (Customer customer : customers) {
				not = new NotificationHistory();
				not.setCustomerID(customer.getCustomerID());
				not.setDescription(description);
				not.setTitle(title);
				not.setNotificationType(notificationType);
				nrepository.save(not);
			}
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

	public String sendToSingle(String customerID, String notificationType, String title, String description) {

		NotificationHistory not = new NotificationHistory();
		not.setCustomerID(customerID);
		not.setDescription(description);
		not.setTitle(title);
		not.setNotificationType(notificationType);
		try {
			nrepository.save(not);
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

	public String sendToCorporate(String projectID, String notificationType, String title, String description) {

		List<CustomerMerchantBPAccount> accs = accrepository.findByMerchantProjectID(projectID);
		NotificationHistory not = null;

		try {
			for (CustomerMerchantBPAccount customerMerchantBPAccount : accs) {
				not = new NotificationHistory();
				not.setCustomerID(customerMerchantBPAccount.getCustomerID());
				not.setDescription(description);
				not.setTitle(title);
				not.setNotificationType(notificationType);
				nrepository.save(not);
			}
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

	public String sendToAllMerchants(String projectID, String notificationType, String title, String description) {

		List<Merchant> merchants = mrepository.findByProjectID(projectID);
		NotificationHistory not = null;
		try {
			for (Merchant merchant : merchants) {
				List<CustomerMerchantBPAccount> accs = accrepository.findByMerchantProjectID(merchant.getMerchantID());

				for (CustomerMerchantBPAccount customerMerchantBPAccount : accs) {
					not = new NotificationHistory();
					not.setCustomerID(customerMerchantBPAccount.getCustomerID());
					not.setDescription(description);
					not.setTitle(title);
					not.setNotificationType(notificationType);
					nrepository.save(not);
				}
			}
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

	public String sendToSingleMerchants(String merchantID, String notificationType, String title, String description) {
		List<CustomerMerchantBPAccount> accs = accrepository.findByMerchantProjectID(merchantID);
		NotificationHistory not = null;

		try {
			for (CustomerMerchantBPAccount customerMerchantBPAccount : accs) {
				not = new NotificationHistory();
				not.setCustomerID(customerMerchantBPAccount.getCustomerID());
				not.setDescription(description);
				not.setTitle(title);
				not.setNotificationType(notificationType);
				nrepository.save(not);
			}
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

	public String customerPPUploads(MultipartFile privacyPolicy) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(privacyPolicy.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "CprivacyPolicy" + "." + ext;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return "Sorry! Filename contains invalid path sequence " + fileName;
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(privacyPolicy.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadFile/")
					.path(fileName).toUriString();
			if (drepository.findByUserType("customer") != null) {
				DocumentUploads uploads = drepository.findByUserType("customer");
				uploads.setPrivacyPolicyPath(fileDownloadUri);
				uploads.setUpdateCounter(uploads.getUpdateCounter() + 1);
				drepository.save(uploads);
			} else {
				DocumentUploads uploads = new DocumentUploads();
				uploads.setUserType("customer");
				uploads.setPrivacyPolicyPath(fileDownloadUri);
				drepository.save(uploads);
			}
			return "Success";
		} catch (IOException ex) {
			return "Could not store file : " + fileName + ". Please try again!";
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

	public String customerTCUploads(MultipartFile termsAndConditons) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(termsAndConditons.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "CtermsAndConditons" + "." + ext;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return "Sorry! Filename contains invalid path sequence " + fileName;
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(termsAndConditons.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadFile/")
					.path(fileName).toUriString();
			if (drepository.findByUserType("customer") != null) {
				DocumentUploads uploads = drepository.findByUserType("customer");
				uploads.setTermsAndConditionsPath(fileDownloadUri);
				uploads.setUpdateCounter(uploads.getUpdateCounter() + 2);
				drepository.save(uploads);
			} else {
				DocumentUploads uploads = new DocumentUploads();
				uploads.setUserType("customer");
				uploads.setTermsAndConditionsPath(fileDownloadUri);
				drepository.save(uploads);
			}
			return "Success";
		} catch (IOException ex) {
			return "Could not store file : " + fileName + ". Please try again!";
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

	public String customerUGUploads(MultipartFile userGuide) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(userGuide.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "CuserGuide" + "." + ext;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return "Sorry! Filename contains invalid path sequence " + fileName;
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(userGuide.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadFile/")
					.path(fileName).toUriString();
			if (drepository.findByUserType("customer") != null) {
				DocumentUploads uploads = drepository.findByUserType("customer");
				uploads.setUserGuidePath(fileDownloadUri);
				uploads.setUpdateCounter(uploads.getUpdateCounter() + 5);
				drepository.save(uploads);
			} else {
				DocumentUploads uploads = new DocumentUploads();
				uploads.setUserType("customer");
				uploads.setUserGuidePath(fileDownloadUri);
				drepository.save(uploads);
			}
			return "Success";
		} catch (IOException ex) {
			return "Could not store file : " + fileName + ". Please try again!";
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

	public DocumentUploads getCustomerDocUploads() {
		return drepository.findByUserType("customer");
	}
}
