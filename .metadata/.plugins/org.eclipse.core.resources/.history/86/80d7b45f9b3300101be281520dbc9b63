package com.pbl5.controller.user;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.controller.admin.UserAdminController;
import com.pbl5.model.MockExam;
import com.pbl5.model.TestResult;
import com.pbl5.model.User;
import com.pbl5.service.MockExamService;
import com.pbl5.service.TestResultService;
import com.pbl5.service.UserService;

@Controller
@RequestMapping("/user/mock-exam")
public class MockExamController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private MockExamService mockExamService;

	@Autowired
	private UserService userService;

	@Autowired
	private TestResultService testResultService;

	@RequestMapping("/{id}")
	public String mockExam(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			MockExam mockExam = this.mockExamService.findById(id);

			model.addAttribute("mockExam", mockExam);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài thi thử!");
		}

		return "user/mock-exam";
	}

	@PostMapping("/save-score")
	public String saveTestResult(@RequestParam("correct") Long correct, @RequestParam("wrong") Long wrong,
			@RequestParam("mockExamId") Long mockExamId, RedirectAttributes redirectAttributes) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = auth.getName();

			User user = userService.findByUsername(currentUsername);

			MockExam mockExam = this.mockExamService.findById(mockExamId);

			TestResult result = new TestResult();
			result.setUser(user);
			result.setMockExam(mockExam);
			result.setCorrectAnswers(correct);
			result.setIncorrectAnswers(wrong);
			result.setCreatedAt(LocalDateTime.now());

			this.testResultService.save(result);

			return "redirect:/user/account/history-test";
		} catch (Exception e) {
			logger.error("Lỗi khi lưu kết quả bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi lưu kết quả bài thi thử!");
			return "redirect:/user/mock-exam/" + mockExamId;
		}
	}

}
