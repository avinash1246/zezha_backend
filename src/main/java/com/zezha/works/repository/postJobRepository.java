package com.zezha.works.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zezha.works.Entity.PostJob;
import com.zezha.works.Entity.Registration;

public interface postJobRepository extends JpaRepository<PostJob, String> {
	PostJob findByJobId(String id);
	List<PostJob> findByJobPostedBy(String email);
}
