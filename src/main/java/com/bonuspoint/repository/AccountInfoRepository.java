package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.AccountInfo;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long> {
	

}
