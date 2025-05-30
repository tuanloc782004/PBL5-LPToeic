package com.pbl5.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pbl5.model.TestResult;
import com.pbl5.service.GrammarLessonService;
import com.pbl5.service.ListeningExerciseService;
import com.pbl5.service.MockExamService;
import com.pbl5.service.ReadingExerciseService;
import com.pbl5.service.TestResultService;
import com.pbl5.service.UserService;
import com.pbl5.service.VocabularyLessonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MockExamService mockExamService;

	@Autowired
	private TestResultService testResultService;

	@Autowired
	private VocabularyLessonService vocabularyLessonService;

	@Autowired
	private GrammarLessonService grammarLessonService;

	@Autowired
	private ListeningExerciseService listeningExerciseService;

	@Autowired
	private ReadingExerciseService readingExerciseService;

	@RequestMapping("")
	public String home(Model model) {
		try {
			long countAllUsers = userService.countAllUsers();
			long countAllMockExams = mockExamService.countAllMockExams();
			long countAllTestResults = testResultService.countAllTestResults();
			List<TestResult> top5Results = testResultService.findTop10ByOrderByCorrectAnswersDesc();

			Double averageScore = testResultService.getAverageScore();
			Long maxScore = testResultService.getMaxScore();
			Long minScore = testResultService.getMinScore();
			Long mostAttemptedMockExamId = testResultService.getMostAttemptedMockExamId();

			String mostAttemptedExamTitle = mostAttemptedMockExamId != null
					? mockExamService.findById(mostAttemptedMockExamId).getMockExamName()
					: "Không có";

			Map<Integer, Double> averageScoresByDay = testResultService.getAverageScoresByDayInCurrentMonth();

			long vocabCount = vocabularyLessonService.countAllVocabularyLessons();
			long grammarCount = grammarLessonService.countAllGrammarLessons();
			long listeningCount = listeningExerciseService.countAllListeningExercises();
			long readingCount = readingExerciseService.countAllReadingExercises();

			model.addAttribute("countAllUsers", countAllUsers);
			model.addAttribute("countAllMockExams", countAllMockExams);
			model.addAttribute("countAllTestResults", countAllTestResults);
			model.addAttribute("list", top5Results);

			model.addAttribute("averageScore", averageScore);
			model.addAttribute("maxScore", maxScore);
			model.addAttribute("minScore", minScore);
			model.addAttribute("mostAttemptedExamTitle", mostAttemptedExamTitle);

			model.addAttribute("avgScoreLabels", new ArrayList<>(averageScoresByDay.keySet()));
			model.addAttribute("avgScoreData", new ArrayList<>(averageScoresByDay.values()));

			model.addAttribute("vocabCount", vocabCount);
			model.addAttribute("grammarCount", grammarCount);
			model.addAttribute("listeningCount", listeningCount);
			model.addAttribute("readingCount", readingCount);
			model.addAttribute("mockExamCount", countAllMockExams);
		} catch (Exception e) {
			logger.error("Lỗi khi tải dashboard: ", e);
		}

		return "admin/home";
	}
}
