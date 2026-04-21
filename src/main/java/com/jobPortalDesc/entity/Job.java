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
@Table(name= "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String title;
	private String description;
	private String location;
	private String salary;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User postedBy;
	private LocalDateTime createdAt;
	
	public Job(Long id, String title, String description, String location, String salary, User postedBy,
			LocalDateTime createdAt) {
		super();
		Id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.salary = salary;
		this.postedBy = postedBy;
		this.createdAt = createdAt;
	}
	public Job() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Job [Id=" + Id + ", title=" + title + ", description=" + description + ", location=" + location
				+ ", salary=" + salary + ", postedBy=" + postedBy + ", createdAt=" + createdAt + "]";
	}
	
	
	
}
