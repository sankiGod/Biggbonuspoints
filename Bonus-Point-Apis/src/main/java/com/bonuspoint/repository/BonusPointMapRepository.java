package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.BonusPointMap;

@Repository
public interface BonusPointMapRepository extends JpaRepository<BonusPointMap, Long> {

	List<BonusPointMap> findByProjectID(String projectID);
	
	BonusPointMap findByMerchantID(String merchantID);
	
	BonusPointMap deleteByMerchantID(String merchantID);
	
}
