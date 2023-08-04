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
public class PostJob {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int sno;
	@Id
	private String jobId;
	private String jobHeadline;
	private String employmentType;
	@Column(columnDefinition = "LONGTEXT")
	private String jobDescription;
	@Column(columnDefinition = "LONGTEXT")
	private String candidateProfile;
	private String keySkills;
	private String minYear;
	private String maxyear;
	private String minSalary;
	private String maxSalary;
	@Column(columnDefinition = "LONGTEXT")
	private String location;
	private String industry;
	private String functionalArea;
	private String role;
	@Column(columnDefinition = "LONGTEXT")
	private String eduQualification;
	private String companyName;
	private String companyProfile;
	private String companyAddress;
	private String vacancies;
	private String jobPostedBy;
	private String organisationSize;
	private String logoImg;
	@CreationTimestamp
	private LocalDateTime createdDate;
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getOrganisationSize() {
		return organisationSize;
	}
	public void setOrganisationSize(String organisationSize) {
		this.organisationSize = organisationSize;
	}
	public String getOrganisationType() {
		return organisationType;
	}
	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType;
	}
	private String organisationType;
	
	@Transient
	private String message;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJobHeadline() {
		return jobHeadline;
	}
	public void setJobHeadline(String jobHeadline) {
		this.jobHeadline = jobHeadline;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getCandidateProfile() {
		return candidateProfile;
	}
	public void setCandidateProfile(String candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
	public String getMinYear() {
		return minYear;
	}
	public void setMinYear(String minYear) {
		this.minYear = minYear;
	}
	public String getMaxyear() {
		return maxyear;
	}
	public void setMaxyear(String maxyear) {
		this.maxyear = maxyear;
	}
	public String getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}
	public String getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getFunctionalArea() {
		return functionalArea;
	}
	public void setFunctionalArea(String functionalArea) {
		this.functionalArea = functionalArea;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEduQualification() {
		return eduQualification;
	}
	public void setEduQualification(String eduQualification) {
		this.eduQualification = eduQualification;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyProfile() {
		return companyProfile;
	}
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getKeySkills() {
		return keySkills;
	}
	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}
	public String getVacancies() {
		return vacancies;
	}
	public void setVacancies(String vacancies) {
		this.vacancies = vacancies;
	}
	public String getJobPostedBy() {
		return jobPostedBy;
	}
	public void setJobPostedBy(String jobPostedBy) {
		this.jobPostedBy = jobPostedBy;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	
}
