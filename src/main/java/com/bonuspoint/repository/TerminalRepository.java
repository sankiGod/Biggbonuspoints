package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.Terminal;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {

	List<Terminal> findByMerchantID(String merchantID);
	
	List<Terminal> findByProjectID(String projectID);
	
	Terminal findByTerminalID(String terminalID);
}
