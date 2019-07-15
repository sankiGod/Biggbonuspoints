package com.bonuspoint.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.Project;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.util.ErrorCodes;
import com.bonuspoint.util.GenerateID;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository repository;

	public List<Project> getAllProjects() {
		return repository.findAll();
	}

	public Project createProject(Project proj) {

		try {
			Project projDetails = repository.save(proj);

			if (projDetails.getProjectName() != null) {
				if ((projDetails.getProjectName().length() >= 4)) {
					String projectName = projDetails.getProjectName();
					String trimmedProjName = projectName.replaceAll(" ", "");
					projDetails.setAbbrv(trimmedProjName.substring(0, 4).toUpperCase());
				} else
					projDetails.setAbbrv(projDetails.getProjectName());
			}
			projDetails.setProjectID(GenerateID.generateProjID(projDetails.getpId(), projDetails.getAbbrv()));

			if (projDetails.getIsCorporate()) {
				projDetails.setProjectType("CORPORATE");
			} else if (projDetails.getIsTransferAllowedWithinProject()) {
				projDetails.setProjectType("MULTIPLE");
			} else {
				projDetails.setProjectType("SINGLE");
			}
			return repository.save(projDetails);
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			throw new CustomErrorException("Project", errMsg[0], ErrorCodes.DUPLICATE_ENTRY.getCode());
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			throw new CustomErrorException("Project", errMsg[0], ErrorCodes.VALIDATION_FAILED.getCode());
		}
	}

	public Project findByProjectID(String projectID) {
		if (repository.findByProjectID(projectID) != null) {
			return repository.findByProjectID(projectID);
		} else
			throw new ResourceNotFoundException("Project", "projectID", projectID);
	}

	public Project updateProject(long pid, @Valid Project p) {
		if (repository.existsById(pid)) {
			return repository.save(p);
		} else
			throw new ResourceNotFoundException("Project", "pid", pid);
	}

	public String projectType(String projectID) {
		Project project = repository.findByProjectID(projectID);
		if (project.getProjectType().equalsIgnoreCase("CORPORATE")) {
			return "CORPORATE";
		} else if (project.getProjectType().equalsIgnoreCase("SINGLE")) {
			return "SINGLE";
		} else {
			return "MULTIPLE";
		}
	}

	public List<Project> getAllCorporateProjects() {
		if (!repository.findByIsCorporate(true).isEmpty()) {
			return repository.findByIsCorporate(true);
		} else {
			return new ArrayList<Project>();
		}
	}

	public List<Project> getAllNonCorporateProjects() {
		if (!repository.findByIsCorporate(false).isEmpty()) {
			return repository.findByIsCorporate(false);
		} else {
			return new ArrayList<Project>();
		}
	}

}
