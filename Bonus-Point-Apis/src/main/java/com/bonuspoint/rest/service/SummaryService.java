package com.bonuspoint.rest.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.Customer;
import com.bonuspoint.model.CustomerSummary;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.repository.SummaryRepository;

@Service
public class SummaryService {

	@Autowired
	SummaryRepository repository;

	@Autowired
	MerchantRepository mrepository;

	@Autowired
	ProjectRepository prepository;

	@Autowired
	CustomerRepository crepository;

	public CustomerSummary getSummary(String customerID, String merchantID) {
		if (repository.findByCustomerIDAndMerchantID(customerID, merchantID) != null) {
			return repository.findByCustomerIDAndMerchantID(customerID, merchantID);
		} else
			throw new ResourceNotFoundException("Customer Summary", "customerID", customerID);
	}

	public List<CustomerSummary> getAll() {
		return repository.findAll();
	}

	public CustomerSummary getByMobileNo(String mobileNumber, String merchantID) {
		if (mrepository.findByMerchantID(merchantID) != null) {
			if (crepository.findByMobileNumber(mobileNumber) != null) {
				Merchant merchant = mrepository.findByMerchantID(merchantID);
				Customer customer = crepository.findByMobileNumber(mobileNumber);
				if (prepository.findByProjectID(merchant.getProjectID()).getProjectType()
						.equalsIgnoreCase("CORPORATE")) {
					CustomerSummary sum = new CustomerSummary();
					sum.setMerchantID(merchantID);
					sum.setMobileNumber(mobileNumber);
					sum.setCustomerID(customer.getCustomerID());
					sum.setCustomerName(customer.getCustomerName());
					sum.setCsId(1l);
					sum.setCreatedAt(new Date());
					sum.setUpdatedAt(new Date());
					List<Merchant> merchants = mrepository.findByProjectID(merchant.getProjectID());
					for (Merchant m : merchants) {
						if (repository.findByMobileNumberAndMerchantID(mobileNumber, m.getMerchantID()) != null) {
							CustomerSummary cSum = repository.findByMobileNumberAndMerchantID(mobileNumber,
									m.getMerchantID());
							int oldTotalBonusPoints = sum.getTotalBonusPoints();
							int oldTotalPointsAwarded = sum.getTotalBonusPointsAwarded();
							int oldTotalPointsRedeemed = sum.getTotalBonusPointsRedeemed();
							BigDecimal oldTotalBonusAmount = sum.getTotalBonusAmount();
							BigDecimal oldTotalAmountAwarded = sum.getTotalBonusAmountAwarded();
							BigDecimal oldTotalAmountRedeemed = sum.getTotalBonusAmountRedeemed();

							int newTotalBonusPoints = oldTotalBonusPoints + cSum.getTotalBonusPoints();
							int newTotalPointsAwarded = oldTotalPointsAwarded + cSum.getTotalBonusPointsAwarded();
							int newTotalPointsRedeemed = oldTotalPointsRedeemed + cSum.getTotalBonusPointsRedeemed();
							BigDecimal newTotalBonusAmount = oldTotalBonusAmount.add(cSum.getTotalBonusAmount());
							BigDecimal newTotalAmountAwarded = oldTotalAmountAwarded
									.add(cSum.getTotalBonusAmountAwarded());
							BigDecimal newTotalAmountRedeemed = oldTotalAmountRedeemed
									.add(cSum.getTotalBonusAmountRedeemed());

							sum.setTotalBonusPoints(newTotalBonusPoints);
							sum.setTotalBonusPointsAwarded(newTotalPointsAwarded);
							sum.setTotalBonusPointsRedeemed(newTotalPointsRedeemed);
							sum.setTotalBonusAmount(newTotalBonusAmount);
							sum.setTotalBonusAmountAwarded(newTotalAmountAwarded);
							sum.setTotalBonusAmountRedeemed(newTotalAmountRedeemed);

						}
					}
					return sum;

				} else if (repository.findByMobileNumberAndMerchantID(mobileNumber, merchantID) != null) {
					return repository.findByMobileNumberAndMerchantID(mobileNumber, merchantID);
				} else
					throw new ResourceNotFoundException("Customer Summary", "Mobile Number And Merchant",
							mobileNumber + "-" + merchantID);
			} else
				throw new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber);
		} else
			throw new ResourceNotFoundException("Merchant", "MerchantID", merchantID);
	}

	public CustomerSummary getByCustomerAndProjectId(String customerID, String projectID) {
		if (!mrepository.findByProjectID(projectID).isEmpty()) {
			CustomerSummary sum = new CustomerSummary();
			int totalBonusPoints = 0;
			BigDecimal totalBonusAmount = new BigDecimal(0);
			int totalBonusPointsAwarded = 0;
			BigDecimal totalBonusAmountAwarded = new BigDecimal(0);
			int totalBonusPointsRedeemed = 0;
			BigDecimal totalBonusAmountRedeemed = new BigDecimal(0);
			String customerName = "";
			String mobileNumber = "";

			List<Merchant> merchants = mrepository.findByProjectID(projectID);
			for (Merchant m : merchants) {
				if (repository.findByCustomerIDAndMerchantID(customerID, m.getMerchantID()) != null) {
					CustomerSummary sum1 = repository.findByCustomerIDAndMerchantID(customerID, m.getMerchantID());
					totalBonusPoints = totalBonusPoints + sum1.getTotalBonusPoints();
					totalBonusAmount = totalBonusAmount.add(sum1.getTotalBonusAmount());
					totalBonusPointsAwarded = totalBonusPointsAwarded + sum1.getTotalBonusPointsAwarded();
					totalBonusAmountAwarded = totalBonusAmountAwarded.add(sum1.getTotalBonusAmountAwarded());
					totalBonusPointsRedeemed = totalBonusPointsRedeemed + sum1.getTotalBonusPointsRedeemed();
					totalBonusAmountRedeemed = totalBonusAmountRedeemed.add(sum1.getTotalBonusAmountRedeemed());
					customerName = sum1.getCustomerName();
					mobileNumber = sum1.getMobileNumber();
				}
			}
			sum.setCustomerID(customerID);
			sum.setCustomerName(customerName);
			sum.setMerchantID(projectID);
			sum.setMobileNumber(mobileNumber);
			sum.setTotalBonusAmount(totalBonusAmount);
			sum.setTotalBonusAmountAwarded(totalBonusAmountAwarded);
			sum.setTotalBonusAmountRedeemed(totalBonusAmountRedeemed);
			sum.setTotalBonusPoints(totalBonusPoints);
			sum.setTotalBonusPointsAwarded(totalBonusPointsAwarded);
			sum.setTotalBonusPointsRedeemed(totalBonusPointsRedeemed);
			sum.setCsId(1l);
			sum.setCreatedAt(new Date());
			sum.setUpdatedAt(new Date());

			return sum;

		} else
			throw new ResourceNotFoundException("Merchants", "ProjectID", projectID);
	}

}
