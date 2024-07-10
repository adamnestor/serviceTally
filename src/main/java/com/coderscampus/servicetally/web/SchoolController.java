package com.coderscampus.servicetally.web;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.School;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.UsersRepository;
import com.coderscampus.servicetally.service.AdminProfileService;
import com.coderscampus.servicetally.service.SchoolService;

@Controller
@RequestMapping("/school")
public class SchoolController {

	private final SchoolService schoolService;
	private final AdminProfileService adminProfileService;
	private final UsersRepository usersRepo;

	public SchoolController(SchoolService schoolService, AdminProfileService adminProfileService,
			UsersRepository usersRepo) {
		this.schoolService = schoolService;
		this.adminProfileService = adminProfileService;
		this.usersRepo = usersRepo;
	}

	@GetMapping("/")
	public String manageSchools(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user = usersRepo.findByEmail(currentUsername)
					.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			Optional<AdminProfile> adminProfile = adminProfileService.getOne(user.getUserId());

			if (adminProfile.isPresent()) {
				School adminSchool = adminProfile.get().getSchool();
				model.addAttribute("school", adminSchool);
			} else {
				return "redirect:/dashboard/";
			}
		} else {
			return "redirect:/login";
		}
		return "school-details";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("school", new School());
		return "school-create";
	}

	@PostMapping("/create")
	public String createSchool(@ModelAttribute("school") School school) {
		School savedSchool = schoolService.createOrUpdateSchool(school);
		return "redirect:/school/";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model) {
		Optional<School> school = schoolService.getOne(id);
		school.ifPresent(model::addAttribute);
		return "school-edit";
	}

	@PostMapping("/edit/{id}")
	public String updateSchool(@PathVariable("id") Integer id, @ModelAttribute("school") School school) {
		school.setSchoolId(id);
		School updatedSchool = schoolService.createOrUpdateSchool(school);
		return "redirect:/school/";
	}

}
