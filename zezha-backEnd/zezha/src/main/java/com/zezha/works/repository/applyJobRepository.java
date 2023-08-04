package com.zezha.works.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zezha.works.Entity.ApplyJob;

public interface applyJobRepository extends JpaRepository<ApplyJob, Integer>{
	ApplyJob findByAppliedEmailId(String appliedEmailId);
}
