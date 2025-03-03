//package com.pbl5.controller.admin;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.pbl5.model.GrammarLesson;
//import com.pbl5.service.GrammarService;
//import com.pbl5.storage.StorageService;
//
//@Controller
//@RequestMapping("/admin/grammar")
//public class GrammarController {
//
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//	@Autowired
//	private StorageService storageService;
//
//	@Autowired
//	private GrammarService grammarService;
//
//	@RequestMapping("")
//	public String list(Model model, @Param("keyword") String keyword,
//			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {
//
//		try {
//			Page<GrammarLesson> list = this.grammarService.findAll(pageNo);
//
//			if (keyword != null && !keyword.trim().isEmpty()) {
//				list = this.grammarService.findByKeyword(keyword, pageNo);
//				model.addAttribute("keyword", keyword);
//			}
//
//			model.addAttribute("totalPage", list.getTotalPages());
//			model.addAttribute("currentPage", pageNo);
//			model.addAttribute("list", list);
//
//		} catch (Exception e) {
//			logger.error("Lỗi khi lấy danh sách bài học ngữ pháp: ", e);
//			redirectAttributes.addFlashAttribute("errorMessage",
//					"Có lỗi khi tải danh sách danh sách bài học ngữ pháp!");
//		}
//
//		return "admin/grammar";
//	}
//
//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//		try {
//			this.grammarService.deleteById(id);
//			redirectAttributes.addFlashAttribute("successMessage", "Xóa danh sách bài học ngữ pháp thành công!");
//		} catch (Exception e) {
//			logger.error("Lỗi khi xóa danh sách bài học ngữ pháp với ID: " + id, e);
//			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa danh sách bài học ngữ pháp!");
//		}
//		return "redirect:/admin/grammar";
//	}
//
//	@GetMapping("/create")
//	public String create(Model model, RedirectAttributes redirectAttributes) {
//		try {
//			GrammarLesson grammar = new GrammarLesson();
//			model.addAttribute("grammar", grammar);
//		} catch (Exception e) {
//			logger.error("Lỗi khi khởi tạo form thêm ngữ pháp: ", e);
//			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi mở form thêm ngữ pháp!");
//			return "redirect:/admin/grammar";
//		}
//		return "admin/grammar-form";
//	}
//
//	@PostMapping("/create")
//	public String create(@ModelAttribute("grammar") GrammarLesson grammar, @RequestParam("imageFile") MultipartFile imageFile,
//			RedirectAttributes redirectAttributes) {
//		try {
//			// Tạo mã định danh duy nhất cho file
//			String myCode = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_";
//
//			// Kiểm tra file ảnh đại diện có rỗng không
//			if (imageFile != null && !imageFile.isEmpty()) {
//				// Gán URL ảnh đại diện vào Grammar
//				grammar.setImageUrl("/upload-dir/image/" + myCode + imageFile.getOriginalFilename());
//				// Lưu ảnh đại diện vào hệ thống
//				this.storageService.storage(imageFile, "image/" + myCode + imageFile.getOriginalFilename());
//			}
//
//			// Lưu Grammar vào DB
//			this.grammarService.save(grammar);
//
//			redirectAttributes.addFlashAttribute("successMessage", "Tạo bài học ngữ pháp thành công!");
//			return "redirect:/admin/grammar";
//
//		} catch (Exception e) {
//			logger.error("Lỗi khi tạo bài học ngữ pháp: ", e);
//			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi tạo bài học ngữ pháp!");
//			return "redirect:/admin/admin/create";
//		}
//	}
//
//}
