//package com.pbl5.controller.user;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.pbl5.model.User;
//import com.pbl5.service.UserService;
//
//@Controller
//public class UpdatePasswordController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @PostMapping("/user/account-information/update-password")
//    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
//                                 @RequestParam("newPassword") String newPassword,
//                                 @RequestParam("confirmPassword") String confirmPassword,
//                                 RedirectAttributes redirectAttributes) {
//        // Lấy thông tin người dùng hiện tại
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = auth.getName();
//        User currentUser = userService.findByUsername(currentUsername);
//
//        // Kiểm tra mật khẩu cũ
//        if (!passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu cũ không đúng.");
//            return "redirect:/user/account-information";
//        }
//
//        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
//        if (!newPassword.equals(confirmPassword)) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
//            return "redirect:/user/account-information";
//        }
//
//        // Cập nhật mật khẩu mới
//        currentUser.setPassword(passwordEncoder.encode(newPassword));  // Mã hóa mật khẩu mới
//        userService.save(currentUser);
//
//        // Cập nhật thông tin xác thực của người dùng
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(currentUser.getUsername(), currentUser.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(newAuth);
//
//        redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu đã được cập nhật thành công.");
//        return "redirect:/user/account-information";
//    }
//}
