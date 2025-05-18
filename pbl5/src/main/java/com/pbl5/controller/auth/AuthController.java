package com.pbl5.controller.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.dto.UserRegisterDTO;
import com.pbl5.model.Role;
import com.pbl5.model.User;
import com.pbl5.service.OtpService;
import com.pbl5.service.RoleService;
import com.pbl5.service.UserService;

@Controller
@RequestMapping
public class AuthController {

	@Autowired
	private OtpService otpService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	Long idOfUserRole = 2L; // Default user role

	// Trang đăng ký
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new UserRegisterDTO());
		model.addAttribute("showRegister", true);
		return "login-form";
	}

	// Xử lý đăng ký người dùng
	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserRegisterDTO user, Model model, HttpSession session) {
		if (userService.findByEmail(user.getEmail()) != null) {
			model.addAttribute("error", "Email đã tồn tại");
			model.addAttribute("showRegister", true);
			return "login-form";
		}

		if (userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("error", "Username đã tồn tại");
			model.addAttribute("showRegister", true);
			return "login-form";
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			model.addAttribute("error", "Mật khẩu nhập lại không khớp");
			model.addAttribute("showRegister", true);
			return "login-form";
		}

		String otp = otpService.generateOtp(user.getEmail());
		sendOtpEmail(user.getEmail(), otp);

		session.setAttribute("otp", otp);
		session.setAttribute("registerUser", user);
		session.setAttribute("registerEmail", user.getEmail());
		session.setAttribute("otpStartTime", System.currentTimeMillis());
		session.setAttribute("otpAttempts", 0);
		session.setAttribute("actionType", "register"); // Đánh dấu chức năng đăng ký

		model.addAttribute("email", user.getEmail());
		model.addAttribute("remainingAttempts", 3);
		model.addAttribute("remainingTime", 60);

		return "otp"; // Điều hướng đến trang OTP
	}

	// Gửi lại OTP
	@PostMapping("/resend-otp")
	public String resendOtp(HttpSession session, Model model) {
		String email = (String) session.getAttribute("registerEmail");
		String actionType = (String) session.getAttribute("actionType");

		if (email == null) {
			return "redirect:/register";
		}

		String otp = otpService.generateOtp(email);
		session.setAttribute("otp", otp);
		sendOtpEmail(email, otp);

		model.addAttribute("email", email);
		model.addAttribute("remainingTime", 60);
		model.addAttribute("remainingAttempts", 3);
		model.addAttribute("resendSuccess", true);

		if ("forgot-password".equals(actionType)) {
			return "new-password-form"; // Điều hướng lại trang OTP cho chức năng quên mật khẩu
		} else {
			return "otp"; // Điều hướng lại trang OTP cho chức năng đăng ký
		}
	}

	// Xác minh OTP
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam String otp, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		String email = (String) session.getAttribute("email"); // Lấy email từ session
		String actionType = (String) session.getAttribute("actionType"); // Lấy actionType từ session
		Integer attempts = (Integer) session.getAttribute("otpAttempts");
		if (attempts == null)
			attempts = 0;

		boolean isValid = otpService.verifyOtp(email, otp); // Kiểm tra OTP
		if (!isValid) {
			attempts++;
			session.setAttribute("otpAttempts", attempts);

			if (attempts >= 3) {
				session.invalidate();
				redirectAttributes.addFlashAttribute("error", "Bạn đã nhập sai 3 lần. Hãy thử lại.");
				return "redirect:/register"; // Quay lại trang đăng ký nếu nhập sai 3 lần
			}

			model.addAttribute("email", email);
			model.addAttribute("error", "Mã OTP không đúng hoặc đã hết hạn.");
			model.addAttribute("remainingAttempts", 3 - attempts);
			return "otp"; // Quay lại trang nhập OTP nếu mã OTP không đúng
		}

		// Kiểm tra actionType và điều hướng người dùng
		if ("forgot-password".equals(actionType)) {
			return "new-password-form"; // Điều hướng đến trang nhập mật khẩu mới
		} else {
			// Tiến hành tạo tài khoản mới cho chức năng đăng ký
			UserRegisterDTO dto = (UserRegisterDTO) session.getAttribute("registerUser");
			User user = new User();
			user.setUsername(dto.getUsername());
			user.setEmail(dto.getEmail());
			user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
			user.setAvatarUrl("/upload-dir/avatar/default-avatar.jpg");

			Role role = roleService.findById(idOfUserRole);
			user.setRole(role);
			userService.save(user);

			otpService.clearOtp(email);
			session.invalidate();

			redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
			return "redirect:/login"; // Quay về trang đăng nhập sau khi đăng ký thành công
		}
	}

	// Trang quên mật khẩu
	@GetMapping("/forgot-password")
	public String showForgotPasswordForm() {
		return "forgot-password"; // Tạo view cho trang quên mật khẩu
	}

	// Xử lý quên mật khẩu (POST)
	@PostMapping("/forgot-password") // Đổi URL để tránh trùng với GET
	public String handleForgotPassword(@RequestParam("email") String email, HttpSession session, Model model) {
		User user = userService.findByEmail(email);
		if (user == null) {
			model.addAttribute("error", "Email không tồn tại");
			return "forgot-password";
		}

		String otp = otpService.generateOtp(email); // Tạo OTP
		sendOtpEmail(email, otp); // Gửi OTP qua email

		session.setAttribute("otp", otp);
		session.setAttribute("email", email);
		session.setAttribute("otpStartTime", System.currentTimeMillis());
		session.setAttribute("actionType", "forgot-password"); // Đánh dấu chức năng quên mật khẩu

		model.addAttribute("email", email);

		return "otp"; // Chuyển người dùng tới trang OTP
	}

	// Xác minh OTP cho quên mật khẩu (POST)
	// Xác minh OTP cho quên mật khẩu (POST)
//    @PostMapping("/verify-otp-for-reset")
//    public String verifyOtpForReset(@RequestParam String otp, 
//                                     @ModelAttribute ChangePasswordDTO changePasswordDTO, 
//                                     HttpSession session, 
//                                     Model model) {
//
//        String email = (String) session.getAttribute("email");
//
//        // Kiểm tra OTP
//        boolean isValid = otpService.verifyOtp(email, otp);
//        if (!isValid) {
//            model.addAttribute("error", "Mã OTP không đúng hoặc đã hết hạn.");
//            return "otp";  // Nếu OTP không hợp lệ, quay lại trang OTP
//        }
//
//        // Kiểm tra sự khớp của mật khẩu mới và mật khẩu xác nhận
//        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
//            model.addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
//            return "new-password-form";  // Nếu mật khẩu mới và xác nhận mật khẩu không khớp, quay lại trang nhập mật khẩu
//        }
//
//        // Mã hóa và lưu mật khẩu mới
//        User user = userService.findByEmail(email);
//        if (user != null) {
//            // Mã hóa mật khẩu mới
//            String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
//            user.setPassword(encodedPassword);  // Cập nhật mật khẩu mới
//            userService.save(user);  // Lưu người dùng với mật khẩu mới vào cơ sở dữ liệu
//
//            // Xóa OTP đã sử dụng
//            otpService.clearOtp(email);
//
//            // Hủy session và thông báo thành công
//            session.invalidate();
//            model.addAttribute("success", "Mật khẩu đã được thay đổi thành công!");
//            return "login-form";  // Chuyển người dùng về trang đăng nhập
//        }
//
//        session.invalidate();  // Hủy session nếu có lỗi xảy ra
//        model.addAttribute("error", "Có lỗi xảy ra, vui lòng thử lại.");
//        return "forgot-password";  // Trở lại trang quên mật khẩu nếu có lỗi
//    }
//
	@PostMapping("/update-password-forgot")
	public String updatePasswordForForgot(@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword, RedirectAttributes redirectAttributes,
			HttpSession session) {
		// Lấy email người dùng từ session sau khi xác minh OTP
		String email = (String) session.getAttribute("email");
		User currentUser = userService.findByEmail(email);

		if (currentUser == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng với email này.");
			return "redirect:/forgot-password"; // Quay lại trang quên mật khẩu nếu không tìm thấy người dùng
		}

		// Kiểm tra mật khẩu mới và xác nhận mật khẩu
		if (!newPassword.equals(confirmPassword)) {
			redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
			return "redirect:/user/account-information?changepass";
		}

		// Cập nhật mật khẩu mới
		currentUser.setPassword(passwordEncoder.encode(newPassword)); // Mã hóa mật khẩu mới
		userService.save(currentUser); // Lưu mật khẩu mới vào cơ sở dữ liệu

		redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu đã được cập nhật thành công.");
		return "redirect:/login"; // Điều hướng về trang đăng nhập sau khi thay đổi mật khẩu thành công
	}

	// Gửi OTP qua email
	private void sendOtpEmail(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Mã OTP xác thực");
		message.setText("Mã OTP của bạn là: " + otp);
		mailSender.send(message);
	}
}