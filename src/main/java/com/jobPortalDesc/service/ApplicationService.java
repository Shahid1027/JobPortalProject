package com.jobPortalDesc.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPortalDesc.entity.Application;
import com.jobPortalDesc.entity.Job;
import com.jobPortalDesc.entity.User;
import com.jobPortalDesc.exceptions.ResourceNotFoundException;
import com.jobPortalDesc.repository.ApplicationRepository;
import com.jobPortalDesc.repository.JobRepository;
import com.jobPortalDesc.repository.UserRepository;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public String applyJob(Long jobId, String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		
		Job job = jobRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		
		Application application = new Application();
		application.setApplicant(user);
		application.setJob(job);
		application.setStatus("Applied");
		application.setAppliedAt(LocalDateTime.now());
		applicationRepository.save(application);
		return "Applied Successfully";
	}

	public List<Application> getMyApplications(String email){
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		
		return applicationRepository.findByApplicant(user);
		
	}
}
