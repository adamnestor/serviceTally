package com.coderscampus.servicetally.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.repository.SchoolRepository;

@Service
public class SchoolService {

	private final SchoolRepository schoolRepo;

	@Autowired
	public SchoolService(SchoolRepository schoolRepo) {
		this.schoolRepo = schoolRepo;
	}
	
	public List<School> getAllSchools(){
		return schoolRepo.findAll();
	}
	
	public Optional<School> getOne(Integer id){
		return schoolRepo.findById(id);
	}
	
	public School createOrUpdateSchool(School school) {
		return schoolRepo.save(school);
	}
	
	
}
