package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.Tickets;
@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {

	List<Tickets> findByCustomerIDOrderByCreatedAtDesc(String customerID);
	
	List<Tickets> findByMerchantIDOrderByCreatedAtDesc(String merchantID);
}
