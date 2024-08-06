package com.coderscampus.servicetally.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.UsersType;
import com.coderscampus.servicetally.repository.UsersTypeRepository;

@Service
public class UsersTypeService {

	private final UsersTypeRepository usersTypeRepo;

	@Autowired
	public UsersTypeService(UsersTypeRepository usersTypeRepo) {
		this.usersTypeRepo = usersTypeRepo;
	}

	public List<UsersType> getAll() {
		return usersTypeRepo.findAll();
	}

	public boolean isAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("Admin"));
	}
}
