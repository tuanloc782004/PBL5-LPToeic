package com.pbl5.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.Part7;
import com.pbl5.repository.Part7Repository;
import com.pbl5.service.Part7Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Part7ServiceImpl implements Part7Service {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private Part7Repository part7Repository;

	@Override
	public Part7 save(Part7 part7) {
		// TODO Auto-generated method stub
		try {
			return this.part7Repository.save(part7);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài luyện đọc phần 7: " + part7, e);
			throw new RuntimeException("Không thể lưu bài bài luyện đọc phần 7", e);
		}
	}

	@Override
	public List<Part7> findAll() {
		// TODO Auto-generated method stub
		try {
			return part7Repository.findAll();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách Part7", e);
			throw new RuntimeException("Không thể lấy danh sách Part7", e);
		}
	}

	@Override
	public Part7 findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return this.part7Repository.findById(id).orElseThrow(
					() -> new EntityNotFoundException("Bài luyện đọc phần 7 với ID " + id + " không tồn tại."));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện đọc với ID: " + id, e);
			return null;
		}
	}
	
}
