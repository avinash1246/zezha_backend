package com.zezha.works.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class LegendsDetails {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sno;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String firstName;
	private String lastName;
	private String dob;
	private String mobileNo;
	private String email;
	private String gender;
	private String workStatus;
	@Column(columnDefinition = "json")
	private String experienceDetails;			//JSONObject
	@Column(columnDefinition = "LONGTEXT")
	private String achievements;
	@Column(columnDefinition = "json")
	private String educationDetails;			//JSONObject
	@Column(columnDefinition = "LONGTEXT")
	private String skillDetails;
	@Column(columnDefinition = "LONGTEXT")
	private String preferredLocation;
	@Column(columnDefinition = "LONGTEXT")
	private String aboutYourself;
	private String resumeDocument;
	@Column(columnDefinition = "LONGTEXT")
	private String cur_address;
	private String cur_state;
	private String cur_city;
	private String cur_pincode;
	@Column(columnDefinition = "LONGTEXT")
	private String per_address;
	private String per_state;
	private String per_city;
	private String per_pincode;
	@Column(columnDefinition = "json")
	private String projectDetails;			//JSONObject
	@Column(columnDefinition = "LONGTEXT")
	private String certifications;
	private String current_salary;
	private String expected_salary;
	@CreationTimestamp
	private LocalDateTime createdDate;
	private String industry_type;
	private String employment_type;
	
	//image
	private String photoName;
	private String photoType;
	@Lob
	@Column(name = "photograph",length = 1000)
	private byte[] photoData;
	
	//Resume
	private String resumeName;
	private String resumeType;
	@Lob
	@Column(name = "resume",length = 1000)
	private byte[] resumeData;
	
	
	//Class X
	@Transient
	private String tenth_school;
	@Transient
	private String tenth_board;
	@Transient
	private String tenth_medium;
	@Transient
	private String tenth_marks;
	@Transient
	private String tenth_from;
	@Transient
	private String tenth_to;
	
	//Class XII
	@Transient
	private String twelth_name;
	@Transient
	private String twelth_board;
	@Transient
	private String twelth_medium;
	@Transient
	private String twelth_mark;
	@Transient
	private String twelth_from;
	@Transient
	private String twelth_to;
	

	//UG College
	@Transient
	private String ug_college;
	@Transient
	private String ug_degree;
	@Transient
	private String ug_dept;
	@Transient
	private String ug_mark;
	@Transient
	private String ug_from;
	@Transient
	private String ug_to;
	
	//UG College
	@Transient
	private String pg_college;
	@Transient
	private String pg_degree;
	@Transient
	private String pg_dept;
	@Transient
	private String pg_mark;
	@Transient
	private String pg_from;
	@Transient
	private String pg_to;
	
	//UG College
	@Transient
	private String phd_college;
	@Transient
	private String phd_degree;
	@Transient
	private String phd_dept;
	@Transient
	private String phd_mark;
	@Transient
	private String phd_from;
	@Transient
	private String phd_to;
	
	//Current company name
	@Transient
	private String cur_office;
	@Transient
	private String cur_profile;
	@Transient
	private String cur_tech;
	@Transient
	private String cur_working;
	@Transient
	private String notice_period;
	@Transient
	private String cur_from;
	@Transient
	private String cur_to;
	
	//company1 name
	@Transient
	private String emp1_office;
	@Transient
	private String emp1_profile;
	@Transient
	private String emp1_tech;
	@Transient
	private String emp1_from;
	@Transient
	private String emp1_to;
	
	
	//company2 name
	@Transient
	private String emp2_office;
	@Transient
	private String emp2_profile;
	@Transient
	private String emp2_tech;
	@Transient
	private String emp2_from;
	@Transient
	private String emp2_to;
	
	//company3 name
	@Transient
	private String emp3_office;
	@Transient
	private String emp3_profile;
	@Transient
	private String emp3_tech;
	@Transient
	private String emp3_from;
	@Transient
	private String emp3_to;
	
	//company4 name
	@Transient
	private String emp4_office;
	@Transient
	private String emp4_profile;
	@Transient
	private String emp4_tech;
	@Transient
	private String emp4_from;
	@Transient
	private String emp4_to;
	
	//project Details
	@Transient
	private String proj_title;
	@Transient
	private String proj_desc;
	
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
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public byte[] getPhotoData() {
		return photoData;
	}
	public void setPhotoData(byte[] photoData) {
		this.photoData = photoData;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getResumeType() {
		return resumeType;
	}
	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}
	public byte[] getResumeData() {
		return resumeData;
	}
	public void setResumeData(byte[] resumeData) {
		this.resumeData = resumeData;
	}

	public String getProj_title() {
		return proj_title;
	}
	public void setProj_title(String proj_title) {
		this.proj_title = proj_title;
	}
	public String getProj_desc() {
		return proj_desc;
	}
	public void setProj_desc(String proj_desc) {
		this.proj_desc = proj_desc;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getAchievements() {
		return achievements;
	}
	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}
	public String getSkillDetails() {
		return skillDetails;
	}
	public void setSkillDetails(String skillDetails) {
		this.skillDetails = skillDetails;
	}
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	public String getAboutYourself() {
		return aboutYourself;
	}
	public void setAboutYourself(String aboutYourself) {
		this.aboutYourself = aboutYourself;
	}
	public String getResumeDocument() {
		return resumeDocument;
	}
	public void setResumeDocument(String resumeDocument) {
		this.resumeDocument = resumeDocument;
	}
	public String getCur_address() {
		return cur_address;
	}
	public void setCur_address(String cur_address) {
		this.cur_address = cur_address;
	}
	public String getCur_state() {
		return cur_state;
	}
	public void setCur_state(String cur_state) {
		this.cur_state = cur_state;
	}
	public String getCur_city() {
		return cur_city;
	}
	public void setCur_city(String cur_city) {
		this.cur_city = cur_city;
	}
	public String getCur_pincode() {
		return cur_pincode;
	}
	public void setCur_pincode(String cur_pincode) {
		this.cur_pincode = cur_pincode;
	}
	public String getPer_address() {
		return per_address;
	}
	public void setPer_address(String per_address) {
		this.per_address = per_address;
	}
	public String getPer_state() {
		return per_state;
	}
	public void setPer_state(String per_state) {
		this.per_state = per_state;
	}
	public String getPer_city() {
		return per_city;
	}
	public void setPer_city(String per_city) {
		this.per_city = per_city;
	}
	public String getPer_pincode() {
		return per_pincode;
	}
	public void setPer_pincode(String per_pincode) {
		this.per_pincode = per_pincode;
	}
	public String getCurrent_salary() {
		return current_salary;
	}
	public void setCurrent_salary(String current_salary) {
		this.current_salary = current_salary;
	}
	public String getExpected_salary() {
		return expected_salary;
	}
	public void setExpected_salary(String expected_salary) {
		this.expected_salary = expected_salary;
	}
	public String getIndustry_type() {
		return industry_type;
	}
	public void setIndustry_type(String industry_type) {
		this.industry_type = industry_type;
	}
	public String getEmployment_type() {
		return employment_type;
	}
	public void setEmployment_type(String employment_type) {
		this.employment_type = employment_type;
	}
	public String getTenth_school() {
		return tenth_school;
	}
	public void setTenth_school(String tenth_school) {
		this.tenth_school = tenth_school;
	}
	public String getTenth_board() {
		return tenth_board;
	}
	public void setTenth_board(String tenth_board) {
		this.tenth_board = tenth_board;
	}
	public String getTenth_medium() {
		return tenth_medium;
	}
	public void setTenth_medium(String tenth_medium) {
		this.tenth_medium = tenth_medium;
	}
	public String getTenth_marks() {
		return tenth_marks;
	}
	public void setTenth_marks(String tenth_marks) {
		this.tenth_marks = tenth_marks;
	}
	public String getTenth_from() {
		return tenth_from;
	}
	public void setTenth_from(String tenth_from) {
		this.tenth_from = tenth_from;
	}
	public String getTenth_to() {
		return tenth_to;
	}
	public void setTenth_to(String tenth_to) {
		this.tenth_to = tenth_to;
	}
	public String getTwelth_name() {
		return twelth_name;
	}
	public void setTwelth_name(String twelth_name) {
		this.twelth_name = twelth_name;
	}
	public String getTwelth_board() {
		return twelth_board;
	}
	public void setTwelth_board(String twelth_board) {
		this.twelth_board = twelth_board;
	}
	public String getTwelth_medium() {
		return twelth_medium;
	}
	public void setTwelth_medium(String twelth_medium) {
		this.twelth_medium = twelth_medium;
	}
	public String getTwelth_mark() {
		return twelth_mark;
	}
	public void setTwelth_mark(String twelth_mark) {
		this.twelth_mark = twelth_mark;
	}
	public String getTwelth_from() {
		return twelth_from;
	}
	public void setTwelth_from(String twelth_from) {
		this.twelth_from = twelth_from;
	}
	public String getTwelth_to() {
		return twelth_to;
	}
	public void setTwelth_to(String twelth_to) {
		this.twelth_to = twelth_to;
	}
	public String getUg_college() {
		return ug_college;
	}
	public void setUg_college(String ug_college) {
		this.ug_college = ug_college;
	}
	public String getUg_degree() {
		return ug_degree;
	}
	public void setUg_degree(String ug_degree) {
		this.ug_degree = ug_degree;
	}
	public String getUg_dept() {
		return ug_dept;
	}
	public void setUg_dept(String ug_dept) {
		this.ug_dept = ug_dept;
	}
	public String getUg_mark() {
		return ug_mark;
	}
	public void setUg_mark(String ug_mark) {
		this.ug_mark = ug_mark;
	}
	public String getUg_from() {
		return ug_from;
	}
	public void setUg_from(String ug_from) {
		this.ug_from = ug_from;
	}
	public String getUg_to() {
		return ug_to;
	}
	public void setUg_to(String ug_to) {
		this.ug_to = ug_to;
	}
	public String getPg_college() {
		return pg_college;
	}
	public void setPg_college(String pg_college) {
		this.pg_college = pg_college;
	}
	public String getPg_degree() {
		return pg_degree;
	}
	public void setPg_degree(String pg_degree) {
		this.pg_degree = pg_degree;
	}
	public String getPg_dept() {
		return pg_dept;
	}
	public void setPg_dept(String pg_dept) {
		this.pg_dept = pg_dept;
	}
	public String getPg_mark() {
		return pg_mark;
	}
	public void setPg_mark(String pg_mark) {
		this.pg_mark = pg_mark;
	}
	public String getPg_from() {
		return pg_from;
	}
	public void setPg_from(String pg_from) {
		this.pg_from = pg_from;
	}
	public String getPg_to() {
		return pg_to;
	}
	public void setPg_to(String pg_to) {
		this.pg_to = pg_to;
	}
	public String getPhd_college() {
		return phd_college;
	}
	public void setPhd_college(String phd_college) {
		this.phd_college = phd_college;
	}
	public String getPhd_degree() {
		return phd_degree;
	}
	public void setPhd_degree(String phd_degree) {
		this.phd_degree = phd_degree;
	}
	public String getPhd_dept() {
		return phd_dept;
	}
	public void setPhd_dept(String phd_dept) {
		this.phd_dept = phd_dept;
	}
	public String getPhd_mark() {
		return phd_mark;
	}
	public void setPhd_mark(String phd_mark) {
		this.phd_mark = phd_mark;
	}
	public String getPhd_from() {
		return phd_from;
	}
	public void setPhd_from(String phd_from) {
		this.phd_from = phd_from;
	}
	public String getPhd_to() {
		return phd_to;
	}
	public void setPhd_to(String phd_to) {
		this.phd_to = phd_to;
	}
	public String getCur_office() {
		return cur_office;
	}
	public void setCur_office(String cur_office) {
		this.cur_office = cur_office;
	}
	public String getCur_profile() {
		return cur_profile;
	}
	public void setCur_profile(String cur_profile) {
		this.cur_profile = cur_profile;
	}
	public String getCur_tech() {
		return cur_tech;
	}
	public void setCur_tech(String cur_tech) {
		this.cur_tech = cur_tech;
	}
	public String getCur_working() {
		return cur_working;
	}
	public void setCur_working(String cur_working) {
		this.cur_working = cur_working;
	}
	public String getNotice_period() {
		return notice_period;
	}
	public void setNotice_period(String notice_period) {
		this.notice_period = notice_period;
	}
	public String getCur_from() {
		return cur_from;
	}
	public void setCur_from(String cur_from) {
		this.cur_from = cur_from;
	}
	public String getCur_to() {
		return cur_to;
	}
	public void setCur_to(String cur_to) {
		this.cur_to = cur_to;
	}
	public String getEmp1_office() {
		return emp1_office;
	}
	public void setEmp1_office(String emp1_office) {
		this.emp1_office = emp1_office;
	}
	public String getEmp1_profile() {
		return emp1_profile;
	}
	public void setEmp1_profile(String emp1_profile) {
		this.emp1_profile = emp1_profile;
	}
	public String getEmp1_tech() {
		return emp1_tech;
	}
	public void setEmp1_tech(String emp1_tech) {
		this.emp1_tech = emp1_tech;
	}
	public String getEmp1_from() {
		return emp1_from;
	}
	public void setEmp1_from(String emp1_from) {
		this.emp1_from = emp1_from;
	}
	public String getEmp1_to() {
		return emp1_to;
	}
	public void setEmp1_to(String emp1_to) {
		this.emp1_to = emp1_to;
	}
	public String getEmp2_office() {
		return emp2_office;
	}
	public void setEmp2_office(String emp2_office) {
		this.emp2_office = emp2_office;
	}
	public String getEmp2_profile() {
		return emp2_profile;
	}
	public void setEmp2_profile(String emp2_profile) {
		this.emp2_profile = emp2_profile;
	}
	public String getEmp2_tech() {
		return emp2_tech;
	}
	public void setEmp2_tech(String emp2_tech) {
		this.emp2_tech = emp2_tech;
	}
	public String getEmp2_from() {
		return emp2_from;
	}
	public void setEmp2_from(String emp2_from) {
		this.emp2_from = emp2_from;
	}
	public String getEmp2_to() {
		return emp2_to;
	}
	public void setEmp2_to(String emp2_to) {
		this.emp2_to = emp2_to;
	}
	public String getEmp3_office() {
		return emp3_office;
	}
	public void setEmp3_office(String emp3_office) {
		this.emp3_office = emp3_office;
	}
	public String getEmp3_profile() {
		return emp3_profile;
	}
	public void setEmp3_profile(String emp3_profile) {
		this.emp3_profile = emp3_profile;
	}
	public String getEmp3_tech() {
		return emp3_tech;
	}
	public void setEmp3_tech(String emp3_tech) {
		this.emp3_tech = emp3_tech;
	}
	public String getEmp3_from() {
		return emp3_from;
	}
	public void setEmp3_from(String emp3_from) {
		this.emp3_from = emp3_from;
	}
	public String getEmp3_to() {
		return emp3_to;
	}
	public void setEmp3_to(String emp3_to) {
		this.emp3_to = emp3_to;
	}
	public String getEmp4_office() {
		return emp4_office;
	}
	public void setEmp4_office(String emp4_office) {
		this.emp4_office = emp4_office;
	}
	public String getEmp4_profile() {
		return emp4_profile;
	}
	public void setEmp4_profile(String emp4_profile) {
		this.emp4_profile = emp4_profile;
	}
	public String getEmp4_tech() {
		return emp4_tech;
	}
	public void setEmp4_tech(String emp4_tech) {
		this.emp4_tech = emp4_tech;
	}
	public String getEmp4_from() {
		return emp4_from;
	}
	public void setEmp4_from(String emp4_from) {
		this.emp4_from = emp4_from;
	}
	public String getEmp4_to() {
		return emp4_to;
	}
	public void setEmp4_to(String emp4_to) {
		this.emp4_to = emp4_to;
	}
	public String getCertifications() {
		return certifications;
	}
	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getExperienceDetails() {
		return experienceDetails;
	}
	public void setExperienceDetails(String experienceDetails) {
		this.experienceDetails = experienceDetails;
	}
	public String getEducationDetails() {
		return educationDetails;
	}
	public void setEducationDetails(String educationDetails) {
		this.educationDetails = educationDetails;
	}
	public String getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

}
