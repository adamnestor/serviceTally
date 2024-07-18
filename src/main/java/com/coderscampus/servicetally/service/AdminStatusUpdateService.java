package com.coderscampus.servicetally.service;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.ServiceEventStatus;

@Service
public class AdminStatusUpdateService {

	private final ServiceEventActivityService serviceEventActivityService;
	
	public AdminStatusUpdateService(ServiceEventActivityService serviceEventActivityService) {
		this.serviceEventActivityService = serviceEventActivityService;
	}



	public void updateStatus(int id, String status) {
		ServiceEventActivity serviceEvent = serviceEventActivityService.getOne(id);
		try {
			ServiceEventStatus eventStatus = ServiceEventStatus.valueOf(status);
			serviceEvent.setStatus(eventStatus);
			serviceEventActivityService.save(serviceEvent);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid status value: " + status);
		}
	}

}
