package com.pbl5.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/mock-exam")
public class MockExamController {
	
	@RequestMapping("")
	public String cardTests () {
		return "user/mock-exam"; 
	}

}
