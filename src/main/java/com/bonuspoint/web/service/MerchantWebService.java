package com.bonuspoint.web.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.FileStorageException;
import com.bonuspoint.model.BonusPointMap;
import com.bonuspoint.model.BonusPointMapTransactions;
import com.bonuspoint.model.DocumentUploads;
import com.bonuspoint.model.Mail;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.MerchantLimit;
import com.bonuspoint.model.MerchantReduceLimit;
import com.bonuspoint.model.MerchantTopupLimit;
import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.model.PaymentDueReport;
import com.bonuspoint.model.PotentialMerchants;
import com.bonuspoint.model.Terminal;
import com.bonuspoint.property.FileStorageProperties;
import com.bonuspoint.repository.BonusPointMapRepository;
import com.bonuspoint.repository.BonusPointMapTransactionRepository;
import com.bonuspoint.repository.DocumentUploadRepository;
import com.bonuspoint.repository.MerchantLimitRepository;
import com.bonuspoint.repository.MerchantReduceLimitRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.MerchantTopupLimitRepository;
import com.bonuspoint.repository.NotificationRepository;
import com.bonuspoint.repository.PotentialMerchantsRespository;
import com.bonuspoint.repository.TerminalRepository;
import com.bonuspoint.rest.service.EmailService;
import com.bonuspoint.rest.service.ProjectService;
import com.bonuspoint.rest.service.TerminalService;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;
import com.bonuspoint.util.SendSMS;

import okhttp3.Response;

@Service
public class MerchantWebService {

	@Autowired
	MerchantRepository repository;

	@Autowired
	TerminalRepository trepository;

	@Autowired
	PotentialMerchantsRespository pmrepository;

	@Autowired
	BonusPointMapRepository bprepository;

	@Autowired
	BonusPointMapTransactionRepository bptrepository;

	@Autowired
	MerchantTopupLimitRepository mtlrepository;

	@Autowired
	MerchantReduceLimitRepository mrrepository;

	@Autowired
	MerchantLimitRepository mlrepository;

	@Autowired
	NotificationRepository nrepository;

	@Autowired
	TerminalService service;

	@Autowired
	ProjectService pservice;

	@Autowired
	DocumentUploadRepository drepository;

	@Autowired
	EmailService emailService;

	private final Path fileStorageLocation;

	@Autowired
	public MerchantWebService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String createMerchant(Merchant merchant) {
		try {

			List<Merchant> merchants = repository.findAll();
			ArrayList<String> mids = new ArrayList<String>();
			for (Merchant m : merchants) {
				mids.add(m.getMerchantID());
			}

			merchant.setMerchantID(GenerateID.generateMerchantID(mids, merchant.getProjectID().substring(0, 4)));
			Merchant merchantDetails = repository.save(merchant);

			List<Terminal> terminals = new ArrayList<Terminal>();

			Terminal terminal = service.createTerminal(merchantDetails.getProjectID(), merchantDetails.getMerchantID());
			terminals.add(terminal);

			merchantDetails.setTerminals(terminals);

			if (pmrepository.findByEmailId(merchantDetails.getEmailId()) != null) {
				PotentialMerchants pmerchant = pmrepository.findByEmailId(merchantDetails.getEmailId());
				pmerchant.setIsApproved(true);
				pmrepository.save(pmerchant);
			}

			/*
			 * String messageLine1 = "Biggbonuspoints Credentials "; String messageLine2 =
			 * " MID : " + merchantDetails.getMerchantID(); String messageLine3 = " TID : "
			 * + merchantDetails.getTerminals().get(0).getTerminalID(); String messageLine4
			 * = " PWD : " + merchantDetails.getTerminals().get(0).getPassword(); String
			 * messageLine5 = " PIN : " + merchantDetails.getTerminals().get(0).getPin();
			 * 
			 * String fullMessage = messageLine1 + "\n" + messageLine2 + "\n" + messageLine3
			 * + "\n" + messageLine4 + "\n" + messageLine5;
			 * 
			 * Response response = SendSMS.sendSMS(merchantDetails.getMobileNumber(),
			 * fullMessage); response.close();
			 */

			repository.save(merchantDetails);
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

	public Merchant getMerchantByMid(long mid) {
		return repository.findById(mid).orElse(new Merchant());
	}

	public PotentialMerchants getPotentialMerchantBypmId(long pmId) {
		return pmrepository.findById(pmId).orElse(new PotentialMerchants());
	}

	public String updateMerchant(Merchant merchant) {
		Merchant merchDetails = repository.findById(merchant.getmId()).orElse(new Merchant());
		if (merchant.getAadharNumber() != null) {
			merchDetails.setAadharNumber(merchant.getAadharNumber());
		}

		if (merchant.getAadharNumber() != null) {
			merchDetails.setAddress(merchant.getAddress());
		}

		if (merchant.getContactPerson() != null) {
			merchDetails.setContactPerson(merchant.getContactPerson());
		}

		if (merchant.getEmailId() != null) {
			merchDetails.setEmailId(merchant.getEmailId());
		}

		if (merchant.getGstNumber() != null) {
			merchDetails.setGstNumber(merchant.getGstNumber());
		}

		if (merchant.getLegalName() != null) {
			merchDetails.setLegalName(merchant.getLegalName());
		}
		if (merchant.getMobileNumber() != null) {
			merchDetails.setMobileNumber(merchant.getMobileNumber());
		}

		if (merchant.getPanNumber() != null) {
			merchDetails.setPanNumber(merchant.getPanNumber());
		}

		if (merchant.getShopName() != null) {
			merchDetails.setShopName(merchant.getShopName());
		}

		try {
			repository.save(merchDetails);
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

	public String sendMerchantCredentials(String projectID, String merchantID, String terminalID) {

		Merchant merchant = repository.findByMerchantID(merchantID);
		Terminal terminal = trepository.findByTerminalID(terminalID);

		String message = "Welcome to Biggbonuspoints. MID " + merchantID
				+ ",TID " + terminalID + ",Pwd " + terminal.getPassword() + ",PIN " + terminal.getPin()
				+ ".Change your password and pin on receiving";
		Response response;
		try {
			response = SendSMS.sendSMS(merchant.getMobileNumber(), message);
			response.close();

			Mail mail = new Mail();
			mail.setFrom("customercare@biggbonuspoints.com");
			mail.setTo(merchant.getEmailId());
			mail.setSubject("Welcome to Biggbonuspoints, score as you shop, a biggest loyalty program.");
			String contentLine1 = "MID :" + merchantID;
			String contentLine2 = "TID :" + terminalID;
			String contentLine3 = "PWD :" + terminal.getPassword();
			String contentLine4 = "PIN :" + terminal.getPin();

			String finalContent = contentLine1 + "/n" + contentLine2 + "\n" + contentLine3 + "\n" + contentLine4;
			mail.setContent(finalContent);
			emailService.sendSimpleMessage(mail);

			return "Success";
		} catch (IOException e) {
			return "Not Able To Send Details To Merchant";
		} catch (Exception e) {
			return "Not Able To Send Details To Merchant";
		}

	}

	public String insertOrUpdateBonusPointsMap(String projectID, String merchantID, String user, int bpPer100,
			BigDecimal amountPerBp) {

		BonusPointMapTransactions mapTransactions = new BonusPointMapTransactions();
		mapTransactions.setMappedBy(user);
		mapTransactions.setProjectID(projectID);
		mapTransactions.setMerchantID(merchantID);
		mapTransactions.setMappingType("Individual");

		if (bprepository.findByMerchantID(merchantID) != null) {

			BonusPointMap map = bprepository.findByMerchantID(merchantID);
			mapTransactions.setPrevAmountPerBP(map.getAmountPerBP());
			mapTransactions.setPrevBpPer100(map.getBpPer100());
			mapTransactions.setNewAmountPerBP(amountPerBp);
			mapTransactions.setNewBpPer100(bpPer100);
			map.setAmountPerBP(amountPerBp);
			map.setBpPer100(bpPer100);
			try {
				bprepository.save(map);
				bptrepository.save(mapTransactions);
				return "Successfully Updated";
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
			BonusPointMap map = new BonusPointMap();
			map.setAmountPerBP(amountPerBp);
			map.setBpPer100(bpPer100);
			map.setMerchantID(merchantID);
			map.setProjectID(projectID);
			mapTransactions.setNewAmountPerBP(amountPerBp);
			mapTransactions.setNewBpPer100(bpPer100);

			try {
				bprepository.save(map);
				bptrepository.save(mapTransactions);
				return "Successfully Added";
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

	public String topupLimit(MerchantTopupLimit limit) {
		String projectID = limit.getProjectID();

		if (pservice.projectType(projectID).equalsIgnoreCase("SINGLE")) {
			return "Cannot Set Limit For SINGLE Merchants";
		}

		if (limit.getChequeClearanceDate().before(limit.getChequeDate())) {
			return "Clearance Date Cannot be before Cheque Date";
		}

		if (limit.getMerchantID() == null || limit.getMerchantID().equals("0")) {
			return "Please Select Merchant ID And Then Proceed";
		}

		try {
			MerchantTopupLimit lim = mtlrepository.save(limit);
			String merchantID = lim.getMerchantID();

			if (mlrepository.findByMerchantID(merchantID) != null) {
				MerchantLimit mlim = mlrepository.findByMerchantID(merchantID);

				BigDecimal oldLimitAmount = mlim.getMerchantLimit();
				BigDecimal newLimitAmount = oldLimitAmount.add(lim.getActualTopupPaid());
				Date oldDueDate = mlim.getDueDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(oldDueDate);
				cal.add(Calendar.YEAR, 1);
				Date newDueDate = cal.getTime();
				mlim.setMerchantLimit(newLimitAmount);
				mlim.setDueDate(newDueDate);
				mlrepository.save(mlim);
			} else {

				MerchantLimit mlim = new MerchantLimit();
				mlim.setMerchantID(merchantID);
				mlim.setMerchantLimit(lim.getActualTopupPaid());
				Calendar cal = Calendar.getInstance();
				cal.setTime(limit.getChequeClearanceDate());
				cal.add(Calendar.YEAR, 1);
				mlim.setDueDate(cal.getTime());
				mlrepository.save(mlim);
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

	public String reduceLimit(MerchantReduceLimit limit) {
		String projectID = limit.getProjectID();
		String merchantID = limit.getMerchantID();

		if (pservice.projectType(projectID).equalsIgnoreCase("SINGLE")) {
			return "Cannot Reduce Limit For SINGLE Merchants";
		}

		try {
			if (mlrepository.findByMerchantID(merchantID) != null) {
				MerchantLimit mlim = mlrepository.findByMerchantID(merchantID);
				double currentLimit = mlim.getMerchantLimit().doubleValue();
				double reduceAmount = limit.getReduceAmount().doubleValue();

				if (currentLimit < reduceAmount) {
					return "Reduce Amount Cannot Be Less Than Current Limit";
				} else {
					BigDecimal oldLimitAmount = mlim.getMerchantLimit();
					BigDecimal newLimitAmount = oldLimitAmount.subtract(limit.getReduceAmount());
					mlim.setMerchantLimit(newLimitAmount);
					mlrepository.save(mlim);
					mrrepository.save(limit);
					return "Success";
				}
			} else {
				return "Limit Not Found For This Merchant";
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
	}

	public String sendNotification(NotificationHistory not) {
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

	public String sendNotificationToProject(String projectID, String notificationType, String title,
			String description) {

		List<Merchant> merchants = repository.findByProjectID(projectID);
		NotificationHistory not = null;
		try {

			for (Merchant merchant : merchants) {
				not = new NotificationHistory();
				not.setMerchantID(merchant.getMerchantID());
				not.setDescription(description);
				not.setNotificationType(notificationType);
				not.setTitle(title);
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

	public String insertCorporateBonusPoints(String projectID, String user, int bpPer100, BigDecimal amountPerBp) {

		String retString = "Something Went Wrong. Try Again! If Problem Persists, Contact Developers!";
		boolean isErrorOccured = false;

		if (!repository.findByProjectID(projectID).isEmpty()) {

			List<Merchant> merchants = repository.findByProjectID(projectID);
			BonusPointMapTransactions mapTransactions = null;
			for (Merchant merchant : merchants) {

				mapTransactions = new BonusPointMapTransactions();
				mapTransactions.setProjectID(projectID);
				mapTransactions.setMerchantID(merchant.getMerchantID());
				mapTransactions.setMappedBy(user);
				mapTransactions.setMappingType("Corporate");
				mapTransactions.setCorpAmountPerBP(amountPerBp);
				mapTransactions.setCorpBpPer100(bpPer100);
				if (bprepository.findByMerchantID(merchant.getMerchantID()) != null) {

					BonusPointMap map = bprepository.findByMerchantID(merchant.getMerchantID());

					mapTransactions.setPrevAmountPerBP(map.getAmountPerBP());
					mapTransactions.setPrevBpPer100(map.getBpPer100());
					mapTransactions.setNewAmountPerBP(amountPerBp);
					mapTransactions.setNewBpPer100(bpPer100);
					map.setAmountPerBP(amountPerBp);
					map.setBpPer100(bpPer100);
					map.setCorpAmountPerBP(amountPerBp);
					map.setCorpBpPer100(bpPer100);
					try {
						bprepository.save(map);
						bptrepository.save(mapTransactions);
					} catch (DataIntegrityViolationException e) {
						String[] msg = e.getRootCause().toString().split(":");
						String[] errMsg = msg[1].split("for");
						retString = errMsg[0];
						isErrorOccured = true;
					} catch (ConstraintViolationException e) {
						String[] msg = e.getMessage().split("=");
						String[] errMsg = msg[1].split(",");
						retString = errMsg[0];
						isErrorOccured = true;
					}
					retString = "Successfully Updated";
				} else {
					BonusPointMap map = new BonusPointMap();
					map.setAmountPerBP(amountPerBp);
					map.setBpPer100(bpPer100);
					map.setCorpAmountPerBP(amountPerBp);
					map.setCorpBpPer100(bpPer100);
					map.setMerchantID(merchant.getMerchantID());
					map.setProjectID(projectID);
					mapTransactions.setNewAmountPerBP(amountPerBp);
					mapTransactions.setNewBpPer100(bpPer100);

					try {
						bprepository.save(map);
						bptrepository.save(mapTransactions);
					} catch (DataIntegrityViolationException e) {
						String[] msg = e.getRootCause().toString().split(":");
						String[] errMsg = msg[1].split("for");
						retString = errMsg[0];
						isErrorOccured = true;
					} catch (ConstraintViolationException e) {
						String[] msg = e.getMessage().split("=");
						String[] errMsg = msg[1].split(",");
						retString = errMsg[0];
						isErrorOccured = true;
					}
					retString = "Successfully Added";
				}

				if (isErrorOccured) {
					return retString;
				}
			}
		} else {
			retString = "No Merchant Found Under This Project";
		}

		return retString;

	}

	public java.util.Map<String, String> updateTopUpLimits(List<MerchantTopupLimit> limits) {

		java.util.Map<String, String> messages = new HashMap<String, String>();

		for (MerchantTopupLimit limit : limits) {
			if (pservice.projectType(limit.getProjectID()).equalsIgnoreCase("SINGLE")) {
				messages.put(limit.getTransactionID(), "Cannot Set Limit For SINGLE Merchants");
			} else {
				try {
					MerchantTopupLimit lim = mtlrepository.save(limit);
					String merchantID = lim.getMerchantID();

					if (mlrepository.findByMerchantID(merchantID) != null) {
						MerchantLimit mlim = mlrepository.findByMerchantID(merchantID);

						BigDecimal oldLimitAmount = mlim.getMerchantLimit();
						BigDecimal newLimitAmount = oldLimitAmount.add(lim.getActualTopupPaid());
						Date oldDueDate = mlim.getDueDate();
						Calendar cal = Calendar.getInstance();
						cal.setTime(oldDueDate);
						cal.add(Calendar.YEAR, 1);
						Date newDueDate = cal.getTime();

						mlim.setMerchantLimit(newLimitAmount);
						mlim.setDueDate(newDueDate);
						mlrepository.save(mlim);
					} else {

						MerchantLimit mlim = new MerchantLimit();
						mlim.setMerchantID(merchantID);
						mlim.setMerchantLimit(lim.getActualTopupPaid());
						Calendar cal = Calendar.getInstance();
						cal.setTime(limit.getChequeClearanceDate());
						cal.add(Calendar.YEAR, 1);
						mlim.setDueDate(cal.getTime());

						mlrepository.save(mlim);
					}
					messages.put(limit.getTransactionID(), "Limit Updated Successfully");
				} catch (DataIntegrityViolationException e) {
					String[] msg = e.getRootCause().toString().split(":");
					String[] errMsg = msg[1].split("for");
					messages.put(limit.getTransactionID(), errMsg[0]);
				} catch (ConstraintViolationException e) {
					String[] msg = e.getMessage().split("=");
					String[] errMsg = msg[1].split(",");
					messages.put(limit.getTransactionID(), errMsg[0]);
				}
			}
		}

		return messages;
	}

	public List<PaymentDueReport> getReports(List<MerchantLimit> limits) {

		List<PaymentDueReport> reports = new ArrayList<PaymentDueReport>();

		PaymentDueReport report = null;

		for (MerchantLimit limit : limits) {
			if (repository.findByMerchantID(limit.getMerchantID()) != null) {
				Merchant merchant = repository.findByMerchantID(limit.getMerchantID());
				report = new PaymentDueReport();

				report.setMerchantID(limit.getMerchantID());
				report.setProjectID(merchant.getProjectID());
				report.setContactPerson(merchant.getContactPerson());
				report.setMobileNumber(merchant.getMobileNumber());
				report.setEmailId(merchant.getEmailId());
				report.setDueDate(limit.getDueDate());
				report.setMerchantLimit(limit.getMerchantLimit());

				reports.add(report);
			} else
				throw new CustomErrorException("Merchant", "Cannot Find Merchant",
						ErrorCodes.RESOURCE_NOT_FOUND.getCode());
		}

		return reports;
	}

	public List<MerchantLimit> getAllMerchantLimits() {
		return mlrepository.findAll();
	}

	public String merchantPPUploads(MultipartFile privacyPolicy) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(privacyPolicy.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "MprivacyPolicy" + "." + ext;

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
			if (drepository.findByUserType("merchant") != null) {
				DocumentUploads uploads = drepository.findByUserType("merchant");
				uploads.setPrivacyPolicyPath(fileDownloadUri);
				uploads.setUpdateCounter(uploads.getUpdateCounter() + 1);
				drepository.save(uploads);
			} else {
				DocumentUploads uploads = new DocumentUploads();
				uploads.setUserType("merchant");
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

	public String merchantTCUploads(MultipartFile termsAndConditons) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(termsAndConditons.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "MtermsAndConditons" + "." + ext;

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
			if (drepository.findByUserType("merchant") != null) {
				DocumentUploads uploads = drepository.findByUserType("merchant");
				uploads.setTermsAndConditionsPath(fileDownloadUri);
				uploads.setUpdateCounter(uploads.getUpdateCounter() + 2);
				drepository.save(uploads);
			} else {
				DocumentUploads uploads = new DocumentUploads();
				uploads.setUserType("merchant");
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

	public String merchantUGUploads(MultipartFile userGuide) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(userGuide.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "MuserGuide" + "." + ext;

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
			if (drepository.findByUserType("merchant") != null) {
				DocumentUploads uploads = drepository.findByUserType("merchant");
				uploads.setUserGuidePath(fileDownloadUri);
				uploads.setUpdateCounter(uploads.getUpdateCounter() + 5);
				drepository.save(uploads);
			} else {
				DocumentUploads uploads = new DocumentUploads();
				uploads.setUserType("merchant");
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

	public DocumentUploads getMerchantDocUploads() {
		return drepository.findByUserType("merchant");
	}

}
