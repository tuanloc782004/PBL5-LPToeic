package com.pbl5.ai;

import java.util.List;

import com.pbl5.dto.Part5DTO;
import com.pbl5.dto.Part6DTO;
import com.pbl5.dto.Part7DTO;

public interface AiQuestionService {

	public List<Part5DTO> generatePart5Questions(int quantity) throws Exception;
	
	public List<Part6DTO> generatePart6Questions(int quantity) throws Exception;
	
	public List<Part7DTO> generatePart7Questions(int quantity) throws Exception;

}
