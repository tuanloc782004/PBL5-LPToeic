package com.pbl5.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbl5.dto.UserRegisterDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class LoginFormController {

	@GetMapping("/login")
	public String showRegisterForm(@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request, Model model) {

		if (logout != null) {
			model.addAttribute("successMessage", "Đăng xuất thành công!");
		}

		String errorMessage = (String) request.getSession().getAttribute("errorMessage");
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			// Xóa khỏi session sau khi dùng
			request.getSession().removeAttribute("errorMessage");
		}

		model.addAttribute("user", new UserRegisterDTO());
		return "login-form";
	}

}
