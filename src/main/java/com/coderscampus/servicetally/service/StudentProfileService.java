package com.coderscampus.servicetally.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.repository.StudentProfileRepository;

@Service
public class StudentProfileService {
	
	private final StudentProfileRepository studentProfileRepo;

	public StudentProfileService(StudentProfileRepository studentProfileRepo) {
		this.studentProfileRepo = studentProfileRepo;
	}



	public Optional<StudentProfile> getOne(Integer id) {
		return studentProfileRepo.findById(id);
	}

	public StudentProfile addNew(StudentProfile studentProfile) {
		return studentProfileRepo.save(studentProfile);
	}	

}
