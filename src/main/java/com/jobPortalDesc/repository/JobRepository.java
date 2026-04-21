package com.jobPortalDesc.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobPortalDesc.entity.Job;


@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
	
	List<Job> findByLocation(String location);
	List<Job> findByTitleContaining(String keyword);

}
