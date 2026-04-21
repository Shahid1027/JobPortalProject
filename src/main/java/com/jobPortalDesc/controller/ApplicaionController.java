package com.jobPortalDesc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortalDesc.entity.Application;
import com.jobPortalDesc.service.ApplicationService;


@RestController
@RequestMapping("/")
public class ApplicaionController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("apply")
	public ResponseEntity<String> applyJob(@RequestParam Long jobId, @RequestParam String email ){
		String response =applicationService.applyJob(jobId, email);
		return ResponseEntity.ok(response);	
	}
	
	@GetMapping("my")
	public ResponseEntity<List<Application>> getMyApplications(@RequestParam String email){
		List<Application> applications=applicationService.getMyApplications(email);
		return ResponseEntity.ok(applications);
	}
	
	
	

}
