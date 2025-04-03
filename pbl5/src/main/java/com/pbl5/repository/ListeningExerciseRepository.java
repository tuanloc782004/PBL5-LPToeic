package com.pbl5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.ListeningExercise;

public interface ListeningExerciseRepository extends JpaRepository<ListeningExercise, Long> {

	@Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part1s) > 0")
    List<ListeningExercise> findByPart1sIsNotEmpty();
	
	@Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part2s) > 0")
    List<ListeningExercise> findByPart2sIsNotEmpty();

    @Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part1s) > 0")
    Page<ListeningExercise> findByPart1sIsNotEmpty(Pageable pageable);

    @Query("SELECT l FROM ListeningExercise l WHERE l.exerciseName LIKE %:keyword% AND SIZE(l.part1s) > 0")
    List<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword);
    
    @Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part3s) > 0")
    List<ListeningExercise> findByPart3sIsNotEmpty();

    @Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part3s) > 0")
    Page<ListeningExercise> findByPart3sIsNotEmpty(Pageable pageable);

    @Query("SELECT l FROM ListeningExercise l WHERE l.exerciseName LIKE %:keyword% AND SIZE(l.part3s) > 0")
    List<ListeningExercise> findByKeywordAndPart3sIsNotEmpty(String keyword);
    
    @Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part4s) > 0")
    List<ListeningExercise> findByPart4sIsNotEmpty();

    @Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part4s) > 0")
    Page<ListeningExercise> findByPart4sIsNotEmpty(Pageable pageable);

    @Query("SELECT l FROM ListeningExercise l WHERE l.exerciseName LIKE %:keyword% AND SIZE(l.part4s) > 0")
    List<ListeningExercise> findByKeywordAndPart4sIsNotEmpty(String keyword);
    
}
