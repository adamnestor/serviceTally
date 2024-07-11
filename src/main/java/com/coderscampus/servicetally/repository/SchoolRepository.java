package com.coderscampus.servicetally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {

	List<School> findBySchoolAdminId_UserAccountId(int userAccountId);

}
