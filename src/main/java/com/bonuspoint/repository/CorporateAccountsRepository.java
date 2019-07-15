package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.CorporateAccounts;

@Repository
public interface CorporateAccountsRepository extends JpaRepository<CorporateAccounts, Long> {

	CorporateAccounts findByProjectID(String projectID);
}
