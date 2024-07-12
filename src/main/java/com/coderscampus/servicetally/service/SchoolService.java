package com.coderscampus.servicetally.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.SchoolRepository;

import jakarta.transaction.Transactional;

@Service
public class SchoolService {

	private final SchoolRepository schoolRepo;
	private final AdminProfileService adminProfileService;
	private final UsersService usersService;

	@Autowired
	public SchoolService(SchoolRepository schoolRepo, AdminProfileService adminProfileService,
			UsersService usersService) {
		this.schoolRepo = schoolRepo;
		this.adminProfileService = adminProfileService;
		this.usersService = usersService;
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

	public School createOrUpdateSchool(School school) {
		return schoolRepo.save(school);
	}

	public void deleteSchool(Integer id) {
		schoolRepo.deleteById(id);
	}

	public void createSchoolForCurrentUser(School school) {
		Users currentUser = usersService.getCurrentUser();
		AdminProfile adminProfile = adminProfileService.getByEmail(currentUser.getEmail());
		school.setSchoolAdminId(adminProfile);
		schoolRepo.save(school);
	}

}
