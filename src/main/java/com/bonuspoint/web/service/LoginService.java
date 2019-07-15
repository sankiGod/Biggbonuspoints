package com.bonuspoint.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonuspoint.model.Employee;
import com.bonuspoint.model.EmployeeRoles;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.PotentialCorporateProjects;
import com.bonuspoint.model.PotentialMerchants;
import com.bonuspoint.model.Project;
import com.bonuspoint.repository.EmployeeRepository;
import com.bonuspoint.repository.EmployeeRoleRepository;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.PotentialCorporateProjectsRepository;
import com.bonuspoint.repository.PotentialMerchantsRespository;
import com.bonuspoint.repository.ProjectRepository;

@Service
public class LoginService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	ProjectRepository pRepository;

	@Autowired
	PotentialCorporateProjectsRepository pcRepository;

	@Autowired
	MerchantRepository mRepository;

	@Autowired
	PotentialMerchantsRespository pmRespository;

	@Autowired
	EmployeeRoleRepository erepository;

	public String check(String username, String password) {
		if (repository.findByUsername(username) != null) {
			Employee emp = repository.findByUsername(username);
			if (emp.getPassword().equals(password)) {
				return "Success";
			} else
				return "Incorrect Password";
		} else
			return "User Does Not Exist";
	}

	public long getProjectCount() {
		List<Project> projects = pRepository.findAll();
		long count = 0;
		for (int i = 0; i < projects.size(); i++) {
			count++;
		}
		return count;
	}

	public long getPotentialProjectCount() {
		List<PotentialCorporateProjects> cprojects = pcRepository.findByIsApproved(false);
		long count = 0;
		for (int i = 0; i < cprojects.size(); i++) {
			count++;
		}
		return count;
	}

	public long getMerchantsCount() {
		List<Merchant> merchants = mRepository.findAll();
		long count = 0;
		for (int i = 0; i < merchants.size(); i++) {
			count++;
		}
		return count;
	}

	public long getPotentialMerchantsCount() {
		List<PotentialMerchants> pmerchants = pmRespository.findByIsApproved(false);
		long count = 0;
		for (int i = 0; i < pmerchants.size(); i++) {
			count++;
		}
		return count;
	}

	public Employee getUser(String username) {

		return repository.findByUsername(username);
	}

	public EmployeeRoles getRoles(String username) {

		return erepository.findByEmployeeID(repository.findByUsername(username).getEmployeeID());
	}
}
