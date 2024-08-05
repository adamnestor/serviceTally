package com.coderscampus.servicetally.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.CompletedStatus;
import com.coderscampus.servicetally.domain.GraduationYearDto;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.repository.ServiceEventActivityRepository;
import com.coderscampus.servicetally.repository.StudentProfileRepository;
import com.coderscampus.servicetally.repository.UsersRepository;

@Service
public class GraduationClassService {

	private final StudentProfileRepository studentProfileRepo;
	private final ServiceHoursService serviceHoursService;

	public GraduationClassService(StudentProfileRepository studentProfileRepo,
			ServiceEventActivityRepository serviceEventActivityRepo, UsersRepository usersRepo,
			ServiceHoursService serviceHoursService) {
		this.studentProfileRepo = studentProfileRepo;
		this.serviceHoursService = serviceHoursService;
	}

	public List<GraduationYearDto> getGraduationClass(Integer schoolId, String graduationYear) { 
		List<StudentProfile> studentProfiles = studentProfileRepo.findBySchoolIdAndGraduationYear(schoolId,
				graduationYear);

		List<GraduationYearDto> graduationYearDtoList = new ArrayList<>();
		for (StudentProfile profile : studentProfiles) {
			Integer userAccountId = profile.getUserAccountId();
			float approvedHours = serviceHoursService.getTotalApprovedServiceHours(userAccountId);
			float requiredHours = serviceHoursService.getRequiredServiceHours(userAccountId);

			// Determine completedStatus
			CompletedStatus completedStatus = approvedHours > requiredHours ? CompletedStatus.COMPLETED
					: CompletedStatus.INCOMPLETE;

			graduationYearDtoList.add(new GraduationYearDto(userAccountId, profile.getFirstName(),
					profile.getLastName(), profile.getGraduationYear(),
					completedStatus, approvedHours, requiredHours));
		}
		return graduationYearDtoList;
	}

}
