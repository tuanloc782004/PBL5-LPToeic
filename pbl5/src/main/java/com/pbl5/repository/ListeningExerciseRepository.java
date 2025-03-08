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

    @Query("SELECT l FROM ListeningExercise l WHERE SIZE(l.part1s) > 0")
    Page<ListeningExercise> findByPart1sIsNotEmpty(Pageable pageable);

    @Query("SELECT l FROM ListeningExercise l WHERE l.exerciseName LIKE %:keyword% AND SIZE(l.part1s) > 0")
    List<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword);
    
}
