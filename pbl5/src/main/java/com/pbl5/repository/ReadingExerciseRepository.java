package com.pbl5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pbl5.model.ReadingExercise;

public interface ReadingExerciseRepository extends JpaRepository<ReadingExercise, Long> {

    @Query("SELECT r FROM ReadingExercise r WHERE SIZE(r.part5s) > 0")
    List<ReadingExercise> findByPart5sIsNotEmpty();

    @Query("SELECT r FROM ReadingExercise r WHERE SIZE(r.part5s) > 0")
    Page<ReadingExercise> findByPart5sIsNotEmpty(Pageable pageable);

    @Query("SELECT r FROM ReadingExercise r WHERE r.exerciseName LIKE %:keyword% AND SIZE(r.part5s) > 0")
    List<ReadingExercise> findByKeywordAndPart5sIsNotEmpty(String keyword);
    
    @Query("SELECT r FROM ReadingExercise r WHERE SIZE(r.part6s) > 0")
    List<ReadingExercise> findByPart6sIsNotEmpty();

    @Query("SELECT r FROM ReadingExercise r WHERE SIZE(r.part6s) > 0")
    Page<ReadingExercise> findByPart6sIsNotEmpty(Pageable pageable);

    @Query("SELECT r FROM ReadingExercise r WHERE r.exerciseName LIKE %:keyword% AND SIZE(r.part6s) > 0")
    List<ReadingExercise> findByKeywordAndPart6sIsNotEmpty(String keyword);
    
    @Query("SELECT r FROM ReadingExercise r WHERE SIZE(r.part7s) > 0")
    List<ReadingExercise> findByPart7sIsNotEmpty();

    @Query("SELECT r FROM ReadingExercise r WHERE SIZE(r.part7s) > 0")
    Page<ReadingExercise> findByPart7sIsNotEmpty(Pageable pageable);

    @Query("SELECT r FROM ReadingExercise r WHERE r.exerciseName LIKE %:keyword% AND SIZE(r.part7s) > 0")
    List<ReadingExercise> findByKeywordAndPart7sIsNotEmpty(String keyword);
	
}
