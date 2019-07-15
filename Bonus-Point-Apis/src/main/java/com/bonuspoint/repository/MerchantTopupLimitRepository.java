package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.MerchantTopupLimit;

@Repository
public interface MerchantTopupLimitRepository extends JpaRepository<MerchantTopupLimit, Long> {

	List<MerchantTopupLimit> findByMerchantIDOrderByCreatedAtDesc(String merchantID);
	
	MerchantTopupLimit findTop1ByMerchantIDOrderByCreatedAtDesc(String merchantID);
}
