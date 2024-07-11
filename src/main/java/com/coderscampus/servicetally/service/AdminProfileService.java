package com.coderscampus.servicetally.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.repository.AdminProfileRepository;

@Service
public class AdminProfileService {
	
	private final AdminProfileRepository adminProfileRepo;

	@Autowired
	public AdminProfileService(AdminProfileRepository adminProfileRepo) {
		this.adminProfileRepo = adminProfileRepo;
	}
	
	public Optional<AdminProfile> getOne(Integer id){
		return adminProfileRepo.findById(id);
	}

	public AdminProfile addNew(AdminProfile adminProfile) {
		return adminProfileRepo.save(adminProfile);
	}

	public AdminProfile getByUserId(int userId) {
		Optional<AdminProfile> adminProfile = adminProfileRepo.findByUserId(userId);
		return adminProfile.orElse(null);
	}


}
