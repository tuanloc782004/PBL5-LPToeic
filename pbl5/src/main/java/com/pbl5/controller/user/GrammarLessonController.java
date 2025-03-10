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
import com.pbl5.markdown.MarkdownService;
import com.pbl5.model.GrammarLesson;
import com.pbl5.service.GrammarLessonService;

@Controller
@RequestMapping("/user/grammar-lesson")
public class GrammarLessonController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private MarkdownService markdownService;
	
	@Autowired
	private GrammarLessonService grammarLessonService;

	@RequestMapping("")
	public String list(Model model, RedirectAttributes redirectAttributes) {

		try {
			List<GrammarLesson> list = this.grammarLessonService.findAll();
			
			GrammarLesson grammarLesson = this.grammarLessonService.findFirstLesson();
			grammarLesson.setContent(this.markdownService.convertMarkdownToHtml(grammarLesson.getContent()));

			model.addAttribute("list", list);
			model.addAttribute("grammarLesson", grammarLesson);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học ngữ pháp: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài học ngữ pháp!");
		}

		return "user/grammar-lesson";
	}

	@RequestMapping("/{id}")
	public String list(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		try {
			List<GrammarLesson> list = this.grammarLessonService.findAll();
			
			GrammarLesson grammarLesson = this.grammarLessonService.findById(id);
			grammarLesson.setContent(this.markdownService.convertMarkdownToHtml(grammarLesson.getContent()));

			model.addAttribute("list", list);
			model.addAttribute("grammarLesson", grammarLesson);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học ngữ pháp: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài học ngữ pháp!");
		}

		return "user/grammar-lesson";
	}
	
}
