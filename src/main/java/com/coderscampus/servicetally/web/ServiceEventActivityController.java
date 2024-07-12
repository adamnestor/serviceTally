package com.coderscampus.servicetally.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.servicetally.domain.ServiceEventActivity;
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

	@PostMapping("/dashboard/addNew")
	public String addNew(ServiceEventActivity serviceEventActivity, Model model) {

		Users user = usersService.getCurrentUser();
		if (user != null) {
			serviceEventActivity.setPostedById(user);
		}
		model.addAttribute("serviceEventActivity", serviceEventActivity);
		serviceEventActivityService.addNew(serviceEventActivity);
		return "redirect:/dashboard/";
	}

}
