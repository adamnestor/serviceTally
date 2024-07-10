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
import com.coderscampus.servicetally.service.UsersService;

@Controller
@RequestMapping("/school")
public class SchoolController {

	private final SchoolService schoolService;
	private final UsersService usersService;
	private final AdminProfileService adminProfileService;
	private final UsersRepository usersRepo;

	public SchoolController(SchoolService schoolService, UsersService usersService,
			AdminProfileService adminProfileService, UsersRepository usersRepo) {
		this.schoolService = schoolService;
		this.usersService = usersService;
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
		model.addAttribute("user", usersService.getCurrentUserProfile());
		return "school-create";
	}

	@PostMapping("/create")
	public String createSchool(School school, Model model) {
		
		Users user = usersService.getCurrentUser();
		if (user != null) {
			school.setPostedById(user);
			model.addAttribute("school", school);
			School savedSchool = schoolService.createOrUpdateSchool(school);
			return "redirect:/school/";
		}
	}

	@PostMapping("/edit/{id}")
	public String editSchool(@PathVariable("id") Integer id, Model model) {
		School school = schoolService.getOne(id);
		model.addAttribute("school", school);
		model.addAttribute("user", usersService.getCurrentUserProfile());
	}

}
