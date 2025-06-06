package com.pbl5.controller.admin;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbl5.ai.AiQuestionService;
import com.pbl5.dto.Part5DTO;
import com.pbl5.dto.Part6DTO;
import com.pbl5.dto.Part7DTO;
import com.pbl5.dto.Part7DTO.Part7QuestionDTO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/ai")
public class AiQuestionController {

	private final AiQuestionService aiQuestionService;

	public AiQuestionController(AiQuestionService aiQuestionService) {
		this.aiQuestionService = aiQuestionService;
	}

	@RequestMapping("")
	public String home(Model model) {
		return "admin/ai-menu";
	}

	@GetMapping("/part5-generate")
	public String generatePart5Questions(@RequestParam(name = "quantity", defaultValue = "1") int quantity, Model model,
			HttpSession session) {
		try {
			List<Part5DTO> questions = aiQuestionService.generatePart5Questions(quantity);
			model.addAttribute("questions", questions);
			model.addAttribute("quantity", quantity);

			session.setAttribute("part5Questions", questions);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Lỗi khi gọi AI sinh câu hỏi: " + e.getMessage());
			return "admin/ai-menu";
		}
		return "admin/part5-generate";
	}

	@PostMapping("/part5-export-excel")
	public void exportPart5ToExcel(HttpServletResponse response, HttpSession session) {
		try {
			@SuppressWarnings("unchecked")
			List<Part5DTO> questions = (List<Part5DTO>) session.getAttribute("part5Questions");

			if (questions == null || questions.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không có câu hỏi nào để xuất.");
				return;
			}

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Part5 Questions");

			Row headerRow = sheet.createRow(0);
			String[] columns = { "Câu hỏi", "Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D", "Đáp án", "Giải thích" };
			for (int i = 0; i < columns.length; i++) {
				headerRow.createCell(i).setCellValue(columns[i]);
			}

			int rowIdx = 1;
			for (Part5DTO q : questions) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(q.getQuestion());
				row.createCell(1).setCellValue(q.getOptionA());
				row.createCell(2).setCellValue(q.getOptionB());
				row.createCell(3).setCellValue(q.getOptionC());
				row.createCell(4).setCellValue(q.getOptionD());
				row.createCell(5).setCellValue(q.getCorrectAnswer());
				row.createCell(6).setCellValue(q.getExplanation());
			}

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=part5_questions.xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/part6-generate")
	public String generatePart6Questions(@RequestParam(name = "quantity", defaultValue = "1") int quantity, Model model,
			HttpSession session) {
		try {
			List<Part6DTO> questions = aiQuestionService.generatePart6Questions(quantity);
			model.addAttribute("questions", questions);
			model.addAttribute("quantity", quantity);

			session.setAttribute("part6Questions", questions);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Lỗi khi gọi AI sinh câu hỏi: " + e.getMessage());
			return "admin/ai-menu";
		}
		return "admin/part6-generate";
	}

	@PostMapping("/part6-export-excel")
	public void exportPart6ToExcel(HttpServletResponse response, HttpSession session) {
		try {
			@SuppressWarnings("unchecked")
			List<Part6DTO> part6Questions = (List<Part6DTO>) session.getAttribute("part6Questions");

			if (part6Questions == null || part6Questions.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không có câu hỏi nào để xuất.");
				return;
			}

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Part6 Questions");

			Row headerRow = sheet.createRow(0);
			String[] headers = { "Đoạn văn", "Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D", "Đáp án đúng",
					"Giải thích" };
			for (int i = 0; i < headers.length; i++) {
				headerRow.createCell(i).setCellValue(headers[i]);
			}

			int rowIdx = 1;
			for (Part6DTO part6 : part6Questions) {
				String script = part6.getScript();
				List<Part6DTO.Part6QuestionDTO> questions = part6.getQuestions();
				int startRow = rowIdx;
				for (Part6DTO.Part6QuestionDTO q : questions) {
					Row row = sheet.createRow(rowIdx++);
					// Chưa set script ở đây
					row.createCell(1).setCellValue(q.getOptionA());
					row.createCell(2).setCellValue(q.getOptionB());
					row.createCell(3).setCellValue(q.getOptionC());
					row.createCell(4).setCellValue(q.getOptionD());
					row.createCell(5).setCellValue(q.getCorrectAnswer());
					row.createCell(6).setCellValue(q.getExplanation());
				}

				// Gộp các ô ở cột 0 cho đoạn văn này
				int endRow = rowIdx - 1;
				if (startRow <= endRow) {
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 0, 0));
					Row mergedRow = sheet.getRow(startRow);
					mergedRow.createCell(0).setCellValue(script);
				}
			}

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=part6_questions.xlsx");

			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/part7-generate")
	public String generatePart7Questions(@RequestParam(name = "quantity", defaultValue = "1") int quantity, Model model,
			HttpSession session) {
		try {
			List<Part7DTO> questions = aiQuestionService.generatePart7Questions(quantity);
			model.addAttribute("questions", questions);
			model.addAttribute("quantity", quantity);

			session.setAttribute("part7Questions", questions);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Lỗi khi gọi AI sinh câu hỏi: " + e.getMessage());
			return "admin/ai-menu";
		}
		return "admin/part7-generate";
	}

	@PostMapping("/part7-export-excel")
	public void exportPart7ToExcel(HttpServletResponse response, HttpSession session) {
		try {
			@SuppressWarnings("unchecked")
			List<Part7DTO> part7Questions = (List<Part7DTO>) session.getAttribute("part7Questions");

			if (part7Questions == null || part7Questions.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không có câu hỏi nào để xuất.");
				return;
			}

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Part7 Questions");

			Row headerRow = sheet.createRow(0);
			String[] headers = { "Đoạn văn", "Câu hỏi", "Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D", "Đáp án đúng",
					"Giải thích" };
			for (int i = 0; i < headers.length; i++) {
				headerRow.createCell(i).setCellValue(headers[i]);
			}

			int rowIdx = 1;
			for (Part7DTO part7 : part7Questions) {
				String script = part7.getScript();
				List<Part7QuestionDTO> questions = part7.getQuestions();
				int startRow = rowIdx;
				for (Part7QuestionDTO q : questions) {
					Row row = sheet.createRow(rowIdx++);
					// Chưa set script tại đây
					row.createCell(1).setCellValue(q.getQuestion());
					row.createCell(2).setCellValue(q.getOptionA());
					row.createCell(3).setCellValue(q.getOptionB());
					row.createCell(4).setCellValue(q.getOptionC());
					row.createCell(5).setCellValue(q.getOptionD());
					row.createCell(6).setCellValue(q.getCorrectAnswer());
					row.createCell(7).setCellValue(q.getExplanation());
				}

				// Gộp các ô ở cột 0 cho đoạn văn này
				int endRow = rowIdx - 1;
				if (startRow <= endRow) {
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 0, 0));
					Row mergedRow = sheet.getRow(startRow);
					mergedRow.createCell(0).setCellValue(script);
				}
			}

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=part7_questions.xlsx");

			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
