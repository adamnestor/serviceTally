package com.coderscampus.servicetally.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;

	public UsersService(UsersRepository usersRepo, AdminProfileRepository adminProfileRepo,
			StudentProfileRepository studentProfileRepo, PasswordEncoder passwordEncoder) {
		this.usersRepo = usersRepo;
		this.adminProfileRepo = adminProfileRepo;
		this.studentProfileRepo = studentProfileRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public Users addNew(Users users) {

		users.setPassword(passwordEncoder.encode(users.getPassword()));
		Users savedUser = usersRepo.save(users);

		int userTypeId = users.getUserTypeId().getUserTypeId();
		if (userTypeId == 1) {
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
