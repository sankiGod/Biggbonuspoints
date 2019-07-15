package com.bonuspoint.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bonuspoint.model.PotentialCorporateProjects;
import com.bonuspoint.model.Project;
import com.bonuspoint.model.ProjectAddress;
import com.bonuspoint.repository.ProjectRepository;
import com.bonuspoint.rest.service.PotentialService;
import com.bonuspoint.rest.service.ProjectService;
import com.bonuspoint.web.service.ProjectWebService;

@Controller
public class ProjectWebController {

	@Autowired
	ProjectWebService service;

	@Autowired
	ProjectService pservice;
	
	@Autowired
	PotentialService potservice;
	
	@Autowired
	ProjectRepository repository;

	@GetMapping("add_project")
	public String goToAddProject(ModelMap model) {
		return "add_project";
	}

	@PostMapping("goto_edit_project")
	public String goToEditProject(@RequestParam("pid") long pid, ModelMap model) {
		Project project = service.getProjectById(pid);
		model.addAttribute("project", project);
		return "edit_project";
	}

	@PostMapping("edit_project")
	public String editProject(@RequestParam("project_name") String projectName,
			@RequestParam("project_id") String projectID, @RequestParam("contact_person") String contactPerson,
			@RequestParam("mobile_number") String mobileNumber, @RequestParam("email_id") String emailId,
			@RequestParam("legal_name") String legalName, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("abbrv") String abbrv,
			@RequestParam("country") String country, @RequestParam("pin") String pin, ModelMap model) {

		Project project = new Project();
		project.setContactPerson(contactPerson);
		project.setEmailId(emailId);
		project.setLegalName(legalName);
		project.setMobileNumber(mobileNumber);
		project.setProjectName(projectName);
		project.setAbbrv(abbrv);
		project.setProjectID(projectID);
		ProjectAddress address = new ProjectAddress();
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setPin(pin);
		project.setAddress(address);

		String message = service.updateProject(project);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Project Successfully Updated");
			Project updatedProject = repository.findByProjectID(projectID);
			model.addAttribute("project", updatedProject);
			return "edit_project";
		} else {
			model.put("errorMessage", message);
			model.addAttribute("project", project);
			return "edit_project";
		}
	}

	@PostMapping("add_project")
	public String addProject(@RequestParam("project_type") String projectType,
			@RequestParam("merchant_type") String merchantType, @RequestParam("project_name") String projectName,
			@RequestParam("contact_person") String contactPerson, @RequestParam("mobile_number") String mobileNumber,
			@RequestParam("email_id") String emailId, @RequestParam("legal_name") String legalName,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("country") String country, @RequestParam("pin") String pin, ModelMap model) {

		Project project = new Project();
		if (projectType.equalsIgnoreCase("corporate")) {
			project.setIsCorporate(true);
			project.setIsTransferAllowedWithinProject(false);
		} else {
			if (merchantType.equalsIgnoreCase("single")) {
				project.setIsCorporate(false);
				project.setIsTransferAllowedWithinProject(false);
			} else {
				project.setIsCorporate(false);
				project.setIsTransferAllowedWithinProject(true);
			}
		}
		project.setContactPerson(contactPerson);
		project.setEmailId(emailId);
		project.setLegalName(legalName);
		project.setMobileNumber(mobileNumber);
		project.setProjectName(projectName);

		ProjectAddress address = new ProjectAddress();
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setPin(pin);

		project.setAddress(address);

		String message = service.createProject(project);
		if (message.equalsIgnoreCase("Success")) {
			if (projectType.equalsIgnoreCase("corporate")) {
				model.put("successMessage", "Project Created");
				Project p = repository.findByMobileNumber(mobileNumber);
				model.put("projectID", p.getProjectID());
				return "upload_logo";
			} else {
				model.put("successMessage", "Project Created");
				return "add_project";
			}
		} else {
			model.put("errorMessage", message);
			model.addAttribute("project", project);
			return "add_project";
		}
	}

	@PostMapping("goto_add_project")
	public String gotoAddProject(@RequestParam("pcid") long pcId, ModelMap model) {
		PotentialCorporateProjects cproject = service.getPotentialProjectById(pcId);
		Project project = new Project();
		project.setAddress(cproject.getAddress());
		project.setContactPerson(cproject.getContactPerson());
		project.setEmailId(cproject.getEmailId());
		project.setIsCorporate(true);
		project.setIsTransferAllowedWithinProject(false);
		project.setMobileNumber(cproject.getMobileNumber());
		project.setProjectName(cproject.getCompanyName());

		model.addAttribute("project", project);
		return "add_project";
	}
	
	@GetMapping("upload_logo")
	public String goToUploadLogo(@RequestParam("pid") String projectID, ModelMap model) {
		model.put("projectID", projectID);
		return "upload_logo";
	}

	@PostMapping("upload_logo")
	public String uploadLogo(@RequestParam("id") String ID, @RequestParam("logo") MultipartFile file, ModelMap model) {
		if(file.isEmpty()) {
			model.put("errorMessage","Please Select An Image To Upload" );
			return "upload_logo";
		}
		String fileName = service.storeFile(file, ID);
		if (fileName.startsWith("logo")) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/downloadLogo/")
					.path(fileName).toUriString();

			service.updateProject(ID, fileDownloadUri);
			List<Project> projects = pservice.getAllProjects();
			List<PotentialCorporateProjects> cprojects = potservice.getAllPotentialProjects();
			model.addAttribute("cprojects", cprojects);
			model.addAttribute("projects", projects);
			return "project_onboarding";
		} else {
			model.put("errorMessage", fileName);
			model.put("projectID", ID);
			return "upload_logo";
		}
	}
}
