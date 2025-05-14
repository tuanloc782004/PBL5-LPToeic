package com.pbl5.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("requestURI")
    public String populateRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
