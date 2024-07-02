package com.coderscampus.servicetally.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.AdminProfileRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;
import com.coderscampus.servicetally.repository.UsersRepository;

@Service
public class UsersService {

	private final UsersRepository usersRepo;
	private final AdminProfileRepository adminProfileRepo;
	private final StudentProfileRepository studentProfileRepo;

	public UsersService(UsersRepository usersRepo, AdminProfileRepository adminProfileRepo,
			StudentProfileRepository studentProfileRepo) {
		this.usersRepo = usersRepo;
		this.adminProfileRepo = adminProfileRepo;
		this.studentProfileRepo = studentProfileRepo;
	}

	public Users addNew(Users users) {
		
		Users savedUser = usersRepo.save(users);
		
		int userTypeId = users.getUserTypeId().getUserTypeId();
		if(userTypeId == 1) {
			adminProfileRepo.save(new AdminProfile(savedUser));
		} else {
			studentProfileRepo.save(new StudentProfile(savedUser));
		}
		
		
		return savedUser;
	}

	public Optional<Users> getUserByEmail(String email) {
		return usersRepo.findByEmail(email);
	}
}
