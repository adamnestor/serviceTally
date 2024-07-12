package com.coderscampus.servicetally.service;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;

@Service
public class ServiceEventActivityService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;

	public ServiceEventActivityService(ServiceEventActivityRepository serviceEventActivityRepo) {
		this.serviceEventActivityRepo = serviceEventActivityRepo;
	}

	public ServiceEventActivity addNew(ServiceEventActivity serviceEventActivity) {
		return serviceEventActivityRepo.save(serviceEventActivity);
	}
}
