package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.ReadingExercise;

public interface ReadingExerciseService {

	public List<ReadingExercise> findByKeywordAndPart5sIsNotEmpty(String keyword);

	public Page<ReadingExercise> findByPart5sIsNotEmpty(Integer pageno);

	public Page<ReadingExercise> findByKeywordAndPart5sIsNotEmpty(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public ReadingExercise save(ReadingExercise readingExercise);

	public List<ReadingExercise> findByKeywordAndPart6sIsNotEmpty(String keyword);

	public List<ReadingExercise> findByKeywordAndPart7sIsNotEmpty(String keyword);

	public Page<ReadingExercise> findByPart6sIsNotEmpty(Integer pageno);

	public Page<ReadingExercise> findByPart7sIsNotEmpty(Integer pageno);

	public Page<ReadingExercise> findByKeywordAndPart6sIsNotEmpty(String keyword, Integer pageNo);

	public Page<ReadingExercise> findByKeywordAndPart7sIsNotEmpty(String keyword, Integer pageNo);

	public ReadingExercise findById(Long id);

	public List<ReadingExercise> findByPart5sIsNotEmpty();

}
