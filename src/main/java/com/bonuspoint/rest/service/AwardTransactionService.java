package com.bonuspoint.rest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.AwardTransactions;
import com.bonuspoint.model.CorporateAccounts;
import com.bonuspoint.model.CorporateMerchantsBalanceTables;
import com.bonuspoint.model.Customer;
import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.model.CustomersBalanceTables;
import com.bonuspoint.model.Mail;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.MerchantLimit;
import com.bonuspoint.model.MerchantTopupLimit;
import com.bonuspoint.model.MerchantsBalanceTables;
import com.bonuspoint.model.Project;
import com.bonuspoint.model.ProjectsBalanceTable;
import com.bonuspoint.model.CustomerSummary;
import com.bonuspoint.repository.AwardTransationsRepository;
import com.bonuspoint.repository.CorporateAccountsRepository;
import com.bonuspoint.repository.CorporateMerchantsBalanceTablesRepository;
import com.bonuspoint.repository.CustomerBalanceTableRepository;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.repository.MerchantBalanceTableRepository;
import com.bonuspoint.repository.MerchantLimitRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.MerchantTopupLimitRepository;
import com.bonuspoint.repository.ProjectBalanceTableRepository;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.repository.SummaryRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AwardTransactionService {

	@Autowired
	AwardTransationsRepository arepository;

	@Autowired
	CustomerMerchantBPAccountRepository cmrepository;

	@Autowired
	MerchantLimitRepository mlrepository;

	@Autowired
	SummaryRepository sumrepository;

	@Autowired
	MerchantRepository merchantRepository;

	@Autowired
	CustomerRepository crepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CorporateAccountsRepository coRepository;

	@Autowired
	MerchantBalanceTableRepository mbrepository;

	@Autowired
	CustomerBalanceTableRepository cbrepository;

	@Autowired
	ProjectBalanceTableRepository pbrepository;

	@Autowired
	CorporateMerchantsBalanceTablesRepository cmbrepository;

	@Autowired
	EmailService emailService;

	@Autowired
	MerchantTopupLimitRepository mtlrepository;

	public AwardTransactions create(@Valid AwardTransactions awards) {

		String customerID = awards.getCustomerID();
		String merchantID = awards.getMerchantID();

		int awardPoints = awards.getAwardPointsAccrued();
		BigDecimal awardAmount = awards.getAwardAmount();

		if (merchantRepository.findByMerchantID(merchantID) == null) {
			throw new ResourceNotFoundException("Merchant", "merchantID", merchantID);
		}

		if (crepository.findByCustomerID(customerID) == null) {
			throw new ResourceNotFoundException("Customer", "customerID", customerID);
		}

		Merchant merchant = merchantRepository.findByMerchantID(merchantID);
		String projectID = merchant.getProjectID();
		Project project = projectRepository.findByProjectID(projectID);
		Customer customer = crepository.findByCustomerID(customerID);

		if (project.getProjectType().equalsIgnoreCase("CORPORATE")) {
			awardCorporateMerchant(customerID, merchantID, projectID, awardPoints, awardAmount, customer, merchant,
					project);
		} else if (project.getProjectType().equalsIgnoreCase("MULTIPLE")) {
			awardMultipleMerchant(customerID, merchantID, projectID, awardPoints, awardAmount, customer);
		} else {
			awardSingleMerchant(customerID, merchantID, projectID, awardPoints, awardAmount, customer);
		}

		AwardTransactions award = arepository.save(awards);
		award.setRrn(GenerateID.generateRrn(award.getAtId(), award.getCreatedAt()));

		CustomerSummary summ = sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID);
		try {
			if (customer.getEmailId() != null) {
				String toAddress = customer.getEmailId();
				String subject = "You have been Awarded " + awardPoints + " points and " + awardAmount + " rupees by "
						+ merchant.getShopName();
				String content1 = "This is to notify that you have been awarded " + awardPoints + " points and "
						+ awardAmount + " rupees by " + merchant.getShopName() + " at "
						+ merchant.getAddress().getAreaName() + " dated " + award.getCreatedAt().toString() + "."
						+ "Your current balance is " + summ.getTotalBonusPoints() + " points and "
						+ summ.getTotalBonusAmount()
						+ " rupees. Please check your Biggbonuspoints mobile app for verification.";
				String content2 = "In case of any discrepancy please reach out to the mentioned shop immediately."
						+ " In case its not reflecting in mobile App, do write us on "
						+ "customercare@biggbonuspoints.com ";
				String finalContent = content1 + "/n" + content2;

				Mail mail = new Mail();
				mail.setTo(toAddress);
				mail.setFrom("customercare@biggbonuspoints.com");
				mail.setSubject(subject);
				mail.setContent(finalContent);

				emailService.sendSimpleMessage(mail);

			}
		} catch (Exception e) {
			return arepository.save(award);
		}

		return arepository.save(award);
	}

	public List<AwardTransactions> getAllTransactions() {
		return arepository.findAll();
	}

	public List<AwardTransactions> getTransactionsByCustomerId(String customerID) {

		if (!arepository.findByCustomerID(customerID).isEmpty()) {
			return arepository.findByCustomerID(customerID);
		} else
			throw new ResourceNotFoundException("AwardTransacctions", "customerID", customerID);
	}

	public List<AwardTransactions> getTransactionsByMerchantId(String merchantID) {

		if (!arepository.findByMerchantID(merchantID).isEmpty()) {
			return arepository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("AwardTransacctions", "merchantID", merchantID);
	}

	private void awardSingleMerchant(String customerID, String merchantID, String projectID, int awardPoints,
			BigDecimal awardAmount, Customer customer) {
		try {
			if (cmrepository.findByCustomerIDAndMerchantProjectID(customerID, merchantID) != null) {

				CustomerMerchantBPAccount acc = cmrepository.findByCustomerIDAndMerchantProjectID(customerID,
						merchantID);

				int existingBonusPoints = acc.getBonusPoints();
				int updatedBonusPoints = existingBonusPoints + awardPoints;
				BigDecimal existingBonusAmount = acc.getBonusPointAmount();
				BigDecimal updatedBonusAmount = existingBonusAmount.add(awardAmount);
				acc.setBonusPoints(updatedBonusPoints);
				acc.setBonusPointAmount(updatedBonusAmount);
				acc.setShopName((merchantRepository.findByMerchantID(merchantID).getShopName()));
				acc.setLogo(merchantRepository.findByMerchantID(merchantID).getLogo());
				cmrepository.save(acc);

			} else {

				CustomerMerchantBPAccount acc = new CustomerMerchantBPAccount();

				acc.setCustomerID(customerID);
				acc.setMerchantProjectID(merchantID);
				acc.setShopName((merchantRepository.findByMerchantID(merchantID).getShopName()));
				acc.setLogo(merchantRepository.findByMerchantID(merchantID).getLogo());
				acc.setBonusPoints(awardPoints);
				acc.setBonusPointAmount(awardAmount);
				acc.setType("merchant");
				cmrepository.save(acc);

			}

			if (sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID) != null) {

				CustomerSummary summary = sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID);
				int currentTotalBonusPoints = summary.getTotalBonusPoints();
				int updatedTotalBonusPoints = currentTotalBonusPoints + awardPoints;
				int currentTotalBonusPointsAwarded = summary.getTotalBonusPointsAwarded();
				int updatedTotalBonusPointsAwarded = currentTotalBonusPointsAwarded + awardPoints;
				BigDecimal currentTotalBonusAmount = summary.getTotalBonusAmount();
				BigDecimal updatedTotalBonusAmount = currentTotalBonusAmount.add(awardAmount);
				BigDecimal currentTotalBonusAmountAwarded = summary.getTotalBonusAmountAwarded();
				BigDecimal updatedTotalBonusAmountAwarded = currentTotalBonusAmountAwarded.add(awardAmount);
				summary.setTotalBonusPoints(updatedTotalBonusPoints);
				summary.setTotalBonusPointsAwarded(updatedTotalBonusPointsAwarded);
				summary.setTotalBonusAmount(updatedTotalBonusAmount);
				summary.setTotalBonusAmountAwarded(updatedTotalBonusAmountAwarded);
				if (summary.getCustomerName().isEmpty()) {
					summary.setCustomerName(customer.getCustomerName());
				}
				sumrepository.save(summary);
			} else {

				CustomerSummary summary = new CustomerSummary();
				summary.setCustomerID(customerID);
				summary.setMerchantID(merchantID);
				summary.setTotalBonusPoints(awardPoints);
				summary.setTotalBonusPointsAwarded(awardPoints);
				summary.setTotalBonusAmount(awardAmount);
				summary.setTotalBonusAmountAwarded(awardAmount);
				summary.setCustomerName(customer.getCustomerName());
				summary.setMobileNumber(customer.getMobileNumber());
				sumrepository.save(summary);
			}

			if (mbrepository.findByMerchantID(merchantID) != null) {
				MerchantsBalanceTables mtable = mbrepository.findByMerchantID(merchantID);
				int currentTotalPointsAwarded = mtable.getTotalPointsAwarded();
				int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
				BigDecimal currentTotalAmountAwarded = mtable.getTotalAmountAwarded();
				BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
				int currentFinalPoints = mtable.getFinalPoints();
				int updatedFinalPoints = currentFinalPoints + awardPoints;
				BigDecimal currentFinalAmount = mtable.getFinalAmount();
				BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
				mtable.setTotalAmountAwarded(updatedTotalAmountAwarded);
				mtable.setTotalPointsAwarded(updatedTotalPointsAwarded);
				mtable.setFinalAmount(updatedFinalAmount);
				mtable.setFinalPoints(updatedFinalPoints);
				mbrepository.save(mtable);
			} else {
				MerchantsBalanceTables mtable = new MerchantsBalanceTables();
				mtable.setMerchantID(merchantID);
				mtable.setProjectID(projectID);
				mtable.setTotalAmountAwarded(awardAmount);
				mtable.setTotalPointsAwarded(awardPoints);
				mtable.setFinalAmount(awardAmount);
				mtable.setFinalPoints(awardPoints);
				mbrepository.save(mtable);
			}

			if (cbrepository.findByCustomerIDAndProjectID(customerID, projectID) != null) {
				CustomersBalanceTables ctable = cbrepository.findByCustomerIDAndProjectID(customerID, projectID);
				int currentTotalPointsAwarded = ctable.getTotalPointsAwarded();
				int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
				BigDecimal currentTotalAmountAwarded = ctable.getTotalAmountAwarded();
				BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
				int currentFinalPoints = ctable.getFinalPoints();
				int updatedFinalPoints = currentFinalPoints + awardPoints;
				BigDecimal currentFinalAmount = ctable.getFinalAmount();
				BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
				ctable.setTotalAmountAwarded(updatedTotalAmountAwarded);
				ctable.setTotalPointsAwarded(updatedTotalPointsAwarded);
				ctable.setFinalAmount(updatedFinalAmount);
				ctable.setFinalPoints(updatedFinalPoints);
				cbrepository.save(ctable);
			} else {
				CustomersBalanceTables ctable = new CustomersBalanceTables();
				ctable.setCustomerID(customerID);
				ctable.setProjectID(projectID);
				ctable.setTotalAmountAwarded(awardAmount);
				ctable.setTotalPointsAwarded(awardPoints);
				ctable.setFinalAmount(awardAmount);
				ctable.setFinalPoints(awardPoints);
				cbrepository.save(ctable);
			}

			if (pbrepository.findByProjectID(projectID) != null) {
				ProjectsBalanceTable ptable = pbrepository.findByProjectID(projectID);
				int currentTotalPointsAwarded = ptable.getTotalPointsAwarded();
				int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
				BigDecimal currentTotalAmountAwarded = ptable.getTotalAmountAwarded();
				BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
				int currentFinalPoints = ptable.getFinalPoints();
				int updatedFinalPoints = currentFinalPoints + awardPoints;
				BigDecimal currentFinalAmount = ptable.getFinalAmount();
				BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
				ptable.setTotalAmountAwarded(updatedTotalAmountAwarded);
				ptable.setTotalPointsAwarded(updatedTotalPointsAwarded);
				ptable.setFinalAmount(updatedFinalAmount);
				ptable.setFinalPoints(updatedFinalPoints);
				pbrepository.save(ptable);
			} else {
				ProjectsBalanceTable ptable = new ProjectsBalanceTable();
				ptable.setProjectID(projectID);
				ptable.setTotalAmountAwarded(awardAmount);
				ptable.setTotalPointsAwarded(awardPoints);
				ptable.setFinalAmount(awardAmount);
				ptable.setFinalPoints(awardPoints);
				pbrepository.save(ptable);
			}
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Award Single", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Award Single", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	private void awardMultipleMerchant(String customerID, String merchantID, String projectID, int awardPoints,
			BigDecimal awardAmount, Customer customer) {
		try {
			if (mlrepository.findByMerchantID(merchantID) != null) {
				MerchantLimit lim = mlrepository.findByMerchantID(merchantID);

				BigDecimal existingLimit = lim.getMerchantLimit();

				if (existingLimit.compareTo(awardAmount) < 0) {
					throw new CustomErrorException("MerchantLimit", "Not Enough Limit",
							ErrorCodes.INSUFFICIENT_FUNDS.getCode());
				}
				BigDecimal updatedLimit = existingLimit.subtract(awardAmount);
				lim.setMerchantLimit(updatedLimit);
				MerchantTopupLimit limit = mtlrepository.findTop1ByMerchantIDOrderByCreatedAtDesc(merchantID);
				BigDecimal twentyPercent = (limit.getActualTopupPaid().divide(new BigDecimal(100)))
						.multiply(new BigDecimal(20));
				if (updatedLimit.compareTo(twentyPercent) < 0) {
					Mail mail = new Mail();
					mail.setTo(merchantRepository.findByMerchantID(merchantID).getEmailId());
					mail.setFrom("customercare@biggbonuspoints.com");
					mail.setSubject("Alert! Limit Less Than 20%!");
					String content1 = "Greetings Merchant";
					String content2 = "After your last transaction , your Top-Up Limit has reduced below 20%.";
					String content3 = "Recharge immediately to continue awarding without any interruptions.";
					String content4 = "Your Account Details are as follows:";
					String content5 = "MID :" + merchantID;
					String content6 = "Current Limit :" + updatedLimit;
					String content7 = "Due Date :" + lim.getDueDate().toString();
					String finalContent = content1 + "\n" + content2 + "\n" + content3 + "\n" + content4 + "\n"
							+ content5 + "\n" + content6 + "\n" + content7;
					mail.setContent(finalContent);
					emailService.sendSimpleMessage(mail);

					/*
					 * String message1 = "Alert! Limit below 20%! "; String message2 =
					 * "Recharge Immediately! "; String message3 = "MID : " + merchantID; String
					 * message4 = " Current Limit : " + updatedLimit; String message5 =
					 * "Due Date : " + lim.getDueDate().toString(); String finalMessage = message1 +
					 * "\n" + message2 + "\n" + message3 + "\n" + message4 + "\n" + message5;
					 */

					/*
					 * try { SendSMS.sendSMS(merchantRepository.findByMerchantID(merchantID).
					 * getMobileNumber(), finalMessage); } catch (IOException e) { throw new
					 * CustomErrorException("Award", "Could Not Send Sms to Merchant",
					 * ErrorCodes.INTERNAL_SERVER_ERROR.getCode()); }
					 */
				}
				mlrepository.save(lim);
			} else
				throw new CustomErrorException("MerchantLimit", ErrorCodes.LIMIT_NOT_SET.getDescription(),
						ErrorCodes.LIMIT_NOT_SET.getCode());

			if (cmrepository.findByCustomerIDAndMerchantProjectID(customerID, merchantID) != null) {

				CustomerMerchantBPAccount acc = cmrepository.findByCustomerIDAndMerchantProjectID(customerID,
						merchantID);

				int existingBonusPoints = acc.getBonusPoints();
				int updatedBonusPoints = existingBonusPoints + awardPoints;
				BigDecimal existingBonusAmount = acc.getBonusPointAmount();
				BigDecimal updatedBonusAmount = existingBonusAmount.add(awardAmount);
				acc.setBonusPoints(updatedBonusPoints);
				acc.setBonusPointAmount(updatedBonusAmount);
				acc.setShopName((merchantRepository.findByMerchantID(merchantID).getShopName()));
				acc.setLogo(merchantRepository.findByMerchantID(merchantID).getLogo());
				cmrepository.save(acc);

			} else {

				CustomerMerchantBPAccount acc = new CustomerMerchantBPAccount();

				acc.setCustomerID(customerID);
				acc.setMerchantProjectID(merchantID);
				acc.setShopName(merchantRepository.findByMerchantID(merchantID).getShopName());
				acc.setLogo(merchantRepository.findByMerchantID(merchantID).getLogo());
				acc.setBonusPoints(awardPoints);
				acc.setBonusPointAmount(awardAmount);
				acc.setType("merchant");
				cmrepository.save(acc);

			}

			if (sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID) != null) {

				CustomerSummary summary = sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID);
				int currentTotalBonusPoints = summary.getTotalBonusPoints();
				int updatedTotalBonusPoints = currentTotalBonusPoints + awardPoints;
				int currentTotalBonusPointsAwarded = summary.getTotalBonusPointsAwarded();
				int updatedTotalBonusPointsAwarded = currentTotalBonusPointsAwarded + awardPoints;
				BigDecimal currentTotalBonusAmount = summary.getTotalBonusAmount();
				BigDecimal updatedTotalBonusAmount = currentTotalBonusAmount.add(awardAmount);
				BigDecimal currentTotalBonusAmountAwarded = summary.getTotalBonusAmountAwarded();
				BigDecimal updatedTotalBonusAmountAwarded = currentTotalBonusAmountAwarded.add(awardAmount);
				summary.setTotalBonusPoints(updatedTotalBonusPoints);
				summary.setTotalBonusPointsAwarded(updatedTotalBonusPointsAwarded);
				summary.setTotalBonusAmount(updatedTotalBonusAmount);
				summary.setTotalBonusAmountAwarded(updatedTotalBonusAmountAwarded);
				if (summary.getCustomerName().isEmpty()) {
					summary.setCustomerName(customer.getCustomerName());
				}
				sumrepository.save(summary);
			} else {

				CustomerSummary summary = new CustomerSummary();
				summary.setCustomerID(customerID);
				summary.setMerchantID(merchantID);
				summary.setTotalBonusPoints(awardPoints);
				summary.setTotalBonusPointsAwarded(awardPoints);
				summary.setTotalBonusAmount(awardAmount);
				summary.setTotalBonusAmountAwarded(awardAmount);
				summary.setCustomerName(customer.getCustomerName());
				summary.setMobileNumber(customer.getMobileNumber());
				sumrepository.save(summary);
			}

			if (mbrepository.findByMerchantID(merchantID) != null) {
				MerchantsBalanceTables mtable = mbrepository.findByMerchantID(merchantID);
				int currentTotalPointsAwarded = mtable.getTotalPointsAwarded();
				int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
				BigDecimal currentTotalAmountAwarded = mtable.getTotalAmountAwarded();
				BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
				int currentFinalPoints = mtable.getFinalPoints();
				int updatedFinalPoints = currentFinalPoints + awardPoints;
				BigDecimal currentFinalAmount = mtable.getFinalAmount();
				BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
				mtable.setTotalAmountAwarded(updatedTotalAmountAwarded);
				mtable.setTotalPointsAwarded(updatedTotalPointsAwarded);
				mtable.setFinalAmount(updatedFinalAmount);
				mtable.setFinalPoints(updatedFinalPoints);
				mbrepository.save(mtable);
			} else {
				MerchantsBalanceTables mtable = new MerchantsBalanceTables();
				mtable.setMerchantID(merchantID);
				mtable.setProjectID(projectID);
				mtable.setTotalAmountAwarded(awardAmount);
				mtable.setTotalPointsAwarded(awardPoints);
				mtable.setFinalAmount(awardAmount);
				mtable.setFinalPoints(awardPoints);
				mbrepository.save(mtable);
			}

			if (cbrepository.findByCustomerIDAndProjectID(customerID, projectID) != null) {
				CustomersBalanceTables ctable = cbrepository.findByCustomerIDAndProjectID(customerID, projectID);
				int currentTotalPointsAwarded = ctable.getTotalPointsAwarded();
				int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
				BigDecimal currentTotalAmountAwarded = ctable.getTotalAmountAwarded();
				BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
				int currentFinalPoints = ctable.getFinalPoints();
				int updatedFinalPoints = currentFinalPoints + awardPoints;
				BigDecimal currentFinalAmount = ctable.getFinalAmount();
				BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
				ctable.setTotalAmountAwarded(updatedTotalAmountAwarded);
				ctable.setTotalPointsAwarded(updatedTotalPointsAwarded);
				ctable.setFinalAmount(updatedFinalAmount);
				ctable.setFinalPoints(updatedFinalPoints);
				cbrepository.save(ctable);
			} else {
				CustomersBalanceTables ctable = new CustomersBalanceTables();
				ctable.setCustomerID(customerID);
				ctable.setProjectID(projectID);
				ctable.setTotalAmountAwarded(awardAmount);
				ctable.setTotalPointsAwarded(awardPoints);
				ctable.setFinalAmount(awardAmount);
				ctable.setFinalPoints(awardPoints);
				cbrepository.save(ctable);
			}

			if (pbrepository.findByProjectID(projectID) != null) {
				ProjectsBalanceTable ptable = pbrepository.findByProjectID(projectID);
				int currentTotalPointsAwarded = ptable.getTotalPointsAwarded();
				int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
				BigDecimal currentTotalAmountAwarded = ptable.getTotalAmountAwarded();
				BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
				int currentFinalPoints = ptable.getFinalPoints();
				int updatedFinalPoints = currentFinalPoints + awardPoints;
				BigDecimal currentFinalAmount = ptable.getFinalAmount();
				BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
				ptable.setTotalAmountAwarded(updatedTotalAmountAwarded);
				ptable.setTotalPointsAwarded(updatedTotalPointsAwarded);
				ptable.setFinalAmount(updatedFinalAmount);
				ptable.setFinalPoints(updatedFinalPoints);
				pbrepository.save(ptable);
			} else {
				ProjectsBalanceTable ptable = new ProjectsBalanceTable();
				ptable.setProjectID(projectID);
				ptable.setTotalAmountAwarded(awardAmount);
				ptable.setTotalPointsAwarded(awardPoints);
				ptable.setFinalAmount(awardAmount);
				ptable.setFinalPoints(awardPoints);
				pbrepository.save(ptable);
			}
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Award Multiple", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Award Multiple", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}

	}

	private void awardCorporateMerchant(String customerID, String merchantID, String projectID, int awardPoints,
			BigDecimal awardAmount, Customer customer, Merchant merchant, Project project) {

		try {
			if (mlrepository.findByMerchantID(merchantID) != null) {
				MerchantLimit lim = mlrepository.findByMerchantID(merchantID);

				BigDecimal existingLimit = lim.getMerchantLimit();

				if (existingLimit.compareTo(awardAmount) < 0) {
					throw new CustomErrorException("MerchantLimit", "Not Enough Limit",
							ErrorCodes.INSUFFICIENT_FUNDS.getCode());
				}
				BigDecimal updatedLimit = existingLimit.subtract(awardAmount);
				lim.setMerchantLimit(updatedLimit);

				mlrepository.save(lim);

				if (cmrepository.findByCustomerIDAndMerchantProjectID(customerID, projectID) != null) {

					CustomerMerchantBPAccount acc = cmrepository.findByCustomerIDAndMerchantProjectID(customerID,
							projectID);

					int existingBonusPoints = acc.getBonusPoints();
					int updatedBonusPoints = existingBonusPoints + awardPoints;
					BigDecimal existingBonusAmount = acc.getBonusPointAmount();
					BigDecimal updatedBonusAmount = existingBonusAmount.add(awardAmount);
					acc.setBonusPoints(updatedBonusPoints);
					acc.setBonusPointAmount(updatedBonusAmount);
					acc.setShopName(projectRepository.findByProjectID(projectID).getProjectName());
					acc.setLogo(projectRepository.findByProjectID(projectID).getLogo());
					cmrepository.save(acc);

				} else {

					CustomerMerchantBPAccount acc = new CustomerMerchantBPAccount();

					acc.setCustomerID(customerID);
					acc.setMerchantProjectID(projectID);
					acc.setShopName(projectRepository.findByProjectID(projectID).getProjectName());
					acc.setLogo(projectRepository.findByProjectID(projectID).getLogo());
					acc.setBonusPoints(awardPoints);
					acc.setBonusPointAmount(awardAmount);
					acc.setType("project");
					cmrepository.save(acc);

				}

				if (sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID) != null) {

					CustomerSummary summary = sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID);
					int currentTotalBonusPoints = summary.getTotalBonusPoints();
					int updatedTotalBonusPoints = currentTotalBonusPoints + awardPoints;
					int currentTotalBonusPointsAwarded = summary.getTotalBonusPointsAwarded();
					int updatedTotalBonusPointsAwarded = currentTotalBonusPointsAwarded + awardPoints;
					BigDecimal currentTotalBonusAmount = summary.getTotalBonusAmount();
					BigDecimal updatedTotalBonusAmount = currentTotalBonusAmount.add(awardAmount);
					BigDecimal currentTotalBonusAmountAwarded = summary.getTotalBonusAmountAwarded();
					BigDecimal updatedTotalBonusAmountAwarded = currentTotalBonusAmountAwarded.add(awardAmount);
					summary.setTotalBonusPoints(updatedTotalBonusPoints);
					summary.setTotalBonusPointsAwarded(updatedTotalBonusPointsAwarded);
					summary.setTotalBonusAmount(updatedTotalBonusAmount);
					summary.setTotalBonusAmountAwarded(updatedTotalBonusAmountAwarded);
					sumrepository.save(summary);
				} else {

					CustomerSummary summary = new CustomerSummary();
					summary.setCustomerID(customerID);
					summary.setMerchantID(merchantID);
					summary.setTotalBonusPoints(awardPoints);
					summary.setTotalBonusPointsAwarded(awardPoints);
					summary.setTotalBonusAmount(awardAmount);
					summary.setTotalBonusAmountAwarded(awardAmount);
					summary.setCustomerName(customer.getCustomerName());
					summary.setMobileNumber(customer.getMobileNumber());
					sumrepository.save(summary);
				}

				if (coRepository.findByProjectID(projectID) != null) {
					CorporateAccounts account = coRepository.findByProjectID(projectID);

					int existingBonusPoint = account.getCorporateBonusPoint();
					BigDecimal existingBonusAmount = account.getCorporateBonusAmount();

					int updatedBonusPoint = existingBonusPoint + awardPoints;
					BigDecimal updatedBonusAmount = existingBonusAmount.add(awardAmount);

					account.setCorporateBonusPoint(updatedBonusPoint);
					account.setCorporateBonusAmount(updatedBonusAmount);
					coRepository.save(account);

				} else {
					CorporateAccounts account = new CorporateAccounts();
					account.setProjectID(projectID);
					account.setProjectName(project.getProjectName());
					account.setCorporateBonusPoint(awardPoints);
					account.setCorporateBonusAmount(awardAmount);
					coRepository.save(account);
				}

				if (cmbrepository.findByMerchantID(merchantID) != null) {
					CorporateMerchantsBalanceTables cmbtable = cmbrepository.findByMerchantID(merchantID);
					int currentTotalPointsAwarded = cmbtable.getTotalPointsAwarded();
					int updatedTotalPointsAwarded = currentTotalPointsAwarded + awardPoints;
					BigDecimal currentTotalAmountAwarded = cmbtable.getTotalAmountAwarded();
					BigDecimal updatedTotalAmountAwarded = currentTotalAmountAwarded.add(awardAmount);
					int currentFinalPoints = cmbtable.getFinalPoints();
					int updatedFinalPoints = currentFinalPoints + awardPoints;
					BigDecimal currentFinalAmount = cmbtable.getFinalAmount();
					BigDecimal updatedFinalAmount = currentFinalAmount.add(awardAmount);
					cmbtable.setTotalAmountAwarded(updatedTotalAmountAwarded);
					cmbtable.setTotalPointsAwarded(updatedTotalPointsAwarded);
					cmbtable.setFinalAmount(updatedFinalAmount);
					cmbtable.setFinalPoints(updatedFinalPoints);
					cmbtable.setPreviousDayBalance(existingLimit);
					cmbtable.setCurrentBalance(updatedLimit);
					cmbrepository.save(cmbtable);
				} else {
					CorporateMerchantsBalanceTables cmbtable = new CorporateMerchantsBalanceTables();
					cmbtable.setAddress(merchant.getAddress());
					cmbtable.setContactPerson(merchant.getContactPerson());
					cmbtable.setCurrentBalance(updatedLimit);
					cmbtable.setFinalAmount(awardAmount);
					cmbtable.setFinalPoints(awardPoints);
					cmbtable.setMerchantID(merchantID);
					cmbtable.setMobileNumber(merchant.getMobileNumber());
					cmbtable.setPreviousDayBalance(existingLimit);
					cmbtable.setProjectName(project.getProjectName());
					cmbtable.setProjectID(projectID);
					cmbtable.setTotalAmountAwarded(awardAmount);
					cmbtable.setTotalPointsAwarded(awardPoints);
					cmbrepository.save(cmbtable);
				}

			} else {
				throw new CustomErrorException("MerchantLimit", ErrorCodes.LIMIT_NOT_SET.getDescription(),
						ErrorCodes.LIMIT_NOT_SET.getCode());
			}
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Award Corporate", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Award Corporate", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public List<AwardTransactions> get10RecentTransactionsByCustomerId(String customerID) {
		if (!arepository.findFirst10ByCustomerIDOrderByCreatedAtDesc(customerID).isEmpty()) {
			return arepository.findFirst10ByCustomerIDOrderByCreatedAtDesc(customerID);
		} else
			return new ArrayList<AwardTransactions>();
	}

	public List<AwardTransactions> get10RecentTransactionsByMerchantId(String merchantID) {
		if (!arepository.findFirst10ByMerchantIDOrderByCreatedAtDesc(merchantID).isEmpty()) {
			return arepository.findFirst10ByMerchantIDOrderByCreatedAtDesc(merchantID);
		} else
			return new ArrayList<AwardTransactions>();
	}

	public List<AwardTransactions> getTransactionsByCustomerIdInRange(String customerID, Date startDate, Date endDate) {
		if (!arepository.findByCustomerIDAndCreatedAtBetween(customerID, startDate, endDate).isEmpty()) {
			return arepository.findByCustomerIDAndCreatedAtBetween(customerID, startDate, endDate);
		} else
			return new ArrayList<AwardTransactions>();
	}

	public List<AwardTransactions> getTransactionsByMerchantIdInRange(String merchantID, Date createdStartDate,
			Date createdEndDate) {
		if (!arepository.findByMerchantIDAndCreatedAtBetween(merchantID, createdStartDate, createdEndDate).isEmpty()) {
			return arepository.findByMerchantIDAndCreatedAtBetween(merchantID, createdStartDate, createdEndDate);
		} else
			return new ArrayList<AwardTransactions>();
	}

	public List<AwardTransactions> getByMerchantAndCustomerId(String merchantID, String customerID) {
		if (!arepository.findByMerchantIDAndCustomerID(merchantID, customerID).isEmpty()) {
			return arepository.findByMerchantIDAndCustomerID(merchantID, customerID);
		} else
			throw new ResourceNotFoundException("AwardTransacctions", "MerchantID And CustomerID",
					merchantID + " - " + customerID);
	}

	public List<AwardTransactions> getByFirst10MerchantAndCustomerId(String merchantID, String customerID) {
		if (!arepository.findFirst10ByMerchantIDAndCustomerIDOrderByCreatedAtDesc(merchantID, customerID).isEmpty()) {
			return arepository.findFirst10ByMerchantIDAndCustomerIDOrderByCreatedAtDesc(merchantID, customerID);
		} else
			throw new ResourceNotFoundException("AwardTransacctions", "MerchantID And CustomerID",
					merchantID + " - " + customerID);
	}

	public List<AwardTransactions> getByFirst10ProjectAndCustomerId(String projectID, String customerID) {
		if (!arepository.findFirst10ByProjectIDAndCustomerIDOrderByCreatedAtDesc(projectID, customerID).isEmpty()) {
			return arepository.findFirst10ByProjectIDAndCustomerIDOrderByCreatedAtDesc(projectID, customerID);
		} else
			throw new ResourceNotFoundException("AwardTransacctions", "ProjectID And CustomerID",
					projectID + " - " + customerID);
	}

	public List<AwardTransactions> get10RecentTransactionsByMerchantIdAndTerminalId(String merchantID,
			String terminalID) {
		if (!arepository.findFirst10ByMerchantIDAndTerminalIDOrderByCreatedAtDesc(merchantID, terminalID).isEmpty()) {
			return arepository.findFirst10ByMerchantIDAndTerminalIDOrderByCreatedAtDesc(merchantID, terminalID);
		} else
			return new ArrayList<AwardTransactions>();
	}

}
