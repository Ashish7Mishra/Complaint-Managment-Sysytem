package com.example.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cms.entity.Complaint;
import com.example.cms.entity.User;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

	List<Complaint> findByUserOrderByCreatedAtDesc(User user);
	
	List<Complaint> findAllByOrderByCreatedAtDesc();
	
}
