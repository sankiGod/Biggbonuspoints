package com.bonuspoint.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.model.Offers;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;
import com.bonuspoint.repository.CustomerRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.NotificationRepository;
import com.bonuspoint.repository.OfferRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;

@Service
public class OfferService {

	@Autowired
	OfferRepository repository;

	@Autowired
	NotificationRepository notrepository;

	@Autowired
	CustomerMerchantBPAccountRepository accrepository;

	@Autowired
	MerchantRepository mrepository;

	@Autowired
	CustomerRepository crepository;

	public Offers createOffer(@Valid Offers offer) {
		try {
			Offers offers = repository.save(offer);
			offers.setOfferCode(GenerateID.generateOfferCode(offers.getOfferId(), offers.getOfferTitle()));
			return repository.save(offers);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Offer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Offer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public List<Offers> getAll() {
		return repository.findAll();
	}

	public List<Offers> getByMerchantID(String merchantID) {
		if (!repository.findByMerchantID(merchantID).isEmpty()) {
			return repository.findByMerchantID(merchantID);
		} else
			throw new ResourceNotFoundException("Offers", "merchantID", merchantID);

	}

	public Offers approveOffer(String offerCode) {
		try {
			if (repository.findByOfferCode(offerCode) != null) {
				Offers offer = repository.findByOfferCode(offerCode);
				offer.setApprovedStatus(true);
				String merchantID = offer.getMerchantID();

				if (accrepository.findByMerchantProjectID(merchantID).isEmpty()) {
					throw new ResourceNotFoundException("CustomerMerchantBPAccount", "merchantID", merchantID);
				}

				List<CustomerMerchantBPAccount> accounts = accrepository.findByMerchantProjectID(merchantID);
				NotificationHistory not = null;
				for (CustomerMerchantBPAccount acc : accounts) {
					not = new NotificationHistory();
					not.setCustomerID(acc.getCustomerID());
					not.setNotificationType("Offer");
					not.setTitle(offer.getOfferTitle() + "-" + offer.getOfferCode());
					not.setDescription(offer.getOfferDescription());
					notrepository.save(not);
				}

				not = new NotificationHistory();
				not.setMerchantID(merchantID);
				not.setNotificationType("Offer Approved");
				not.setTitle(offer.getOfferTitle() + "-" + offer.getOfferCode());
				not.setDescription("Your Offer with OfferCode: " + offer.getOfferCode() + " has been approved!");
				notrepository.save(not);

				return repository.save(offer);
			} else
				throw new ResourceNotFoundException("Offer", "offerCode", offerCode);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Offer", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Offer", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public List<Offers> getApprovedByMerchantId(String merchantID) {
		if (!repository.findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(merchantID, true).isEmpty()) {
			return repository.findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(merchantID, true);
		} else
			throw new ResourceNotFoundException("Offers", "merchantID", merchantID);
	}

	public List<Offers> getOffersForCustomerId(String customerID) {

		if (crepository.findByCustomerID(customerID) == null) {
			throw new ResourceNotFoundException("Customer", "customerID", customerID);
		}

		List<Offers> finalOffers = new ArrayList<Offers>();
		if (accrepository.findByCustomerID(customerID).isEmpty()) {
			throw new ResourceNotFoundException("CustomerMerchantBPAccount", "customerID", customerID);
		}

		List<CustomerMerchantBPAccount> accs = accrepository.findByCustomerID(customerID);
		for (CustomerMerchantBPAccount acc : accs) {
			if (acc.getType().equals("merchant")) {
				if (!repository.findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(acc.getMerchantProjectID(), true)
						.isEmpty()) {
					List<Offers> offers = repository
							.findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(acc.getMerchantProjectID(), true);
					for (Offers offer : offers) {
						finalOffers.add(offer);
					}
				}
			} else {
				List<Merchant> cmerchants = mrepository.findByProjectID(acc.getMerchantProjectID());
				for (Merchant cm : cmerchants) {
					if (!repository.findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(cm.getMerchantID(), true)
							.isEmpty()) {
						List<Offers> coffers = repository
								.findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(cm.getMerchantID(), true);
						for (Offers offer : coffers) {
							finalOffers.add(offer);
						}
					}
				}
			}
		}
		return finalOffers;
	}

}
