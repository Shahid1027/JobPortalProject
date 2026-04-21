package com.jobPortalDesc.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User applicant;
	
	@ManyToOne
	@JoinColumn(name="job_id")
	private Job job;
	
	private String status;
	private LocalDateTime appliedAt;
	public Application(Long id, Job job, String status, LocalDateTime appliedAt) {
		super();
		this.id = id;
		this.job = job;
		this.status = status;
		this.appliedAt = appliedAt;
	}
	
	
	
	public Application() {
		// TODO Auto-generated constructor stub
	}



	public User getApplicant() {
		return applicant;
	}



	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Job getJob() {
		return job;
	}



	public void setJob(Job job) {
		this.job = job;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}



	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}



	@Override
	public String toString() {
		return "Application [id=" + id + ", job=" + job + ", status=" + status + ", appliedAt=" + appliedAt + "]";
	}
	
	

}
