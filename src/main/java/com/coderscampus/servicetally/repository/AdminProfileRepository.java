package com.coderscampus.servicetally.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.AdminProfile;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Integer>{

	Optional<AdminProfile> findByUserId(int userId);

}
