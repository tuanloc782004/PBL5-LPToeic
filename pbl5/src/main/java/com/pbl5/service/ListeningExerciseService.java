package com.pbl5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbl5.model.ListeningExercise;

public interface ListeningExerciseService {

	public List<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword);

	public Page<ListeningExercise> findByPart1sIsNotEmpty(Integer pageno);

	public Page<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword, Integer pageNo);

	public void deleteById(Long id);

	public ListeningExercise save(ListeningExercise listeningExercise);

}
