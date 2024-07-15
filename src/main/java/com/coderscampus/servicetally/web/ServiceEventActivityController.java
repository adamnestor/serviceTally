package com.coderscampus.servicetally.web;

import java.util.List;

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

import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.ServiceEventStatus;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.StudentServiceEventsDto;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.service.ServiceEventActivityService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
public class ServiceEventActivityController {

	private final UsersService usersService;
	private final ServiceEventActivityService serviceEventActivityService;

	@Autowired
	public ServiceEventActivityController(UsersService usersService,
			ServiceEventActivityService serviceEventActivityService) {
		this.usersService = usersService;
		this.serviceEventActivityService = serviceEventActivityService;
	}

	@GetMapping("/dashboard/")
	public String searchServiceEvents(Model model) {

		Object currentUserProfile = usersService.getCurrentUserProfile();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			model.addAttribute("username", currentUsername);
			if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Student"))) {
				List<StudentServiceEventsDto> studentServiceEvents = serviceEventActivityService
						.getStudentServiceEvents(((StudentProfile) currentUserProfile).getUserAccountId());
				model.addAttribute("serviceEvent", studentServiceEvents);
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
		serviceEventActivityService.addNew(serviceEventActivity);
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
		serviceEventActivityService.addNew(serviceEventActivity);
		return "redirect:/service-details/" + serviceEventActivity.getEventId();
	}
	
	@PostMapping("dashboard/deleteEvent/{id}")
	public String deleteServiceEvent(@PathVariable("id") Integer id) {
		serviceEventActivityService.deleteServiceEvent(id);
		return "redirect:/dashboard/";
	}

}
