package com.example.cms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cms.entity.Complaint;
import com.example.cms.service.ComplaintService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final ComplaintService complaintService;

	public AdminController(ComplaintService complaintService) {
		super();
		this.complaintService = complaintService;
	}
	
	@GetMapping("/complaints")
	public String showAllComplaints(Model model) {
		
		List<Complaint> allComplaints=complaintService.findAllComplaints();
		model.addAttribute("complaints",allComplaints);
		
		return "admin-complaints";
	}
	
	@GetMapping("/complaint/{id}")
    public String showComplaintView(@PathVariable("id") Long id, Model model) {
        Optional<Complaint> complaintOptional = complaintService.findById(id);
        if (complaintOptional.isPresent()) {
            model.addAttribute("complaint", complaintOptional.get());
        } else {
            // Optionally, handle the case where the complaint is not found,
            // for example, by adding an error message to the model.
            // The view already handles the case where the 'complaint' attribute is null.
        }
        return "admin-complaint-view";
    }
	
	// This is the NEW code
	@PostMapping("/complaint/update")
	public String updateComplaintStatus(@RequestParam("complaintId") Long complaintId,
	                                    @RequestParam("status") String status) {
	    // The controller's job is simple: pass the request data to the service.
	    // All the logic is now handled by the service layer.
	    complaintService.updateComplaintStatus(complaintId, status);
	    
	    // Redirect back to the admin dashboard
	    return "redirect:/admin/complaints";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
