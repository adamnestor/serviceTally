package com.coderscampus.servicetally.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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


}
