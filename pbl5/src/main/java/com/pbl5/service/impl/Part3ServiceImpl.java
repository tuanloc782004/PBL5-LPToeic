package com.pbl5.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.Part3;
import com.pbl5.repository.Part3Repository;
import com.pbl5.service.Part3Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class Part3ServiceImpl implements Part3Service {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private Part3Repository part3Repository;

	@Override
	public Part3 save(Part3 part3) {
		// TODO Auto-generated method stub
		try {
			return this.part3Repository.save(part3);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài luyện nghe phần 3: " + part3, e);
			throw new RuntimeException("Không thể lưu bài luyện nghe phần 3", e);
		}
	}

	@Override
	public List<Part3> findAll() {
		// TODO Auto-generated method stub
		try {
			return part3Repository.findAll();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách Part3", e);
			throw new RuntimeException("Không thể lấy danh sách Part3", e);
		}
	}

	@Override
	public Part3 findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return this.part3Repository.findById(id).orElseThrow(
					() -> new EntityNotFoundException("Bài luyện nghe phần 3 với ID " + id + " không tồn tại."));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện nghe với ID: " + id, e);
			return null;
		}
	}

}
