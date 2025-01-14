package com.coderscampus.servicetally.web;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.domain.ServiceEventStatus;
import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.service.AdminStatusUpdateService;
import com.coderscampus.servicetally.service.ServiceEventActivityService;
import com.coderscampus.servicetally.service.StudentProfileService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
public class AdminStatusUpdateController {

	private final ServiceEventActivityService serviceEventActivityService;
	private final AdminStatusUpdateService adminStatusUpdateService;
	private final StudentProfileService studentProfileService;
	private final UsersService usersService;

	@Autowired
	public AdminStatusUpdateController(ServiceEventActivityService serviceEventActivityService,
			AdminStatusUpdateService adminStatusUpdateService, StudentProfileService studentProfileService,
			UsersService usersService) {
		this.serviceEventActivityService = serviceEventActivityService;
		this.adminStatusUpdateService = adminStatusUpdateService;
		this.studentProfileService = studentProfileService;
		this.usersService = usersService;
	}

	@GetMapping("service-details/{id}")
	@PreAuthorize("@serviceEventSecurityService.hasAccessToEvent(#id)")
	public String display(@PathVariable("id") int id, Model model) {
		Object currentUserProfile = usersService.getCurrentUserProfile();
		ServiceEventActivity serviceDetails = serviceEventActivityService.getOne(id);

		// Fetch the Users object associated with postedById
		Users postedByUser = serviceDetails.getPostedById();
		// Fetch the StudentProfile associated with postedById
		Optional<StudentProfile> optionalPostedByProfile = studentProfileService.getOne(postedByUser.getUserId());
		if(optionalPostedByProfile.isPresent()) {
			StudentProfile postedByProfile = optionalPostedByProfile.get();
			model.addAttribute("postedByProfile", postedByProfile);
		} else {
			model.addAttribute("postedByProfile", "N/A");
		}

		// Define status options using enum
		List<ServiceEventStatus> statusOptions = Arrays.asList(ServiceEventStatus.values());
		model.addAttribute("statusOptions", statusOptions);

		model.addAttribute("serviceDetails", serviceDetails);
		model.addAttribute("user", currentUserProfile);

		return "service-event-details";
	}

	@PostMapping("service-details/{id}")
	public String updateStatus(@PathVariable("id") int id, @RequestParam("status") String status, Model model) {
		try {
			adminStatusUpdateService.updateStatus(id, status);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error-page";
		}
		return "redirect:/service-details/" + id;

	}

}
