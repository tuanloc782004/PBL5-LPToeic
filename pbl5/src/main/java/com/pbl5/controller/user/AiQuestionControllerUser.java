package com.pbl5.controller.user;

import com.pbl5.dto.ChatRequest;
import com.pbl5.dto.ChatResponse;
import com.pbl5.ai.AiQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/chatbot")
public class AiQuestionControllerUser {

    @Autowired
    private AiQuestionService aiQuestionService;

    @PostMapping("/ask")
    public ResponseEntity<ChatResponse> askQuestion(@RequestBody ChatRequest chatRequest) {
        String prompt = chatRequest.getPrompt();
        String reply = aiQuestionService.askQuestion(prompt);
        return ResponseEntity.ok(new ChatResponse(reply));
    }
}
