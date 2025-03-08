package com.pbl5.excel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pbl5.model.Part5;
import com.pbl5.model.Part6;
import com.pbl5.model.Part6Question;
import com.pbl5.model.Part7;
import com.pbl5.model.Part7Question;
import com.pbl5.model.ReadingExercise;
import com.pbl5.model.VocabularyLesson;
import com.pbl5.model.VocabularyLessonContent;

public interface ExcelService {
		
	public List<VocabularyLessonContent> readVocabularyLessonContentExcelFile(MultipartFile file, VocabularyLesson vocabularyLesson, String myCode);

	public void saveVocabularyLessonContentFromExcel(MultipartFile file, VocabularyLesson vocabularyLesson, String myCode);
	
	public List<Part5> readPart5ReadingExerciseExcelFile(MultipartFile file, ReadingExercise readingExercise);

	public void savePart5ReadingExerciseFromExcel(MultipartFile file, ReadingExercise readingExercise);
	
	public List<Part6Question> readPart6ReadingExerciseExcelFile(MultipartFile file, Part6 part6);

	public void savePart6ReadingExerciseFromExcel(MultipartFile file, Part6 part6);
	
	public List<Part7Question> readPart7ReadingExerciseExcelFile(MultipartFile file, Part7 part7);

	public void savePart7ReadingExerciseFromExcel(MultipartFile file, Part7 part7);
	
}
