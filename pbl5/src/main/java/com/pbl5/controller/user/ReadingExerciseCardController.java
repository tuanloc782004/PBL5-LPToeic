package com.pbl5.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/reading-exercise-card")
public class ReadingExerciseCardController {
	
	@RequestMapping("")
	public String listening () {
		return "user/reading-exercise-card"; 
	}
	
}