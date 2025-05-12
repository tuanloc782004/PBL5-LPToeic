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
import com.pbl5.model.VocabularyLesson;
import com.pbl5.service.VocabularyLessonService;

@Controller
@RequestMapping("/vocabulary-lesson")
public class VocabularyLessonController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private VocabularyLessonService vocabularyLessonService;
	
	@RequestMapping("")
	public String list(Model model, RedirectAttributes redirectAttributes) {

		try {
			List<VocabularyLesson> list = this.vocabularyLessonService.findAll();
			
			VocabularyLesson vocabularyLesson = this.vocabularyLessonService.findFirstLesson();

			model.addAttribute("list", list);
			model.addAttribute("vocabularyLesson", vocabularyLesson);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học từ vựng: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài học từ vựng!");
		}

		return "user/vocabulary-lesson";
	}

	@RequestMapping("/{id}")
	public String list(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			List<VocabularyLesson> list = this.vocabularyLessonService.findAll();
			
			VocabularyLesson vocabularyLesson = this.vocabularyLessonService.findById(id);

			model.addAttribute("list", list);
			model.addAttribute("vocabularyLesson", vocabularyLesson);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học từ vựng: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài học từ vựng!");
		}

		return "user/vocabulary-lesson";
	}
}
