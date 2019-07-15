package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.MerchantLimit;

@Repository
public interface MerchantLimitRepository extends JpaRepository<MerchantLimit, Long> {

	MerchantLimit findByMerchantID(String merchantID);

	List<MerchantLimit> findByDueDateBetween(Date startDate, Date endDate);

}
