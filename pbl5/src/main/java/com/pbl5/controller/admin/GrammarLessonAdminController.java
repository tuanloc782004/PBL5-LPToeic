package com.pbl5.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.markdown.MarkdownService;
import com.pbl5.model.GrammarLesson;
import com.pbl5.service.GrammarLessonService;

@Controller
@RequestMapping("/admin/grammar-lesson")
public class GrammarLessonAdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private GrammarLessonService grammarLessonService;

	@Autowired
	private MarkdownService markdownService;

	@RequestMapping("")
	public String list(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {

		try {
			Page<GrammarLesson> list = this.grammarLessonService.findAll(pageNo);

			if (keyword != null && !keyword.trim().isEmpty()) {
				list = this.grammarLessonService.findByKeyword(keyword, pageNo);
				model.addAttribute("keyword", keyword);
			}

			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học ngữ pháp: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi khi tải danh sách danh sách bài học ngữ pháp!");
		}

		return "admin/grammar-lesson";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			this.grammarLessonService.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa bài học ngữ pháp thành công!");
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài học ngữ pháp với ID: " + id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa bài học ngữ pháp!");
		}
		return "redirect:/admin/grammar-lesson";
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			GrammarLesson grammarLesson = this.grammarLessonService.findById(id);
			if (grammarLesson == null) {
				redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bài học ngữ pháp!");
				return "redirect:/admin/grammar-lesson";
			}

			grammarLesson.setContent(this.markdownService.convertMarkdownToHtml(grammarLesson.getContent()));
			model.addAttribute("grammarLesson", grammarLesson);
			return "admin/grammar-lesson-view";
		} catch (Exception e) {
			logger.error("Lỗi khi lấy bài học ngữ pháp với ID: " + id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi lấy thông tin bài học!");
			return "redirect:/admin/grammar-lesson";
		}
	}

	@GetMapping("/create")
	public String create(Model model, RedirectAttributes redirectAttributes) {
		try {
			GrammarLesson grammarLesson = new GrammarLesson();
			model.addAttribute("grammarLesson", grammarLesson);
		} catch (Exception e) {
			logger.error("Lỗi khi khởi tạo form thêm ngữ pháp: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi mở form thêm ngữ pháp!");
			return "redirect:/admin/grammar-lesson";
		}
		return "admin/grammar-lesson-form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("grammarLesson") GrammarLesson grammarLesson,
			RedirectAttributes redirectAttributes) {

		try {
			// Lưu Grammar Lesson vào DB
			this.grammarLessonService.save(grammarLesson);

			redirectAttributes.addFlashAttribute("successMessage", "Tạo bài học ngữ pháp thành công!");
			return "redirect:/admin/grammar-lesson";

		} catch (Exception e) {
			logger.error("Lỗi khi tạo bài học ngữ pháp: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi tạo bài học ngữ pháp!");
			return "redirect:/admin/grammar-lesson/create";
		}
	}

	@GetMapping("/update/{id}")
	public String edit(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			GrammarLesson grammarLesson = this.grammarLessonService.findById(id);
			if (grammarLesson == null) {
				redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy bài học ngữ pháp!");
				return "redirect:/admin/grammar-lesson";
			}
			model.addAttribute("grammarLesson", grammarLesson);
			return "admin/grammar-lesson-form";
		} catch (Exception e) {
			logger.error("Lỗi khi lấy thông tin bài học ngữ pháp để cập nhật: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi mở form cập nhật!");
			return "redirect:/admin/grammar-lesson";
		}
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("grammarLesson") GrammarLesson grammarLesson,
			RedirectAttributes redirectAttributes) {
		try {
			this.grammarLessonService.save(grammarLesson);
			redirectAttributes.addFlashAttribute("successMessage", "Cập nhật bài học ngữ pháp thành công!");
		} catch (Exception e) {
			logger.error("Lỗi khi cập nhật bài học ngữ pháp: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật bài học!");
		}
		return "redirect:/admin/grammar-lesson/view/" + grammarLesson.getId();
	}

}
