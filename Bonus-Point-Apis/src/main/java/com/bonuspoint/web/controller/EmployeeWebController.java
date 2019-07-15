package com.bonuspoint.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonuspoint.model.Employee;
import com.bonuspoint.model.EmployeeAddress;
import com.bonuspoint.rest.service.EmployeeService;
import com.bonuspoint.web.service.EmployeeWebService;

@Controller
public class EmployeeWebController {

	@Autowired
	EmployeeWebService service;

	@Autowired
	EmployeeService empservice;

	@GetMapping("add_employee")
	public String goToAddEmployee(ModelMap model) {
		return "create_employee";
	}

	@PostMapping("goto_view_employee")
	public String goToViewEmployee(@RequestParam("eid") long eId, ModelMap model) {
		Employee employee = service.getByEId(eId);
		model.addAttribute("employee", employee);
		return "view_employee";
	}

	@PostMapping("add_employee")
	public String addEmployee(@RequestParam("employee_name") String employeeName,
			@RequestParam("username") String username, @RequestParam("email_id") String emailId,
			@RequestParam("mobile_number") String mobileNumber, @RequestParam("adhaar_number") String aadharNumber,
			@RequestParam("pan_number") String panNumber, @RequestParam("house_no") String houseNo,
			@RequestParam("lane_no") String laneNo, @RequestParam("lane_name") String laneName,
			@RequestParam("landmark") String landmark, @RequestParam("area_name") String areaName,
			@RequestParam("pincode") String pincode, @RequestParam("state") String state,
			@RequestParam("city") String city, @RequestParam("country") String country, ModelMap model) {

		Employee employee = new Employee();
		employee.setAadharNumber(aadharNumber);
		employee.setEmailId(emailId);
		employee.setEmployeeName(employeeName);
		employee.setMobileNumber(mobileNumber);
		employee.setPanNumber(panNumber);
		employee.setUsername(username);

		EmployeeAddress address = new EmployeeAddress();
		address.setAreaName(areaName);
		address.setCity(city);
		address.setCountry(country);
		address.setHouseNo(houseNo);
		address.setLandmark(landmark);
		address.setLaneName(laneName);
		address.setLaneNo(laneNo);
		address.setPin(pincode);
		address.setState(state);

		employee.setAddress(address);

		String message = service.addEmployee(employee);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Employee Successfully Added");
			model.addAttribute("employee", service.getEmployee(username));
			return "create_employee";
		} else {
			model.put("errorMessage", message);
			model.addAttribute("employee", employee);
			return "create_employee";
		}
	}

	@PostMapping("edit_employee")
	public String editEmployee(@RequestParam("employee_name") String employeeName,
			@RequestParam("employee_id") String employeeID, @RequestParam("username") String username,
			@RequestParam("email_id") String emailId, @RequestParam("mobile_number") String mobileNumber,
			@RequestParam("adhaar_number") String aadharNumber, @RequestParam("pan_number") String panNumber,
			@RequestParam("house_no") String houseNo, @RequestParam("lane_no") String laneNo,
			@RequestParam("lane_name") String laneName, @RequestParam("landmark") String landmark,
			@RequestParam("area_name") String areaName, @RequestParam("pincode") String pincode,
			@RequestParam("state") String state, @RequestParam("city") String city,
			@RequestParam("country") String country, ModelMap model) {

		Employee employee = new Employee();
		employee.setEmployeeID(employeeID);
		employee.setAadharNumber(aadharNumber);
		employee.setEmailId(emailId);
		employee.setEmployeeName(employeeName);
		employee.setMobileNumber(mobileNumber);
		employee.setPanNumber(panNumber);
		employee.setUsername(username);

		EmployeeAddress address = new EmployeeAddress();
		address.setAreaName(areaName);
		address.setCity(city);
		address.setCountry(country);
		address.setHouseNo(houseNo);
		address.setLandmark(landmark);
		address.setLaneName(laneName);
		address.setLaneNo(laneNo);
		address.setPin(pincode);
		address.setState(state);

		employee.setAddress(address);

		String message = service.editEmployee(employee);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Employee Successfully Updated");
			model.addAttribute("employee", service.getEmployee(username));
			return "view_employee";
		} else {
			model.put("errorMessage", message);
			model.addAttribute("employee", employee);
			return "view_employee";
		}
	}

	@PostMapping("assign_employee_roles")
	public String assignEmployeeRoles(@RequestParam("employee_id") String employeeID,
			@RequestParam("roles") String[] roles, ModelMap model) {

		String message = service.assignRoles(employeeID, roles);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Employee Role Successfully Updated");
			List<Employee> employees = empservice.findAll();
			model.addAttribute("employees", employees);
			return "assign_employee_roles";
		} else {
			model.put("errorMessage", message);
			List<Employee> employees = empservice.findAll();
			model.addAttribute("employees", employees);
			return "assign_employee_roles";
		}
	}

	@PostMapping("change_password")
	public String changePassword(@RequestParam("employee_id") String employeeID,
			@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword,
			ModelMap model) {
		String message = service.changePassword(employeeID, oldPassword, newPassword);
		if (message.equalsIgnoreCase("Success")) {
			model.put("successMessage", "Password Successfully Updated");
			return "profile";
		} else {
			model.put("errorMessage", message);
			return "profile";
		}
	}
}
