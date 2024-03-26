package com.zezha.works.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zezha.works.Entity.ApplyJob;

public interface applyJobRepository extends JpaRepository<ApplyJob, Integer>{
	@Query(value = "select u from ApplyJob u where u.appliedEmailId = :appliedEmailId AND u.appliedOn = (SELECT MAX(a.appliedOn) FROM ApplyJob a WHERE a.appliedEmailId = :appliedEmailId)")
	List<ApplyJob> findByAppliedEmailId(String appliedEmailId);
}
