package com.coderscampus.servicetally.service;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	public Object getCurrentUserProfile() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Users user = usersRepo.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			int userId = user.getUserId();
			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {
				AdminProfile adminProfile = adminProfileRepo.findById(userId).orElse(new AdminProfile());
				return adminProfile;
			} else {
				StudentProfile studentProfile = studentProfileRepo.findById(userId).orElse(new StudentProfile());
				return studentProfile;
			}
		}
		return null;
	}
	
	public Users getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Users user = usersRepo.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Could not found user"));
			return user;
		}
		return null;
	}
}
