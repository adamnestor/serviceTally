package com.coderscampus.servicetally.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.servicetally.domain.CompletedStatus;
import com.coderscampus.servicetally.domain.GraduationYearDto;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.repository.StudentProfileRepository;

@Service
public class GraduationClassService {

	private final StudentProfileRepository studentProfileRepo;
	private final ServiceHoursService serviceHoursService;

	@Autowired
	public GraduationClassService(StudentProfileRepository studentProfileRepo,
			ServiceHoursService serviceHoursService) {
		this.studentProfileRepo = studentProfileRepo;
		this.serviceHoursService = serviceHoursService;
	}

	public List<GraduationYearDto> getAllStudentsForSchools(List<Integer> schoolIds) {
		// Retrieve all student profiles for the given school IDs
		List<StudentProfile> studentProfiles = getStudentsBySchoolIds(schoolIds);
		List<GraduationYearDto> graduationYearDtoList = new ArrayList<>();

		// Convert each StudentProfile to GraduationYearDto
		for (StudentProfile profile : studentProfiles) {

			int userAccountId = profile.getUserAccountId();
			float approvedHours = serviceHoursService.getTotalApprovedServiceHours(userAccountId);
			float requiredHours = serviceHoursService.getRequiredServiceHours(userAccountId);

			CompletedStatus completedStatus = approvedHours > requiredHours ? CompletedStatus.COMPLETED
					: CompletedStatus.INCOMPLETE;

			GraduationYearDto dto = new GraduationYearDto(userAccountId, profile.getFirstName(), profile.getLastName(),
					profile.getGraduationYear(), completedStatus, approvedHours, requiredHours);
			graduationYearDtoList.add(dto);
		}
		return graduationYearDtoList;
	}

	public List<GraduationYearDto> getAllStudentsByCompletedStatus(String completedStatus, List<Integer> schoolIds) {

		List<StudentProfile> studentProfiles = getStudentsBySchoolIds(schoolIds);
		List<GraduationYearDto> graduationYearDtoList = new ArrayList<>();

		for (StudentProfile profile : studentProfiles) {
			int userAccountId = profile.getUserAccountId();
			float approvedHours = serviceHoursService.getTotalApprovedServiceHours(userAccountId);
			float requiredHours = serviceHoursService.getRequiredServiceHours(userAccountId);

			CompletedStatus profileCompletedStatus = approvedHours > requiredHours ? CompletedStatus.COMPLETED
					: CompletedStatus.INCOMPLETE;

			if (completedStatus.equals(profileCompletedStatus.name())) {
				GraduationYearDto dto = new GraduationYearDto(userAccountId, profile.getFirstName(),
						profile.getLastName(), profile.getGraduationYear(), profileCompletedStatus, approvedHours,
						requiredHours);
				graduationYearDtoList.add(dto);
			}
		}
		return graduationYearDtoList;
	}

	public List<GraduationYearDto> getAllStudentsFiltered(String graduationYear, Integer schoolId,
			String completedStatus) {
		// Retrieve all student profiles based on filters
		List<StudentProfile> studentProfiles = new ArrayList<>();

		if (graduationYear != null && !graduationYear.isEmpty() && schoolId != null && completedStatus != null
				&& !completedStatus.isEmpty()) {
			studentProfiles = studentProfileRepo.findBySchoolIdAndGraduationYear(schoolId, graduationYear);
		} else if (graduationYear != null && !graduationYear.isEmpty() && schoolId != null
				&& (completedStatus == null || completedStatus.isEmpty())) {
			studentProfiles = studentProfileRepo.findBySchoolIdAndGraduationYear(schoolId, graduationYear);
		} else if (graduationYear != null && !graduationYear.isEmpty() && schoolId == null && completedStatus != null
				&& !completedStatus.isEmpty()) {
			studentProfiles = studentProfileRepo.getStudentsByGraduationYear(graduationYear);
		} else if (graduationYear != null && !graduationYear.isEmpty() && schoolId == null
				&& (completedStatus == null || completedStatus.isEmpty())) {
			studentProfiles = studentProfileRepo.getStudentsByGraduationYear(graduationYear);
		} else if (graduationYear == null || graduationYear.isEmpty() && schoolId != null && completedStatus != null
				&& !completedStatus.isEmpty()) {
			studentProfiles = studentProfileRepo.getStudentsBySchoolId(schoolId);
		} else if (graduationYear == null || graduationYear.isEmpty() && schoolId != null
				&& (completedStatus == null || completedStatus.isEmpty())) {
			studentProfiles = studentProfileRepo.getStudentsBySchoolId(schoolId);
		}

		List<GraduationYearDto> graduationYearDtoList = new ArrayList<>();
		for (StudentProfile profile : studentProfiles) {
			int userAccountId = profile.getUserAccountId();
			float approvedHours = serviceHoursService.getTotalApprovedServiceHours(userAccountId);
			float requiredHours = serviceHoursService.getRequiredServiceHours(userAccountId);

			CompletedStatus profileCompletedStatus = approvedHours > requiredHours ? CompletedStatus.COMPLETED
					: CompletedStatus.INCOMPLETE;

			if (completedStatus == null || completedStatus.isEmpty()
					|| completedStatus.equals(profileCompletedStatus.name())) {
				GraduationYearDto dto = new GraduationYearDto(userAccountId, profile.getFirstName(),
						profile.getLastName(), profile.getGraduationYear(), profileCompletedStatus, approvedHours,
						requiredHours);
				graduationYearDtoList.add(dto);
			}
		}

		return graduationYearDtoList;
	}

	public List<StudentProfile> getStudentsBySchoolIds(List<Integer> schoolIds) {
		return studentProfileRepo.findAllBySchoolIds(schoolIds);
	}

}
