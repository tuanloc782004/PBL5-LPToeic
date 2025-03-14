package com.pbl5.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.Part4;
import com.pbl5.repository.Part4Repository;
import com.pbl5.service.Part4Service;

@Service
public class Part4ServiceImpl implements Part4Service {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private Part4Repository part4Repository;

	@Override
	public Part4 save(Part4 part4) {
		// TODO Auto-generated method stub
		try {
			return this.part4Repository.save(part4);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài luyện nghe phần 4: " + part4, e);
			throw new RuntimeException("Không thể lưu bài luyện nghe phần 4", e);
		}
	}

}
