package com.coderscampus.servicetally.service;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.UsersRepository;

@Service
public class UsersService {

	private final UsersRepository usersRepo;

	public UsersService(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	public Users addNew(Users users) {
		return usersRepo.save(users);
	}
}
