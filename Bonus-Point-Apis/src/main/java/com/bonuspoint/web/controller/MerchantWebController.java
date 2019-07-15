package com.bonuspoint.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bonuspoint.model.DocumentUploads;
import com.bonuspoint.model.LoginPageInfo;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.MerchantAddress;
import com.bonuspoint.model.MerchantReduceLimit;
import com.bonuspoint.model.MerchantTopupLimit;
import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.model.PotentialMerchants;
import com.bonuspoint.model.Project;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.rest.service.ProjectService;
import com.bonuspoint.web.service.LoginPageInfoService;
import com.bonuspoint.web.service.MerchantWebService;

@Controller
public class MerchantWebController {

	@Autowired
	ProjectService pservice;

	@Autowired
	MerchantWebService mwservice;

	@Autowired
	ProjectRepository repository;

	@Autowired
	LoginPageInfoService lpservice;

	@GetMapping("add_merchant")
	public String goToAddMerchant(ModelMap model) {
		List<Project> projects = pservice.getAllProjects();
		model.addAttribute("projects", projects);
		return "add_merchant";
	}

	@PostMapping("add_merchant")
	public String addMerchant(@RequestParam("project_id") String projectID, @RequestParam("shop_no") String shopNo,
			@RequestParam("contact_person") String contactPerson, @RequestParam("lane_no") String laneNo,
			@RequestParam("email_id") String emailId, @RequestParam("lane_name") String laneName,
			@RequestParam("aadhar_number") String aadharNumber, @RequestParam("area_name") String areaName,
			@RequestParam("shop_name") String shopName, @RequestParam("city") String city,
			@RequestParam("mobile_number") String mobileNumber, @RequestParam("country") String country,
			@RequestParam("gst_number") String gstNumber, @RequestParam("land_mark") String landmark,
			@RequestParam("pan_number") String panNumber, @RequestParam("state") String state,
			@RequestParam("legal_name") String legalName, @RequestParam("pincode") String pincode, ModelMap model) {

		Merchant merchant = new Merchant();
		merchant.setAadharNumber(aadharNumber);
		merchant.setContactPerson(contactPerson);
		merchant.setEmailId(emailId);
		merchant.setGstNumber(gstNumber);
		merchant.setLegalName(legalName);
		merchant.setMobileNumber(mobileNumber);
		merchant.setPanNumber(panNumber);
		merchant.setProjectID(projectID);
		merchant.setShopName(shopName);

		MerchantAddress address = new MerchantAddress();
		address.setAreaName(areaName);
		address.setCity(city);
		address.setCountry(country);
		address.setLandmark(landmark);
		address.setLaneName(laneName);
		address.setLaneNo(laneNo);
		address.setPin(pincode);
		address.setShopNo(shopNo);
		address.setState(state);

		merchant.setAddress(address);
		String message = mwservice.createMerchant(merchant);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Merchant Created");
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "add_merchant";
		} else {
			model.put("errorMessage", message);
			model.addAttribute("merchant", merchant);
			Project project = repository.findByProjectID(projectID);
			model.addAttribute("projectName", project.getProjectName());
			return "add_merchant";
		}
	}

	@PostMapping("edit_merchant")
	public String editMerchant(@RequestParam("merchant_id") String merchantID,
			@RequestParam("project_id") String projectID, @RequestParam("mId") long mId,
			@RequestParam("shop_no") String shopNo, @RequestParam("contact_person") String contactPerson,
			@RequestParam("lane_no") String laneNo, @RequestParam("email_id") String emailId,
			@RequestParam("lane_name") String laneName, @RequestParam("aadhar_number") String aadharNumber,
			@RequestParam("area_name") String areaName, @RequestParam("shop_name") String shopName,
			@RequestParam("city") String city, @RequestParam("mobile_number") String mobileNumber,
			@RequestParam("country") String country, @RequestParam("gst_number") String gstNumber,
			@RequestParam("landmark") String landmark, @RequestParam("pan_number") String panNumber,
			@RequestParam("state") String state, @RequestParam("legal_name") String legalName,
			@RequestParam("pincode") String pincode, ModelMap model) {

		Merchant merchant = new Merchant();
		merchant.setmId(mId);
		merchant.setAadharNumber(aadharNumber);
		merchant.setContactPerson(contactPerson);
		merchant.setEmailId(emailId);
		merchant.setGstNumber(gstNumber);
		merchant.setLegalName(legalName);
		merchant.setMobileNumber(mobileNumber);
		merchant.setPanNumber(panNumber);
		merchant.setProjectID(projectID);
		merchant.setShopName(shopName);
		merchant.setMerchantID(merchantID);

		MerchantAddress address = new MerchantAddress();
		address.setAreaName(areaName);
		address.setCity(city);
		address.setCountry(country);
		address.setLandmark(landmark);
		address.setLaneName(laneName);
		address.setLaneNo(laneNo);
		address.setPin(pincode);
		address.setShopNo(shopNo);
		address.setState(state);

		merchant.setAddress(address);
		String message = mwservice.updateMerchant(merchant);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Merchant Updated");
			Merchant updatedMerchant = mwservice.getMerchantByMid(mId);
			model.addAttribute("merchant", updatedMerchant);
			Project project = repository.findByProjectID(projectID);
			model.addAttribute("projectName", project.getProjectName());
			return "edit_merchant";
		} else {
			model.put("errorMessage", message);
			model.addAttribute("merchant", merchant);
			Project project = repository.findByProjectID(projectID);
			model.addAttribute("projectName", project.getProjectName());
			return "edit_merchant";
		}
	}

	@PostMapping("goto_view_merchant")
	public String goToViewMerchant(@RequestParam("mid") long mid, ModelMap model) {
		Merchant merchant = mwservice.getMerchantByMid(mid);
		Project project = repository.findByProjectID(merchant.getProjectID());
		model.addAttribute("merchant", merchant);
		model.addAttribute("projectName", project.getProjectName());
		return "view_merchant";
	}

	@PostMapping("goto_edit_merchant")
	public String goToEditMerchant(@RequestParam("mid") long mid, ModelMap model) {
		Merchant merchant = mwservice.getMerchantByMid(mid);

		Project project = repository.findByProjectID(merchant.getProjectID());
		model.addAttribute("merchant", merchant);
		model.addAttribute("projectName", project.getProjectName());
		return "edit_merchant";
	}

	@PostMapping("goto_add_merchant")
	public String gotoAddMerchant(@RequestParam("pmId") long pmId, ModelMap model) {
		PotentialMerchants pmerchants = mwservice.getPotentialMerchantBypmId(pmId);
		Merchant merchant = new Merchant();
		merchant.setContactPerson(pmerchants.getContactPerson());
		merchant.setEmailId(pmerchants.getEmailId());
		merchant.setMobileNumber(pmerchants.getMobileNumber());
		merchant.setLegalName(pmerchants.getLegalName());
		merchant.setShopName(pmerchants.getShopName());
		merchant.setAddress(pmerchants.getAddress());
		model.addAttribute("merchant", merchant);
		List<Project> projects = pservice.getAllProjects();
		model.addAttribute("projects", projects);
		model.put("potential", true);
		return "add_merchant";
	}

	@PostMapping("sendMerchantCredentials")
	public String sendMerchantCredentials(@RequestParam("project_id") String projectID,
			@RequestParam("merchant_id") String merchantID, @RequestParam("terminal_id") String terminalID,
			ModelMap model) {
		String message = mwservice.sendMerchantCredentials(projectID, merchantID, terminalID);

		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_merchant_credentials";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_merchant_credentials";
		}

	}

	@PostMapping("merchant_limit_topup")
	public String merchantLimitTopup(@RequestParam("project_id") String projectID,
			@RequestParam("merchant_id") String merchantID,
			@RequestParam("agreed_topup_amount") BigDecimal agreedTopupAmount,
			@RequestParam("actual_topup_paid") BigDecimal actualTopupPaid,
			@RequestParam("cheque_number") String chequeNumber, @RequestParam("cheque_date") Date chequeDate,
			@RequestParam("cheque_clearance_date") Date chequeClearanceDate,
			@RequestParam("transaction_id") String transactionID, @RequestParam("city") String city,
			@RequestParam("bank_name") String bankName, ModelMap model) {

		MerchantTopupLimit limit = new MerchantTopupLimit();
		limit.setActualTopupPaid(actualTopupPaid);
		limit.setAgreedTopupAmount(agreedTopupAmount);
		limit.setChequeClearanceDate(chequeClearanceDate);
		limit.setChequeDate(chequeDate);
		limit.setMerchantID(merchantID);
		limit.setProjectID(projectID);
		limit.setChequeNumber(chequeNumber);
		limit.setTransactionID(transactionID);
		limit.setCity(city);
		limit.setBankName(bankName);
		limit.setProjectName(repository.findByProjectID(projectID).getProjectName());

		String message = mwservice.topupLimit(limit);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Limit Updated Successfully");
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "update_merchant_topup_limit";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "update_merchant_topup_limit";
		}
	}

	@PostMapping("merchant_limit_reduce")
	public String merchantReduceLimit(@RequestParam("project_id") String projectID,
			@RequestParam("merchant_id") String merchantID, @RequestParam("transaction_id") String transactionID,
			@RequestParam("reduce_amount") BigDecimal reduceAmount, ModelMap model) {
		MerchantReduceLimit limit = new MerchantReduceLimit();
		limit.setMerchantID(merchantID);
		limit.setProjectID(projectID);
		limit.setReduceAmount(reduceAmount);
		limit.setTransactionID(transactionID);
		limit.setProjectName(repository.findByProjectID(projectID).getProjectName());

		String message = mwservice.reduceLimit(limit);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Limit Reduced Successfully");
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "merchant_reduce_limit";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "merchant_reduce_limit";
		}
	}

	@PostMapping("send_notification")
	public String sendNotification(@RequestParam("merchant_id") String merchantID,
			@RequestParam("notification_type") String notificationType, @RequestParam("title") String title,
			@RequestParam("description") String description, ModelMap model) {

		NotificationHistory not = new NotificationHistory();
		not.setDescription(description);
		not.setMerchantID(merchantID);
		not.setNotificationType(notificationType);
		not.setTitle(title);

		String message = mwservice.sendNotification(not);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Notification Sent");
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_merchant";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_merchant";
		}
	}

	@PostMapping("send_notification_project")
	public String sendNotificationToProject(@RequestParam("project_id") String projectID,
			@RequestParam("notification_type") String notificationType, @RequestParam("title") String title,
			@RequestParam("description") String description, ModelMap model) {

		String message = mwservice.sendNotificationToProject(projectID, notificationType, title, description);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Notifications Sent");
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_merchant";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			return "send_notifications_merchant";
		}
	}

	@PostMapping("update_login_page_info")
	public String updateLoginPageInfo(@RequestParam("sales_contact_name") String salesContactName,
			@RequestParam("sales_mobile_number") String salesMobileNumber,
			@RequestParam("sales_email_id") String salesEmailId, ModelMap model) {

		String message = lpservice.updateLoginPageInfo(salesContactName, salesMobileNumber, salesEmailId);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Information Updated");
			LoginPageInfo info = lpservice.getInfo();
			model.addAttribute("info", info);
			return "merchant_login_page_info";
		} else {
			model.put("errorMessage", message);
			LoginPageInfo info = lpservice.getInfo();
			model.addAttribute("info", info);
			return "merchant_login_page_info";
		}
	}

	@PostMapping("bonus_points_map")
	public String insertOrUpdateBonusPointsMap(@RequestParam("project_id") String projectID,
			@RequestParam("merchant_id") String merchantID, @RequestParam("user") String user,
			@RequestParam("bpPer100") int bpPer100, @RequestParam("amtPerBp") BigDecimal amountPerBp, ModelMap model) {
		String message = mwservice.insertOrUpdateBonusPointsMap(projectID, merchantID, user, bpPer100, amountPerBp);
		if (message.equalsIgnoreCase("Successfully Added")) {
			model.put("successMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			return "map_and_modify_bonuspoints";
		} else if (message.equalsIgnoreCase("Successfully Updated")) {
			model.put("successMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			return "map_and_modify_bonuspoints";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			return "map_and_modify_bonuspoints";
		}
	}

	@PostMapping("bonus_points_map_corporate")
	public String insertCorporateBonusPointsMap(@RequestParam("cproject_id") String projectID,
			@RequestParam("user") String user, @RequestParam("bpPer100") int bpPer100,
			@RequestParam("amtPerBp") BigDecimal amountPerBp, ModelMap model) {
		String message = mwservice.insertCorporateBonusPoints(projectID, user, bpPer100, amountPerBp);
		if (message.equalsIgnoreCase("Successfully Added")) {
			model.put("successMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			return "map_and_modify_bonuspoints";
		} else if (message.equalsIgnoreCase("Successfully Updated")) {
			model.put("successMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			return "map_and_modify_bonuspoints";
		} else {
			model.put("errorMessage", message);
			List<Project> projects = pservice.getAllProjects();
			model.addAttribute("projects", projects);
			List<Project> cprojects = pservice.getAllCorporateProjects();
			model.addAttribute("cprojects", cprojects);
			return "map_and_modify_bonuspoints";
		}
	}

	@PostMapping("upload_merchant_topup_limit")
	public String uploadLimit(@RequestParam("topup") MultipartFile file, ModelMap model) throws IOException {
		List<MerchantTopupLimit> limits = new ArrayList<MerchantTopupLimit>();

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			MerchantTopupLimit limit = new MerchantTopupLimit();

			XSSFRow row = worksheet.getRow(i);
			limit.setProjectID(row.getCell(0).getStringCellValue());
			limit.setProjectName(row.getCell(1).getStringCellValue());
			limit.setMerchantID(row.getCell(2).getStringCellValue());
			limit.setAgreedTopupAmount(new BigDecimal((double) row.getCell(3).getNumericCellValue()));
			limit.setActualTopupPaid(new BigDecimal((double) row.getCell(4).getNumericCellValue()));
			limit.setBankName(row.getCell(5).getStringCellValue());
			limit.setChequeNumber(row.getCell(6).getRawValue());
			limit.setChequeDate(row.getCell(7).getDateCellValue());
			limit.setChequeClearanceDate(row.getCell(8).getDateCellValue());
			limit.setTransactionID(row.getCell(9).getRawValue());
			limit.setCity(row.getCell(10).getStringCellValue());
			limits.add(limit);
		}

		workbook.close();
		java.util.Map<String, String> messages = mwservice.updateTopUpLimits(limits);
		model.addAttribute("messages", messages);
		return "upload_report";
	}

	@PostMapping("merchant_pp_upload")
	public String merchantPPUploads(@RequestParam("privacy_policy") MultipartFile privacyPolicy, ModelMap model) {
		
		String fileName = StringUtils.cleanPath(privacyPolicy.getOriginalFilename());
		String ext = FilenameUtils.getExtension(fileName);
		if(!ext.equals("txt")) {
			model.put("errorMessage", "Please select a '.txt' file.");
			return "merchant_uploads";
		}
		String message = mwservice.merchantPPUploads(privacyPolicy);
		if(message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Privacy Policy Uploaded Successfully!");
			DocumentUploads uploads = mwservice.getMerchantDocUploads();
			model.addAttribute("uploads", uploads);
			return "merchant_uploads";
		} else {
			model.put("errorMessage", message);
			return "merchant_uploads";
		}
	}
	@PostMapping("merchant_tc_uploads")
	public String merchantTCUploads(@RequestParam("terms_and_conditions") MultipartFile termsAndConditons, ModelMap model) {
		
		String fileName = StringUtils.cleanPath(termsAndConditons.getOriginalFilename());
		String ext = FilenameUtils.getExtension(fileName);
		if( !ext.equals("txt") ) {
			model.put("errorMessage", "Please select a '.txt' file.");
			DocumentUploads uploads = mwservice.getMerchantDocUploads();
			model.addAttribute("uploads", uploads);
			return "merchant_uploads";
		}
		String message = mwservice.merchantTCUploads(termsAndConditons);
		if(message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Terms And Conditions Uploaded Successfully!");
			DocumentUploads uploads = mwservice.getMerchantDocUploads();
			model.addAttribute("uploads", uploads);
			return "merchant_uploads";
		} else {
			model.put("errorMessage", message);
			return "merchant_uploads";
		}
	}
	@PostMapping("merchant_ug_uploads")
	public String merchantUGUploads(@RequestParam("user_guide") MultipartFile userGuide, ModelMap model) {
		
		
		String fileName = StringUtils.cleanPath(userGuide.getOriginalFilename());
		String ext = FilenameUtils.getExtension(fileName);
		if(!ext.equals("txt")) {
			model.put("errorMessage", "Please select a '.txt' file.");
			return "merchant_uploads";
		}
		String message = mwservice.merchantUGUploads(userGuide);
		if(message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "User Guide Uploaded Successfully!");
			DocumentUploads uploads = mwservice.getMerchantDocUploads();
			model.addAttribute("uploads", uploads);
			return "merchant_uploads";
		} else {
			model.put("errorMessage", message);
			return "merchant_uploads";
		}
	}

}
