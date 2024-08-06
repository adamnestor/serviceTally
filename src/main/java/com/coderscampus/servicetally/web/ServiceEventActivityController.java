package com.coderscampus.servicetally.web;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.ServiceEventStatus;
import com.coderscampus.servicetally.domain.ServiceHoursDto;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.StudentServiceEventsDto;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.service.SchoolService;
import com.coderscampus.servicetally.service.ServiceEventActivityService;
import com.coderscampus.servicetally.service.ServiceHoursService;
import com.coderscampus.servicetally.service.StudentProfileService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
public class ServiceEventActivityController {

	private final UsersService usersService;
	private final ServiceEventActivityService serviceEventActivityService;
	private final StudentProfileService studentProfileService;
	private final ServiceHoursService serviceHoursService;

	@Autowired
	public ServiceEventActivityController(UsersService usersService,
			ServiceEventActivityService serviceEventActivityService, SchoolService schoolService,
			StudentProfileService studentProfileService, ServiceHoursService serviceHoursService) {
		this.usersService = usersService;
		this.serviceEventActivityService = serviceEventActivityService;
		this.studentProfileService = studentProfileService;
		this.serviceHoursService = serviceHoursService;
	}

	@GetMapping("/dashboard/")
	public String searchServiceEvents(@RequestParam(value = "userAccountId", required = false) Integer studentIdFilter,
			@RequestParam(value = "schoolId", required = false) Integer schoolIdFilter,
			@RequestParam(value = "status", required = false) String statusFilter, Model model) {

		model.addAttribute("studentIdFilter", studentIdFilter);
		model.addAttribute("schoolIdFilter", schoolIdFilter);
		model.addAttribute("statusFilter", statusFilter);

		Object currentUserProfile = usersService.getCurrentUserProfile();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			model.addAttribute("username", currentUsername);

			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Student"))) {
				List<StudentServiceEventsDto> studentServiceEvents = serviceEventActivityService
						.getStudentServiceEvents(((StudentProfile) currentUserProfile).getUserAccountId());
				
				studentServiceEvents.sort((event1, event2) -> Integer.compare(event2.getEventId(), event1.getEventId()));
				
				model.addAttribute("serviceEvent", studentServiceEvents);

				int studentId = ((StudentProfile) currentUserProfile).getUserAccountId();
				StudentProfile studentProfile = (StudentProfile) currentUserProfile;
				
				if (studentProfile.getSchool() != null) {
					ServiceHoursDto serviceHoursDto = serviceHoursService.getServiceHoursProgress(studentId);
					model.addAttribute("serviceHoursDto", serviceHoursDto);
				} else {
					ServiceHoursDto defaultServiceHoursDto = new ServiceHoursDto(0.0f, 0.0f, 0.0f);
					model.addAttribute("serviceHoursDto", defaultServiceHoursDto);
				}
			
			} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {

				// Create list of school ids managed by current admin profile
				List<School> managedSchools = ((AdminProfile) currentUserProfile).getSchoolsManaged();
				managedSchools.sort(Comparator.comparing(School::getSchoolName));
				List<Integer> schoolIds = managedSchools.stream().map(School::getSchoolId).collect(Collectors.toList());
				model.addAttribute("schools", managedSchools);

				// Create a list of students who attend a school managed by the current admin
				// profile
				List<StudentProfile> studentsInManagedSchools = studentProfileService
						.findBySchoolInOrderByLastName(managedSchools);
				model.addAttribute("students", studentsInManagedSchools);

				// Define status options using enum
				List<ServiceEventStatus> statusOptions = Arrays.asList(ServiceEventStatus.values());
				model.addAttribute("statusOptions", statusOptions);

				// Filter service events
				List<StudentServiceEventsDto> filteredEvents;
				if (studentIdFilter == null && schoolIdFilter == null
						&& (statusFilter == null || statusFilter.isEmpty())) {
					filteredEvents = serviceEventActivityService.getAllServiceEventsForSchools(schoolIds);
				} else {
					filteredEvents = serviceEventActivityService.getAllServiceEventsFiltered(studentIdFilter,
							schoolIdFilter, statusFilter);
				}
				filteredEvents.sort((event1, event2) -> Integer.compare(event2.getEventId(), event1.getEventId()));
				model.addAttribute("serviceEvent", filteredEvents);
			}
		}

		model.addAttribute("user", currentUserProfile);

		return "dashboard";
	}

	@GetMapping("/dashboard/add")
	public String addServiceEvents(Model model) {
		model.addAttribute("serviceEventActivity", new ServiceEventActivity());
		model.addAttribute("user", usersService.getCurrentUserProfile());
		return "add-service-event";
	}

	@PostMapping("/dashboard/add")
	public String addNew(ServiceEventActivity serviceEventActivity, Model model) {

		Users user = usersService.getCurrentUser();
		if (user != null) {
			serviceEventActivity.setPostedById(user);
		}
		serviceEventActivity.setStatus(ServiceEventStatus.SUBMITTED);
		model.addAttribute("serviceEventActivity", serviceEventActivity);
		serviceEventActivityService.save(serviceEventActivity);
		return "redirect:/dashboard/";
	}

	@GetMapping("dashboard/edit/{id}")
	public String editServiceEvent(@PathVariable("id") int id, Model model) {

		ServiceEventActivity serviceEventActivity = serviceEventActivityService.getOne(id);
		model.addAttribute("serviceEventActivity", serviceEventActivity);
		model.addAttribute("user", usersService.getCurrentUserProfile());
		return "add-service-event";
	}

	@PostMapping("dashboard/edit/{id}")
	public String postEditServiceEvent(@PathVariable("id") Integer id, ServiceEventActivity serviceEventActivity) {
		serviceEventActivity.setEventId(id);
		Users currentUser = usersService.getCurrentUser();
		serviceEventActivity.setPostedById(currentUser);
		serviceEventActivity.setStatus(ServiceEventStatus.SUBMITTED);
		serviceEventActivityService.save(serviceEventActivity);
		return "redirect:/service-details/" + serviceEventActivity.getEventId();
	}

	@PostMapping("dashboard/deleteEvent/{id}")
	public String deleteServiceEvent(@PathVariable("id") Integer id) {
		serviceEventActivityService.deleteServiceEvent(id);
		return "redirect:/dashboard/";
	}

}
