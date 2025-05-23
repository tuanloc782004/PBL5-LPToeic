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

import com.pbl5.model.VocabularyLesson;
import com.pbl5.repository.VocabularyLessonRepository;
import com.pbl5.service.VocabularyLessonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VocabularyLessonServiceImpl implements VocabularyLessonService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private VocabularyLessonRepository vocabularyLessonRepository;

	@Override
	public List<VocabularyLesson> findByKeyword(String keyword) {
		try {
			return this.vocabularyLessonRepository.findByKeyword(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học từ vựng với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<VocabularyLesson> findAll(Integer pageno) {
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.vocabularyLessonRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học từ vựng trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<VocabularyLesson> findByKeyword(String keyword, Integer pageNo) {
		try {
			List<VocabularyLesson> list = this.vocabularyLessonRepository.findByKeyword(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<VocabularyLesson> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học từ vựng theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			if (!vocabularyLessonRepository.existsById(id)) {
				throw new EntityNotFoundException("Bài học từ vựng với ID " + id + " không tồn tại.");
			}
			this.vocabularyLessonRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài học từ vựng với ID: " + id, e);
		}
	}

	@Override
	public VocabularyLesson save(VocabularyLesson vocabularyLesson) {
		try {
			return this.vocabularyLessonRepository.save(vocabularyLesson);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài học từ vựng: " + vocabularyLesson, e);
			throw new RuntimeException("Không thể lưu bài học từ vựng", e);
		}
	}

	@Override
	public List<VocabularyLesson> findAll() {
		// TODO Auto-generated method stub
		try {
			return this.vocabularyLessonRepository.findAll();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học từ vựng: " + e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public VocabularyLesson findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return this.vocabularyLessonRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài học từ vựng với ID: " + id));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null; // Hoặc có thể throw lại exception nếu muốn
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học từ vựng với ID: " + id, e);
			return null;
		}
	}

	@Override
	public VocabularyLesson findFirstLesson() {
		try {
			return this.vocabularyLessonRepository.findFirstLesson();
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null; // Hoặc có thể throw lại exception nếu muốn
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học từ vựng: " + e);
			return null;
		}
	}

	@Override
	public long countAllVocabularyLessons() {
		// TODO Auto-generated method stub
		try {
			return vocabularyLessonRepository.count();
		} catch (Exception e) {
			logger.error("Lỗi khi đếm tổng số bài học từ vựng", e);
			return -1;
		}
	}

}
