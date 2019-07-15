package com.bonuspoint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonuspoint.model.ProjectsBalanceTable;

public interface ProjectBalanceTableRepository extends JpaRepository<ProjectsBalanceTable, Long> {

	ProjectsBalanceTable findByProjectID(String projectID);

	List<ProjectsBalanceTable> findByUpdatedAtBetween(Date startDate, Date endDate);
}
