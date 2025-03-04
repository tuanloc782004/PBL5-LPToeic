package com.pbl5.controller.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.pbl5.model.VocabularyLesson;
import com.pbl5.service.VocabularyLessonService;
import com.pbl5.storage.StorageService;

@Controller
@RequestMapping("/admin/vocabulary-lesson")
public class VocabularyLessonController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private StorageService storageService;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private VocabularyLessonService vocabularyLessonService;

	@RequestMapping("")
	public String list(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {

		try {
			Page<VocabularyLesson> list = this.vocabularyLessonService.findAll(pageNo);

			if (keyword != null && !keyword.trim().isEmpty()) {
				list = this.vocabularyLessonService.findByKeyword(keyword, pageNo);
				model.addAttribute("keyword", keyword);
			}

			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học từ vựng: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài học từ vựng!");
		}

		return "admin/vocabulary-lesson";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			this.vocabularyLessonService.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa danh sách bài học từ vựng thành công!");
		} catch (Exception e) {
			logger.error("Lỗi khi xóa danh sách bài học từ vựng với ID: " + id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa danh sách bài học từ vựng!");
		}
		return "redirect:/admin/vocabulary-lesson";
	}

	@GetMapping("/create")
	public String create(Model model, RedirectAttributes redirectAttributes) {
		try {
			VocabularyLesson vocabularyLesson = new VocabularyLesson();
			model.addAttribute("vocabularyLesson", vocabularyLesson);
		} catch (Exception e) {
			logger.error("Lỗi khi khởi tạo form thêm từ vựng: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi mở form thêm từ vựng!");
			return "redirect:/admin/vocabulary-lesson";
		}
		return "admin/vocabulary-lesson-form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("vocabularyLesson") VocabularyLesson vocabularyLesson,
			@RequestParam("excelFile") MultipartFile excelFile, @RequestParam("imageFiles") MultipartFile[] imageFiles,
			@RequestParam("audioFiles") MultipartFile[] audioFiles, RedirectAttributes redirectAttributes) {

		try {
			// Tạo mã định danh duy nhất cho file
			String myCode = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_";

			// Lưu Vocabulary Lesson vào DB
			this.vocabularyLessonService.save(vocabularyLesson);

			// Kiểm tra file Excel có rỗng không trước khi xử lý
			if (excelFile != null && !excelFile.isEmpty()) {
				this.excelService.saveVocabularyLessonContentFromExcel(excelFile, vocabularyLesson, myCode);
			} else {
				logger.warn("File Excel trống, bỏ qua quá trình xử lý nội dung từ vựng.");
			}

			// Lưu các file hình ảnh
			for (MultipartFile file : imageFiles) {
				if (file != null && !file.isEmpty()) {
					this.storageService.storage(file, "image/" + myCode + file.getOriginalFilename());
				}
			}

			// Lưu các file âm thanh
			for (MultipartFile file : audioFiles) {
				if (file != null && !file.isEmpty()) {
					this.storageService.storage(file, "audio/" + myCode + file.getOriginalFilename());
				}
			}

			redirectAttributes.addFlashAttribute("successMessage", "Tạo danh sách bài học từ vựng thành công!");
			return "redirect:/admin/vocabulary-lesson";

		} catch (Exception e) {
			logger.error("Lỗi khi tạo danh sách bài học từ vựng: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi tạo danh sách bài học từ vựng!");
			return "redirect:/admin/vocabulary-lesson/create";
		}
	}

}
