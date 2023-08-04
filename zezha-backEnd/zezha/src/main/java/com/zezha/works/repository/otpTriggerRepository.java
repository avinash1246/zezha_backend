package com.zezha.works.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zezha.works.Entity.otpTrigger;

public interface otpTriggerRepository extends JpaRepository<otpTrigger, Long> {
	otpTrigger findByEmail(String email);
}
