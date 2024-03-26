package com.zezha.works.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zezha.works.Entity.ApplyJob;
import com.zezha.works.Entity.LegendsDetails;
import com.zezha.works.Entity.Model;
import com.zezha.works.Entity.PostJob;
import com.zezha.works.Entity.Registration;
import com.zezha.works.Entity.otpTrigger;
import com.zezha.works.repository.UpdateProfileRepository;
import com.zezha.works.repository.applyJobRepository;
import com.zezha.works.repository.otpTriggerRepository;
import com.zezha.works.repository.postJobRepository;
import com.zezha.works.repository.zezhaRepository;
import com.zezha.works.utility.imageUtils;
import com.zezha.works.utility.zezhaUtils;

@Service
public class zezhaService {

	@Autowired
	private zezhaRepository repo;
	
	@Autowired
	private UpdateProfileRepository updateRepo;
	
	@Autowired
	private postJobRepository postjobRepo;
	
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private otpTriggerRepository otpRepo;
    
    @Autowired
    private applyJobRepository applyJobRepo;
	
	public Registration registration(Registration registration) throws NoSuchAlgorithmException {
		Registration register = new Registration();
		if(registration.getPassword().equals(registration.getConfirmPassword()) == false) {
			register.setMessage("Password and Confirm Password is incorrect"); 
		}else {
			registration.setPassword(zezhaUtils.encryptPassword(registration.getPassword()));
			register = repo.save(registration);
			register.setMessage("Registration Successful");
		}
		return register;
	}
	
	public Registration userLogin(Registration registration) throws NoSuchAlgorithmException {
		Registration register = new Registration();
		String password = "";
		String name = "";
		String loginType = "";
		if(registration.getUsername()!=null) {
			if(registration.getUsername().contains("@")) {
				Registration email = repo.findByEmail(registration.getUsername());
				if(email==null) {
					register.setMessage("Incorrect Mobile No. or Email Id");
					return register;
				}else {
					System.err.println("email.getLoginType()---"+email.getLoginType());
					if(email.getLoginType().equals(registration.getLoginType())) {
						password = email.getPassword();
						System.out.println("email.getFirstName()=="+email.getFirstName());
						name = email.getFirstName() +" "+ email.getLastName();
						loginType = email.getLoginType();
					}else if(email.getLoginType().equals(registration.getLoginType())) {
						password = email.getPassword();
						System.out.println("email.getFirstName()=="+email.getFirstName());
						name = email.getFirstName() +" "+ email.getLastName();
						loginType = email.getLoginType();
					}else {
						register.setMessage("No Account Found, Please SIGN UP..");
						return register;
					}
					
				}
			}else {
				Registration mobileNo = repo.findByMobileNo(registration.getUsername());
				System.err.println("mobileNo=="+mobileNo);
				if(mobileNo==null) {
					register.setMessage("Incorrect Mobile No. or Email Id");
					return register;
				}else {
					if(mobileNo.getLoginType().equals(registration.getLoginType())) {
						password = mobileNo.getPassword();
						System.out.println("email.getFirstName()=="+mobileNo.getFirstName());
						name = mobileNo.getFirstName() +" "+ mobileNo.getLastName();
						loginType = mobileNo.getLoginType();
					}else if(mobileNo.getLoginType().equals(registration.getLoginType())) {
						password = mobileNo.getPassword();
						name = mobileNo.getFirstName() +" "+ mobileNo.getLastName();
						loginType = mobileNo.getLoginType();
					}else {
						register.setMessage("No Account Found, Please SIGN UP..");
						return register;
					}
				}
			}
		}else {
			register.setMessage("Please Enter Mobile No. or Email Id");
			return register;
		}
		boolean passwordCheck= zezhaUtils.checkPassword(registration.getPassword(), password);
		if(passwordCheck==true) {
			register.setFirstName(name);
			register.setLoginType(loginType);
			register.setMessage("Login Successful");
		}else {
			register.setMessage("Incorrect Password");
		}
			
		return register;
	}
	
	public Registration forgetPassword(Registration registration) {
		return registration;		
	}
	
	public LegendsDetails updateProfile(LegendsDetails legendsDetails) {
		LegendsDetails ldDetails = new LegendsDetails();
		Registration registration = new Registration();
		JSONObject educationJson = new JSONObject();
		JSONObject classXJson = new JSONObject();
		JSONObject classXIIJson = new JSONObject();
		JSONObject ugJson = new JSONObject();
		JSONObject pgJson = new JSONObject();
		JSONObject phdJson = new JSONObject();
		JSONObject curWorkJson = new JSONObject();
		JSONObject emp1Json = new JSONObject();
		JSONObject emp2Json = new JSONObject();
		JSONObject emp3Json = new JSONObject();
		JSONObject emp4Json = new JSONObject();
		JSONObject experienceJson = new JSONObject();
		JSONObject projectJson = new JSONObject();
		try {
			registration = repo.findByMobileNo(legendsDetails.getMobileNo());
			ldDetails.setId(registration.getId());
			ldDetails.setFirstName(registration.getFirstName());
			ldDetails.setLastName(registration.getLastName());
			ldDetails.setDob(registration.getDob());
			ldDetails.setMobileNo(registration.getMobileNo());
			ldDetails.setEmail(registration.getEmail());
			ldDetails.setGender(registration.getGender());
			ldDetails.setWorkStatus(registration.getWorkStatus());
			ldDetails.setAchievements(legendsDetails.getAchievements());
			ldDetails.setSkillDetails(legendsDetails.getSkillDetails());
			ldDetails.setPreferredLocation(legendsDetails.getPreferredLocation());
			ldDetails.setAboutYourself(legendsDetails.getAboutYourself());
			ldDetails.setResumeDocument(legendsDetails.getResumeDocument());
			ldDetails.setCur_address(legendsDetails.getCur_address());
			ldDetails.setCur_city(legendsDetails.getCur_city());
			ldDetails.setCur_state(legendsDetails.getCur_state());
			ldDetails.setCur_pincode(legendsDetails.getCur_pincode());
			ldDetails.setPer_address(legendsDetails.getPer_address());
			ldDetails.setPer_city(legendsDetails.getPer_city());
			ldDetails.setPer_state(legendsDetails.getPer_state());
			ldDetails.setPer_pincode(legendsDetails.getPer_pincode());
			ldDetails.setCertifications(legendsDetails.getCertifications());
			ldDetails.setCurrent_salary(legendsDetails.getCurrent_salary());
			ldDetails.setExpected_salary(legendsDetails.getExpected_salary());
			ldDetails.setIndustry_type(legendsDetails.getIndustry_type());
			ldDetails.setEmployment_type(legendsDetails.getEmployment_type());
			
			
			classXJson.put("schoolName", legendsDetails.getTenth_school());
			classXJson.put("board", legendsDetails.getTenth_board());
			classXJson.put("medium", legendsDetails.getTenth_medium());
			classXJson.put("marks", legendsDetails.getTenth_marks());
			classXJson.put("from", legendsDetails.getTenth_from());
			classXJson.put("to", legendsDetails.getTenth_to());
			
			classXIIJson.put("schoolName", legendsDetails.getTwelth_name());
			classXIIJson.put("board", legendsDetails.getTwelth_board());
			classXIIJson.put("medium", legendsDetails.getTwelth_medium());
			classXIIJson.put("marks", legendsDetails.getTwelth_mark());
			classXIIJson.put("from", legendsDetails.getTwelth_from());
			classXIIJson.put("to", legendsDetails.getTwelth_to());
			
			ugJson.put("collegeName", legendsDetails.getUg_college());
			ugJson.put("degree", legendsDetails.getUg_degree());
			ugJson.put("department", legendsDetails.getUg_dept());
			ugJson.put("marks", legendsDetails.getUg_mark());
			ugJson.put("from", legendsDetails.getUg_from());
			ugJson.put("to", legendsDetails.getUg_to());
			
			pgJson.put("collegeName", legendsDetails.getPg_college());
			pgJson.put("degree", legendsDetails.getPg_degree());
			pgJson.put("department", legendsDetails.getPg_dept());
			pgJson.put("marks", legendsDetails.getPg_mark());
			pgJson.put("from", legendsDetails.getPg_from());
			pgJson.put("to", legendsDetails.getPg_to());
			
			phdJson.put("collegeName", legendsDetails.getPhd_college());
			phdJson.put("degree", legendsDetails.getPhd_degree());
			phdJson.put("department", legendsDetails.getPhd_dept());
			phdJson.put("marks", legendsDetails.getPhd_mark());
			phdJson.put("from", legendsDetails.getPhd_from());
			phdJson.put("to", legendsDetails.getPhd_to());
			
			educationJson.put("Class X Details", classXJson);
			educationJson.put("Class XII Details", classXIIJson);
			educationJson.put("UG Details", ugJson);
			educationJson.put("PG Details", pgJson);
			educationJson.put("PHD Details", phdJson);
			ldDetails.setEducationDetails(educationJson.toString());
			
			curWorkJson.put("currentCompanyName", legendsDetails.getCur_office());
			curWorkJson.put("currentDesignation", legendsDetails.getCur_profile());
			curWorkJson.put("tech used", legendsDetails.getCur_tech());
			curWorkJson.put("cur_working_status", legendsDetails.getCur_working());
			curWorkJson.put("Notice Period", legendsDetails.getNotice_period());
			curWorkJson.put("working from", legendsDetails.getCur_from());
			curWorkJson.put("working till", legendsDetails.getCur_to());
			
			emp1Json.put("currentCompanyName", legendsDetails.getEmp1_office());
			emp1Json.put("currentDesignation", legendsDetails.getEmp1_profile());
			emp1Json.put("tech used", legendsDetails.getEmp1_tech());
			emp1Json.put("working from", legendsDetails.getEmp1_from());
			emp1Json.put("working till", legendsDetails.getEmp1_to());
			
			emp2Json.put("currentCompanyName", legendsDetails.getEmp2_office());
			emp2Json.put("currentDesignation", legendsDetails.getEmp2_profile());
			emp2Json.put("tech used", legendsDetails.getEmp2_tech());
			emp2Json.put("working from", legendsDetails.getEmp2_from());
			emp2Json.put("working till", legendsDetails.getEmp2_to());
			
			emp3Json.put("currentCompanyName", legendsDetails.getEmp3_office());
			emp3Json.put("currentDesignation", legendsDetails.getEmp3_profile());
			emp3Json.put("tech used", legendsDetails.getEmp3_tech());
			emp3Json.put("working from", legendsDetails.getEmp3_from());
			emp3Json.put("working till", legendsDetails.getEmp3_to());
			
			emp4Json.put("currentCompanyName", legendsDetails.getEmp4_office());
			emp4Json.put("currentDesignation", legendsDetails.getEmp4_profile());
			emp4Json.put("tech used", legendsDetails.getEmp4_tech());
			emp4Json.put("working from", legendsDetails.getEmp4_from());
			emp4Json.put("working till", legendsDetails.getEmp4_to());
			
			experienceJson.put("Current Employment Details", curWorkJson);
			experienceJson.put("Employment1 Details", emp1Json);
			experienceJson.put("Employment2 Details", emp2Json);
			experienceJson.put("Employment3 Details", emp3Json);
			experienceJson.put("Employment4 Details", emp4Json);
			ldDetails.setExperienceDetails(experienceJson.toString());
			
			projectJson.put("projectTitle", legendsDetails.getProj_title());
			projectJson.put("projectDescription", legendsDetails.getProj_desc());
			ldDetails.setProjectDetails(projectJson.toString());
			ldDetails.setAboutYourself(legendsDetails.getAboutYourself());
			ldDetails.setSkillDetails(legendsDetails.getSkillDetails());
			updateRepo.save(ldDetails);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		return ldDetails;
	}
	
	public Registration updateProfileExisting(Registration registration) throws NoSuchAlgorithmException {
		Registration register = new Registration();
		try {
			if(registration.getUsername().contains("@")) {
				Registration email = repo.findByEmail(registration.getUsername());
				register.setFirstName(email.getFirstName());
				register.setLastName(email.getLastName());
				register.setMobileNo(email.getMobileNo());
				register.setEmail(email.getEmail());
				register.setDob(email.getDob());
				register.setGender(email.getGender());
				register.setWorkStatus(email.getWorkStatus());
			}else {
				Registration mobileNo = repo.findByMobileNo(registration.getUsername());
				register.setFirstName(mobileNo.getFirstName());
				register.setLastName(mobileNo.getLastName());
				register.setMobileNo(mobileNo.getMobileNo());
				register.setEmail(mobileNo.getEmail());
				register.setDob(mobileNo.getDob());
				register.setGender(mobileNo.getGender());
				register.setWorkStatus(mobileNo.getWorkStatus());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return register;
	}
	
	public List<LegendsDetails> displayLegends(LegendsDetails legendsDetails) throws NoSuchAlgorithmException {
		List<LegendsDetails> details = new ArrayList<>();
		List<LegendsDetails> detailList = new ArrayList<LegendsDetails>();
		try{
			details = updateRepo.findAll();
			System.out.println("details.size---"+details.size());
			if(details.size()>0) {
				for(LegendsDetails detail :details) {
					LegendsDetails ldDetails = new LegendsDetails();
					ldDetails.setFirstName(detail.getFirstName());
					ldDetails.setLastName(detail.getLastName());
					ldDetails.setEmail(detail.getEmail());
					ldDetails.setMobileNo(detail.getMobileNo());
					ldDetails.setGender(detail.getGender());
					ldDetails.setDob(detail.getDob());
					ldDetails.setCur_address(detail.getCur_address());
					ldDetails.setCur_city(detail.getCur_city());
					ldDetails.setCur_state(detail.getCur_state());
					ldDetails.setCur_pincode(detail.getCur_pincode());
					JSONObject expJSON = new JSONObject(detail.getExperienceDetails());
					String curCompanyName = expJSON.optJSONObject("Current Employment Details").optString("currentCompanyName");
					String currentDesignation = expJSON.optJSONObject("Current Employment Details").optString("currentDesignation");
					String techused = expJSON.optJSONObject("Current Employment Details").optString("tech used");
					String NoticePeriod = expJSON.optJSONObject("Current Employment Details").optString("Notice Period");
					ldDetails.setIndustry_type(detail.getIndustry_type());
					ldDetails.setCur_office(curCompanyName);
					ldDetails.setCur_profile(currentDesignation);
					ldDetails.setCur_tech(techused);
					ldDetails.setNotice_period(NoticePeriod);
					ldDetails.setSkillDetails(detail.getSkillDetails());
					ldDetails.setEmployment_type(detail.getEmployment_type());
					ldDetails.setPreferredLocation(detail.getPreferredLocation());
					ldDetails.setWorkStatus(detail.getWorkStatus());
					
					detailList.add(ldDetails);
				}
			}else {
				
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return detailList;
	}
	
	public LegendsDetails moreDetails(LegendsDetails legendsDetails) throws NoSuchAlgorithmException {
		LegendsDetails details = new LegendsDetails();
		LegendsDetails ldDetails = new LegendsDetails();
		try{
			details = updateRepo.findByMobileNo(legendsDetails.getMobileNo());
			ldDetails.setFirstName(details.getFirstName());
			ldDetails.setLastName(details.getLastName());
			ldDetails.setEmail(details.getEmail());
			ldDetails.setMobileNo(details.getMobileNo());
			ldDetails.setGender(details.getGender());
			ldDetails.setDob(details.getDob());
			ldDetails.setCur_address(details.getCur_address());
			ldDetails.setCur_city(details.getCur_city());
			ldDetails.setCur_state(details.getCur_state());
			ldDetails.setCur_pincode(details.getCur_pincode());
			JSONObject expJSON = new JSONObject(details.getExperienceDetails());
			String curCompanyName = expJSON.optJSONObject("Current Employment Details").optString("currentCompanyName");
			String currentDesignation = expJSON.optJSONObject("Current Employment Details").optString("currentDesignation");
			String techused = expJSON.optJSONObject("Current Employment Details").optString("tech used");
			String NoticePeriod = expJSON.optJSONObject("Current Employment Details").optString("Notice Period");
			String workingFrom = expJSON.optJSONObject("Current Employment Details").optString("working from");
			String workingTill = expJSON.optJSONObject("Current Employment Details").optString("working till");
			ldDetails.setIndustry_type(details.getIndustry_type());
			ldDetails.setCur_office(curCompanyName);
			ldDetails.setCur_profile(currentDesignation);
			ldDetails.setCur_tech(techused);
			ldDetails.setNotice_period(NoticePeriod);
			ldDetails.setCur_from(workingFrom);
			ldDetails.setCur_to(workingTill);
			ldDetails.setSkillDetails(details.getSkillDetails());
			ldDetails.setEmployment_type(details.getEmployment_type());
			ldDetails.setPreferredLocation(details.getPreferredLocation());
			ldDetails.setWorkStatus(details.getWorkStatus());
			
			String emp1CompanyName = expJSON.optJSONObject("Employment1 Details").optString("currentCompanyName");
			String emp1Designation = expJSON.optJSONObject("Employment1 Details").optString("currentDesignation");
			String emp1techused = expJSON.optJSONObject("Employment1 Details").optString("tech used");
			String emp1workingFrom = expJSON.optJSONObject("Employment1 Details").optString("working from");
			String emp1workingTill = expJSON.optJSONObject("Employment1 Details").optString("working till");
			
			String emp2CompanyName = expJSON.optJSONObject("Employment2 Details").optString("currentCompanyName");
			String emp2Designation = expJSON.optJSONObject("Employment2 Details").optString("currentDesignation");
			String emp2techused = expJSON.optJSONObject("Employment2 Details").optString("tech used");
			String emp2workingFrom = expJSON.optJSONObject("Employment2 Details").optString("working from");
			String emp2workingTill = expJSON.optJSONObject("Employment2 Details").optString("working till");
			
			String emp3CompanyName = expJSON.optJSONObject("Employment3 Details").optString("currentCompanyName");
			String emp3Designation = expJSON.optJSONObject("Employment3 Details").optString("currentDesignation");
			String emp3techused = expJSON.optJSONObject("Employment3 Details").optString("tech used");
			String emp3workingFrom = expJSON.optJSONObject("Employment3 Details").optString("working from");
			String emp3workingTill = expJSON.optJSONObject("Employment3 Details").optString("working till");
			
			String emp4CompanyName = expJSON.optJSONObject("Employment4 Details").optString("currentCompanyName");
			String emp4Designation = expJSON.optJSONObject("Employment4 Details").optString("currentDesignation");
			String emp4techused = expJSON.optJSONObject("Employment4 Details").optString("tech used");
			String emp4workingFrom = expJSON.optJSONObject("Employment4 Details").optString("working from");
			String emp4workingTill = expJSON.optJSONObject("Employment4 Details").optString("working till");
			
			ldDetails.setEmp1_office(emp1CompanyName);
			ldDetails.setEmp1_tech(emp1techused);
			ldDetails.setEmp1_profile(emp1Designation);
			ldDetails.setEmp1_from(emp1workingFrom);
			ldDetails.setEmp1_to(emp1workingTill);
			ldDetails.setEmp2_office(emp2CompanyName);
			ldDetails.setEmp2_tech(emp2techused);
			ldDetails.setEmp2_profile(emp2Designation);
			ldDetails.setEmp2_from(emp2workingFrom);
			ldDetails.setEmp2_to(emp2workingTill);
			ldDetails.setEmp3_office(emp3CompanyName);
			ldDetails.setEmp3_tech(emp3techused);
			ldDetails.setEmp3_profile(emp3Designation);
			ldDetails.setEmp3_from(emp3workingFrom);
			ldDetails.setEmp3_to(emp3workingTill);
			ldDetails.setEmp4_office(emp4CompanyName);
			ldDetails.setEmp4_tech(emp4techused);
			ldDetails.setEmp4_profile(emp4Designation);
			ldDetails.setEmp4_from(emp4workingFrom);
			ldDetails.setEmp4_to(emp4workingTill);
			ldDetails.setResumeName(details.getResumeName());
			ldDetails.setPhotoName(details.getResumeName());
			
			JSONObject eduJSON = new JSONObject(details.getEducationDetails());
			System.out.println("details.getdetails.get===="+eduJSON.optJSONObject("PG Details").optString("collegeName"));
			String schoolNameXII = eduJSON.optJSONObject("Class XII Details").optString("schoolName");
			String mediumXII = eduJSON.optJSONObject("Class XII Details").optString("medium");
			String markXII = eduJSON.optJSONObject("Class XII Details").optString("marks");
			String boardXII = eduJSON.optJSONObject("Class XII Details").optString("board");
			String fromXII = eduJSON.optJSONObject("Class XII Details").optString("from");
			String toXII = eduJSON.optJSONObject("Class XII Details").optString("to");
			
			String schoolNameX = eduJSON.optJSONObject("Class X Details").optString("schoolName");
			String mediumX = eduJSON.optJSONObject("Class X Details").optString("medium");
			String markX = eduJSON.optJSONObject("Class X Details").optString("marks");
			String boardX = eduJSON.optJSONObject("Class X Details").optString("board");
			String fromX = eduJSON.optJSONObject("Class X Details").optString("from");
			String toX = eduJSON.optJSONObject("Class X Details").optString("to");
			
			String ugCollegeName = eduJSON.optJSONObject("UG Details").optString("collegeName");
			String ugDepartment = eduJSON.optJSONObject("UG Details").optString("department");
			String ugMarks = eduJSON.optJSONObject("UG Details").optString("marks");
			String ugDegree = eduJSON.optJSONObject("UG Details").optString("degree");
			String ugFrom = eduJSON.optJSONObject("UG Details").optString("from");
			String ugTo = eduJSON.optJSONObject("UG Details").optString("to");
			
			String pgCollegeName = eduJSON.optJSONObject("PG Details").optString("collegeName");
			String pgDepartment = eduJSON.optJSONObject("PG Details").optString("department");
			String pgMarks = eduJSON.optJSONObject("PG Details").optString("marks");
			String pgDegree = eduJSON.optJSONObject("PG Details").optString("degree");
			String pgFrom = eduJSON.optJSONObject("PG Details").optString("from");
			String pgTo = eduJSON.optJSONObject("PG Details").optString("to");
			
			String phdCollegeName = eduJSON.optJSONObject("PHD Details").optString("collegeName");
			String phdDepartment = eduJSON.optJSONObject("PHD Details").optString("department");
			String phdMarks = eduJSON.optJSONObject("PHD Details").optString("marks");
			String phdDegree = eduJSON.optJSONObject("PHD Details").optString("degree");
			String phdFrom = eduJSON.optJSONObject("PHD Details").optString("from");
			String phdTo = eduJSON.optJSONObject("PHD Details").optString("to");
			
			ldDetails.setTenth_school(schoolNameX);
			ldDetails.setTenth_medium(mediumX);
			ldDetails.setTenth_marks(markX);
			ldDetails.setTenth_board(boardX);
			ldDetails.setTenth_from(fromX);
			ldDetails.setTenth_to(toX);
			
			ldDetails.setTwelth_name(schoolNameXII);
			ldDetails.setTwelth_medium(mediumXII);
			ldDetails.setTwelth_mark(markXII);
			ldDetails.setTwelth_board(boardXII);
			ldDetails.setTwelth_from(fromXII);
			ldDetails.setTwelth_to(toXII);
			
			ldDetails.setUg_college(ugCollegeName);
			ldDetails.setUg_degree(ugDegree);
			ldDetails.setUg_dept(ugDepartment);
			ldDetails.setUg_mark(ugMarks);
			ldDetails.setUg_from(ugFrom);
			ldDetails.setUg_to(ugTo);
			
			ldDetails.setPg_college(pgCollegeName);
			ldDetails.setPg_degree(pgDegree);
			ldDetails.setPg_dept(pgDepartment);
			ldDetails.setPg_mark(pgMarks);
			ldDetails.setPg_from(pgFrom);
			ldDetails.setPg_to(pgTo);
			
			ldDetails.setPhd_college(phdCollegeName);
			ldDetails.setPhd_degree(phdDegree);
			ldDetails.setPhd_dept(phdDepartment);
			ldDetails.setPhd_mark(phdMarks);
			ldDetails.setPhd_from(phdFrom);
			ldDetails.setPhd_to(phdTo);
			
			ldDetails.setSkillDetails(details.getSkillDetails());
			
			JSONObject projectJSON = new JSONObject(details.getProjectDetails());
			ldDetails.setProj_title(projectJSON.optString("projectTitle"));
			ldDetails.setProj_desc(projectJSON.optString("projectDescription"));
			ldDetails.setAboutYourself(details.getAboutYourself());

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ldDetails;
	}
	
	public PostJob postJob(PostJob postJob) throws NoSuchAlgorithmException {
		PostJob job = new PostJob();
		try {
//	        String prefix = "zh";
//	        int length = 8; // The length of the random part of the ID
//	        Random random = new Random();
//	        String randomPart = String.format("%0" + length + "d", random.nextInt((int) Math.pow(10, length)));
//	        String id = prefix + randomPart;
//	        postJob.setJobId(id);
			if(postJob.getJobId()!=null) {
				PostJob entity = postjobRepo.findByJobId(postJob.getJobId());
		    	if (entity != null) {
		    		entity.setCandidateProfile(postJob.getCandidateProfile());
		    		entity.setCompanyAddress(postJob.getCompanyAddress());
		    		entity.setCompanyName(postJob.getCompanyName());
		    		entity.setCompanyProfile(postJob.getCompanyProfile());
		    		entity.setEduQualification(postJob.getEduQualification());
		    		entity.setEmploymentType(postJob.getEmploymentType());
		    		entity.setFunctionalArea(postJob.getFunctionalArea());
		    		entity.setIndustry(postJob.getIndustry());
		    		entity.setJobDescription(postJob.getJobDescription());
		    		entity.setJobHeadline(postJob.getJobHeadline());
		    		entity.setJobPostedBy(postJob.getJobPostedBy());
		    		entity.setKeySkills(postJob.getKeySkills());
		    		entity.setLocation(postJob.getLocation());
		    		entity.setMaxSalary(postJob.getMaxSalary());
		    		entity.setMinSalary(postJob.getMinSalary());
		    		entity.setMaxyear(postJob.getMaxyear());
		    		entity.setMinYear(postJob.getMinYear());
		    		entity.setOrganisationType(postJob.getOrganisationType());
		    		entity.setOrganisationSize(postJob.getOrganisationSize());
		    		entity.setRole(postJob.getRole());
		    		entity.setVacancies(postJob.getVacancies());
		    		
					postjobRepo.save(postJob);
					postJob.setMessage("Job Posted");
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return postJob;
	}
	
	public List<PostJob> getAllJob(PostJob postJob) throws NoSuchAlgorithmException {
		List<PostJob> details = new ArrayList<>();
		List<PostJob> detailList = new ArrayList<PostJob>();
		try{
			details = postjobRepo.findAll();
			if(details.size()>0) {
				for(PostJob detail :details) {
					PostJob ldDetails = new PostJob();
					ldDetails.setCandidateProfile(detail.getCandidateProfile());
					ldDetails.setCompanyAddress(detail.getCompanyAddress());
					ldDetails.setCompanyName(detail.getCompanyName());
					ldDetails.setCompanyProfile(detail.getCompanyProfile());
					ldDetails.setEduQualification(detail.getEduQualification());
					ldDetails.setEmploymentType(detail.getEmploymentType());
					ldDetails.setFunctionalArea(detail.getFunctionalArea());
					ldDetails.setIndustry(detail.getIndustry());
					ldDetails.setJobDescription(detail.getJobDescription());
					ldDetails.setKeySkills(detail.getKeySkills());
					ldDetails.setLocation(detail.getLocation());
					ldDetails.setMaxSalary(detail.getMaxSalary());
					ldDetails.setMaxyear(detail.getMaxyear());
					ldDetails.setMinSalary(detail.getMinSalary());
					ldDetails.setMinYear(detail.getMinYear());
					ldDetails.setJobHeadline(detail.getJobHeadline());
					ldDetails.setVacancies(detail.getVacancies());
					ldDetails.setJobId(detail.getJobId());
					
					detailList.add(ldDetails);
				}
			}else {
				
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return detailList;
	}
	
	
    public void sendSimpleMessage(otpTrigger model) throws MessagingException {
    	try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            message.setFrom("zezhatechnology@gmail.com");
            helper.setTo(model.getEmail());
            helper.setSubject("Email Verification");
            helper.setText(String.valueOf(otp), true);
            emailSender.send(message);
            model.setOtp(String.valueOf(otp));
            otpRepo.save(model);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
        
    }
    
    public otpTrigger otpverification(otpTrigger otpModel) throws MessagingException {
    	otpTrigger model = new otpTrigger();
    	try {
    		String otp = "";
    		List<otpTrigger> list =  otpRepo.findAll();
    		for(int i=list.size()-1; i>=0; i--) {
    			otp = list.get(i).getOtp();
    			if (otp.equals(otpModel.getEmail())) {
    				model.setMessage("Email Verified");
			        break;
    		    }else {
    		    	model.setMessage("Email Verified");
    		    }
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
        return model;
    }

    
    public String uploadImage(MultipartFile file) {
    	LegendsDetails imageUpload = new LegendsDetails();
    	LegendsDetails entity = updateRepo.findByEmail("anandanavinash3@gmail.com");
    	try {
    		if (entity != null) {
    			entity.setPhotoName(file.getOriginalFilename());
    			entity.setPhotoType(file.getContentType());
    			entity.setPhotoData(imageUtils.compressImage(file.getBytes()));
        		imageUpload =  updateRepo.save(entity);
    		}

    		if(imageUpload!=null) {
    			return "Photo Uploaded Successful: "+file.getOriginalFilename();
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return null;
    }
    
    public String uploadLogo(PostJob legendsDetails) {
    	PostJob imageUpload = new PostJob();
    	try {
        	imageUpload =  postjobRepo.save(legendsDetails);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return "updated";
    }
    
    public String uploadResume(LegendsDetails legendsDetails) {
    	LegendsDetails entity = updateRepo.findByEmail(legendsDetails.getEmail());
    	LegendsDetails imageUpload = new LegendsDetails();
    	try {
    		if (entity != null) {
    			entity.setResumeName(legendsDetails.getResumeName());
    			updateRepo.save(entity);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return "updated";
    }
    
    public String uploadImage(LegendsDetails legendsDetails) {
    	LegendsDetails entity = updateRepo.findByEmail(legendsDetails.getEmail());
    	LegendsDetails imageUpload = new LegendsDetails();
    	try {
    		if (entity != null) {
    			entity.setPhotoName(legendsDetails.getPhotoName());
    			updateRepo.save(entity);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return "updated";
    }
    
    public byte[] downloadImage(String fileName) {
    	byte[] images = null;
    	try {
        	Optional<LegendsDetails> dbImage= updateRepo.findByPhotoName(fileName);
        	images = imageUtils.decompressImage(dbImage.get().getPhotoData());
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return images;
    }
    
	public PostJob moreJobDetails(PostJob postJob) throws NoSuchAlgorithmException {
    	PostJob jobDetails = new PostJob();
    	try {
    		jobDetails = postjobRepo.findByJobId(postJob.getJobId());
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return jobDetails;
    }
	
	
	public ApplyJob applyJob(ApplyJob applyJob) {
		ApplyJob job = new ApplyJob();
		ApplyJob jobData = new ApplyJob();
		try {
			String emailId = "";
			if(applyJob.getUsername().contains("@")) {
				emailId = applyJob.getUsername();
			}else {
				Registration mobileNo = repo.findByMobileNo(applyJob.getUsername());
				emailId = mobileNo.getEmail();
			}
			
			List<ApplyJob> emailList = applyJobRepo.findByAppliedEmailId(emailId);
			if(emailList.size()>0) job.setMessage("Job Already Applied");
			else {
				jobData.setAppliedEmailId(emailId);
				jobData.setId(String.valueOf(applyJob.getId()));
				job= applyJobRepo.save(jobData);
				job.setMessage("Job Applied Succesfullly");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			job.setMessage("Job Applied Failure");
		}
		return job;
	}
	
	public List<Registration> displayCompanies(ApplyJob postJob) throws NoSuchAlgorithmException {
		List<Registration> details = new ArrayList<>();
		List<Registration> detailList = new ArrayList<>();
		try{
			details =  repo.findAll();
			
			for(int i=0; i<details.size(); i++) {
				if(details.get(i).getLoginType().equals("heads")) {
					Registration ldDetails = new Registration();
					ldDetails.setMessage(details.get(i).getFirstName()+" "+details.get(i).getLastName());
					ldDetails.setEmail(details.get(i).getEmail());
					detailList.add(ldDetails);
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return detailList;
	}

	public List<PostJob> displayJobsOnCompanies(PostJob legendsDetails) {
		List<PostJob> details = new ArrayList<>();
		List<PostJob> detailList = new ArrayList<>();
		try{
			details = postjobRepo.findByJobPostedBy(legendsDetails.getJobPostedBy());
			
			if(details.size()>0) {
				for(PostJob detail :details) {
					PostJob ldDetails = new PostJob();
					ldDetails.setCandidateProfile(detail.getCandidateProfile());
					ldDetails.setCompanyAddress(detail.getCompanyAddress());
					ldDetails.setCompanyName(detail.getCompanyName());
					ldDetails.setCompanyProfile(detail.getCompanyProfile());
					ldDetails.setEduQualification(detail.getEduQualification());
					ldDetails.setEmploymentType(detail.getEmploymentType());
					ldDetails.setFunctionalArea(detail.getFunctionalArea());
					ldDetails.setIndustry(detail.getIndustry());
					ldDetails.setJobDescription(detail.getJobDescription());
					ldDetails.setKeySkills(detail.getKeySkills());
					ldDetails.setLocation(detail.getLocation());
					ldDetails.setMaxSalary(detail.getMaxSalary());
					ldDetails.setMaxyear(detail.getMaxyear());
					ldDetails.setMinSalary(detail.getMinSalary());
					ldDetails.setMinYear(detail.getMinYear());
					ldDetails.setJobHeadline(detail.getJobHeadline());
					ldDetails.setVacancies(detail.getVacancies());
					ldDetails.setJobId(detail.getJobId());
					
					detailList.add(ldDetails);
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return detailList;
	}

	public LegendsDetails ExistingData(LegendsDetails legendsDetails) {
		LegendsDetails details = new LegendsDetails();
		try {
			if(legendsDetails.getUsername().contains("@")) {
				details = updateRepo.findByEmail(legendsDetails.getUsername());
			}else {
				details = updateRepo.findByMobileNo(legendsDetails.getUsername());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return details;
	}
	
	public LegendsDetails checkDataForResume(LegendsDetails legendsDetails) {
		LegendsDetails lddetails = new LegendsDetails();
		String email = "";
		try {
			if(legendsDetails.getUsername().contains("@")) {
				LegendsDetails details = updateRepo.findByEmail(legendsDetails.getUsername());
				if(details==null) {
					lddetails.setMessage("update profile not completed");
				}else {
					email = details.getEmail();
					if(email!=null || !email.isEmpty() || !email.equals("NA")) {
						lddetails.setMessage("update profile completed");
						lddetails.setMobileNo(details.getMobileNo());
					}else {
						lddetails.setMessage("update profile not completed");
					}
				}
			}else {
				LegendsDetails details = updateRepo.findByMobileNo(legendsDetails.getUsername());
				if(details==null) {
					lddetails.setMessage("update profile not completed");
				}else {
					email = details.getEmail();
					if(email!=null || !email.isEmpty() || !email.equals("NA")) {
						lddetails.setMessage("update profile completed");
						lddetails.setMobileNo(details.getMobileNo());
					}else {
						lddetails.setMessage("update profile not completed");
					}
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return lddetails;
	}

	public LegendsDetails checkMobileNoExist(String mobileNo) {
		LegendsDetails resObj = new LegendsDetails();
		try {
			LegendsDetails ldDetails = updateRepo.findByMobileNo(mobileNo);
			if(mobileNo == null || mobileNo.isEmpty() || mobileNo.length()>10) {
				resObj.setMessage("Please Enter valid Mobile No");
			}else {
				if(ldDetails == null || ldDetails.getMobileNo()==null) {
					resObj.setMessage("New Mobile No");
				}else {
					resObj.setMessage("Mobile No Exist");
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return resObj;
	}

	public LegendsDetails checkEmailExist(String email) {
		LegendsDetails resObj = new LegendsDetails();
		try {
			LegendsDetails ldDetails = updateRepo.findByEmail(email);
			if(ldDetails == null || ldDetails.getMobileNo()==null) {
				resObj.setMessage("New Email Id");
			}else {
				resObj.setMessage("Email Id Exist");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return resObj;
	}

	public LegendsDetails resumeQrCode(LegendsDetails legendsDetails) {
		LegendsDetails details = new LegendsDetails();
		try {
			if(legendsDetails.getUsername().contains("@")) {
				details = updateRepo.findByEmail(legendsDetails.getUsername());
			}else {
				details = updateRepo.findByMobileNo(legendsDetails.getUsername());
			}
			String base64Img = imageUtils.generateQRCodeBase64(details.getResumeName());
			details.setResumeName(base64Img);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return details;
	}
	
}
