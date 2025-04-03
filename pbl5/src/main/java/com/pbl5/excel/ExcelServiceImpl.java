package com.pbl5.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pbl5.model.ListeningExercise;
import com.pbl5.model.Part1;
import com.pbl5.model.Part3;
import com.pbl5.model.Part3Question;
import com.pbl5.model.Part4;
import com.pbl5.model.Part4Question;
import com.pbl5.model.Part5;
import com.pbl5.model.Part6;
import com.pbl5.model.Part6Question;
import com.pbl5.model.Part7;
import com.pbl5.model.Part7Question;
import com.pbl5.model.ReadingExercise;
import com.pbl5.model.VocabularyLesson;
import com.pbl5.model.VocabularyLessonContent;
import com.pbl5.repository.Part1Repository;
import com.pbl5.repository.Part3QuestionRepository;
import com.pbl5.repository.Part4QuestionRepository;
import com.pbl5.repository.Part5Repository;
import com.pbl5.repository.Part6QuestionRepository;
import com.pbl5.repository.Part7QuestionRepository;
import com.pbl5.repository.VocabularyLessonContentRepository;

@Service
public class ExcelServiceImpl implements ExcelService {

	private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

	@Autowired
	private VocabularyLessonContentRepository vocabularyLessonContentRepository;

	@Autowired
	private Part1Repository part1Repository;
	
	@Autowired
	private Part3QuestionRepository part3QuestionRepository;
	
	@Autowired
	private Part4QuestionRepository part4QuestionRepository;

	@Autowired
	private Part5Repository part5Repository;

	@Autowired
	private Part6QuestionRepository part6QuestionRepository;

	@Autowired
	private Part7QuestionRepository part7QuestionRepository;

	@Override
	public List<VocabularyLessonContent> readVocabularyLessonContentExcelFile(MultipartFile file,
			VocabularyLesson vocabularyLesson, String myCode) {
		List<VocabularyLessonContent> vocabularyLessonContentList = new ArrayList<>();

		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Bỏ qua dòng tiêu đề

				try {
					VocabularyLessonContent vocabularyContent = new VocabularyLessonContent();
					vocabularyContent.setNumber(getLongValue(row.getCell(0)));
					vocabularyContent.setContent(getStringValue(row.getCell(1)));
					vocabularyContent.setTranscribe(getStringValue(row.getCell(2)));
					vocabularyContent.setImageUrl("/upload-dir/image/" + myCode + getStringValue(row.getCell(3)));
					vocabularyContent.setAudioUrl("/upload-dir/audio/" + myCode + getStringValue(row.getCell(4)));
					vocabularyContent.setMeaning(getStringValue(row.getCell(5)));
					vocabularyContent.setSentence(getStringValue(row.getCell(6)));
					vocabularyContent.setVocabulary(vocabularyLesson);

					vocabularyLessonContentList.add(vocabularyContent);
				} catch (Exception e) {
					logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
				}
			}
		} catch (IOException e) {
			logger.error("Lỗi khi đọc file Excel", e);
			throw new RuntimeException("Không thể đọc file Excel", e);
		}

		return vocabularyLessonContentList;
	}

	@Override
	public void saveVocabularyLessonContentFromExcel(MultipartFile file, VocabularyLesson vocabularyLesson,
			String myCode) {
		try {
			List<VocabularyLessonContent> list = readVocabularyLessonContentExcelFile(file, vocabularyLesson, myCode);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.vocabularyLessonContentRepository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());
		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}

	private Long getLongValue(Cell cell) {
		if (cell == null)
			return null;
		try {
			if (cell.getCellType() == CellType.STRING) {
				return Long.parseLong(cell.getStringCellValue().trim());
			} else if (cell.getCellType() == CellType.NUMERIC) {
				return (long) cell.getNumericCellValue();
			}
		} catch (NumberFormatException e) {
			logger.warn("Lỗi chuyển đổi số: {}", cell.getStringCellValue());
		}
		return null;
	}

	private String getStringValue(Cell cell) {
		if (cell == null)
			return "";
		return switch (cell.getCellType()) {
		case STRING -> cell.getStringCellValue().trim();
		case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
		case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
		default -> "";
		};
	}
	
	// ========== PART1 ===========
	
	@Override
	public List<Part1> readPart1ListeningExerciseExcelFile(MultipartFile file, ListeningExercise listeningExercise, String myCode) {
	    List<Part1> part1List = new ArrayList<>();
	    Long i = 1L;

	    try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
	        Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) {
	                continue; // Bỏ qua dòng tiêu đề
	            }

	            try {
	                Part1 part1 = new Part1();
	                part1.setNumber(i);
	                part1.setAudioUrl("/upload-dir/audio/" + myCode + getStringValue(row.getCell(0)));
	                part1.setImageUrl("/upload-dir/image/" + myCode + getStringValue(row.getCell(1)));
	                part1.setOptionA(getStringValue(row.getCell(2)));
	                part1.setOptionB(getStringValue(row.getCell(3)));
	                part1.setOptionC(getStringValue(row.getCell(4)));
	                part1.setOptionD(getStringValue(row.getCell(5)));
	                part1.setCorrectAnswer(getStringValue(row.getCell(6)));
	                part1.setExplanation(getStringValue(row.getCell(7)));
	                part1.setListeningExercise(listeningExercise);

	                part1List.add(part1);
	                i++;
	            } catch (Exception e) {
	                logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
	            }
	        }
	    } catch (IOException e) {
	        logger.error("Lỗi khi đọc file Excel", e);
	        throw new RuntimeException("Không thể đọc file Excel", e);
	    }

	    return part1List;
	}


	@Override
	public void savePart1ListeningExerciseFromExcel(MultipartFile file, ListeningExercise listeningExercise, String myCode) {
		// TODO Auto-generated method stub
		try {
			List<Part1> list = readPart1ListeningExerciseExcelFile(file, listeningExercise, myCode);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.part1Repository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());

		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}
	
	// ========== PART2 ===========
	
	// ========== PART3 ===========
	
	@Override
	public List<Part3Question> readPart3ListeningExerciseExcelFile(MultipartFile file, ListeningExercise listeningExercise, Part3 part3, String myCode) {
		// TODO Auto-generated method stub
		List<Part3Question> part3QuestionList = new ArrayList<>();
		Long i = 1L;

		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Bỏ qua dòng tiêu đề

				try {
					if (row.getCell(0) == null) {
					    logger.warn("Ô dữ liệu bị trống ở hàng " + row.getRowNum());
					    continue;
					}

					Part3Question part3Question = new Part3Question();
					part3Question.setNumber(i);
					part3Question.setOptionA(getStringValue(row.getCell(0)));
					part3Question.setOptionB(getStringValue(row.getCell(1)));
					part3Question.setOptionC(getStringValue(row.getCell(2)));
					part3Question.setOptionD(getStringValue(row.getCell(3)));
					part3Question.setCorrectAnswer(getStringValue(row.getCell(4)));
					part3Question.setQuestion(getStringValue(row.getCell(5)));
					part3Question.setPart3(part3);

					part3QuestionList.add(part3Question);
					i++;
				} catch (Exception e) {
					logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
				}
			}
		} catch (IOException e) {
			logger.error("Lỗi khi đọc file Excel", e);
			throw new RuntimeException("Không thể đọc file Excel", e);
		}

		return part3QuestionList;
	}

	@Override
	public void savePart3ListeningExerciseFromExcel(MultipartFile file, ListeningExercise listeningExercise, Part3 part3, String myCode) {
		// TODO Auto-generated method stub
		try {
			List<Part3Question> list = readPart3ListeningExerciseExcelFile(file, listeningExercise, part3, myCode);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.part3QuestionRepository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());
		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}
	
	// ========== PART4 ===========
	
	@Override
	public List<Part4Question> readPart4ListeningExerciseExcelFile(MultipartFile file, ListeningExercise listeningExercise, Part4 part4, String myCode) {
		// TODO Auto-generated method stub
		List<Part4Question> part4QuestionList = new ArrayList<>();
		Long i = 1L;

		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Bỏ qua dòng tiêu đề

				try {
					if (row.getCell(0) == null) {
					    logger.warn("Ô dữ liệu bị trống ở hàng " + row.getRowNum());
					    continue;
					}

					Part4Question part4Question = new Part4Question();
					part4Question.setNumber(i);
					part4Question.setOptionA(getStringValue(row.getCell(0)));
					part4Question.setOptionB(getStringValue(row.getCell(1)));
					part4Question.setOptionC(getStringValue(row.getCell(2)));
					part4Question.setOptionD(getStringValue(row.getCell(3)));
					part4Question.setCorrectAnswer(getStringValue(row.getCell(4)));
					part4Question.setQuestion(getStringValue(row.getCell(5)));
					part4Question.setExplanation(getStringValue(row.getCell(6)));
					part4.setAudioUrl("/upload-dir/audio/" + myCode + getStringValue(row.getCell(0)));
					part4Question.setPart4(part4);

					part4QuestionList.add(part4Question);
					i++;
				} catch (Exception e) {
					logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
				}
			}
		} catch (IOException e) {
			logger.error("Lỗi khi đọc file Excel", e);
			throw new RuntimeException("Không thể đọc file Excel", e);
		}

		return part4QuestionList;
	}

	@Override
	public void savePart4ListeningExerciseFromExcel(MultipartFile file, ListeningExercise listeningExercise, Part4 part4, String myCode) {
		// TODO Auto-generated method stub
		try {
			List<Part4Question> list = readPart4ListeningExerciseExcelFile(file, listeningExercise, part4, myCode);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.part4QuestionRepository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());
		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}
	
	// ========== PART5 ===========
	
	@Override
	public List<Part5> readPart5ReadingExerciseExcelFile(MultipartFile file, ReadingExercise readingExercise) {
		// TODO Auto-generated method stub
		List<Part5> part5List = new ArrayList<>();
		Long i = 1L;

		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Bỏ qua dòng tiêu đề

				try {
					Part5 part5 = new Part5();
					part5.setNumber(i);
					part5.setQuestion(getStringValue(row.getCell(0)));
					part5.setOptionA(getStringValue(row.getCell(1)));
					part5.setOptionB(getStringValue(row.getCell(2)));
					part5.setOptionC(getStringValue(row.getCell(3)));
					part5.setOptionD(getStringValue(row.getCell(4)));
					part5.setCorrectAnswer(getStringValue(row.getCell(5)));
					part5.setExplanation(getStringValue(row.getCell(6)));
					part5.setReadingExercise(readingExercise);

					part5List.add(part5);
					i++;
				} catch (Exception e) {
					logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
				}
			}
		} catch (IOException e) {
			logger.error("Lỗi khi đọc file Excel", e);
			throw new RuntimeException("Không thể đọc file Excel", e);
		}

		return part5List;
	}

	@Override
	public void savePart5ReadingExerciseFromExcel(MultipartFile file, ReadingExercise readingExercise) {
		// TODO Auto-generated method stub
		try {
			List<Part5> list = readPart5ReadingExerciseExcelFile(file, readingExercise);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.part5Repository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());
		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}

	// ========== PART6 ===========
	
	@Override
	public List<Part6Question> readPart6ReadingExerciseExcelFile(MultipartFile file, Part6 part6) {
		// TODO Auto-generated method stub
		List<Part6Question> part6QuestionList = new ArrayList<>();
		Long i = 1L;

		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

			for (Row row : sheet) {
				if (row.getRowNum() == 0)
					continue; // Bỏ qua dòng tiêu đề

				try {
					Part6Question part6Question = new Part6Question();
					part6Question.setNumber(i);
					part6Question.setOptionA(getStringValue(row.getCell(0)));
					part6Question.setOptionB(getStringValue(row.getCell(1)));
					part6Question.setOptionC(getStringValue(row.getCell(2)));
					part6Question.setOptionD(getStringValue(row.getCell(3)));
					part6Question.setCorrectAnswer(getStringValue(row.getCell(4)));
					part6Question.setExplanation(getStringValue(row.getCell(5)));
					part6Question.setPart6(part6);

					part6QuestionList.add(part6Question);
					i++;
				} catch (Exception e) {
					logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
				}
			}
		} catch (IOException e) {
			logger.error("Lỗi khi đọc file Excel", e);
			throw new RuntimeException("Không thể đọc file Excel", e);
		}

		return part6QuestionList;
	}

	@Override
	public void savePart6ReadingExerciseFromExcel(MultipartFile file, Part6 part6) {
		// TODO Auto-generated method stub
		try {
			List<Part6Question> list = readPart6ReadingExerciseExcelFile(file, part6);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.part6QuestionRepository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());
		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}
	
	// ========== PART7 ===========
	
	@Override
	public List<Part7Question> readPart7ReadingExerciseExcelFile(MultipartFile file, Part7 part7) {
	    List<Part7Question> part7QuestionList = new ArrayList<>();
	    Long i = 1L;

	    try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
	        Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) {
	                continue; // Bỏ qua dòng tiêu đề
	            }

	            try {
	                Part7Question part7Question = new Part7Question();
	                part7Question.setNumber(i);
	                part7Question.setOptionA(getStringValue(row.getCell(0)));
	                part7Question.setOptionB(getStringValue(row.getCell(1)));
	                part7Question.setOptionC(getStringValue(row.getCell(2)));
	                part7Question.setOptionD(getStringValue(row.getCell(3)));
	                part7Question.setCorrectAnswer(getStringValue(row.getCell(4)));
	                part7Question.setExplanation(getStringValue(row.getCell(5)));
	                part7Question.setQuestion(getStringValue(row.getCell(6)));
	                part7Question.setPart7(part7);

	                part7QuestionList.add(part7Question);
	                i++;
	            } catch (Exception e) {
	                logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
	            }
	        }
	    } catch (IOException e) {
	        logger.error("Lỗi khi đọc file Excel", e);
	        throw new RuntimeException("Không thể đọc file Excel", e);
	    }

	    return part7QuestionList;
	}


	@Override
	public void savePart7ReadingExerciseFromExcel(MultipartFile file, Part7 part7) {
		// TODO Auto-generated method stub
		try {
			List<Part7Question> list = readPart7ReadingExerciseExcelFile(file, part7);

			if (list.isEmpty()) {
				logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
				return;
			}

			this.part7QuestionRepository.saveAll(list);
			logger.info("Đã lưu thành công {} mục vào database.", list.size());

		} catch (RuntimeException e) {
			logger.error("Lỗi khi lưu dữ liệu vào database", e);
			throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
		}
	}

}
