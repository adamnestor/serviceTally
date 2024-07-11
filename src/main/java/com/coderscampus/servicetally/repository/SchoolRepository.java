package com.coderscampus.servicetally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.Users;

public interface SchoolRepository extends JpaRepository<School, Integer> {

	List<School> findBySchoolAdminId(Users schoolAdminId);

}
