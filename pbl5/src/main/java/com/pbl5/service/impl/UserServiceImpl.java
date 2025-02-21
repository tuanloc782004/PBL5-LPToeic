package com.pbl5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pbl5.model.User;
import com.pbl5.repository.UserRepository;
import com.pbl5.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        try {
            return this.userRepository.findByUsername(username);
        } catch (Exception e) {
            logger.error("Lỗi khi tìm user với username: " + username, e);
            return null;
        }
    }

    @Override
    public List<User> findByKeyword(String keyword) {
        try {
            return this.userRepository.findByKeyword(keyword);
        } catch (Exception e) {
            logger.error("Lỗi khi tìm user với keyword: " + keyword, e);
            return List.of(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    @Override
    public Page<User> findAll(Integer pageno) {
        try {
            Pageable pageable = PageRequest.of(pageno - 1, 5);
            return this.userRepository.findAll(pageable);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách user trang " + pageno, e);
            return Page.empty(); // Trả về trang rỗng nếu có lỗi
        }
    }

    @Override
    public Page<User> findByKeyword(String keyword, Integer pageNo) {
        try {
            List<User> list = this.userRepository.findByKeyword(keyword);
            Pageable pageable = PageRequest.of(pageNo - 1, 5);
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), list.size());

            if (start > list.size()) {
                return Page.empty();
            }

            List<User> sublist = list.subList(start, end);
            return new PageImpl<>(sublist, pageable, list.size());
        } catch (Exception e) {
            logger.error("Lỗi khi tìm user theo keyword: " + keyword + " trang " + pageNo, e);
            return Page.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new EntityNotFoundException("User với ID " + id + " không tồn tại.");
            }
            this.userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi khi xóa user với ID: " + id, e);
        }
    }
}