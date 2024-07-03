package com.coderscampus.servicetally.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.AdminProfile;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Integer>{

}
