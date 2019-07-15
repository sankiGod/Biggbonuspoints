package com.bonuspoint.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bonuspoint.web.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService service;

	@GetMapping("login")
	public String goToLogin(ModelMap model, HttpServletRequest request) {
		request.getSession().invalidate();
		model.remove("user");
		return "login";
	}

	@PostMapping("login")
	public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			ModelMap model, HttpSession session) {

		String message = service.check(username, password);

		if (message.equalsIgnoreCase("Success")) {
			session.setAttribute("user", service.getUser(username));
			model.addAttribute("user", service.getUser(username));
			session.setAttribute("roles", service.getRoles(username));
			model.put("projectsCount", service.getProjectCount());
			model.put("potentialProjectsCount", service.getPotentialProjectCount());
			model.put("merchantsCount", service.getMerchantsCount());
			model.put("potentialMerchantsCount", service.getPotentialMerchantsCount());

			return "dashboard";
		} else {
			model.put("errorMessage", message);
			return "login";
		}

	}

	@GetMapping("logout")
	public String Logout(ModelMap model, HttpServletRequest request) {
		request.getSession().invalidate();
		model.remove("user");
		return "login";
	}
}
