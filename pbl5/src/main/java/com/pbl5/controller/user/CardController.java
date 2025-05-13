package com.pbl5.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.controller.admin.UserAdminController;
import com.pbl5.model.MockExam;
import com.pbl5.service.MockExamService;

@Controller
@RequestMapping("")
public class CardController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private MockExamService mockExamService;

	@RequestMapping("mock-exam-card")
	public String cardMockExam(Model model, RedirectAttributes redirectAttributes) {
		
		try {
			List<MockExam> list = this.mockExamService.findAll();
			
			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài thi thử!");
		}
		return "user/mock-exam-card";
	}

	@RequestMapping("listening-exercise-card")
	public String cardListeningExercise() {
		return "user/listening-exercise-card";
	}

	@RequestMapping("reading-exercise-card")
	public String cardReadingExercise() {
		return "user/reading-exercise-card";
	}

}
