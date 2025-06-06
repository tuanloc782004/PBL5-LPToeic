package com.pbl5.controller.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
import com.pbl5.model.MockExam;
import com.pbl5.service.MockExamService;
import com.pbl5.storage.StorageService;

@Controller
@RequestMapping("/admin/mock-exam")
public class MockExamAdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

	@Autowired
	private MockExamService mockExamService;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private StorageService storageService;

	@RequestMapping("")
	public String list(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {

		try {
			Page<MockExam> list = this.mockExamService.findAll(pageNo);

			if (keyword != null && !keyword.trim().isEmpty()) {
				list = this.mockExamService.findByKeyword(keyword, pageNo);
				model.addAttribute("keyword", keyword);
			}

			model.addAttribute("totalPage", list.getTotalPages());
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách danh sách bài thi thử!");
		}

		return "admin/mock-exam";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			this.mockExamService.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa bài thi thử thành công!");
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài học ngữ pháp với ID: " + id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa bài thi thử!");
		}
		return "redirect:/admin/mock-exam";
	}

	@GetMapping("/create")
	public String create(Model model, RedirectAttributes redirectAttributes) {
		try {
			MockExam mockExam = new MockExam();
			model.addAttribute("mockExam", mockExam);
		} catch (Exception e) {
			logger.error("Lỗi khi khởi tạo form thêm bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi mở form thêm bài thi thử!");
			return "redirect:/admin/mock-exam";
		}
		return "admin/mock-exam-form";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("mockExam") MockExam mockExam,
			@RequestParam("part1ExcelFile") MultipartFile part1ExcelFile,
			@RequestParam("part2ExcelFile") MultipartFile part2ExcelFile,
			@RequestParam("part3ExcelFile") MultipartFile part3ExcelFile,
			@RequestParam("part4ExcelFile") MultipartFile part4ExcelFile,
			@RequestParam("part5ExcelFile") MultipartFile part5ExcelFile,
			@RequestParam("part6ExcelFile") MultipartFile part6ExcelFile,
			@RequestParam("part7ExcelFile") MultipartFile part7ExcelFile,
			@RequestParam("part1ImageFiles") MultipartFile[] part1ImageFiles,
			@RequestParam("part1AudioFiles") MultipartFile[] part1AudioFiles,
			@RequestParam("part2AudioFiles") MultipartFile[] part2AudioFiles,
			@RequestParam("part3AudioFiles") MultipartFile[] part3AudioFiles,
			@RequestParam("part4AudioFiles") MultipartFile[] part4AudioFiles, RedirectAttributes redirectAttributes) {
		try {
			String myCodePart1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_P1_";
			String myCodePart2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_P2_";
			String myCodePart3 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_P3_";
			String myCodePart4 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_P4_";

			// Lưu mock exam
			this.mockExamService.save(mockExam);

			AtomicLong number = new AtomicLong(1); // dùng để đánh số liên tục từ Part1 -> Part7

			Map<MultipartFile, Consumer<MultipartFile>> excelProcessors = new LinkedHashMap<>();
			excelProcessors.put(part1ExcelFile,
					file -> this.excelService.savePart1MockExamFromExcel(file, mockExam, myCodePart1, number));
			excelProcessors.put(part2ExcelFile,
					file -> this.excelService.savePart2MockExamFromExcel(file, mockExam, myCodePart2, number));
			excelProcessors.put(part3ExcelFile,
					file -> this.excelService.savePart3MockExamFromExcel(file, mockExam, myCodePart3, number));
			excelProcessors.put(part4ExcelFile,
					file -> this.excelService.savePart4MockExamFromExcel(file, mockExam, myCodePart4, number));
			excelProcessors.put(part5ExcelFile,
					file -> this.excelService.savePart5MockExamFromExcel(file, mockExam, number));
			excelProcessors.put(part6ExcelFile,
					file -> this.excelService.savePart6MockExamFromExcel(file, mockExam, number));
			excelProcessors.put(part7ExcelFile,
					file -> this.excelService.savePart7MockExamFromExcel(file, mockExam, number));

			for (Map.Entry<MultipartFile, Consumer<MultipartFile>> entry : excelProcessors.entrySet()) {
				MultipartFile file = entry.getKey();
				if (file != null && !file.isEmpty()) {
					entry.getValue().accept(file);
				} else {
					logger.warn("File Excel " + file.getName() + " trống, bỏ qua.");
				}
			}

			BiConsumer<MultipartFile[], String> saveFilesWithCode = (files, code) -> {
				for (MultipartFile file : files) {
					if (file != null && !file.isEmpty()) {
						String folder = file.getContentType() != null && file.getContentType().startsWith("image")
								? "image/"
								: "audio/";
						this.storageService.storage(file, folder + code + file.getOriginalFilename());
					}
				}
			};

			saveFilesWithCode.accept(part1ImageFiles, myCodePart1);
			saveFilesWithCode.accept(part1AudioFiles, myCodePart1);
			saveFilesWithCode.accept(part2AudioFiles, myCodePart2);
			saveFilesWithCode.accept(part3AudioFiles, myCodePart3);
			saveFilesWithCode.accept(part4AudioFiles, myCodePart4);

			redirectAttributes.addFlashAttribute("successMessage", "Tạo bài học thi thử thành công!");
			return "redirect:/admin/mock-exam";

		} catch (Exception e) {
			logger.error("Lỗi khi tạo bài thi thử: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi tạo bài thi thử!");
			return "redirect:/admin/mock-examn/create";
		}
	}
}
