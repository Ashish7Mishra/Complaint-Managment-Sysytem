package com.example.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHomePage(Model model) {
		model.addAttribute("pageTitle", "Home");
		return "index";
	}

	// In HomeController.java
	@GetMapping("/login")
	public String showLoginPage(Model model) { 
		model.addAttribute("pageTitle", "Login"); // Add the title
		return "login";
	}

}
