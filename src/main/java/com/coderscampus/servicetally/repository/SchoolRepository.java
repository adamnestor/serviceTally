package com.coderscampus.servicetally.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coderscampus.servicetally.domain.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {

}