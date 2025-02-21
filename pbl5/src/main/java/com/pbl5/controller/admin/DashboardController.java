package com.pbl5.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {

	@RequestMapping("")
	public String home () {
		return "admin/home"; 
	}
}
