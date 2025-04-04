package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.ListeningExercise;

public interface ListeningExerciseService {

	public List<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword);

	public Page<ListeningExercise> findByPart1sIsNotEmpty(Integer pageno);

	public Page<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword, Integer pageNo);
	
	public List<ListeningExercise> findByKeywordAndPart2sIsNotEmpty(String keyword);

	public Page<ListeningExercise> findByPart2sIsNotEmpty(Integer pageno);

	public Page<ListeningExercise> findByKeywordAndPart2sIsNotEmpty(String keyword, Integer pageNo);
	
	public List<ListeningExercise> findByKeywordAndPart3sIsNotEmpty(String keyword);

	public Page<ListeningExercise> findByPart3sIsNotEmpty(Integer pageno);

	public Page<ListeningExercise> findByKeywordAndPart3sIsNotEmpty(String keyword, Integer pageNo);
	
	public List<ListeningExercise> findByKeywordAndPart4sIsNotEmpty(String keyword);

	public Page<ListeningExercise> findByPart4sIsNotEmpty(Integer pageno);

	public Page<ListeningExercise> findByKeywordAndPart4sIsNotEmpty(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public ListeningExercise save(ListeningExercise listeningExercise);
	
	public ListeningExercise findById(Long id);
	
	public List<ListeningExercise> findByPart1sIsNotEmpty();
	
	public List<ListeningExercise> findByPart2sIsNotEmpty();

}
