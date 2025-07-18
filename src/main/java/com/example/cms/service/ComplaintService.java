package com.example.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cms.entity.Complaint;
import com.example.cms.entity.User;
import com.example.cms.repository.ComplaintRepository;

@Service
public class ComplaintService {

	private final ComplaintRepository complaintRepository;
	private final EmailService emailService;

	public ComplaintService(ComplaintRepository complaintRepository, EmailService emailService) {
		super();
		this.complaintRepository = complaintRepository;
		this.emailService = emailService;
	}

	/**
	 * This method is now specifically for CREATING new complaints.
	 * It saves the complaint and sends the initial "Complaint Received" email.
	 */
	public void createNewComplaint(Complaint complaint) {
		complaintRepository.save(complaint);

		// Prepare the email content for a NEW complaint
		String subject = "Complaint Received - ID: " + complaint.getId();
		String body = "Dear " + complaint.getUser().getName() + ",\n\n"
				+ "Thank you for filing your complaint. We have received it and assigned it the ID: "
				+ complaint.getId() + ".\n\n" + "Title: " + complaint.getTitle() + "\n" + "Status: "
				+ complaint.getStatus() + "\n\n" + "We will review it shortly.\n\n"
				+ "Sincerely,\nThe Complaint Management Team";

		emailService.sendEmail(complaint.getUser().getEmail(), subject, body);
	}

	/**
	 * NEW METHOD TO IMPLEMENT OUR FEATURE
	 */
	public void updateComplaintStatus(Long complaintId, String newStatus) {
		// Find the existing complaint from the database
		Optional<Complaint> complaintOptional = complaintRepository.findById(complaintId);
		
		if (complaintOptional.isPresent()) {
			Complaint complaint = complaintOptional.get();
			String oldStatus = complaint.getStatus(); // Get the old status for the email

			// Only proceed if the status has actually changed
			if (!oldStatus.equals(newStatus)) {
				complaint.setStatus(newStatus);
				complaintRepository.save(complaint); // Save the change to the database

				// Prepare the STATUS UPDATE email content
				String subject = "Update on your Complaint ID: " + complaint.getId();
				String body = "Dear " + complaint.getUser().getName() + ",\n\n"
						+ "There has been an update on your complaint titled '" + complaint.getTitle() + "'.\n\n"
						+ "The status has been changed from '" + oldStatus + "' to '" + newStatus + "'.\n\n"
						+ "You can view your complaints by logging into the system.\n\n"
						+ "Sincerely,\nThe Complaint Management Team";

				// Send the email
				emailService.sendEmail(complaint.getUser().getEmail(), subject, body);
			}
		}
	}


	public List<Complaint> findComplaintsByUser(User user) {
		return complaintRepository.findByUserOrderByCreatedAtDesc(user);
	}

	public List<Complaint> findAllComplaints() {
		return complaintRepository.findAllByOrderByCreatedAtDesc();
	}

	public Optional<Complaint> findById(Long id) {
		return complaintRepository.findById(id);
	}

}