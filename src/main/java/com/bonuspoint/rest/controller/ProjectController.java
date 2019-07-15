package com.bonuspoint.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonuspoint.model.Project;
import com.bonuspoint.rest.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService service;

	@GetMapping("/getAll")
	public List<Project> getAllProjects() {
		return service.getAllProjects();
	}

	@PostMapping("/create")
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project p) {
		Project pro = service.createProject(p);
		return new ResponseEntity<Project>(pro, HttpStatus.CREATED);
	}

	@GetMapping("/getByProjectId/{projectID}")
	public ResponseEntity<Project> getByProjectID(@PathVariable String projectID) {
		Project pro = service.findByProjectID(projectID);
		return new ResponseEntity<Project>(pro, HttpStatus.OK);
	}

	@PutMapping("/update/{pid}")
	public ResponseEntity<Project> updateProject(@PathVariable long pid, @Valid @RequestBody Project p) {
		Project pro = service.updateProject(pid, p);
		return new ResponseEntity<Project>(pro, HttpStatus.OK);
	}

	@GetMapping("/getProjectType/{projectID}")
	public ResponseEntity<String> getProjectType(@PathVariable String projectID) {
		String type = service.projectType(projectID);
		return new ResponseEntity<String>(type, HttpStatus.OK);
	}
}
