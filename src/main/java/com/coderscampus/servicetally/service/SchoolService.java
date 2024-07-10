package com.coderscampus.servicetally.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.SchoolRepository;

import jakarta.transaction.Transactional;

@Service
public class SchoolService {

	private final SchoolRepository schoolRepo;

	@Autowired
	public SchoolService(SchoolRepository schoolRepo) {
		this.schoolRepo = schoolRepo;
	}

	public List<School> getAllSchools() {
		return schoolRepo.findAll();
	}

	public School getSchoolById(Integer id) {
		return schoolRepo.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
	}

	public List<School> getSchoolsByAdmin(Users admin) {
		return schoolRepo.findBySchoolAdmin(admin);
	}

	@Transactional
	public School createOrUpdateSchool(School school) {

		if (school.getSchoolName() == null || school.getSchoolName().isEmpty()) {
			throw new IllegalArgumentException("School name cannot by empty");
		}

		return schoolRepo.save(school);
	}

	public void deleteSchool(Integer id) {
		schoolRepo.deleteById(id);
	}

}
