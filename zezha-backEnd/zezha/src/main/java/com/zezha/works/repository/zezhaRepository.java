package com.zezha.works.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zezha.works.Entity.Registration;

public interface zezhaRepository extends JpaRepository<Registration, Long>{
	Registration findByMobileNo(String mobileNo);
	Registration findByEmail(String email);
	Registration findByLoginType(String loginType);
}
