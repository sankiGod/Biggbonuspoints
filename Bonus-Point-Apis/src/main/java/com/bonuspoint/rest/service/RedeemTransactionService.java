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
import com.bonuspoint.model.Map;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.MerchantLimit;
import com.bonuspoint.model.MerchantsBalanceTables;
import com.bonuspoint.model.Project;
import com.bonuspoint.model.ProjectsBalanceTable;
import com.bonuspoint.model.RedeemTransactions;
import com.bonuspoint.model.CustomerSummary;
import com.bonuspoint.model.CustomersBalanceTables;
import com.bonuspoint.model.Mail;
import com.bonuspoint.repository.CorporateAccountsRepository;
import com.bonuspoint.repository.CorporateMerchantsBalanceTablesRepository;
import com.bonuspoint.repository.CustomerBalanceTableRepository;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.repository.MerchantBalanceTableRepository;
import com.bonuspoint.repository.MerchantLimitRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.ProjectBalanceTableRepository;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.repository.RedeemTransactionsRepository;
import com.bonuspoint.repository.SummaryRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RedeemTransactionService {

	@Autowired
	RedeemTransactionsRepository rtrepository;

	@Autowired
	CustomerMerchantBPAccountRepository cmrepository;

	@Autowired
	MerchantLimitRepository mlrepository;

	@Autowired
	SummaryRepository sumrepository;

	@Autowired
	MerchantRepository merchantRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CustomerRepository crepository;

	@Autowired
	CorporateAccountsRepository coRepository;

	@Autowired
	AwardTransactionService atservice;

	@Autowired
	MappingService mservice;

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

	public List<RedeemTransactions> getAll() {

		return rtrepository.findAll();
	}

	public RedeemTransactions create(@Valid RedeemTransactions redeems) {

		String customerID = redeems.getCustomerID();
		String merchantID = redeems.getMerchantID();

		int redeemPoints = redeems.getRedeemPointsAccrued();
		BigDecimal redeemAmount = redeems.getRedeemAmount();

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
			redeemCorporateMerchant(customerID, merchantID, projectID, redeemPoints, redeemAmount, merchant, project,
					customer);
		} else if (project.getProjectType().equalsIgnoreCase("MULTIPLE")) {
			redeemMultipleMerchant(customerID, merchantID, projectID, redeemPoints, redeemAmount);
		} else {
			redeemSingleMerchant(customerID, merchantID, projectID, redeemPoints, redeemAmount);
		}

		awardWhileRedeem(redeems);

		RedeemTransactions redeem = rtrepository.save(redeems);
		redeem.setRrn(GenerateID.generateRrn(redeem.getRtId(), redeem.getCreatedAt()));
		CustomerSummary summ = sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID);
		try {
			if (customer.getEmailId() != null) {
				String toAddress = customer.getEmailId();
				String subject = "You have Redeemed " + redeemPoints + " points and " + redeemAmount + " rupees from "
						+ merchant.getShopName();
				String content1 = "This is to notify that you have Redeemed your " + redeemPoints + " points and "
						+ redeemAmount + " rupees from " + merchant.getShopName() + " at "
						+ merchant.getAddress().getAreaName() + " dated " + redeem.getCreatedAt().toString() + "."
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
			return rtrepository.save(redeem);
		}

		return rtrepository.save(redeem);
	}

	private void awardWhileRedeem(@Valid RedeemTransactions redeems) {
		String customerID = redeems.getCustomerID();
		String merchantID = redeems.getMerchantID();
		BigDecimal redeemAmount = redeems.getRedeemAmount();
		BigDecimal purchaseAmount = redeems.getPurchaseAmount();

		BigDecimal awardOnAmount = purchaseAmount.subtract(redeemAmount);
		if (!awardOnAmount.equals(new BigDecimal(0))) {
			Map map = mservice.getAccrued(merchantID, awardOnAmount);

			AwardTransactions awards = new AwardTransactions();
			awards.setAwardAmount(map.getAmount());
			awards.setAwardCode("AwardWhileRedeem");
			awards.setAwardPointsAccrued(map.getPoints());
			awards.setCustomerID(customerID);
			awards.setMerchantID(merchantID);
			awards.setMobileNumber(redeems.getMobileNumber());
			awards.setProjectID(redeems.getProjectID());
			awards.setPurchaseAmount(awardOnAmount);
			awards.setTerminalID(redeems.getTerminalID());

			atservice.create(awards);
		}
	}

	public List<RedeemTransactions> getByCustomerId(String customerID) {

		if (!rtrepository.findByCustomerID(customerID).isEmpty()) {
			return rtrepository.findByCustomerID(customerID);
		} else
			throw new ResourceNotFoundException("RedeemTransactions", "CustomerID", customerID);
	}

	public List<RedeemTransactions> getByMerchantId(String merchantID) {

		if (!rtrepository.findByMerchantID(merchantID).isEmpty()) {
			return rtrepository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("RedeemTransactions", "MerchantID", merchantID);
	}

	private void redeemSingleMerchant(String customerID, String merchantID, String projectID, int redeemPoints,
			BigDecimal redeemAmount) {

		try {
			if (cmrepository.findByCustomerIDAndMerchantProjectID(customerID, merchantID) == null) {

				throw new CustomErrorException("CustomerBPAccount", ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
						ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
			} else {

				CustomerMerchantBPAccount acc = cmrepository.findByCustomerIDAndMerchantProjectID(customerID,
						merchantID);
				if (sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID) == null) {

					throw new CustomErrorException("CustomerSummary", ErrorCodes.SUMMARY_NOT_AVAILABLE.getDescription(),
							ErrorCodes.SUMMARY_NOT_AVAILABLE.getCode());

				} else {
					if (pbrepository.findByProjectID(projectID) == null) {
						throw new CustomErrorException("ProjectsBalanceTable",
								ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(), ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
					} else {

						if (mbrepository.findByMerchantID(merchantID) == null) {
							throw new CustomErrorException("MerchantsBalanceTable",
									ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
									ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
						} else {
							if (cbrepository.findByCustomerIDAndProjectID(customerID, projectID) == null) {
								throw new CustomErrorException("CustomersBalanceTable",
										ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
										ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
							} else {

								int existingBonusPoints = acc.getBonusPoints();
								BigDecimal existingBonusAmount = acc.getBonusPointAmount();

								if (existingBonusPoints < redeemPoints
										|| existingBonusAmount.compareTo(redeemAmount) < 0) {
									throw new CustomErrorException("CustomerBPAccount",
											ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
											ErrorCodes.INSUFFICIENT_FUNDS.getCode());
								}

								int updatedBonusPoints = existingBonusPoints - redeemPoints;
								BigDecimal updatedBonusAmount = existingBonusAmount.subtract(redeemAmount);
								acc.setBonusPoints(updatedBonusPoints);
								acc.setBonusPointAmount(updatedBonusAmount);
								cmrepository.save(acc);

								CustomerSummary summary = sumrepository.findByCustomerIDAndMerchantID(customerID,
										merchantID);
								int currentTotalBonusPoints = summary.getTotalBonusPoints();
								int updatedTotalBonusPoints = currentTotalBonusPoints - redeemPoints;
								int currentTotalBonusPointsRedeemed = summary.getTotalBonusPointsRedeemed();
								int updatedTotalBonusPointsRedeemed = currentTotalBonusPointsRedeemed + redeemPoints;
								BigDecimal currentTotalBonusAmount = summary.getTotalBonusAmount();
								BigDecimal updatedTotalBonusAmount = currentTotalBonusAmount.subtract(redeemAmount);
								BigDecimal currentTotalBonusAmountRedeemed = summary.getTotalBonusAmountRedeemed();
								BigDecimal updatedTotalBonusAmountRedeemed = currentTotalBonusAmountRedeemed
										.add(redeemAmount);
								summary.setTotalBonusPoints(updatedTotalBonusPoints);
								summary.setTotalBonusPointsRedeemed(updatedTotalBonusPointsRedeemed);
								summary.setTotalBonusAmount(updatedTotalBonusAmount);
								summary.setTotalBonusAmountRedeemed(updatedTotalBonusAmountRedeemed);
								sumrepository.save(summary);

								MerchantsBalanceTables mtable = mbrepository.findByMerchantID(merchantID);
								int currentTotalPointsRedeemedM = mtable.getTotalPointsRedeemed();
								int updatedTotalPointsRedeemedM = currentTotalPointsRedeemedM + redeemPoints;
								BigDecimal currentTotalAmountRedeemedM = mtable.getTotalAmountRedeemed();
								BigDecimal updatedTotalAmountRedeemedM = currentTotalAmountRedeemedM.add(redeemAmount);
								int currentFinalPointsM = mtable.getFinalPoints();
								int updatedFinalPointsM = currentFinalPointsM - redeemPoints;
								BigDecimal currentFinalAmountM = mtable.getFinalAmount();
								BigDecimal updatedFinalAmountM = currentFinalAmountM.subtract(redeemAmount);
								mtable.setTotalAmountRedeemed(updatedTotalAmountRedeemedM);
								mtable.setTotalPointsRedeemed(updatedTotalPointsRedeemedM);
								mtable.setFinalAmount(updatedFinalAmountM);
								mtable.setFinalPoints(updatedFinalPointsM);
								mbrepository.save(mtable);

								CustomersBalanceTables ctable = cbrepository.findByCustomerIDAndProjectID(customerID,
										projectID);
								int currentTotalPointsRedeemedC = ctable.getTotalPointsRedeemed();
								int updatedTotalPointsRedeemedC = currentTotalPointsRedeemedC + redeemPoints;
								BigDecimal currentTotalAmountRedeemedC = ctable.getTotalAmountRedeemed();
								BigDecimal updatedTotalAmountRedeemedC = currentTotalAmountRedeemedC.add(redeemAmount);
								int currentFinalPointsC = ctable.getFinalPoints();
								int updatedFinalPointsC = currentFinalPointsC - redeemPoints;
								BigDecimal currentFinalAmountC = ctable.getFinalAmount();
								BigDecimal updatedFinalAmountC = currentFinalAmountC.subtract(redeemAmount);
								ctable.setTotalAmountRedeemed(updatedTotalAmountRedeemedC);
								ctable.setTotalPointsRedeemed(updatedTotalPointsRedeemedC);
								ctable.setFinalAmount(updatedFinalAmountC);
								ctable.setFinalPoints(updatedFinalPointsC);
								cbrepository.save(ctable);

								ProjectsBalanceTable ptable = pbrepository.findByProjectID(projectID);
								int currentTotalPointsRedeemedP = ptable.getTotalPointsRedeemed();
								int updatedTotalPointsRedeemedP = currentTotalPointsRedeemedP + redeemPoints;
								BigDecimal currentTotalAmountRedeemedP = ptable.getTotalAmountRedeemed();
								BigDecimal updatedTotalAmountRedeemedP = currentTotalAmountRedeemedP.add(redeemAmount);
								int currentFinalPointsP = ptable.getFinalPoints();
								int updatedFinalPointsP = currentFinalPointsP - redeemPoints;
								BigDecimal currentFinalAmountP = ptable.getFinalAmount();
								BigDecimal updatedFinalAmountP = currentFinalAmountP.subtract(redeemAmount);
								ptable.setTotalAmountRedeemed(updatedTotalAmountRedeemedP);
								ptable.setTotalPointsRedeemed(updatedTotalPointsRedeemedP);
								ptable.setFinalAmount(updatedFinalAmountP);
								ptable.setFinalPoints(updatedFinalPointsP);
								pbrepository.save(ptable);

							}
						}
					}
				}

			}
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Redeem Single", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Redeem Single", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	private void redeemMultipleMerchant(String customerID, String merchantID, String projectID, int redeemPoints,
			BigDecimal redeemAmount) {
		try {

			if (mlrepository.findByMerchantID(merchantID) != null) {
				if (cmrepository.findByCustomerIDAndMerchantProjectID(customerID, merchantID) == null) {

					throw new CustomErrorException("CustomerBPAccount", ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
							ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
				} else {
					if (pbrepository.findByProjectID(projectID) == null) {
						throw new CustomErrorException("ProjectsBalanceTable",
								ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(), ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
					} else {
						if (mbrepository.findByMerchantID(merchantID) == null) {
							throw new CustomErrorException("MerchantsBalanceTable",
									ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
									ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
						} else {
							if (cbrepository.findByCustomerIDAndProjectID(customerID, projectID) == null) {
								throw new CustomErrorException("CustomersBalanceTable",
										ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
										ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
							} else {

								CustomerMerchantBPAccount acc = cmrepository
										.findByCustomerIDAndMerchantProjectID(customerID, merchantID);
								if (sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID) == null) {

									throw new CustomErrorException("CustomerSummary",
											ErrorCodes.SUMMARY_NOT_AVAILABLE.getDescription(),
											ErrorCodes.SUMMARY_NOT_AVAILABLE.getCode());

								} else {

									int existingBonusPoints = acc.getBonusPoints();
									BigDecimal existingBonusAmount = acc.getBonusPointAmount();

									if (existingBonusPoints < redeemPoints
											|| existingBonusAmount.compareTo(redeemAmount) < 0) {
										throw new CustomErrorException("CustomerBPAccount",
												ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
												ErrorCodes.INSUFFICIENT_FUNDS.getCode());
									}

									int updatedBonusPoints = existingBonusPoints - redeemPoints;
									BigDecimal updatedBonusAmount = existingBonusAmount.subtract(redeemAmount);
									acc.setBonusPoints(updatedBonusPoints);
									acc.setBonusPointAmount(updatedBonusAmount);
									cmrepository.save(acc);

									CustomerSummary summary = sumrepository.findByCustomerIDAndMerchantID(customerID,
											merchantID);
									int currentTotalBonusPoints = summary.getTotalBonusPoints();
									int updatedTotalBonusPoints = currentTotalBonusPoints - redeemPoints;
									int currentTotalBonusPointsRedeemed = summary.getTotalBonusPointsRedeemed();
									int updatedTotalBonusPointsRedeemed = currentTotalBonusPointsRedeemed
											+ redeemPoints;
									BigDecimal currentTotalBonusAmount = summary.getTotalBonusAmount();
									BigDecimal updatedTotalBonusAmount = currentTotalBonusAmount.subtract(redeemAmount);
									BigDecimal currentTotalBonusAmountRedeemed = summary.getTotalBonusAmountRedeemed();
									BigDecimal updatedTotalBonusAmountRedeemed = currentTotalBonusAmountRedeemed
											.add(redeemAmount);
									summary.setTotalBonusPoints(updatedTotalBonusPoints);
									summary.setTotalBonusPointsRedeemed(updatedTotalBonusPointsRedeemed);
									summary.setTotalBonusAmount(updatedTotalBonusAmount);
									summary.setTotalBonusAmountRedeemed(updatedTotalBonusAmountRedeemed);
									sumrepository.save(summary);

									MerchantLimit lim = mlrepository.findByMerchantID(merchantID);
									BigDecimal existingLimit = lim.getMerchantLimit();
									BigDecimal updatedLimit = existingLimit.add(redeemAmount);
									lim.setMerchantLimit(updatedLimit);
									mlrepository.save(lim);

									MerchantsBalanceTables mtable = mbrepository.findByMerchantID(merchantID);
									int currentTotalPointsRedeemedM = mtable.getTotalPointsRedeemed();
									int updatedTotalPointsRedeemedM = currentTotalPointsRedeemedM + redeemPoints;
									BigDecimal currentTotalAmountRedeemedM = mtable.getTotalAmountRedeemed();
									BigDecimal updatedTotalAmountRedeemedM = currentTotalAmountRedeemedM
											.add(redeemAmount);
									int currentFinalPointsM = mtable.getFinalPoints();
									int updatedFinalPointsM = currentFinalPointsM - redeemPoints;
									BigDecimal currentFinalAmountM = mtable.getFinalAmount();
									BigDecimal updatedFinalAmountM = currentFinalAmountM.subtract(redeemAmount);
									mtable.setTotalAmountRedeemed(updatedTotalAmountRedeemedM);
									mtable.setTotalPointsRedeemed(updatedTotalPointsRedeemedM);
									mtable.setFinalAmount(updatedFinalAmountM);
									mtable.setFinalPoints(updatedFinalPointsM);
									mbrepository.save(mtable);

									CustomersBalanceTables ctable = cbrepository
											.findByCustomerIDAndProjectID(customerID, projectID);
									int currentTotalPointsRedeemedC = ctable.getTotalPointsRedeemed();
									int updatedTotalPointsRedeemedC = currentTotalPointsRedeemedC + redeemPoints;
									BigDecimal currentTotalAmountRedeemedC = ctable.getTotalAmountRedeemed();
									BigDecimal updatedTotalAmountRedeemedC = currentTotalAmountRedeemedC
											.add(redeemAmount);
									int currentFinalPointsC = ctable.getFinalPoints();
									int updatedFinalPointsC = currentFinalPointsC - redeemPoints;
									BigDecimal currentFinalAmountC = ctable.getFinalAmount();
									BigDecimal updatedFinalAmountC = currentFinalAmountC.subtract(redeemAmount);
									ctable.setTotalAmountRedeemed(updatedTotalAmountRedeemedC);
									ctable.setTotalPointsRedeemed(updatedTotalPointsRedeemedC);
									ctable.setFinalAmount(updatedFinalAmountC);
									ctable.setFinalPoints(updatedFinalPointsC);
									cbrepository.save(ctable);

									ProjectsBalanceTable ptable = pbrepository.findByProjectID(projectID);
									int currentTotalPointsRedeemedP = ptable.getTotalPointsRedeemed();
									int updatedTotalPointsRedeemedP = currentTotalPointsRedeemedP + redeemPoints;
									BigDecimal currentTotalAmountRedeemedP = ptable.getTotalAmountRedeemed();
									BigDecimal updatedTotalAmountRedeemedP = currentTotalAmountRedeemedP
											.add(redeemAmount);
									int currentFinalPointsP = ptable.getFinalPoints();
									int updatedFinalPointsP = currentFinalPointsP - redeemPoints;
									BigDecimal currentFinalAmountP = ptable.getFinalAmount();
									BigDecimal updatedFinalAmountP = currentFinalAmountP.subtract(redeemAmount);
									ptable.setTotalAmountRedeemed(updatedTotalAmountRedeemedP);
									ptable.setTotalPointsRedeemed(updatedTotalPointsRedeemedP);
									ptable.setFinalAmount(updatedFinalAmountP);
									ptable.setFinalPoints(updatedFinalPointsP);
									pbrepository.save(ptable);

								}

							}
						}
					}
				}

			} else {
				throw new CustomErrorException("MerchantLimit", ErrorCodes.LIMIT_NOT_SET.getDescription(),
						ErrorCodes.LIMIT_NOT_SET.getCode());
			}
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Redeem Multiple", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Redeem Multiple", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	private void redeemCorporateMerchant(String customerID, String merchantID, String projectID, int redeemPoints,
			BigDecimal redeemAmount, Merchant merchant, Project project, Customer customer) {
		try {
			if (mlrepository.findByMerchantID(merchantID) != null) {
				if (cmrepository.findByCustomerIDAndMerchantProjectID(customerID, projectID) == null) {
					throw new CustomErrorException("CustomerBPAccount", ErrorCodes.ACCOUNT_NOT_FOUND.getDescription(),
							ErrorCodes.ACCOUNT_NOT_FOUND.getCode());
				} else {
					CustomerMerchantBPAccount acc = cmrepository.findByCustomerIDAndMerchantProjectID(customerID,
							projectID);

					int existingBonusPoints = acc.getBonusPoints();
					BigDecimal existingBonusAmount = acc.getBonusPointAmount();

					if (existingBonusPoints < redeemPoints || existingBonusAmount.compareTo(redeemAmount) < 0) {

						throw new CustomErrorException("CustomerBPAccount",
								ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
								ErrorCodes.INSUFFICIENT_FUNDS.getCode());
					}

					int updatedBonusPoints = existingBonusPoints - redeemPoints;
					BigDecimal updatedBonusAmount = existingBonusAmount.subtract(redeemAmount);
					acc.setBonusPoints(updatedBonusPoints);
					acc.setBonusPointAmount(updatedBonusAmount);
					cmrepository.save(acc);

					if (sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID) == null) {

						CustomerSummary newSum = new CustomerSummary();
						newSum.setCustomerID(customerID);
						newSum.setMerchantID(merchantID);
						newSum.setMobileNumber(customer.getMobileNumber());
						newSum.setCustomerName(customer.getCustomerName());
						sumrepository.save(newSum);

					}
					CustomerSummary summary = sumrepository.findByCustomerIDAndMerchantID(customerID, merchantID);
					int currentTotalBonusPoints = summary.getTotalBonusPoints();
					int updatedTotalBonusPoints = currentTotalBonusPoints - redeemPoints;
					int currentTotalBonusPointsRedeemed = summary.getTotalBonusPointsRedeemed();
					int updatedTotalBonusPointsRedeemed = currentTotalBonusPointsRedeemed + redeemPoints;
					BigDecimal currentTotalBonusAmount = summary.getTotalBonusAmount();
					BigDecimal updatedTotalBonusAmount = currentTotalBonusAmount.subtract(redeemAmount);
					BigDecimal currentTotalBonusAmountRedeemed = summary.getTotalBonusAmountRedeemed();
					BigDecimal updatedTotalBonusAmountRedeemed = currentTotalBonusAmountRedeemed.add(redeemAmount);
					summary.setTotalBonusPoints(updatedTotalBonusPoints);
					summary.setTotalBonusPointsRedeemed(updatedTotalBonusPointsRedeemed);
					summary.setTotalBonusAmount(updatedTotalBonusAmount);
					summary.setTotalBonusAmountRedeemed(updatedTotalBonusAmountRedeemed);
					sumrepository.save(summary);

					MerchantLimit lim = mlrepository.findByMerchantID(merchantID);
					BigDecimal existingLimit = lim.getMerchantLimit();
					BigDecimal updatedLimit = existingLimit.add(redeemAmount);
					lim.setMerchantLimit(updatedLimit);
					mlrepository.save(lim);

					if (cmbrepository.findByMerchantID(merchantID) != null) {
						CorporateMerchantsBalanceTables cmbtable = cmbrepository.findByMerchantID(merchantID);
						int currentTotalPointsRedeemedM = cmbtable.getTotalPointsRedeemed();
						int updatedTotalPointsRedeemedM = currentTotalPointsRedeemedM + redeemPoints;
						BigDecimal currentTotalAmountRedeemedM = cmbtable.getTotalAmountRedeemed();
						BigDecimal updatedTotalAmountRedeemedM = currentTotalAmountRedeemedM.add(redeemAmount);
						int currentFinalPointsM = cmbtable.getFinalPoints();
						int updatedFinalPointsM = currentFinalPointsM - redeemPoints;
						BigDecimal currentFinalAmountM = cmbtable.getFinalAmount();
						BigDecimal updatedFinalAmountM = currentFinalAmountM.subtract(redeemAmount);
						cmbtable.setTotalAmountRedeemed(updatedTotalAmountRedeemedM);
						cmbtable.setTotalPointsRedeemed(updatedTotalPointsRedeemedM);
						cmbtable.setFinalAmount(updatedFinalAmountM);
						cmbtable.setFinalPoints(updatedFinalPointsM);
						cmbtable.setPreviousDayBalance(existingLimit);
						cmbtable.setCurrentBalance(updatedLimit);
						cmbrepository.save(cmbtable);
					} else {
						CorporateMerchantsBalanceTables cmbtable = new CorporateMerchantsBalanceTables();
						cmbtable.setAddress(merchant.getAddress());
						cmbtable.setContactPerson(merchant.getContactPerson());
						cmbtable.setMerchantID(merchantID);
						cmbtable.setMobileNumber(merchant.getMobileNumber());
						cmbtable.setProjectName(project.getProjectName());
						cmbtable.setProjectID(projectID);
						cmbtable.setTotalAmountRedeemed(redeemAmount);
						cmbtable.setTotalPointsRedeemed(redeemPoints);
						cmbtable.setFinalAmount(new BigDecimal(0).subtract(redeemAmount));
						cmbtable.setFinalPoints(0 - redeemPoints);
						cmbtable.setPreviousDayBalance(existingLimit);
						cmbtable.setCurrentBalance(updatedLimit);
						cmbrepository.save(cmbtable);
					}

				}

			} else
				throw new CustomErrorException("MerchantLimit", ErrorCodes.LIMIT_NOT_SET.getDescription(),
						ErrorCodes.LIMIT_NOT_SET.getCode());

			if (coRepository.findByProjectID(projectID) != null) {
				CorporateAccounts account = coRepository.findByProjectID(projectID);

				int existingBonusPoint = account.getCorporateBonusPoint();
				BigDecimal existingBonusAmount = account.getCorporateBonusAmount();

				int updatedBonusPoint = existingBonusPoint - redeemPoints;
				BigDecimal updatedBonusAmount = existingBonusAmount.subtract(redeemAmount);

				account.setCorporateBonusPoint(updatedBonusPoint);
				account.setCorporateBonusAmount(updatedBonusAmount);
				coRepository.save(account);

			} else {
				throw new CustomErrorException("CorporateAccount", ErrorCodes.ACCOUNT_DOES_NOT_EXIST.getDescription(),
						ErrorCodes.ACCOUNT_DOES_NOT_EXIST.getCode());
			}
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Redeem Corporate", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Redeem Corporate", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}

	}

	public List<RedeemTransactions> get10RecentTransactionsByCustomerId(String customerID) {
		if (!rtrepository.findFirst10ByCustomerIDOrderByCreatedAtDesc(customerID).isEmpty()) {
			return rtrepository.findFirst10ByCustomerIDOrderByCreatedAtDesc(customerID);
		} else
			return new ArrayList<RedeemTransactions>();
	}

	public List<RedeemTransactions> get10RecentTransactionsByMerchantId(String merchantID) {
		if (!rtrepository.findFirst10ByMerchantIDOrderByCreatedAtDesc(merchantID).isEmpty()) {
			return rtrepository.findFirst10ByMerchantIDOrderByCreatedAtDesc(merchantID);
		} else
			return new ArrayList<RedeemTransactions>();
	}

	public List<RedeemTransactions> getTransactionsByCustomerIdInRange(String customerID, Date createdStartDate,
			Date createdEndDate) {
		if (!rtrepository.findByCustomerIDAndCreatedAtBetween(customerID, createdStartDate, createdEndDate).isEmpty()) {
			return rtrepository.findByCustomerIDAndCreatedAtBetween(customerID, createdStartDate, createdEndDate);
		} else
			return new ArrayList<RedeemTransactions>();
	}

	public List<RedeemTransactions> getTransactionsByMerchantIdInRange(String merchantID, Date createdStartDate,
			Date createdEndDate) {
		if (!rtrepository.findByMerchantIDAndCreatedAtBetween(merchantID, createdStartDate, createdEndDate).isEmpty()) {
			return rtrepository.findByMerchantIDAndCreatedAtBetween(merchantID, createdStartDate, createdEndDate);
		} else
			return new ArrayList<RedeemTransactions>();
	}

	public List<RedeemTransactions> getByMerchantAndCustomerId(String merchantID, String customerID) {
		if (!rtrepository.findByMerchantIDAndCustomerID(merchantID, customerID).isEmpty()) {
			return rtrepository.findByMerchantIDAndCustomerID(merchantID, customerID);
		} else
			throw new ResourceNotFoundException("RedeemTransactions", "MerchantID And CustomerID",
					merchantID + " - " + customerID);

	}

	public List<RedeemTransactions> getByFirst10MerchantAndCustomerId(String merchantID, String customerID) {
		if (!rtrepository.findFirst10ByMerchantIDAndCustomerIDOrderByCreatedAtDesc(merchantID, customerID).isEmpty()) {
			return rtrepository.findFirst10ByMerchantIDAndCustomerIDOrderByCreatedAtDesc(merchantID, customerID);
		} else
			throw new ResourceNotFoundException("RedeemTransactions", "MerchantID And CustomerID",
					merchantID + " - " + customerID);
	}

	public List<RedeemTransactions> getByFirst10ProjectAndCustomerId(String projectID, String customerID) {
		if (!rtrepository.findFirst10ByProjectIDAndCustomerIDOrderByCreatedAtDesc(projectID, customerID).isEmpty()) {
			return rtrepository.findFirst10ByProjectIDAndCustomerIDOrderByCreatedAtDesc(projectID, customerID);
		} else
			throw new ResourceNotFoundException("RedeemTransactions", "ProjectID And CustomerID",
					projectID + " - " + customerID);
	}

	public List<RedeemTransactions> get10RecentTransactionsByMerchantIdAndTerminalId(String merchantID,
			String terminalID) {
		if (!rtrepository.findFirst10ByMerchantIDAndTerminalIDOrderByCreatedAtDesc(merchantID, terminalID).isEmpty()) {
			return rtrepository.findFirst10ByMerchantIDAndTerminalIDOrderByCreatedAtDesc(merchantID, terminalID);
		} else
			return new ArrayList<RedeemTransactions>();
	}

}
