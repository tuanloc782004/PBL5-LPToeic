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

import com.pbl5.model.GrammarLesson;
import com.pbl5.repository.GrammarLessonRepository;
import com.pbl5.service.GrammarLessonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GrammarLessonServiceImpl implements GrammarLessonService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private GrammarLessonRepository grammarLessonRepository;

	@Override
	public List<GrammarLesson> findByKeyword(String keyword) {
		try {
			return this.grammarLessonRepository.findByKeyword(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học ngữ pháp với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<GrammarLesson> findAll(Integer pageno) {
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.grammarLessonRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài học ngữ pháp trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<GrammarLesson> findByKeyword(String keyword, Integer pageNo) {
		try {
			List<GrammarLesson> list = this.grammarLessonRepository.findByKeyword(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<GrammarLesson> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học ngữ pháp theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			if (!grammarLessonRepository.existsById(id)) {
				throw new EntityNotFoundException("Bài học ngữ pháp với ID " + id + " không tồn tại.");
			}
			this.grammarLessonRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài học ngữ pháp với ID: " + id, e);
		}
	}

	@Override
	public GrammarLesson save(GrammarLesson grammarLesson) {
		try {
			return this.grammarLessonRepository.save(grammarLesson);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài học ngữ pháp: " + grammarLesson, e);
			throw new RuntimeException("Không thể lưu bài học ngữ pháp", e);
		}
	}

	@Override
	public GrammarLesson findById(Long id) {
		try {
			return this.grammarLessonRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài học ngữ pháp với ID: " + id));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null; // Hoặc có thể throw lại exception nếu muốn
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học ngữ pháp với ID: " + id, e);
			return null;
		}
	}

	@Override
	public List<GrammarLesson> findAll() {
		try {
			return this.grammarLessonRepository.findAll();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy tất cả bài học ngữ pháp", e);
			return List.of();
		}
	}

	@Override
	public GrammarLesson findFirstLesson() {
		// TODO Auto-generated method stub
		try {
			return this.grammarLessonRepository.findFirstLesson();
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null; // Hoặc có thể throw lại exception nếu muốn
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài học ngữ pháp: " + e);
			return null;
		}
	}

	@Override
	public long countAllGrammarLessons() {
		// TODO Auto-generated method stub
		try {
			return grammarLessonRepository.count();
		} catch (Exception e) {
			logger.error("Lỗi khi đếm tổng số bài học ngữ pháp", e);
			return -1;
		}
	}

}
