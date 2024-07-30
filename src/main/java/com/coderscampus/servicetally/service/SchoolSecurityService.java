package com.coderscampus.servicetally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;

@Service
public class SchoolSecurityService {

	private final AdminProfileService adminProfileService;
	private final SchoolService schoolService;
	
	@Autowired
	public SchoolSecurityService(AdminProfileService adminProfileService, SchoolService schoolService) {
		this.adminProfileService = adminProfileService;
		this.schoolService = schoolService;
	}
	
	public boolean isAdminAssociatedWithSchool(String adminUsername, int schoolId) {
		AdminProfile admin = adminProfileService.getAdminProfileByUserEmail(adminUsername);
		if (admin == null) {
			return false;
		}
		School school = schoolService.getSchoolById(schoolId);
		return school != null && school.getSchoolAdminId().equals(admin);
	}
	
}
