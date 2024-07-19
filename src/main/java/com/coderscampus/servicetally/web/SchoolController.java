package com.coderscampus.servicetally.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.AdminProfileRepository;
import com.coderscampus.servicetally.service.AdminProfileService;
import com.coderscampus.servicetally.service.SchoolService;
import com.coderscampus.servicetally.service.UsersService;

@Controller
@RequestMapping("/schools")
public class SchoolController {

	private final SchoolService schoolService;
	private final AdminProfileService adminProfileService;
	private final UsersService usersService;

	public SchoolController(SchoolService schoolService, AdminProfileService adminProfileService,
			UsersService usersService) {
		this.schoolService = schoolService;
		this.adminProfileService = adminProfileService;
		this.usersService = usersService;
	}

	@GetMapping("/")
	public String manageSchools(Model model) {
		Object currentUserProfile = usersService.getCurrentUserProfile();
		String currentUserEmail = usersService.getCurrentUser().getEmail();
		AdminProfile adminProfile = adminProfileService.getAdminProfileByUserEmail(currentUserEmail);
		List<School> schools = schoolService.getSchoolsByAdmin(adminProfile);

		model.addAttribute("user", currentUserProfile);
		model.addAttribute("adminProfile", adminProfile);
		model.addAttribute("schools", schools);
		return "school-dash";
	}

	@GetMapping("/new")
	public String createSchoolForm(Model model) {
		model.addAttribute("school", new School());
		return "school-form";
	}

	@PostMapping("/new")
	public String createSchool(School school) {
		Users currentUser = usersService.getCurrentUser();
		AdminProfile adminProfile = adminProfileService.getByEmail(currentUser.getEmail());
		school.setSchoolAdminId(adminProfile);
		schoolService.saveSchool(school);
		return "redirect:/schools/";
	}

	@GetMapping("/{id}")
	public String viewSchoolDetails(@PathVariable("id") Integer id, Model model) {
		Object currentUserProfile = usersService.getCurrentUserProfile();
		School school = schoolService.getSchoolById(id);
		if (school == null) {
			return "redirect:/schools";
		}
		model.addAttribute("school", school);
		model.addAttribute("user", currentUserProfile);
		return "school-details";
	}

	@PostMapping("/delete/{id}")
	public String deleteSchool(@PathVariable("id") Integer id) {
		schoolService.deleteSchool(id);
		return "redirect:/schools/";
	}

	@GetMapping("/edit/{id}")
	public String editSchool(@PathVariable("id") Integer id, Model model) {
		School school = schoolService.getSchoolById(id);
		if (school == null) {
			return "redirect:/schools";
		}
		model.addAttribute("school", school);
		return "school-form";
	}

	@PostMapping("/edit/{id}")
	public String postEditSchool(@PathVariable("id") Integer id, School school) {
		school.setSchoolId(id);
		Users currentUser = usersService.getCurrentUser();
		AdminProfile adminProfile = adminProfileService.getByEmail(currentUser.getEmail());
		school.setSchoolAdminId(adminProfile);
		schoolService.saveSchool(school);

		return "redirect:/schools/" + school.getSchoolId();
	}

}
