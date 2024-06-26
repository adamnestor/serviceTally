package com.coderscampus.servicetally.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);
}
