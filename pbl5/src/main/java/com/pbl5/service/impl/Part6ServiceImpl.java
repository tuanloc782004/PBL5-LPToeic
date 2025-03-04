package com.pbl5.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.Part6;
import com.pbl5.repository.Part6Repository;
import com.pbl5.service.Part6Service;

@Service
public class Part6ServiceImpl implements Part6Service {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private Part6Repository part6Repository;

	@Override
	public Part6 save(Part6 part6) {
		// TODO Auto-generated method stub
		try {
			return this.part6Repository.save(part6);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài luyện đọc phần 6: " + part6, e);
			throw new RuntimeException("Không thể lưu bài bài luyện đọc phần 6", e);
		}
	}

}
