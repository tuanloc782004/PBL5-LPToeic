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
@RequestMapping("/part5-reading-exercise")
public class Part5ReadingExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private ReadingExerciseService readingExerciseService;

	@RequestMapping("")
	public String list(Model model, RedirectAttributes redirectAttributes) {
		try {
			List<ReadingExercise> list = this.readingExerciseService.findByPart5sIsNotEmpty();

			// Kiểm tra nếu danh sách không rỗng thì lấy phần tử đầu tiên
			ReadingExercise part5ReadingExercise = list.isEmpty() ? null : list.get(0);

			model.addAttribute("list", list);
			model.addAttribute("part5ReadingExercise", part5ReadingExercise);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 5: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện đọc phần 5!");
		}

		return "user/part5-reading-exercise";
	}

	@RequestMapping("/{id}")
	public String list(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			List<ReadingExercise> list = this.readingExerciseService.findByPart5sIsNotEmpty();

			ReadingExercise part5ReadingExercise = this.readingExerciseService.findById(id);

			model.addAttribute("list", list);
			model.addAttribute("part5ReadingExercise", part5ReadingExercise);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 5: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện đọc phần 5!");
		}

		return "user/part5-reading-exercise";
	}

}
