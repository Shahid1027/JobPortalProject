package com.jobPortalDesc.dto;

import java.time.LocalDateTime;

public class ApplicationDTO {
    private Long id;
    private String applicantName;
    private String applicantEmail;
    private String jobTitle;
    private String status;
    private LocalDateTime appliedAt;

    public ApplicationDTO() {}

    public ApplicationDTO(Long id, String applicantName, String applicantEmail, 
                          String jobTitle, String status, LocalDateTime appliedAt) {
        this.id = id;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.jobTitle = jobTitle;
        this.status = status;
        this.appliedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

    // Getters and Setters
    
}
