package com.pbl5.controller.user;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.pbl5.security.CustomUserDetails;
import com.pbl5.controller.admin.UserAdminController;
import com.pbl5.model.TestResult;
import com.pbl5.model.User;
import com.pbl5.service.TestResultService;
import com.pbl5.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/account")
public class AccountInformationController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private TestResultService testResultService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
	@GetMapping("/information")
    public String getAccountInformation(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        
        User currentUser = userService.findByUsername(currentUsername);
        
        if (currentUser != null) {
            model.addAttribute("currentUserUsername", currentUser.getUsername());
            model.addAttribute("currentUserEmail", currentUser.getEmail());
            model.addAttribute("currentUserAvatarUrl", currentUser.getAvatarUrl()); 
        }
        
        return "user/account/information"; 
    }
	
	@PostMapping("/information/update")
	public String updateUserInfo(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam(value = "avatarUrl", required = false) String avatarUrl,
			RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = auth.getName();

		User currentUser = userService.findByUsername(currentUsername);

		if (currentUser != null) {
			currentUser.setUsername(username);
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

		return "redirect:/user/account/information";
	}

	@PostMapping("/information/update-avatar")
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

		return "redirect:/user/account/information";
	}
	
	@GetMapping("/change-password")
	public String showChangePasswordForm(HttpServletRequest request, Model model) {
	    request.setAttribute("requestURI", "/user/account/change-password");
	    return "user/account/change-password";  
	}

	
	// @PostMapping("/change-password")
	// public String updatePassword(@RequestParam("oldPassword") String oldPassword,
	// 		@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
	// 		RedirectAttributes redirectAttributes) {
	// 	System.out.println(">>> Đã nhận request đổi mật khẩu <<<");

	// 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	// 	String currentUsername = auth.getName();
	// 	User currentUser = userService.findByUsername(currentUsername);

	// 	if (!passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
	// 		redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu cũ không đúng.");
	// 		return "redirect:/user/account/change-password";
	// 	}

	// 	if (!newPassword.equals(confirmPassword)) {
	// 		redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
	// 		return "redirect:/user/account/change-password";
	// 	}

	// 	currentUser.setPassword(passwordEncoder.encode(newPassword));
	// 	userService.save(currentUser);

	// 	redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu đã được cập nhật thành công.");
	// 	return "redirect:/user/account/change-password";
	// }

	@PostMapping("/change-password")
public String updatePassword(@RequestParam("oldPassword") String oldPassword,
	@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
	RedirectAttributes redirectAttributes) {
	System.out.println(">>> Đã nhận request đổi mật khẩu <<<");

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String currentUsername = auth.getName();
	User currentUser = userService.findByUsername(currentUsername);

	// Kiểm tra mật khẩu cũ
	if (!passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
		redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu cũ không đúng.");
		return "redirect:/user/account/change-password";
	}

	// Kiểm tra mật khẩu mới và xác nhận mật khẩu có khớp không
	if (!newPassword.equals(confirmPassword)) {
		redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
		return "redirect:/user/account/change-password";
	}

	// Cập nhật mật khẩu mới
	currentUser.setPassword(passwordEncoder.encode(newPassword));
	userService.save(currentUser);

	// Thêm thông báo thành công
	redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu đã được cập nhật thành công.");
	return "redirect:/user/account/change-password";
}


	@RequestMapping("/history-test")
	public String cardMockExam(Model model, RedirectAttributes redirectAttributes) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = auth.getName();

			User user = this.userService.findByUsername(currentUsername);

			List<TestResult> list = this.testResultService.findByUser(user);

			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách kết quả bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải kết quả danh sách bài thi thử!");
		}
		return "user/account/history-test";
	}


}
