package com.jobPortalDesc.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPortalDesc.dto.request.JobRequest;
import com.jobPortalDesc.entity.Job;
import com.jobPortalDesc.entity.User;
import com.jobPortalDesc.exceptions.ResourceNotFoundException;
import com.jobPortalDesc.repository.JobRepository;
import com.jobPortalDesc.repository.UserRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public String postJob(JobRequest request, String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		
		Job job = new Job();
		job.setTitle(request.getTitle());
		job.setDescription(request.getDescription());
		job.setLocation(request.getLoaction());
		job.setSalary(request.getSalary());
		job.setPostedBy(user);
		job.setCreatedAt(LocalDateTime.now());
		jobRepository.save(job);
		return "Job posted successfully";
		
	}
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}
	public List<Job> serachByLocation(String location){
		return jobRepository.findByLocation(location);
	}
	public List<Job> searchByTitle(String keyword){
		return jobRepository.findByTitleContaining(keyword);
	}

}
