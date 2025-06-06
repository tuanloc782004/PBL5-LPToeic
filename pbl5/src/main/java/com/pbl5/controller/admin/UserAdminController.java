package com.pbl5.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbl5.model.User;
import com.pbl5.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String list(Model model, @Param("keyword") String keyword,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, RedirectAttributes redirectAttributes) {

        try {
            Page<User> list = this.userService.findAll(pageNo);

            if (keyword != null && !keyword.trim().isEmpty()) {
                list = this.userService.findByKeyword(keyword, pageNo);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("totalPage", list.getTotalPages());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("users", list);

        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách user: ", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi khi tải danh sách tài khoản!");
        }

        return "admin/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            this.userService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa tài khoản thành công!");
        } catch (Exception e) {
            logger.error("Lỗi khi xóa user với ID: " + id, e);
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa tài khoản!");
        }
        return "redirect:/admin/user";
    }
    
}
