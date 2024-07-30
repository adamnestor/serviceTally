package com.coderscampus.servicetally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.ServiceHoursDto;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;

@Service
public class ServiceHoursService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;
	private final StudentProfileRepository studentProfileRepo;

	@Autowired
	public ServiceHoursService(ServiceEventActivityRepository serviceEventActivityRepo,
			StudentProfileRepository studentProfileRepository) {
		this.serviceEventActivityRepo = serviceEventActivityRepo;
		this.studentProfileRepo = studentProfileRepository;
	}

	public float getTotalServiceHours(int studentId) {
		return serviceEventActivityRepo.findTotalHoursByStudentId(studentId);
	}

	public float getRequiredServiceHours(int studentId) {
		StudentProfile studentProfile = studentProfileRepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		return studentProfile.getSchool().getHoursRequired();
	}
	
	public ServiceHoursDto getServiceHoursProgress(int studentId) {
		float totalHours = getTotalServiceHours(studentId);
		float requiredHours = getRequiredServiceHours(studentId);
		return new ServiceHoursDto(totalHours, requiredHours);
	}

}
