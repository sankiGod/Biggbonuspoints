package com.bonuspoint.web.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bonuspoint.exception.FileStorageException;
import com.bonuspoint.model.CorporateAccounts;
import com.bonuspoint.model.PotentialCorporateProjects;
import com.bonuspoint.model.Project;
import com.bonuspoint.property.FileStorageProperties;
import com.bonuspoint.repository.CorporateAccountsRepository;
import com.bonuspoint.repository.PotentialCorporateProjectsRepository;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.util.GenerateID;

@Service
public class ProjectWebService {

	@Autowired
	ProjectRepository repository;
	
	@Autowired 
	CorporateAccountsRepository crepository;

	@Autowired
	PotentialCorporateProjectsRepository prRepository;

	private final Path fileStorageLocation;

	@Autowired
	public ProjectWebService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public Project getProjectById(long pid) {
		return repository.findById(pid).orElse(new Project());
	}

	public String createProject(Project proj) {

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

			if (prRepository.findByEmailId(proj.getEmailId()) != null) {
				PotentialCorporateProjects cproject = prRepository.findByEmailId(proj.getEmailId());
				cproject.setIsApproved(true);
				prRepository.save(cproject);
			}
			repository.save(projDetails);
			return "Success";
		} catch (DataIntegrityViolationException e) {
			String[] msg = e.getRootCause().toString().split(":");
			String[] errMsg = msg[1].split("for");
			return errMsg[0];
		} catch (ConstraintViolationException e) {
			String[] msg = e.getMessage().split("=");
			String[] errMsg = msg[1].split(",");
			return errMsg[0];
		}
	}

	public String updateProject(Project project) {
		if (repository.findByProjectID(project.getProjectID()) != null) {
			Project projDetails = repository.findByProjectID(project.getProjectID());
			projDetails.setAbbrv(project.getAbbrv());
			projDetails.setAddress(project.getAddress());
			projDetails.setContactPerson(project.getContactPerson());
			projDetails.setEmailId(project.getEmailId());
			projDetails.setLegalName(project.getLegalName());
			projDetails.setMobileNumber(project.getMobileNumber());
			projDetails.setProjectName(project.getProjectName());

			try {
				repository.save(projDetails);
				return "Success";
			} catch (DataIntegrityViolationException e) {
				String[] msg = e.getRootCause().toString().split(":");
				String[] errMsg = msg[1].split("for");
				return errMsg[0];
			} catch (ConstraintViolationException e) {
				String[] msg = e.getMessage().split("=");
				String[] errMsg = msg[1].split(",");
				return errMsg[0];
			}
		} else
			return "Cannot Find Project";
	}

	public PotentialCorporateProjects getPotentialProjectById(long pcid) {
		return prRepository.findById(pcid).orElse(new PotentialCorporateProjects());
	}

	public String storeFile(MultipartFile file, String iD) {
		// Normalize file name
		String oldfileName = StringUtils.cleanPath(file.getOriginalFilename());
		String ext = FilenameUtils.getExtension(oldfileName);
		String fileName = "logo_" + iD + "." + ext;

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return "Sorry! Filename contains invalid path sequence " + fileName;
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException ex) {
			return "Could not store file " + fileName + ". Please try again!";
		}
	}

	public void updateProject(String iD, String fileDownloadUri) {
		Project proj = repository.findByProjectID(iD);
		proj.setLogo(fileDownloadUri);
		repository.save(proj);
	}

	public List<CorporateAccounts> getAllCorporateAccounts() {
		return crepository.findAll();
	}
}
