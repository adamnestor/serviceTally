package com.coderscampus.servicetally.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coderscampus.servicetally.domain.ServiceEventActivity;
import com.coderscampus.servicetally.service.ServiceEventActivityService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
public class AdminStatusUpdateController {

	private final ServiceEventActivityService serviceEventActivityService;
	private final UsersService usersService;
	
	@Autowired
	public AdminStatusUpdateController(ServiceEventActivityService serviceEventActivityService,
			UsersService usersService) {
		this.serviceEventActivityService = serviceEventActivityService;
		this.usersService = usersService;
	}
	
	@GetMapping("service-details/{id}")
	public String display(@PathVariable("id") int id, Model model) {
		ServiceEventActivity serviceDetails = serviceEventActivityService.getOne(id);
		
		model.addAttribute("serviceDetails", serviceDetails);
		model.addAttribute("user", usersService.getCurrentUserProfile());
		
		return "service-event-details";
	}
	
}
