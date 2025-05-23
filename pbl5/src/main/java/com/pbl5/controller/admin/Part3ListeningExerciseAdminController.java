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
import com.pbl5.model.ListeningExercise;
import com.pbl5.model.Part3;
import com.pbl5.service.ListeningExerciseService;
import com.pbl5.service.Part3Service;
import com.pbl5.storage.StorageService;

@Controller
@RequestMapping("/admin/part3-listening-exercise")
public class Part3ListeningExerciseAdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private ExcelService excelService;
	
	@Autowired
	private StorageService storageService;

	@Autowired
	private ListeningExerciseService listeningExerciseService;
	
	@Autowired
	private Part3Service part3Service;

	@RequestMapping("")
	public String list(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {

		try {
			Page<ListeningExercise> list = this.listeningExerciseService.findByPart3sIsNotEmpty(pageNo);

			if (keyword != null && !keyword.trim().isEmpty()) {
				list = this.listeningExerciseService.findByKeywordAndPart3sIsNotEmpty(keyword, pageNo);
				model.addAttribute("keyword", keyword);
			}

			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 3: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi khi tải danh sách danh sách bài luyện nghe phần 3!");
		}

		return "admin/part3-listening-exercise";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			this.listeningExerciseService.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa bài luyện nghe phần 3 thành công!");
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài luyện nghe phần 3 với ID: " + id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa bài luyện nghe phần 3!");
		}
		return "redirect:/admin/part3-listening-exercise";
	}

	@GetMapping("/create")
	public String create(Model model, RedirectAttributes redirectAttributes) {
		try {
			ListeningExercise listeningExercise = new ListeningExercise();
			model.addAttribute("listeningExercise", listeningExercise);
		} catch (Exception e) {
			logger.error("Lỗi khi khởi tạo form thêm bài luyện nghe phần 3: ", e);
			redirectAttributes.addFlashAttribute("errorMessage",
					"Có lỗi xảy ra khi mở form thêm bài luyện nghe phần 3!");
			return "redirect:/admin/part3-listening-exercise";
		}
		return "admin/part3-listening-exercise-form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("listeningExercise") ListeningExercise listeningExercise,
			@RequestParam("excelFile") MultipartFile excelFile,
			@RequestParam("audioFile") MultipartFile audioFile, RedirectAttributes redirectAttributes) {

		try {
			// Tạo mã định danh duy nhất cho file
			String myCode = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_";
			
			// Lưu ListeningExercise vào DB
			this.listeningExerciseService.save(listeningExercise);
			
			Part3 part3 = new Part3();
			part3.setListeningExercise(listeningExercise);
			part3.setAudioUrl("/upload-dir/audio/" + myCode + audioFile.getOriginalFilename());
			this.part3Service.save(part3);

			// Kiểm tra file Excel có rỗng không trước khi xử lý
			if (excelFile != null && !excelFile.isEmpty()) {
				this.excelService.savePart3ListeningExerciseFromExcel(excelFile, listeningExercise, part3, myCode);
			} else {
				logger.warn("File Excel trống, bỏ qua quá trình xử lý nội dung bài luyện nghe phần 3.");
			}

			// Lưu các file âm thanh
			if (audioFile != null && !audioFile.isEmpty()) {
				this.storageService.storage(audioFile, "audio/" + myCode + audioFile.getOriginalFilename());
			} else {
				logger.warn("File Audio trống, bỏ qua quá trình xử lý nội dung bài luyện nghe phần 3.");
			}

			redirectAttributes.addFlashAttribute("successMessage", "Tạo danh sách bài luyện nghe phần 3 thành công!");
			return "redirect:/admin/part3-listening-exercise";

		} catch (Exception e) {
			logger.error("Lỗi khi tạo bài luyện nghe phần 3: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi tạo bài luyện nghe phần 3!");
			logger.info("Tên file Excel: " + excelFile.getOriginalFilename());
			logger.info("Tên file Audio: " + audioFile.getOriginalFilename());
			return "redirect:/admin/part3-listening-exercise/create";
		}
	}

}
