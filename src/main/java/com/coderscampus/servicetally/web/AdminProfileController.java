package com.coderscampus.servicetally.web;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coderscampus.servicetally.domain.AdminProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.UsersRepository;
import com.coderscampus.servicetally.service.AdminProfileService;
import com.coderscampus.servicetally.util.FileUploadUtil;

import ch.qos.logback.core.util.StringUtil;

@Controller
@RequestMapping("/admin-profile")
public class AdminProfileController {

	private final UsersRepository usersRepo;
	private final AdminProfileService adminProfileService;

	@Autowired
	public AdminProfileController(UsersRepository usersRepo, AdminProfileService adminProfileService) {
		this.usersRepo = usersRepo;
		this.adminProfileService = adminProfileService;
	}

	@GetMapping("/")
	public String adminProfile(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user = usersRepo.findByEmail(currentUsername)
					.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			Optional<AdminProfile> adminProfile = adminProfileService.getOne(user.getUserId());

			if (!adminProfile.isEmpty())
				model.addAttribute("profile", adminProfile.get());

		}

		return "admin_profile";
	}

	public String addNew(AdminProfile adminProfile, @RequestParam("image") MultipartFile multipartFile, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user = usersRepo.findByEmail(currentUsername)
					.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			adminProfile.setUserId(user);
			adminProfile.setUserAccountId(user.getUserId());
		}
		model.addAttribute("profile", adminProfile);
		String fileName = "";
		if (!multipartFile.getOriginalFilename().equals("")) {
			fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
			adminProfile.setProfilePhoto(fileName);
		}
		AdminProfile savedUser = adminProfileService.addNew(adminProfile);
		
		String uploadDir = "photos/admin/" + savedUser.getUserAccountId();
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/dashboard/";
	}

}
