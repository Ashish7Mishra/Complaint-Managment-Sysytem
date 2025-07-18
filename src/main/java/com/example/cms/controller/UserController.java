package com.example.cms.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cms.entity.Complaint;
import com.example.cms.entity.User;
import com.example.cms.service.ComplaintService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final ComplaintService complaintService;
	
	public UserController( ComplaintService complaintService) {
		super();
		this.complaintService = complaintService;
	}
	
	@GetMapping("/complaint/new")
	public String showNewComplaintForm(Model model) {
		
		model.addAttribute("complaint", new Complaint());
		
		return "new-complaint-form";
		
		 
	}
	
	@PostMapping("/complaint/new")
	public String fileNewComplaint(@ModelAttribute("complaint") Complaint complaint, @AuthenticationPrincipal User user) {
	    
	    complaint.setUser(user);
	    complaintService.createNewComplaint(complaint); // <-- The updated method call
	    
	    return "redirect:/user/complaints";
	}
	
	
	@GetMapping("/complaints")
	public String showMyComplaints(Model model ,@AuthenticationPrincipal User user) {
		
		List<Complaint> theListOfComplaints=complaintService.findComplaintsByUser(user);
		
		model.addAttribute("complaints",theListOfComplaints);
		
		return "user-complaints";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
