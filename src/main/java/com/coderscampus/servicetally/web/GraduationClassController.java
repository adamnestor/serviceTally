package com.coderscampus.servicetally.web;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.CompletedStatus;
import com.coderscampus.servicetally.domain.GraduationYearDto;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.service.GraduationClassService;
import com.coderscampus.servicetally.service.StudentProfileService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
public class GraduationClassController {

	private final UsersService usersService;
	private final StudentProfileService studentProfileService;
	private final GraduationClassService graduationClassService;

	public GraduationClassController(UsersService usersService, StudentProfileService studentProfileService,
			GraduationClassService graduationClassService) {
		this.usersService = usersService;
		this.studentProfileService = studentProfileService;
		this.graduationClassService = graduationClassService;
	}

	@GetMapping("/overview/")
	public String viewGraduationClass(@RequestParam(value = "schoolId", required = false) Integer schoolIdFilter,
			@RequestParam(value = "graduationYear", required = false) String graduationYearFilter,
			@RequestParam(value = "completedStatus", required = false) String completedStatusFilter, Model model) {

		model.addAttribute("schoolIdFilter", schoolIdFilter);
		model.addAttribute("graduationYearFilter", graduationYearFilter);
		model.addAttribute("completedStatusFilter", completedStatusFilter);

		Object currentUserProfile = usersService.getCurrentUserProfile();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			model.addAttribute("username", currentUsername);

			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {
				// Add managed schools
				List<School> managedSchools = ((AdminProfile) currentUserProfile).getSchoolsManaged();
				managedSchools.sort(Comparator.comparing(School::getSchoolName));
				List<Integer> schoolIds = managedSchools.stream().map(School::getSchoolId).collect(Collectors.toList());
				model.addAttribute("schools", managedSchools);

				// Add graduation years (distinct from student profiles)
				List<String> graduationYears = studentProfileService.findDistinctGraduationYears();
				model.addAttribute("graduationYears", graduationYears);

				// Define status options using enum
				List<CompletedStatus> statusOptions = Arrays.asList(CompletedStatus.values());
				model.addAttribute("completedStatusOptions", statusOptions);

				// Filter and get graduation class data
				List<GraduationYearDto> filteredGraduationClass = null;
				if ((graduationYearFilter == null || graduationYearFilter.isEmpty()) && schoolIdFilter == null
						&& (completedStatusFilter == null || completedStatusFilter.isEmpty())) {
					filteredGraduationClass = graduationClassService.getAllStudentsForSchools(schoolIds);
				} else {
					filteredGraduationClass = graduationClassService.getAllStudentsFiltered(graduationYearFilter, schoolIdFilter, completedStatusFilter);
				}
				
				model.addAttribute("graduationClass", filteredGraduationClass);
			}
		}

		model.addAttribute("user", currentUserProfile);

		return "overview";
	}
}
