package com.pbl5.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatbotViewController {

    @GetMapping("/user/chatbot")
    public String showChatbotPage() {
        return "user/chatbot"; 
    }
}
