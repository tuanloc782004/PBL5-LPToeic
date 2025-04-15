package com.pbl5.controller.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private final Map<String, UserRegisterDTO> tempUsers = new ConcurrentHashMap<>();

	Long idOfUserRole = 2L;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new UserRegisterDTO());
		model.addAttribute("showRegister", true);
		return "login-form";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserRegisterDTO user, Model model) {
		
		// Kiểm tra email đã tồn tại
	    if (userService.findByEmail(user.getEmail()) != null) {
	        model.addAttribute("error", "Email đã tồn tại");
	        model.addAttribute("user", user);
	        model.addAttribute("showRegister", true);
	        return "login-form";
	    }

	    // Kiểm tra username đã tồn tại
	    if (userService.findByUsername(user.getUsername()) != null) {
	        model.addAttribute("error", "Username đã tồn tại");
	        model.addAttribute("user", user);
	        model.addAttribute("showRegister", true);
	        return "login-form";
	    }

	    // Kiểm tra mật khẩu nhập lại không trùng
	    if (!user.getPassword().equals(user.getConfirmPassword())) {
	        model.addAttribute("error", "Mật khẩu nhập lại không khớp");
	        model.addAttribute("user", user);
	        model.addAttribute("showRegister", true);
	        return "login-form";
	    }

		String otp = otpService.generateOtp(user.getEmail());
		tempUsers.put(user.getEmail(), user);
		sendOtpEmail(user.getEmail(), otp);

		model.addAttribute("email", user.getEmail());
		return "otp";
	}

	@PostMapping("/resend-otp")
	public String resendOtp(@RequestParam String email, Model model) {
		String otp = otpService.generateOtp(email);
		sendOtpEmail(email, otp);

		model.addAttribute("email", email);
		model.addAttribute("resendSuccess", true);
		return "otp";
	}

	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam String email, @RequestParam String otp, Model model, RedirectAttributes redirectAttributes) {
		if (!otpService.verifyOtp(email, otp)) {
			model.addAttribute("email", email);
			model.addAttribute("error", "Mã OTP không đúng");
			return "otp";
		}

		UserRegisterDTO dto = tempUsers.get(email);
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

		Role role = this.roleService.findById(idOfUserRole);
		user.setRole(role);

		userService.save(user);

		otpService.clearOtp(email);
		tempUsers.remove(email);

		redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
		return "redirect:/login";
	}

	private void sendOtpEmail(String email, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Mã OTP xác thực");
		message.setText("Mã OTP của bạn là: " + otp);
		mailSender.send(message);
	}
}
