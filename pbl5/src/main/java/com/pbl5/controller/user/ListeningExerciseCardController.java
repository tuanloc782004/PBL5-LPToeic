package com.pbl5.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/listening-exercise-card")
public class ListeningExerciseCardController {
	
	@RequestMapping("")
	public String listening () {
		return "user/listening-exercise-card"; 
	}
	
}