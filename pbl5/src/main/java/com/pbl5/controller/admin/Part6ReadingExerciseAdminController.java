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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.excel.ExcelService;
import com.pbl5.model.Part6;
import com.pbl5.model.ReadingExercise;
import com.pbl5.service.Part6Service;
import com.pbl5.service.ReadingExerciseService;

@Controller
@RequestMapping("/admin/part6-reading-exercise")
public class Part6ReadingExerciseAdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private ExcelService excelService;

	@Autowired
	private ReadingExerciseService readingExerciseService;

	@Autowired
	private Part6Service part6Service;

	@RequestMapping("")
	public String list(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {

		try {
			Page<ReadingExercise> list = this.readingExerciseService.findByPart6sIsNotEmpty(pageNo);

			if (keyword != null && !keyword.trim().isEmpty()) {
				list = this.readingExerciseService.findByKeywordAndPart6sIsNotEmpty(keyword, pageNo);
				model.addAttribute("keyword", keyword);
			}

			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 6: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi khi tải danh sách danh sách bài luyện đọc phần 6!");
		}

		return "admin/part6-reading-exercise";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			this.readingExerciseService.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa bài luyện đọc phần 6 thành công!");
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài luyện nghe phần 6 với ID: " + id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa bài luyện đọc phần 6!");
		}
		return "redirect:/admin/part6-reading-exercise";
	}

	@GetMapping("/create")
	public String create(Model model, RedirectAttributes redirectAttributes) {
		try {
			ReadingExercise readingExercise = new ReadingExercise();
			model.addAttribute("readingExercise", readingExercise);
		} catch (Exception e) {
			logger.error("Lỗi khi khởi tạo form thêm bài luyện đọc phần 6: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi xảy ra khi mở form thêm bài luyện đọc phần 6!");
			return "redirect:/admin/part6-reading-exercise";
		}
		return "admin/part6-reading-exercise-form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("readingExercise") ReadingExercise readingExercise,
			@RequestParam("excelFile") MultipartFile excelFile, RedirectAttributes redirectAttributes) {

		try {
			// Lưu ReadingExercise vào DB
			this.readingExerciseService.save(readingExercise);

			Part6 part6 = new Part6();
			part6.setReadingExercise(readingExercise);
			this.part6Service.save(part6);

			// Kiểm tra file Excel có rỗng không trước khi xử lý
			if (excelFile != null && !excelFile.isEmpty()) {
				this.excelService.savePart6ReadingExerciseFromExcel(excelFile, part6);
			} else {
				logger.warn("File Excel trống, bỏ qua quá trình xử lý nội dung bài luyện đọc phần 6.");
			}

			redirectAttributes.addFlashAttribute("successMessage", "Tạo danh sách bài luyện đọc phần 6 thành công!");
			return "redirect:/admin/part6-reading-exercise";

		} catch (Exception e) {
			logger.error("Lỗi khi tạo danh sách bài luyện đọc phần 6: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi xảy ra khi tạo danh sách bài luyện đọc phần 6!");
			return "redirect:/admin/part6-reading-exercise/create";
		}
	}
}
