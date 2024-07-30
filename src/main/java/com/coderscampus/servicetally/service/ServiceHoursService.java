package com.coderscampus.servicetally.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.ServiceHoursDto;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;
import com.coderscampus.servicetally.repository.UsersRepository;

@Service
public class ServiceHoursService {

	private final ServiceEventActivityRepository serviceEventActivityRepo;
	private final StudentProfileRepository studentProfileRepo;
	private final UsersRepository usersRepo;

	@Autowired
	public ServiceHoursService(ServiceEventActivityRepository serviceEventActivityRepo,
			StudentProfileRepository studentProfileRepository, UsersRepository usersRepo) {
		this.serviceEventActivityRepo = serviceEventActivityRepo;
		this.studentProfileRepo = studentProfileRepository;
		this.usersRepo = usersRepo;
	}

	public float getTotalServiceHours(int studentId) {
		StudentProfile studentProfile = studentProfileRepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		Users user = usersRepo.findById(studentProfile.getUserAccountId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		return serviceEventActivityRepo.findTotalHoursByStudentId(user);
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
