package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.Offers;

public interface OfferRepository extends JpaRepository<Offers, Long> {

	Offers findByOfferCode(String offerCode);

	List<Offers> findByMerchantID(String merchantID);

	List<Offers> findByMerchantIDAndApprovedStatusOrderByApprovedAtDesc(String merchantID, boolean status);

	List<Offers> findByApprovedStatusAndIsRejected(boolean status, boolean isRejected);

	List<Offers> findByIsRejectedAndApprovedStatus(boolean isRejected, boolean status);

}
