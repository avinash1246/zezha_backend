package com.zezha.works.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.zezha.works.Entity.ApplyJob;
import com.zezha.works.Entity.LegendsDetails;
import com.zezha.works.Entity.Model;
import com.zezha.works.Entity.PostJob;
import com.zezha.works.Entity.Registration;
import com.zezha.works.Entity.otpTrigger;
import com.zezha.works.service.zezhaService;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@CrossOrigin(origins = "http://localhost:4401")

@RestController
public class zezhaController {

	@Autowired
	private zezhaService service;

	@PostMapping("/registration")
	public Registration registration(@RequestBody Registration reqistration) throws NoSuchAlgorithmException {
		return service.registration(reqistration);
	}

	@PostMapping("/login")
	public Registration login(@RequestBody Registration reqistration) throws NoSuchAlgorithmException {
		return service.userLogin(reqistration);
	}

	@GetMapping("/displayLegends")
	public List<LegendsDetails> displayLegends() throws NoSuchAlgorithmException {
		LegendsDetails legendsDetails = new LegendsDetails();
		List<LegendsDetails> details = service.displayLegends(legendsDetails);
		return details;
	}

	@GetMapping("/displayJobs")
	public List<PostJob> displayJobs() throws NoSuchAlgorithmException {
		PostJob legendsDetails = new PostJob();
		List<PostJob> details = service.getAllJob(legendsDetails);
		return details;
	}

	@PostMapping("/forgetPassword")
	public Registration forgetPassword(@RequestBody Registration reqistration) throws NoSuchAlgorithmException {
		return service.forgetPassword(reqistration);
	}

	@PostMapping("/updateProfile")
	public LegendsDetails updateProfile(@RequestBody LegendsDetails legendsDetails) {
		System.out.println("legendsDetails----" + legendsDetails.getAboutYourself());
		return service.updateProfile(legendsDetails);
	}

	@GetMapping("/logout")
	public void userLogout() {
		System.out.println("logout");
	}

	@PostMapping("/updateProfileExisting")
	public Registration updateProfileExisting(@RequestBody Registration registration) throws NoSuchAlgorithmException {
		return service.updateProfileExisting(registration);
	}

	@GetMapping("/moreDetails")
	public LegendsDetails moreDetails(@RequestParam String mobileNo) throws NoSuchAlgorithmException {
		LegendsDetails legendsDetails = new LegendsDetails();
		legendsDetails.setMobileNo(mobileNo);
		LegendsDetails details = service.moreDetails(legendsDetails);
		return details;
	}

	@PostMapping("/postJob")
	public PostJob postJob(@RequestBody PostJob legendsDetails, HttpServletRequest request, HttpSession sessionObj)
			throws NoSuchAlgorithmException {
		sessionObj = request.getSession(true);
		legendsDetails.setJobId(sessionObj.getAttribute("jobId").toString());
		return service.postJob(legendsDetails);
	}

	@PostMapping("/emailVerification")
	public Model emailVerification(@RequestBody otpTrigger model) throws NoSuchAlgorithmException, MessagingException {
		Model mdl = new Model();
		service.sendSimpleMessage(model);
		mdl.setMessage("Sent Successfully");
		return mdl;
	}

	@PostMapping("/otpVerification")
	public otpTrigger otpVerification(@RequestBody otpTrigger model)
			throws NoSuchAlgorithmException, MessagingException {
		otpTrigger mdl = service.otpverification(model);
		return mdl;
	}

	@PostMapping("/uploadImage")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
		String uploadImage = service.uploadImage(file);
		System.out.println("uploadImage--" + uploadImage);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/moreJobDetails")
	public PostJob moreJobDetails(@RequestParam String id) throws NoSuchAlgorithmException {
		PostJob postJob = new PostJob();
		postJob.setJobId(id);
		PostJob details = service.moreJobDetails(postJob);
		return details;
	}

	@PostMapping("/applyJob")
	public ApplyJob applyJob(@RequestBody ApplyJob applyJob) {
		return service.applyJob(applyJob);
	}

	@GetMapping("/displayCompanies")
	public List<Registration> displayCompanies() throws NoSuchAlgorithmException {
		ApplyJob legendsDetails = new ApplyJob();
		List<Registration> details = service.displayCompanies(legendsDetails);
		return details;
	}

	@PostMapping("/uploadResumeFile")
	public String uploadResume(@RequestParam("resume") MultipartFile file1, @RequestParam("myString") String username) {
		LegendsDetails reg = new LegendsDetails();
		try {
			String accessKey = "AKIA36NDIH54ACQTPQM2";
			String secretKey = "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";
			String bucketName = "zezha-talent";
			String prefix = "res";
			int length = 10; // The length of the random part of the ID
			Random random = new Random();
			String randomPart = String.format("%0" + length + "d", random.nextInt((int) Math.pow(10, length)));
			String id = prefix + randomPart + "."
					+ file1.getContentType().substring(file1.getContentType().lastIndexOf("/") + 1);
			String objectKey = "resume/" + id;
			System.out.println("id---" + id);

			BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
			AWSStaticCredentialsProvider credsProvider = new AWSStaticCredentialsProvider(awsCreds);

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(credsProvider).withRegion("us-east-1")
					.build();

			// File file = new File(filePath);
			InputStream inputStream = file1.getInputStream();
			File file = new File("newfile." + file1.getContentType().lastIndexOf("/"));
			OutputStream outputStream = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			String imageUrl = s3Client.getUrl(bucketName, objectKey).toString();
			System.out.println("imageUrl===" + imageUrl);
			reg.setResumeName(imageUrl);
			reg.setEmail(username);
			service.uploadResume(reg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "File Uploaded";
	}

	@PostMapping("/uploadImageFile")
	public String uploadImage(@RequestParam("image") MultipartFile file1, @RequestParam("myString") String username) {
		LegendsDetails reg = new LegendsDetails();
		try {
			String accessKey = "AKIA36NDIH54ACQTPQM2";
			String secretKey = "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";
			String bucketName = "zezha-talent";
			String prefix = "res";
			int length = 10; // The length of the random part of the ID
			Random random = new Random();
			String randomPart = String.format("%0" + length + "d", random.nextInt((int) Math.pow(10, length)));
			String id = prefix + randomPart + "."
					+ file1.getContentType().substring(file1.getContentType().lastIndexOf("/") + 1);
			String objectKey = "image/" + id;
			System.out.println("id---" + id);

			BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
			AWSStaticCredentialsProvider credsProvider = new AWSStaticCredentialsProvider(awsCreds);

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(credsProvider).withRegion("us-east-1")
					.build();

			// File file = new File(filePath);
			InputStream inputStream = file1.getInputStream();
			File file = new File("newfile." + file1.getContentType().lastIndexOf("/"));
			OutputStream outputStream = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			String imageUrl = s3Client.getUrl(bucketName, objectKey).toString();
			System.out.println("imageUrl===" + imageUrl);
			reg.setPhotoName(imageUrl);
			reg.setEmail(username);
			service.uploadImage(reg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "File Uploaded";
	}

	@PostMapping("/uploadLogoFile")
	public String uploadLogo(@RequestParam("image") MultipartFile file1, @RequestParam("myString") String username,
			HttpSession sessionObj) {
		PostJob reg = new PostJob();
		String id1 = "";
		try {
			String accessKey = "AKIA36NDIH54ACQTPQM2";
			String secretKey = "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";
			String bucketName = "zezha-talent";
			String prefix = "res";
			int length = 10; // The length of the random part of the ID
			Random random = new Random();
			String randomPart = String.format("%0" + length + "d", random.nextInt((int) Math.pow(10, length)));
			String id = prefix + randomPart + "."
					+ file1.getContentType().substring(file1.getContentType().lastIndexOf("/") + 1);
			String objectKey = "logo/" + id;
			System.out.println("id---" + id);

			BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
			AWSStaticCredentialsProvider credsProvider = new AWSStaticCredentialsProvider(awsCreds);

			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(credsProvider).withRegion("us-east-1")
					.build();

			// File file = new File(filePath);
			InputStream inputStream = file1.getInputStream();
			File file = new File("newfile." + file1.getContentType().lastIndexOf("/"));
			OutputStream outputStream = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			String imageUrl = s3Client.getUrl(bucketName, objectKey).toString();
			System.out.println("imageUrl===" + imageUrl);
			String prefix1 = "res";
			int length1 = 10; // The length of the random part of the ID
			Random random1 = new Random();
			String randomPart1 = String.format("%0" + length + "d", random1.nextInt((int) Math.pow(10, length1)));
			id1 = prefix1 + randomPart1;

			reg.setLogoImg(imageUrl);
			reg.setJobId(id1);
			sessionObj.setAttribute("jobId", id1);
			service.uploadLogo(reg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id1;
	}

	@PostMapping("/displayJobsOnCompanies")
	public List<PostJob> displayJobsOnCompanies(@RequestBody PostJob applyJob) {
		List<PostJob> details = new ArrayList<>();
		try {
			details = service.displayJobsOnCompanies(applyJob);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return details;
	}

	@PostMapping("/updateProfileExistingData")
	public LegendsDetails ExistingData(@RequestBody LegendsDetails legendsDetails) throws NoSuchAlgorithmException {
		return service.ExistingData(legendsDetails);
	}

	@PostMapping("/checkDataForResume")
	public LegendsDetails checkDataForResume(@RequestBody LegendsDetails legendsDetails)
			throws NoSuchAlgorithmException {
		return service.checkDataForResume(legendsDetails);
	}

	@GetMapping("/checkMobileNoExist")
	public LegendsDetails checkMobileNoExist(@RequestParam String mobileNo) throws NoSuchAlgorithmException {
		return service.checkMobileNoExist(mobileNo);
	}

	@GetMapping("/checkEmailExist")
	public LegendsDetails checkEmailExist(@RequestParam String email) throws NoSuchAlgorithmException {
		return service.checkEmailExist(email);
	}
	
	@PostMapping("/resumeQrCode")
	public LegendsDetails resumeQrCode(@RequestBody LegendsDetails legendsDetails) throws NoSuchAlgorithmException {
		return service.resumeQrCode(legendsDetails);
	}
	
    public static void main(String[] args) {
        File imageFile = new File("D://images.jpg");
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        try {
            String result = tesseract.doOCR(imageFile);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
