package com.coderscampus.servicetally.web;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coderscampus.servicetally.domain.StudentProfile;
import com.coderscampus.servicetally.domain.Users;
import com.coderscampus.servicetally.repository.UsersRepository;
import com.coderscampus.servicetally.service.StudentProfileService;
import com.coderscampus.servicetally.util.FileUploadUtil;

@Controller
@RequestMapping("/student-profile")
public class StudentProfileController {

	private final UsersRepository usersRepo;
	private final StudentProfileService studentProfileService;

	public StudentProfileController(UsersRepository usersRepo, StudentProfileService studentProfileService) {
		this.usersRepo = usersRepo;
		this.studentProfileService = studentProfileService;
	}

	@GetMapping("/")
	public String studentProfile(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user = usersRepo.findByEmail(currentUsername)
					.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			Optional<StudentProfile> studentProfile = studentProfileService.getOne(user.getUserId());

			if (!studentProfile.isEmpty())
				model.addAttribute("studentProfile", studentProfile);

		}
		return "student-profile";
	}

	@PostMapping("/addNew")
	public String addNew(StudentProfile studentProfile, @RequestParam("image") MultipartFile multipartFile,
			Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user = usersRepo.findByEmail(currentUsername)
					.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			studentProfile.setUserId(user);
			studentProfile.setUserAccountId(user.getUserId());
		}
		model.addAttribute("profile", studentProfile);

		String fileName = "";
		if (!multipartFile.getOriginalFilename().equals("")) {
			fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
			studentProfile.setProfilePhoto(fileName);
		}
		StudentProfile savedUser = studentProfileService.addNew(studentProfile);
		
		String uploadDir = "photos/student/" + savedUser.getUserAccountId();
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "redirect:/dashboard/";
	}

}