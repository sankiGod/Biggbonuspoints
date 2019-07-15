package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.LoginPageInfo;

@Repository
public interface LoginPageInfoRepository extends JpaRepository<LoginPageInfo, Long> {

}
