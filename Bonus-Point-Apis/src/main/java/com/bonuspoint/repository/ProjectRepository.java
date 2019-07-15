package com.bonuspoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findByProjectID(String projectID);

	Project findByMobileNumber(String mobileNumber);

	Project findByEmailId(String emailId);

	List<Project> findByIsCorporate(boolean value);

	List<Project> findByIsTransferAllowedWithinProject(boolean value);

	List<Project> findByProjectType(String type);

}
