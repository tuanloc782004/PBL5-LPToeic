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
import com.pbl5.model.Part6;
import com.pbl5.service.Part6Service;

@Controller
@RequestMapping("/user/part6-reading-exercise")
public class Part6ReadingExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private Part6Service part6Service;

	@RequestMapping("")
	public String list(Model model, RedirectAttributes redirectAttributes) {
		try {
			List<Part6> list = this.part6Service.findAll();

			// Kiểm tra nếu danh sách không rỗng thì lấy phần tử đầu tiên
			Part6 part6ReadingExercise = list.isEmpty() ? null : list.get(0);

			model.addAttribute("list", list);
			model.addAttribute("part6ReadingExercise", part6ReadingExercise);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 6: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện đọc phần 6!");
		}

		return "user/part6-reading-exercise";
	}

	@RequestMapping("/{id}")
	public String list(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			List<Part6> list = this.part6Service.findAll();

			Part6 part6ReadingExercise = this.part6Service.findById(id);

			model.addAttribute("list", list);
			model.addAttribute("part6ReadingExercise", part6ReadingExercise);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 6: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện đọc phần 6!");
		}

		return "user/part6-reading-exercise";
	}

}
