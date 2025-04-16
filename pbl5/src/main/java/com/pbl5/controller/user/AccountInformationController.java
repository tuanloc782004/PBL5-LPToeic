package com.pbl5.controller.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import java.nio.file.Path;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import com.pbl5.security.CustomUserDetails;

import com.pbl5.model.User;
import com.pbl5.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import com.pbl5.security.CustomUserDetails;

import com.pbl5.model.User;
import com.pbl5.service.UserService;

@Controller
@RequestMapping("/user/account-information")
public class AccountInformationController {

	@Autowired
	private UserService userService;

	@RequestMapping("")
	public String UserProfile() {
		return "user/account-information";
	}

	@PostMapping("update")
	public String updateUserInfo(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam(value = "avatarUrl", required = false) String avatarUrl,
			RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = auth.getName();

		User currentUser = userService.findByUsername(currentUsername);

		if (currentUser != null) {
			currentUser.setUsername(username);
			currentUser.setEmail(email);
			if (avatarUrl != null && !avatarUrl.isEmpty()) {
				currentUser.setAvatarUrl(avatarUrl);
			}

			userService.save(currentUser);

			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			CustomUserDetails newUserDetails = new CustomUserDetails(currentUser, authorities);

			Authentication newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, auth.getCredentials(),
					newUserDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(newAuth);

			redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công!");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng!");
		}

		return "redirect:/user/account-information";
	}

	@PostMapping("/update-avatar")
	public String updateAvatar(@RequestParam("avatarFile") MultipartFile avatarFile,
			RedirectAttributes redirectAttributes) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUsername(username);

		if (user != null && !avatarFile.isEmpty()) {
			try {

				String uploadDir = "src/main/resources/static/upload-dir/avatar/"; 

				File uploadPath = new File(uploadDir);
				if (!uploadPath.exists()) {
				    uploadPath.mkdirs();
				}

				String filename = System.currentTimeMillis() + "_" + avatarFile.getOriginalFilename();
				Path filePath = Paths.get(uploadDir, filename);

				Files.copy(avatarFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

				String oldAvatarUrl = user.getAvatarUrl();
				if (oldAvatarUrl != null && !oldAvatarUrl.equals("/upload-dir/avatar.jpg")) {
				    File oldFile = new File("src/main/resources/static" + oldAvatarUrl);
				    if (oldFile.exists()) {
				        oldFile.delete();
				    }
				}

				user.setAvatarUrl("/upload-dir/avatar/" + filename);
				userService.save(user);

				redirectAttributes.addFlashAttribute("successMessage", "Avatar cập nhật thành công!");

			} catch (IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi lưu avatar.");
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng hoặc file rỗng.");
		}

		return "redirect:/user/account-information";
	}
}
