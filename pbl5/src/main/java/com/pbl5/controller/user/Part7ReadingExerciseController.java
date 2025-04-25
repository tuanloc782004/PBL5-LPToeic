package com.pbl5.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.controller.admin.UserAdminController;
import com.pbl5.model.ReadingExercise;
import com.pbl5.service.ReadingExerciseService;

@Controller
@RequestMapping("/user/part7-reading-exercise")
public class Part7ReadingExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private ReadingExerciseService readingExerciseService;

	@RequestMapping("")
	public String list(Model model, RedirectAttributes redirectAttributes) {
		try {
			List<ReadingExercise> list = this.readingExerciseService.findByPart7sIsNotEmpty();

			// Kiểm tra nếu danh sách không rỗng thì lấy phần tử đầu tiên
			ReadingExercise part7ReadingExercise = list.isEmpty() ? null : list.get(0);

			model.addAttribute("list", list);
			model.addAttribute("part7ReadingExercise", part7ReadingExercise.getPart7s().get(0));

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 7: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện đọc phần 7!");
		}

		return "user/part7-reading-exercise";
	}

	@RequestMapping("/{id}")
	public String list(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			List<ReadingExercise> list = this.readingExerciseService.findByPart7sIsNotEmpty();

			ReadingExercise part7ReadingExercise = this.readingExerciseService.findById(id);

			model.addAttribute("list", list);
			model.addAttribute("part7ReadingExercise", part7ReadingExercise.getPart7s().get(0));

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 7: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện đọc phần 7!");
		}

		return "user/part7-reading-exercise";
	}

}
