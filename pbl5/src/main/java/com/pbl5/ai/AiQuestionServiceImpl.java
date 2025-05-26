package com.pbl5.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl5.dto.Part5DTO;
import com.pbl5.dto.Part6DTO;
import com.pbl5.dto.Part7DTO;
import com.pbl5.dto.Part7DTO.Part7QuestionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class AiQuestionServiceImpl implements AiQuestionService {

	private static final Logger logger = LoggerFactory.getLogger(AiQuestionServiceImpl.class);

	@Value("${cohere.api.key}")
	private String apiKey;

	@Value("${cohere.api.url}")
	private String apiUrl;

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public AiQuestionServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Part5DTO> generatePart5Questions(int quantity) throws Exception {
		List<Part5DTO> result = new ArrayList<>();

		for (int i = 1; i <= quantity; i++) {
			String prompt = buildPrompt();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(apiKey);

			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("model", "command-r-plus");
			requestBody.put("message", prompt);
			requestBody.put("temperature", 0.7);
			requestBody.put("max_tokens", 300);

			String bodyJson = objectMapper.writeValueAsString(requestBody);
			HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				String text = parseTextFromResponse(response.getBody());
				Part5DTO question = parsePart5FromText(text);
				result.add(question);
			} else {
				throw new RuntimeException("Lỗi gọi API Cohere: " + response.getStatusCode());
			}
		}

		return result;
	}

	private String buildPrompt() {
		return "Sinh câu hỏi TOEIC Part 5 (Hoàn thành câu) đầy đủ và chất lượng theo định dạng sau:\n" + "(A) ...\n"
				+ "(B) ...\n" + "(C) ...\n" + "(D) ...\n" + "Đáp án đúng chỉ 1 ký tự A/B/C/D.\n"
				+ "Giải thích bằng tiếng Việt.\n" + "Trả lời theo định dạng:\n" + "Câu hỏi: <câu hỏi>\n" + "(A) ...\n"
				+ "(B) ...\n" + "(C) ...\n" + "(D) ...\n" + "Đáp án: <A/B/C/D>\n" + "Giải thích: <giải thích>";
	}

	private String parseTextFromResponse(String responseBody) throws Exception {
		JsonNode root = objectMapper.readTree(responseBody);
		return root.path("text").asText(); // Cohere trả về response có trường "text"
	}

	private Part5DTO parsePart5FromText(String text) {
		Part5DTO dto = new Part5DTO();

		String[] lines = text.split("\\r?\\n");
		StringBuilder questionBuilder = new StringBuilder();
		String optionA = "", optionB = "", optionC = "", optionD = "";
		String correctAnswer = "", explanation = "";

		for (String line : lines) {
			line = line.trim();
			if (line.startsWith("Câu hỏi:")) {
				questionBuilder.append(line.substring(8).trim());
			} else if (line.startsWith("(A)")) {
				optionA = line;
			} else if (line.startsWith("(B)")) {
				optionB = line;
			} else if (line.startsWith("(C)")) {
				optionC = line;
			} else if (line.startsWith("(D)")) {
				optionD = line;
			} else if (line.startsWith("Đáp án:")) {
				correctAnswer = line.substring(7).trim();
			} else if (line.startsWith("Giải thích:")) {
				explanation = line.substring(11).trim();
			}
		}

		dto.setQuestion(questionBuilder.toString());
		dto.setOptionA(optionA);
		dto.setOptionB(optionB);
		dto.setOptionC(optionC);
		dto.setOptionD(optionD);
		dto.setCorrectAnswer(correctAnswer);
		dto.setExplanation(explanation);

		return dto;
	}

	@Override
	public List<Part6DTO> generatePart6Questions(int quantity) throws Exception {
		List<Part6DTO> result = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			String prompt = buildPart6Prompt();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(apiKey);

			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("model", "command-r-plus");
			requestBody.put("message", prompt);
			requestBody.put("temperature", 0.7);
			requestBody.put("max_tokens", 600);

			String bodyJson = objectMapper.writeValueAsString(requestBody);
			HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				String text = parseTextFromResponse(response.getBody());
				Part6DTO dto = parsePart6FromText(text);
				result.add(dto);
			} else {
				throw new RuntimeException("Lỗi gọi API Cohere: " + response.getStatusCode());
			}
		}

		return result;
	}

	private String buildPart6Prompt() {
		return "Sinh 1 đoạn văn tiếng Anh TOEIC Part 6 (Điền từ vào đoạn văn) đầy đủ và chất lượng để làm đề thi, kèm theo các chỗ trống và 4 lựa chọn A, B, C, D cho mỗi chỗ trống theo định dạng sau:\n"
				+ "Script: <đoạn văn tiếng Anh có chỗ trống, ví dụ: \"The meeting will be held ___ Friday.\">\n\n"
				+ "Blank 1:\n" + "(A) on\n" + "(B) in\n" + "(C) at\n" + "(D) by\n" + "Correct answer: <A/B/C/D>\n"
				+ "Explanation: <giải thích bằng tiếng Việt>\n\n" + "Blank 2:\n" + "(A) ...\n" + "(B) ...\n"
				+ "(C) ...\n" + "(D) ...\n" + "Correct answer: ...\n" + "Explanation: ...\n\n"
				+ "Lưu ý: Có khoảng 4 chỗ trống, mỗi chỗ có 4 lựa chọn và đáp án đúng, giải thích bằng tiếng Việt.";
	}

	private Part6DTO parsePart6FromText(String text) {
		Part6DTO part6 = new Part6DTO();
		List<Part6DTO.Part6QuestionDTO> questions = new ArrayList<>();

		String[] lines = text.split("\\r?\\n");
		StringBuilder scriptBuilder = new StringBuilder();

		boolean scriptStarted = false;
		boolean scriptEnded = false;

		String optionA = "", optionB = "", optionC = "", optionD = "", correctAnswer = "", explanation = "";

		for (String line : lines) {
			line = line.trim();

			if (line.startsWith("Script:")) {
				scriptStarted = true;
				scriptBuilder.append(line.substring(7).trim()).append(" ");
			} else if (scriptStarted && !scriptEnded) {
				if (line.toLowerCase().startsWith("blank")) {
					scriptEnded = true;
				} else {
					scriptBuilder.append(line).append(" ");
					continue;
				}
			}

			if (line.toLowerCase().startsWith("blank")) {
				// Nếu đã có option của blank trước thì lưu lại câu hỏi
				if (!optionA.isEmpty()) {
					Part6DTO.Part6QuestionDTO q = new Part6DTO.Part6QuestionDTO(optionA, optionB, optionC, optionD,
							correctAnswer, explanation);
					questions.add(q);
					optionA = optionB = optionC = optionD = correctAnswer = explanation = "";
				}
			} else if (line.startsWith("(A)")) {
				optionA = line;
			} else if (line.startsWith("(B)")) {
				optionB = line;
			} else if (line.startsWith("(C)")) {
				optionC = line;
			} else if (line.startsWith("(D)")) {
				optionD = line;
			} else if (line.toLowerCase().startsWith("correct answer:")) {
				correctAnswer = line.substring(15).trim();
			} else if (line.toLowerCase().startsWith("explanation:")) {
				explanation = line.substring(12).trim();
			}
		}

		// Thêm câu hỏi cuối cùng
		if (!optionA.isEmpty()) {
			Part6DTO.Part6QuestionDTO q = new Part6DTO.Part6QuestionDTO(optionA, optionB, optionC, optionD,
					correctAnswer, explanation);
			questions.add(q);
		}

		part6.setScript(scriptBuilder.toString().trim());
		part6.setQuestions(questions);

		return part6;
	}

	@Override
	public List<Part7DTO> generatePart7Questions(int quantity) throws Exception {
		List<Part7DTO> result = new ArrayList<>();

		for (int i = 0; i < quantity; i++) {
			String prompt = buildPart7Prompt();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(apiKey);

			Map<String, Object> requestBody = new HashMap<>();
			requestBody.put("model", "command-r-plus");
			requestBody.put("message", prompt);
			requestBody.put("temperature", 0.7);
			requestBody.put("max_tokens", 600);

			String bodyJson = objectMapper.writeValueAsString(requestBody);
			HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				String text = parseTextFromResponse(response.getBody());
				Part7DTO dto = parsePart7FromText(text);
				result.add(dto);
			} else {
				throw new RuntimeException("Lỗi gọi API Cohere: " + response.getStatusCode());
			}
		}

		return result;
	}

	private String buildPart7Prompt() {
		return "Sinh 1 đoạn văn tiếng Anh (script) TOEIC Part 7 (Đọc hiểu đoạn văn) đầy đủ và chất lượng để làm đề thi, và 4 câu hỏi liên quan theo định dạng sau:\n"
				+ "Script: <đoạn văn tiếng Anh>\n\n" + "Câu hỏi 1: <nội dung>\n" + "(A) ...\n" + "(B) ...\n"
				+ "(C) ...\n" + "(D) ...\n" + "Đáp án: <A/B/C/D>\n" + "Giải thích: <tiếng Việt>\n\n"

				+ "Câu hỏi 2: ... (tương tự)\n\n" + "Câu hỏi 3: ...\n\n" + "Câu hỏi 4: ...\n\n"
				+ "Lưu ý: Mỗi câu hỏi chỉ có 1 đáp án đúng và có giải thích bằng tiếng Việt.";
	}

	private Part7DTO parsePart7FromText(String text) {
		Part7DTO part7 = new Part7DTO();
		List<Part7QuestionDTO> questions = new ArrayList<>();

		String[] lines = text.split("\\r?\\n");
		StringBuilder scriptBuilder = new StringBuilder();

		boolean scriptStarted = false;
		boolean scriptEnded = false;

		String questionText = "", optionA = "", optionB = "", optionC = "", optionD = "", correctAnswer = "",
				explanation = "";

		for (String line : lines) {
			line = line.trim();

			if (line.startsWith("Script:")) {
				scriptStarted = true;
				scriptBuilder.append(line.substring(7).trim()).append(" ");
			} else if (scriptStarted && !scriptEnded) {
				if (line.toLowerCase().startsWith("câu hỏi")) {
					scriptEnded = true;
				} else {
					scriptBuilder.append(line).append(" ");
					continue;
				}
			}

			if (line.toLowerCase().startsWith("câu hỏi")) {
				if (!questionText.isEmpty()) {
					Part7DTO.Part7QuestionDTO q = new Part7DTO.Part7QuestionDTO(questionText, optionA, optionB, optionC,
							optionD, correctAnswer, explanation);
					questions.add(q);

					questionText = optionA = optionB = optionC = optionD = correctAnswer = explanation = "";
				}
				questionText = line.substring(line.indexOf(":") + 1).trim();
			} else if (line.startsWith("(A)")) {
				optionA = line;
			} else if (line.startsWith("(B)")) {
				optionB = line;
			} else if (line.startsWith("(C)")) {
				optionC = line;
			} else if (line.startsWith("(D)")) {
				optionD = line;
			} else if (line.startsWith("Đáp án:")) {
				correctAnswer = line.substring(7).trim();
			} else if (line.startsWith("Giải thích:")) {
				explanation = line.substring(11).trim();
			}
		}

		// Thêm câu hỏi cuối cùng nếu có
		if (!questionText.isEmpty()) {
			Part7DTO.Part7QuestionDTO q = new Part7DTO.Part7QuestionDTO(questionText, optionA, optionB, optionC,
					optionD, correctAnswer, explanation);
			questions.add(q);
		}

		part7.setScript(scriptBuilder.toString().trim());
		part7.setQuestions(questions);

		return part7;
	}

	private String buildPrompt(String userPrompt) {
		userPrompt = userPrompt.trim().toLowerCase();

		if (userPrompt.startsWith("dịch") || userPrompt.contains("tiếng anh") || userPrompt.contains("tiếng việt")) {
			return "Bạn là một trợ lý học ngôn ngữ. Hãy dịch câu sau một cách chính xác:\n" + userPrompt;
		} else if (userPrompt.contains("ngữ pháp") || userPrompt.startsWith("giải thích")) {
			return "Bạn là một giáo viên tiếng Anh. Hãy giải thích ngữ pháp một cách rõ ràng và dễ hiểu:\n"
					+ userPrompt;
		} else {
			return "Bạn là một trợ lý học tiếng Anh thân thiện. Hãy trả lời câu hỏi sau:\n" + userPrompt;
		}
	}

	@Override
	public String askQuestion(String userPrompt) {
	    try {
	        String prompt = buildPrompt(userPrompt);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(apiKey);

	        Map<String, Object> requestBody = new HashMap<>();
	        requestBody.put("model", "command-r-plus");
	        requestBody.put("message", prompt);
	        requestBody.put("temperature", 0.7);
	        requestBody.put("max_tokens", 300);

	        String bodyJson = objectMapper.writeValueAsString(requestBody);
	        HttpEntity<String> request = new HttpEntity<>(bodyJson, headers);

	        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            return parseTextFromResponse(response.getBody());
	        } else {
	            logger.error("Lỗi gọi API Cohere: {}", response.getStatusCode());
	            return "Đã xảy ra lỗi khi gọi API. Vui lòng thử lại sau.";
	        }
	    } catch (HttpClientErrorException e) {
	        logger.error("Lỗi HTTP từ Cohere API: {}", e.getResponseBodyAsString());
	        return "Lỗi từ API: " + e.getResponseBodyAsString();
	    } catch (Exception e) {
	        logger.error("Lỗi hệ thống: ", e);
	        return "Đã xảy ra lỗi hệ thống. Vui lòng thử lại.";
	    }
	}

}
