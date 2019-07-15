package com.bonuspoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.DocumentUploads;

public interface DocumentUploadRepository extends JpaRepository<DocumentUploads, Long> {
	DocumentUploads findByUserType(String userType);
}
