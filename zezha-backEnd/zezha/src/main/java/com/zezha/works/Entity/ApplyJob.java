package com.zezha.works.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class ApplyJob {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int sno;
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private String id;
	private String appliedEmailId;
	@CreationTimestamp
	private LocalDateTime appliedOn;
	@Transient
	private String username;
	@Transient
	private String message;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppliedEmailId() {
		return appliedEmailId;
	}
	public void setAppliedEmailId(String appliedEmailId) {
		this.appliedEmailId = appliedEmailId;
	}
	public LocalDateTime getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(LocalDateTime appliedOn) {
		this.appliedOn = appliedOn;
	}
}
