package com.pbl5.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pbl5.model.ListeningExercise;
import com.pbl5.repository.ListeningExerciseRepository;
import com.pbl5.service.ListeningExerciseService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ListeningExerciseServiceImpl implements ListeningExerciseService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private ListeningExerciseRepository listeningExerciseRepository;
	
	@Override
	public List<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByKeywordAndPart1sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện nghe phần 1 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByPart1sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.listeningExerciseRepository.findByPart1sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 1 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByKeywordAndPart1sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ListeningExercise> list = this.listeningExerciseRepository.findByKeywordAndPart1sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ListeningExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện nghe phần 1 theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			if (!listeningExerciseRepository.existsById(id)) {
				throw new EntityNotFoundException("Bài luyện nghe phần 1 với ID " + id + " không tồn tại.");
			}
			this.listeningExerciseRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài luyện nghe phần 1 với ID: " + id, e);
		}
	}

	@Override
	public ListeningExercise save(ListeningExercise listeningExercise) {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.save(listeningExercise);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài bài luyện nghe phần 1: " + listeningExercise, e);
			throw new RuntimeException("Không thể lưu bài bài luyện nghe phần 1", e);
		}
	}

}
