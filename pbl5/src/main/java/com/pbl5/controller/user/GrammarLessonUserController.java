package com.pbl5.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbl5.markdown.MarkdownService;
import com.pbl5.model.GrammarLesson;
import com.pbl5.service.GrammarLessonService;

@Controller
@RequestMapping("/user/grammar-lesson")
public class GrammarLessonUserController {

    private static final Logger logger = LoggerFactory.getLogger(GrammarLessonUserController.class);

    @Autowired
    private GrammarLessonService grammarLessonService;

    @Autowired
    private MarkdownService markdownService;

    
    @RequestMapping("")
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {

        try {
            // Lấy tất cả các bài học ngữ pháp
            Iterable<GrammarLesson> list = this.grammarLessonService.findAll(null);

            // Nếu có từ khóa tìm kiếm, thực hiện tìm kiếm bài học ngữ pháp
            if (keyword != null && !keyword.trim().isEmpty()) {
                list = this.grammarLessonService.findByKeyword(keyword); // Tìm kiếm theo từ khóa
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("list", list);

        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách bài học ngữ pháp: ", e);
            model.addAttribute("errorMessage", "Có lỗi khi tải danh sách bài học ngữ pháp!");
        }

        return "user/grammar-lesson";
    }

    
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        try {
            GrammarLesson grammarLesson = this.grammarLessonService.findById(id);
            if (grammarLesson == null) {
                model.addAttribute("errorMessage", "Không tìm thấy bài học ngữ pháp!");
                return "redirect:/user/grammar-lesson";
            }

            
            grammarLesson.setContent(this.markdownService.convertMarkdownToHtml(grammarLesson.getContent()));
            model.addAttribute("grammarLesson", grammarLesson);
            return "user/grammar-lesson-view";
        } catch (Exception e) {
            logger.error("Lỗi khi lấy bài học ngữ pháp với ID: " + id, e);
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi lấy thông tin bài học!");
            return "redirect:/user/grammar-lesson";
        }
    }
}
