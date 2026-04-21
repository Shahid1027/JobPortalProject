package com.jobPortalDesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobPortalDesc.entity.Application;
import com.jobPortalDesc.entity.Job;
import com.jobPortalDesc.entity.User;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	List<Application> findByApplicant(User applicant);
	List<Application> findByJob(Job job);
	
}
