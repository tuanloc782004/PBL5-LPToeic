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

import com.pbl5.model.Listening;
import com.pbl5.model.ListeningQuestion;
import com.pbl5.model.MockExam;
import com.pbl5.model.MockExamQuestion;
import com.pbl5.model.Reading;
import com.pbl5.model.ReadingQuestion;
import com.pbl5.model.Vocabulary;
import com.pbl5.model.VocabularyContent;
import com.pbl5.repository.VocabularyContentRepository;

@Service
public class ExcelServiceImpl implements ExcelService {

	private static final Logger logger = LoggerFactory.getLogger(ExcelService.class);

	@Autowired
	private VocabularyContentRepository vocabularyContentRepository;

	@Override
	public List<VocabularyContent> readVocabularyExcelFile(MultipartFile file, Vocabulary vocabulary, String myCode) {
	    List<VocabularyContent> vocabularyContentList = new ArrayList<>();

	    try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
	        Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // Bỏ qua dòng tiêu đề

	            try {
	                VocabularyContent vocabularyContent = new VocabularyContent();
	                vocabularyContent.setNumber(getLongValue(row.getCell(0)));  
	                vocabularyContent.setContent(getStringValue(row.getCell(1)));
	                vocabularyContent.setTranscribe(getStringValue(row.getCell(2)));
	                vocabularyContent.setImageUrl("/upload-dir/image/" + myCode + getStringValue(row.getCell(3)));
	                vocabularyContent.setAudioUrl("/upload-dir/audio/" + myCode + getStringValue(row.getCell(4)));
	                vocabularyContent.setMeaning(getStringValue(row.getCell(5)));
	                vocabularyContent.setSentence(getStringValue(row.getCell(6)));
	                vocabularyContent.setVocabulary(vocabulary);

	                vocabularyContentList.add(vocabularyContent);
	            } catch (Exception e) {
	                logger.warn("Bỏ qua dòng {} do lỗi xử lý dữ liệu: {}", row.getRowNum(), e.getMessage());
	            }
	        }
	    } catch (IOException e) {
	        logger.error("Lỗi khi đọc file Excel", e);
	        throw new RuntimeException("Không thể đọc file Excel", e);
	    }

	    return vocabularyContentList;
	}

	@Override
	public List<ReadingQuestion> readReadingExcelFile(MultipartFile file, Reading reading, String myCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListeningQuestion> readListeningExcelFile(MultipartFile file, Listening listening, String myCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MockExamQuestion> readMockExamExcelFile(MultipartFile file, MockExam mockExam, String myCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveVocabularyContentFromExcel(MultipartFile file, Vocabulary vocabulary, String myCode) {
	    try {
	        List<VocabularyContent> list = readVocabularyExcelFile(file, vocabulary, myCode);
	        
	        if (list.isEmpty()) {
	            logger.warn("Không có dữ liệu hợp lệ để lưu vào database.");
	            return;
	        }

	        vocabularyContentRepository.saveAll(list);
	        logger.info("Đã lưu thành công {} mục vào database.", list.size());
	    } catch (RuntimeException e) {
	        logger.error("Lỗi khi lưu dữ liệu vào database", e);
	        throw new RuntimeException("Không thể lưu dữ liệu vào database", e);
	    }
	}

	@Override
	public void saveReadingQuestionFromExcel(MultipartFile file, Reading reading, String myCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveListeningQuestionFromExcel(MultipartFile file, Listening listening, String myCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMockExamQuestionFromExcel(MultipartFile file, MockExam mockExam, String myCode) {
		// TODO Auto-generated method stub
		
	}
	
	private Long getLongValue(Cell cell) {
	    if (cell == null) return null;
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
	    if (cell == null) return "";
	    return switch (cell.getCellType()) {
	        case STRING -> cell.getStringCellValue().trim();
	        case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
	        case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
	        default -> "";
	    };
	}

}
