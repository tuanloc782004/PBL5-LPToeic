package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.MockExam;

public interface MockExamService {

	public List<MockExam> findByKeyword(String keyword);

	public Page<MockExam> findAll(Integer pageno);

	public Page<MockExam> findByKeyword(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public MockExam save(MockExam mockExam);

	public MockExam findById(Long id);
	
	public List<MockExam> findAll();
	
	public long countAllMockExams(); 

}
