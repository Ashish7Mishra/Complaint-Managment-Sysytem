package com.example.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cms.entity.User;
import com.example.cms.service.UserService;

@Controller
public class RegistrationController {

	private final UserService userService;

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public User userRegistrationDto() {
		return new User();
	}

	@GetMapping("/register")
	public String showRegistrationForm() {

		return "register";
	}

	@PostMapping("/register")
	public String processRegistration(@ModelAttribute("user") User user) {

		try {
			userService.registerUser(user);

		}
		catch (IllegalStateException e) {
			
			return "redirect:/register?error";
		}

		return "redirect:/login?success";

	}
}
