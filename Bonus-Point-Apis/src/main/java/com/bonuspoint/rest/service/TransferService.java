package com.bonuspoint.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.repository.BonusPointMapRepository;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.ProjectRepository;

@Service
public class TransferService {

	@Autowired
	CustomerMerchantBPAccountRepository repository;

	@Autowired
	BonusPointMapRepository brepository;

	@Autowired
	MerchantRepository merchantRepository;

	@Autowired
	ProjectRepository projectRepository;

	public List<CustomerMerchantBPAccount> transferCustom(String customerID, String fromMerchantID, String toMerchantID,
			int bonusPoint) {
		// TODO Auto-generated method stub
				return null;
		/*
		 * Merchant merchant = merchantRepository.findByMerchantID(fromMerchantID);
		 * String projectID = merchant.getProjectID(); Project project =
		 * projectRepository.findByProjectID(projectID);
		 * 
		 * if (!project.getProjectType().equalsIgnoreCase("MULTIPLE")) { throw new
		 * CustomErrorException("Merchant",
		 * ErrorCodes.CANNOT_TRANSFER_FUNDS.getDescription(),
		 * ErrorCodes.CANNOT_TRANSFER_FUNDS.getCode()); }
		 * 
		 * if (repository.findByCustomerIDAndMerchantProjectID(customerID,
		 * fromMerchantID) == null) { throw new
		 * ResourceNotFoundException("CustomerMerchantBPAccount",
		 * "customerID and fromMerchantID", customerID + fromMerchantID); } else if
		 * (repository.findByCustomerIDAndMerchantProjectID(customerID, toMerchantID) ==
		 * null) {
		 * 
		 * fromMerchant { BP = 50; BA = 150; APBP = 3; } toMerchant{ BP = 0; BA = 0;
		 * APBP = 7; } Transferring 20 points fromMer to toMer.
		 * 
		 * CustomerMerchantBPAccount acc1 =
		 * repository.findByCustomerIDAndMerchantProjectID(customerID, fromMerchantID);
		 * CustomerMerchantBPAccount acc2 = new CustomerMerchantBPAccount();
		 * acc2.setCustomerID(customerID); acc2.setMerchantProjectID(toMerchantID);
		 * acc2.set BonusPointMap toMerchant =
		 * brepository.findByMerchantID(toMerchantID); BonusPointMap fromMerchant =
		 * brepository.findByMerchantID(fromMerchantID);
		 * 
		 * BigDecimal bonusAmount = fromMerchant.getAmountPerBP().multiply(new
		 * BigDecimal(bonusPoint));
		 * 
		 * 
		 * bonusAmount = 20 * 3= 60;
		 * 
		 * 
		 * int fromBonusPoints = acc1.getBonusPoints();// 50 BigDecimal fromBonusAmount
		 * = acc1.getBonusPointAmount();// 150
		 * 
		 * if (bonusAmount.compareTo(fromBonusAmount) > 0) { throw new
		 * CustomErrorException("CustomerMerchantBPAccount",
		 * ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
		 * ErrorCodes.INSUFFICIENT_FUNDS.getCode()); } double tempUBP =
		 * bonusAmount.divide(toMerchant.getAmountPerBP()).doubleValue(); int
		 * updatedToBonusPoint = (int) (Math.round(tempUBP)); BigDecimal
		 * updatedToBonusAmount = toMerchant.getAmountPerBP().multiply(new
		 * BigDecimal(updatedToBonusPoint));
		 * 
		 * updatedToBP = (int) 60/7 = 8.5 = 8; updatedToBA = 8 * 7 = 56 ;
		 * 
		 * 
		 * int updatedFromBonusPoints = fromBonusPoints - bonusPoint; BigDecimal
		 * updatedFromBonusAmount = fromBonusAmount.subtract(bonusAmount);
		 * 
		 * updatedFromBP = 50 - 20 = 30; updatedFromBA = 150 - 56 = 94;
		 * 
		 * 
		 * acc1.setBonusPoints(updatedFromBonusPoints);
		 * acc1.setBonusPointAmount(updatedFromBonusAmount);
		 * 
		 * acc2.setBonusPoints(updatedToBonusPoint);
		 * acc2.setBonusPointAmount(updatedToBonusAmount);
		 * 
		 * repository.save(acc1); repository.save(acc2);
		 * 
		 * } else { CustomerMerchantBPAccount acc1 =
		 * repository.findByCustomerIDAndMerchantProjectID(customerID, fromMerchantID);
		 * CustomerMerchantBPAccount acc2 =
		 * repository.findByCustomerIDAndMerchantProjectID(customerID, toMerchantID);
		 * 
		 * BonusPointMap toMerchant = brepository.findByMerchantID(toMerchantID);
		 * BonusPointMap fromMerchant = brepository.findByMerchantID(fromMerchantID);
		 * 
		 * BigDecimal bonusAmount = fromMerchant.getAmountPerBP().multiply(new
		 * BigDecimal(bonusPoint));
		 * 
		 * int fromBonusPoints = acc1.getBonusPoints(); BigDecimal fromBonusAmount =
		 * acc1.getBonusPointAmount();
		 * 
		 * int toBonusPoints = acc2.getBonusPoints(); BigDecimal toBonusAmount =
		 * acc2.getBonusPointAmount();
		 * 
		 * if (bonusAmount.compareTo(fromBonusAmount) > 0) { throw new
		 * CustomErrorException("CustomerMerchantBPAccount",
		 * ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
		 * ErrorCodes.INSUFFICIENT_FUNDS.getCode()); } double tempNBP =
		 * bonusAmount.divide((toMerchant.getAmountPerBP())).doubleValue(); int
		 * newBonusPoint = (int) Math.round(tempNBP); int updatedToBonusPoint =
		 * toBonusPoints + newBonusPoint;
		 * 
		 * BigDecimal newBonusAmount = toMerchant.getAmountPerBP().multiply(new
		 * BigDecimal(newBonusPoint)); BigDecimal updatedToBonusAmount =
		 * toBonusAmount.add(newBonusAmount);
		 * 
		 * int updatedFromBonusPoints = fromBonusPoints - bonusPoint; BigDecimal
		 * updatedFromBonusAmount = fromBonusAmount.subtract(newBonusAmount);
		 * 
		 * acc1.setBonusPoints(updatedFromBonusPoints);
		 * acc1.setBonusPointAmount(updatedFromBonusAmount);
		 * 
		 * acc2.setBonusPoints(updatedToBonusPoint);
		 * acc2.setBonusPointAmount(updatedToBonusAmount);
		 * 
		 * repository.save(acc1); repository.save(acc2);
		 * 
		 * } return repository.findByCustomerID(customerID);
		 */
	}

	public List<CustomerMerchantBPAccount> transferAll(String customerID, String fromMerchantID, String toMerchantID) {

		// TODO Auto-generated method stub
				return null;
		/*
		 * Merchant merchant = merchantRepository.findByMerchantID(fromMerchantID);
		 * String projectID = merchant.getProjectID(); Project project =
		 * projectRepository.findByProjectID(projectID);
		 * 
		 * if (!project.getProjectType().equalsIgnoreCase("MULTIPLE")) { throw new
		 * CustomErrorException("Merchant",
		 * ErrorCodes.CANNOT_TRANSFER_FUNDS.getDescription(),
		 * ErrorCodes.CANNOT_TRANSFER_FUNDS.getCode()); }
		 * 
		 * if (repository.findByCustomerIDAndMerchantProjectID(customerID,
		 * fromMerchantID) == null) { throw new
		 * ResourceNotFoundException("CustomerMerchantBPAccount",
		 * "customerID and fromMerchantID", customerID + fromMerchantID); } else if
		 * (repository.findByCustomerIDAndMerchantProjectID(customerID, toMerchantID) ==
		 * null) { CustomerMerchantBPAccount acc1 =
		 * repository.findByCustomerIDAndMerchantProjectID(customerID, fromMerchantID);
		 * CustomerMerchantBPAccount acc2 = new CustomerMerchantBPAccount();
		 * 
		 * int fromBonusPoints = acc1.getBonusPoints(); BigDecimal fromBonusAmount =
		 * acc1.getBonusPointAmount();
		 * 
		 * if (fromBonusAmount.equals(new BigDecimal(0)) || fromBonusPoints == 0) {
		 * throw new CustomErrorException("CustomerMerchantBPAccount",
		 * ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
		 * ErrorCodes.INSUFFICIENT_FUNDS.getCode()); }
		 * 
		 * BonusPointMap toMerchant = brepository.findByMerchantID(toMerchantID);
		 * 
		 * int updatedFromBonusPoint = fromBonusPoints - fromBonusPoints; BigDecimal
		 * updatedFromBonusAmount = fromBonusAmount.subtract(fromBonusAmount);
		 * 
		 * double tempTBP =
		 * fromBonusAmount.divide(toMerchant.getAmountPerBP()).doubleValue(); int
		 * toBonusPoint = (int) (Math.round(tempTBP)); BigDecimal toBonusAmount =
		 * fromBonusAmount;
		 * 
		 * acc1.setBonusPoints(updatedFromBonusPoint);
		 * acc1.setBonusPointAmount(updatedFromBonusAmount);
		 * 
		 * acc2.setBonusPoints(toBonusPoint); acc2.setBonusPointAmount(toBonusAmount);
		 * 
		 * repository.save(acc1); repository.save(acc2);
		 * 
		 * } else { CustomerMerchantBPAccount acc1 =
		 * repository.findByCustomerIDAndMerchantProjectID(customerID, fromMerchantID);
		 * CustomerMerchantBPAccount acc2 =
		 * repository.findByCustomerIDAndMerchantProjectID(customerID, toMerchantID);
		 * 
		 * int fromBonusPoints = acc1.getBonusPoints(); int toBonusPoints =
		 * acc2.getBonusPoints();
		 * 
		 * BigDecimal fromBonusAmount = acc1.getBonusPointAmount(); BigDecimal
		 * toBonusAmount = acc2.getBonusPointAmount();
		 * 
		 * if (fromBonusAmount.equals(new BigDecimal(0)) || fromBonusPoints == 0) {
		 * throw new CustomErrorException("CustomerMerchantBPAccount",
		 * ErrorCodes.INSUFFICIENT_FUNDS.getDescription(),
		 * ErrorCodes.INSUFFICIENT_FUNDS.getCode()); }
		 * 
		 * BonusPointMap toMerchant = brepository.findByMerchantID(toMerchantID);
		 * 
		 * int updatedFromBonusPoint = fromBonusPoints - fromBonusPoints; BigDecimal
		 * updatedFromBonusAmount = fromBonusAmount.subtract(fromBonusAmount);
		 * 
		 * double tempUBP =
		 * fromBonusAmount.divide(toMerchant.getAmountPerBP()).doubleValue(); int
		 * updatedToBonusPoint = (int) (toBonusPoints + Math.round(tempUBP)); BigDecimal
		 * updatedToBonusAmount = toBonusAmount.add(fromBonusAmount);
		 * 
		 * acc1.setBonusPoints(updatedFromBonusPoint);
		 * acc1.setBonusPointAmount(updatedFromBonusAmount);
		 * 
		 * acc2.setBonusPoints(updatedToBonusPoint);
		 * acc2.setBonusPointAmount(updatedToBonusAmount);
		 * 
		 * repository.save(acc1); repository.save(acc2);
		 * 
		 * } return repository.findByCustomerID(customerID);
		 */
	}


	/*
	 * Merchant fromMerchant = merchantRepository.findByMerchantID(fromMerchantID);
		
		Merchant toMerchant = merchantRepository.findByMerchantID(toMerchantID);
		
		String fromProjectID = fromMerchant.getProjectID();
		String toProjectID = toMerchant.getProjectID();
		
		if(!fromProjectID.equals(toProjectID)) {
			throw new CustomErrorException("Points Transfer", "Cannot Transfer Funds To Merchant Of Different Project", ErrorCodes.NOT_ALLOWED.getCode());
		}
		
		Project project = projectRepository.findByProjectID(toProjectID);
		
		if (!project.getProjectType().equalsIgnoreCase("MULTIPLE")) {
			throw new  CustomErrorException("Merchant", "Funds Transfer Facility Not Applicable",ErrorCodes.NOT_ALLOWED.getCode());
		}
	 * 
	 * 
	 * 
	 * 
	 * public TransferValues getTransferValues(String customerID, String
	 * fromMerchantID, String toMerchantID, int toBonusPoint) {
	 * 
	 * Merchant merchant = merchantRepository.findByMerchantID(fromMerchantID);
	 * String projectID = merchant.getProjectID(); Project project =
	 * projectRepository.findByProjectID(projectID);
	 * 
	 * if (!project.getProjectType().equalsIgnoreCase("MULTIPLE")) { throw new
	 * CustomErrorException("Merchant", "Funds Transfer Facility Not Applicable",
	 * 108); }
	 * 
	 * if (repository.findByCustomerIDAndMerchantProjectID(customerID,
	 * fromMerchantID) == null) { throw new
	 * ResourceNotFoundException("CustomerMerchantBPAccount",
	 * "customerID and fromMerchantID", customerID + fromMerchantID); } else {
	 * 
	 * }
	 * 
	 * BonusPointMap toMerchant = brepository.findByMerchantID(toMerchantID);
	 * BonusPointMap fromMerchant = brepository.findByMerchantID(fromMerchantID);
	 * 
	 * BigDecimal bonusAmount = toBonusPoint * fromMerchant.getAmountPerBP();
	 * 
	 * int newToBonusPoint = (int) (Math.round((bonusAmount) /
	 * (toMerchant.getAmountPerBP()))); BigDecimal newToBonusAmount =
	 * newToBonusPoint * toMerchant.getAmountPerBP();
	 * 
	 * 
	 * 
	 * }
	 */
}
