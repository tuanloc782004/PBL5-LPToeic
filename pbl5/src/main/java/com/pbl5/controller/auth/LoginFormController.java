package com.pbl5.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pbl5.dto.UserRegisterDTO;

@Controller
@RequestMapping
public class LoginFormController {
	
	@GetMapping("/login")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new UserRegisterDTO());
		return "login-form";
	}

}
