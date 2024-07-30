package com.coderscampus.servicetally.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;

@Service
public class ServiceEventSecurityService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;

	public ServiceEventSecurityService(ServiceEventActivityRepository serviceEventActivityRepo) {
		this.serviceEventActivityRepo = serviceEventActivityRepo;
	}

	public boolean hasAccessToEvent(int eventId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();

		ServiceEventActivity serviceEvent = serviceEventActivityRepo.findById(eventId)
				.orElseThrow(() -> new RuntimeException("Service event not found"));

		if (serviceEvent.getPostedById().getEmail().equals(currentUsername)) {
			return true;
		}

		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("Admin"));
		
		if (isAdmin) {
			return true;
		}
		
		return false;
	}
}
