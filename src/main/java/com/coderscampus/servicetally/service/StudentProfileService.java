package com.coderscampus.servicetally.service;

import java.util.Optional;

import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.repository.StudentProfileRepository;

public class StudentProfileService {

	public Optional<StudentProfile> getOne(Integer id) {
		return StudentProfileRepository.findById(id);
	}

}
