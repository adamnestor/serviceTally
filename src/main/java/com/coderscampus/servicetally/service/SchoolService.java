package com.coderscampus.servicetally.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.repository.SchoolRepository;

@Service
public class SchoolService {

	private final SchoolRepository schoolRepo;
	
	@Autowired
	public SchoolService(SchoolRepository schoolRepo, AdminProfileService adminProfileService,
			UsersService usersService) {
		this.schoolRepo = schoolRepo;
	}

	public List<School> getAllSchools() {
		return schoolRepo.findAll();
	}

	public School getSchoolById(Integer id) {
		return schoolRepo.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
	}

	public List<School> getSchoolsByAdmin(AdminProfile adminProfile) {
		return schoolRepo.findBySchoolAdminId_UserAccountId(adminProfile.getUserAccountId());
	}

	public School saveSchool(School school) {
		return schoolRepo.save(school);
	}

	public void deleteSchool(Integer id) {
		schoolRepo.deleteById(id);
	}

}
