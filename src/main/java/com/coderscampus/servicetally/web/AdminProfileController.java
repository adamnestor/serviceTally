package com.coderscampus.servicetally.web;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.UsersRepository;

@Controller
@RequestMapping("/admin-profile")
public class AdminProfileController {

	private final UsersRepository usersRepo;
	private final AdminProfileService adminProfileService;

	public AdminProfileController(UsersRepository usersRepo, AdminProfileService adminProfileService) {
		this.usersRepo = usersRepo;
		this.adminProfileService = adminProfileService;
	}

	@GetMapping("/")
	public String adminProfile(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user = usersRepo.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not find user"));
			Optional<AdminProfile> adminProfile = adminProfileService.getOne(user.getUserId());
			
			if(!adminProfile.isEmpty())
				model.addAttribute("profile", adminProfile.get())
			
		}
		
		return "admin_profile";
	}

}
