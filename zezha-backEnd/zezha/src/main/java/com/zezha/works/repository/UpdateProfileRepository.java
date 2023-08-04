package com.zezha.works.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zezha.works.Entity.LegendsDetails;

public interface UpdateProfileRepository extends JpaRepository<LegendsDetails, Long>{
	LegendsDetails findByMobileNo(String mobileNo);
	LegendsDetails findByEmail(String email);
	Optional<LegendsDetails> findByPhotoName(String photoName);
}
