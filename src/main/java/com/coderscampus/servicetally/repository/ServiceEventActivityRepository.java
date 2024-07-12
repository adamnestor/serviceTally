package com.coderscampus.servicetally.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.servicetally.domain.ServiceEventActivity;

public interface ServiceEventActivityRepository extends JpaRepository<ServiceEventActivity, Integer>{

}
