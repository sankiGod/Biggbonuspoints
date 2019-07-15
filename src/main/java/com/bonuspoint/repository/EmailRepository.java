package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonuspoint.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
