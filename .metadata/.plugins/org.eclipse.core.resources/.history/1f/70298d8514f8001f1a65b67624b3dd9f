package com.pbl5.excel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pbl5.model.VocabularyLesson;
import com.pbl5.model.VocabularyLessonContent;

public interface ExcelService {
		
	public List<VocabularyLessonContent> readVocabularyExcelFile(MultipartFile file, VocabularyLesson vocabulary, String myCode);

	public void saveVocabularyContentFromExcel(MultipartFile file, VocabularyLesson vocabulary, String myCode);
	
}
