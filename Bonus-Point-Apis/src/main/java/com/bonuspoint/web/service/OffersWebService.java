package com.bonuspoint.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.CustomerMerchantBPAccount;
import com.bonuspoint.model.NotificationHistory;
import com.bonuspoint.model.Offers;
import com.bonuspoint.repository.CustomerMerchantBPAccountRepository;
import com.bonuspoint.repository.NotificationRepository;
import com.bonuspoint.repository.OfferRepository;

@Service
public class OffersWebService {

	@Autowired
	OfferRepository repository;

	@Autowired
	NotificationRepository nrepository;

	@Autowired
	CustomerMerchantBPAccountRepository acrepository;

	public List<Offers> getByStatus(boolean status) {
		if (!repository.findByApprovedStatusAndIsRejected(status, false).isEmpty()) {
			return repository.findByApprovedStatusAndIsRejected(status, false);
		} else {
			List<Offers> offers = new ArrayList<Offers>();
			return offers;
		}
	}

	public List<Offers> getAllRejected(boolean isRejected) {
		if (!repository.findByIsRejectedAndApprovedStatus(isRejected, false).isEmpty()) {
			return repository.findByIsRejectedAndApprovedStatus(isRejected, false);
		} else {
			List<Offers> offers = new ArrayList<Offers>();
			return offers;
		}
	}

	public Offers getOfferById(long offerID) {
		return repository.findById(offerID).orElse(new Offers());
	}

	public String approveOffer(long offerID) {
		if (repository.findById(offerID) != null) {
			Offers offer = repository.findById(offerID).orElse(new Offers());
			offer.setApprovedStatus(true);
			try {
				repository.save(offer);
				String merchantID = offer.getMerchantID();
				NotificationHistory not = null;

				if (!acrepository.findByMerchantProjectID(merchantID).isEmpty()) {

					List<CustomerMerchantBPAccount> accounts = acrepository.findByMerchantProjectID(merchantID);
					for (CustomerMerchantBPAccount acc : accounts) {
						not = new NotificationHistory();
						not.setCustomerID(acc.getCustomerID());
						not.setNotificationType("Offer");
						not.setTitle(offer.getOfferTitle() + "-" + offer.getOfferCode());
						not.setDescription(offer.getOfferDescription());
						nrepository.save(not);
					}
				} 

				not = new NotificationHistory();
				not.setMerchantID(merchantID);
				not.setNotificationType("Offer Approved");
				not.setTitle(offer.getOfferTitle() + "-" + offer.getOfferCode());
				not.setDescription("Your Offer with OfferCode: " + offer.getOfferCode()
						+ " has been approved and Notification has been sent to all your Customers!");
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
		} else {
			return "Unexpected Error";
		}
	}

	public String rejectOffer(long offerID, String reason) {
		if (repository.findById(offerID) != null) {
			Offers offer = repository.findById(offerID).orElse(new Offers());
			offer.setIsRejected(true);
			offer.setApprovedStatus(false);
			offer.setRejectReason(reason);
			try {
				repository.save(offer);
				String merchantID = offer.getMerchantID();
				NotificationHistory not = null;
				not = new NotificationHistory();
				not.setMerchantID(merchantID);
				not.setNotificationType("Offer Rejected");
				not.setTitle(offer.getOfferTitle() + "-" + offer.getOfferCode());
				not.setDescription(
						"Your Offer with OfferCode: " + offer.getOfferCode() + " has been rejected because " + reason);

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
		} else {
			return "Unexpected Error";
		}
	}

}
