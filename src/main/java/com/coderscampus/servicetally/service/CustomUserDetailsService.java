package com.coderscampus.servicetally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.UsersRepository;
import com.coderscampus.servicetally.util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UsersRepository usersRepo;

	@Autowired
	public CustomUserDetailsService(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
		return new CustomUserDetails(user);
	}

}
