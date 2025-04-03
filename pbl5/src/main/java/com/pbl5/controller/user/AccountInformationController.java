package com.pbl5.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/account-information")
public class AccountInformationController {
	
	@RequestMapping("")
	public String UserProfile  () {
		return "user/account-information"; 
	}

}
