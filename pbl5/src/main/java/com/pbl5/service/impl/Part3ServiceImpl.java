package com.pbl5.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.Part3;
import com.pbl5.repository.Part3Repository;
import com.pbl5.service.Part3Service;

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

}
