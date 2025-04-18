package com.pbl5.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.excel.ExcelService;
import com.pbl5.model.GrammarLesson;
import com.pbl5.service.MockExamService;

@Controller
@RequestMapping("/admin/mock-exam")
public class MockExamController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private MockExamService mockExamService;

	@Autowired
	private ExcelService excelService;
	
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

}
