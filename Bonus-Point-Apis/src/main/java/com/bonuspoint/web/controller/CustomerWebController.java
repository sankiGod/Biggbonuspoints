package com.bonuspoint.web.controller;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bonuspoint.model.Customer;
import com.bonuspoint.model.DocumentUploads;
import com.bonuspoint.model.Project;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.rest.service.ProjectService;
import com.bonuspoint.web.service.CustomerWebService;

@Controller
public class CustomerWebController {

	@Autowired
	CustomerRepository repository;

	@Autowired
	CustomerWebService service;

	@Autowired
	ProjectService pservice;

	@PostMapping("get_customer")
	public String getCustomer(@RequestParam("input_type") String inputType, @RequestParam("customer") String customer,
			ModelMap model) {
		Customer cust = new Customer();

		if (inputType.equals("email_id")) {
			if (repository.findByEmailId(customer) != null) {
				cust = repository.findByEmailId(customer);
				model.addAttribute("customer", cust);
				return "edit_customer_details";
			} else {
				model.put("errorMessage", "Customer With This Email ID Does Not Exist");
				return "modify_customer_details";
			}

		} else if (inputType.equals("mobile_no")) {
			if (repository.findByMobileNumber(customer) != null) {
				cust = repository.findByMobileNumber(customer);
				model.addAttribute("customer", cust);
				return "edit_customer_details";
			} else {
				model.put("errorMessage", "Customer With This Mobile Number Does Not Exist");
				return "modify_customer_details";
			}
		} else {
			if (repository.findByCustomerID(customer) != null) {
				cust = repository.findByCustomerID(customer);
				model.addAttribute("customer", cust);
				return "edit_customer_details";
			} else {
				model.put("errorMessage", "Customer With This Customer ID Does Not Exist");
				return "modify_customer_details";
			}
		}
	}

	@PostMapping("edit_customer")
	public String editCustomer(@RequestParam("customer_id") String customerID, @RequestParam("email_id") String emailId,
			@RequestParam("aadhar_number") String aadharNumber, @RequestParam("customer_name") String customerName,
			@RequestParam("mobile_number") String mobileNumber, @RequestParam("pan_number") String panNumber,
			ModelMap model) {

		System.out.println(customerID);

		String message = service.editCustomer(customerID, emailId, aadharNumber, customerName, mobileNumber, panNumber);

		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Customer Updated Successfully");
			Customer cust = repository.findByCustomerID(customerID);
			model.addAttribute("customer", cust);
			return "edit_customer_details";
		} else {
			model.put("errorMessage", message);
			Customer cust = repository.findByCustomerID(customerID);
			model.addAttribute("customer", cust);
			return "edit_customer_details";
		}
	}

	@PostMapping("send_notification_to_all")
	public String sendNotToAll(@RequestParam("notification_type") String notificationType,
			@RequestParam("title") String title, @RequestParam("description") String description, ModelMap model) {
		String message = service.sendToAll(notificationType, title, description);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Notification Sent!");
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		} else {
			model.put("errorMessage", message);
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		}
	}

	@PostMapping("send_notification_single_customer")
	public String sendToSingle(@RequestParam("customer_id") String customerID,
			@RequestParam("notification_type") String notificationType, @RequestParam("title") String title,
			@RequestParam("description") String description, ModelMap model) {
		String message = service.sendToSingle(customerID, notificationType, title, description);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Notification Sent!");
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		} else {
			model.put("errorMessage", message);
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		}
	}

	@PostMapping("send_notification_corporate_project")
	public String sendToCorporate(@RequestParam("cproject_id") String projectID,
			@RequestParam("notification_type") String notificationType, @RequestParam("title") String title,
			@RequestParam("description") String description, ModelMap model) {
		String message = service.sendToCorporate(projectID, notificationType, title, description);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Notification Sent!");
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		} else {
			model.put("errorMessage", message);
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		}
	}

	@PostMapping("send_notification_merchant_wise")
	public String sendMerchantWise(@RequestParam("project_id") String projectID,
			@RequestParam("merchant_id") String merchantID, @RequestParam("notification_type") String notificationType,
			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("all_merchants") boolean allMerchants, ModelMap model) {

		String message = "";

		if (allMerchants) {
			message = service.sendToAllMerchants(projectID, notificationType, title, description);
		} else {
			message = service.sendToSingleMerchants(merchantID, notificationType, title, description);
		}
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Notification Sent!");
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		} else {
			model.put("errorMessage", message);
			List<Customer> customers = service.getAllCustomers();
			model.addAttribute("customers", customers);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			List<Project> ncprojects = pservice.getAllNonCorporateProjects();
			model.addAttribute("ncprojects", ncprojects);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_customer";
		}
	}
	
	@PostMapping("customer_pp_upload")
	public String merchantPPUploads(@RequestParam("privacy_policy") MultipartFile privacyPolicy, ModelMap model) {
		
		String fileName = StringUtils.cleanPath(privacyPolicy.getOriginalFilename());
		String ext = FilenameUtils.getExtension(fileName);
		if(!ext.equals("txt")) {
			model.put("errorMessage", "Please select a '.txt' file.");
			return "customer_uploads";
		}
		String message = service.customerPPUploads(privacyPolicy);
		if(message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Privacy Policy Uploaded Successfully!");
			DocumentUploads uploads = service.getCustomerDocUploads();
			model.addAttribute("uploads",uploads);
			return "customer_uploads";
		} else {
			model.put("errorMessage", message);
			return "customer_uploads";
		}
	}
	@PostMapping("customer_tc_uploads")
	public String merchantTCUploads(@RequestParam("terms_and_conditions") MultipartFile termsAndConditons, ModelMap model) {
		
		String fileName = StringUtils.cleanPath(termsAndConditons.getOriginalFilename());
		String ext = FilenameUtils.getExtension(fileName);
		if( !ext.equals("txt") ) {
			model.put("errorMessage", "Please select a '.txt' file.");
			return "customer_uploads";
		}
		String message = service.customerTCUploads(termsAndConditons);
		if(message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Terms And Conditions Uploaded Successfully!");
			DocumentUploads uploads = service.getCustomerDocUploads();
			model.addAttribute("uploads",uploads);
			return "customer_uploads";
		} else {
			model.put("errorMessage", message);
			return "customer_uploads";
		}
	}
	@PostMapping("customer_ug_uploads")
	public String merchantUGUploads(@RequestParam("user_guide") MultipartFile userGuide, ModelMap model) {
		
		
		String fileName = StringUtils.cleanPath(userGuide.getOriginalFilename());
		String ext = FilenameUtils.getExtension(fileName);
		if(!ext.equals("txt")) {
			model.put("errorMessage", "Please select a '.txt' file.");
			return "customer_uploads";
		}
		String message = service.customerUGUploads(userGuide);
		if(message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "User Guide Uploaded Successfully!");
			DocumentUploads uploads = service.getCustomerDocUploads();
			model.addAttribute("uploads",uploads);
			return "customer_uploads";
		} else {
			model.put("errorMessage", message);
			return "customer_uploads";
		}
	}

}
