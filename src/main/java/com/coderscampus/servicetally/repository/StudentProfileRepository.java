package com.coderscampus.servicetally.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer>{

	List<StudentProfile> findBySchoolIdIn(List<Integer> schoolIds);

}
