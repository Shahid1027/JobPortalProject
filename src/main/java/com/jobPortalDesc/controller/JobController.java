package com.jobPortalDesc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortalDesc.dto.request.JobRequest;
import com.jobPortalDesc.entity.Job;
import com.jobPortalDesc.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping("/post")
	public ResponseEntity<String> postJob(@RequestBody JobRequest request, @RequestParam String email){
	String response =jobService.postJob(request, email);
	return ResponseEntity.ok(response);
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Job>> getAllJobs(){
		List<Job> jobs =jobService.getAllJobs();
		return ResponseEntity.ok(jobs);	
	}
	
	@GetMapping("/search/location")
	public ResponseEntity<List<Job>> searchByLocation(@RequestParam String location){
		List<Job> jobs=jobService.serachByLocation(location);
		return ResponseEntity.ok(jobs);
	}
	
	@GetMapping("serach/title")
	public ResponseEntity<List<Job>> searchByTitile(@RequestParam String title){
		List<Job> jobs = jobService.searchByTitle(title);
		return ResponseEntity.ok(jobs);
	}
}
