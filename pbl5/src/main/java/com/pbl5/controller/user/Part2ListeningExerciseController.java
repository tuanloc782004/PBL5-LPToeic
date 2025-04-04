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
import com.pbl5.model.ListeningExercise;
import com.pbl5.service.ListeningExerciseService;

@Controller
@RequestMapping("/user/part2-listening-exercise")
public class Part2ListeningExerciseController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private ListeningExerciseService listeningExerciseService;

	@RequestMapping("")
	public String list(Model model, RedirectAttributes redirectAttributes) {
		try {
			List<ListeningExercise> list = this.listeningExerciseService.findByPart2sIsNotEmpty();

			// Kiểm tra nếu danh sách không rỗng thì lấy phần tử đầu tiên
			ListeningExercise part2ListeningExercise = list.isEmpty() ? null : list.get(0);

			model.addAttribute("list", list);
			model.addAttribute("part2ListeningExercise", part2ListeningExercise);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 2: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách bài luyện nghe phần 2!");
		}

		return "user/part2-listening-exercise";
	}

	@RequestMapping("/{id}")
	public String list(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			List<ListeningExercise> list = this.listeningExerciseService.findByPart2sIsNotEmpty();

			ListeningExercise part2ListeningExercise = this.listeningExerciseService.findById(id);

			model.addAttribute("list", list);
			model.addAttribute("part2ListeningExercise", part2ListeningExercise);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 2: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi khi tải danh sách danh sách bài luyện nghe phần 2!");
		}

		return "user/part2-listening-exercise";
	}

}
