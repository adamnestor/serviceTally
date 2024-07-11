package com.coderscampus.servicetally.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.service.SchoolService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
@RequestMapping("/school")
public class SchoolController {

	private final SchoolService schoolService;
	private final UsersService usersService;
	
	public SchoolController(SchoolService schoolService, UsersService usersService) {
		this.schoolService = schoolService;
		this.usersService = usersService;
	}
	
	@GetMapping("/schools")
	public String showSchools(Model model) {
		Users loggedInUser = usersService.getCurrentUser();
		if (loggedInUser != null) {
			List<School> schools = schoolService.getSchoolsByAdmin(loggedInUser);
			model.addAttribute("schools", schools);
		} else {
			model.addAttribute("error", "User not authenticated");
		}
		return "school-dash";
	}
	
	@PostMapping("/schools")
	public String createSchool(@ModelAttribute School school) {
		Users loggedInUser = usersService.getCurrentUser();
		if (loggedInUser != null) {
			school.setSchoolAdminId(loggedInUser);
			schoolService.createOrUpdateSchool(school);
		} else {
			return "redirect:/login";
		}
		return "redirect:/schools";
	}
	
	@GetMapping("/schools/{id}")
	public String showSchoolDetails(@PathVariable Integer id, Model model) {
		School school = schoolService.getSchoolById(id);
		model.addAttribute("school", school);
		return "school-details";
	}
	
	@GetMapping("/schools/{id}/edit")
	public String showEditSchoolForm(@PathVariable Integer id, Model model) {
		School school = schoolService.getSchoolById(id);
		model.addAttribute("school", school);
		return "school-form";
	}
	
	@PostMapping("/schools/{id}/edit")
	public String updateSchool(@PathVariable Integer id, @ModelAttribute School school) {
		school.setSchoolId(id);
		schoolService.createOrUpdateSchool(school);
		return "redirect:/schools";
	}
	
	@PostMapping("schools/{id}/delete")
	public String deleteSchool(@PathVariable Integer id) {
		schoolService.deleteSchool(id);
		return "redirect:/schools";
	}
	
	
	
	
	
	
}
