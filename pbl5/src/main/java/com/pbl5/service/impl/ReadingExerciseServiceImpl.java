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

import com.pbl5.model.ReadingExercise;
import com.pbl5.repository.ReadingExerciseRepository;
import com.pbl5.service.ReadingExerciseService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReadingExerciseServiceImpl implements ReadingExerciseService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private ReadingExerciseRepository readingExerciseRepository;

	@Override
	public List<ReadingExercise> findByKeywordAndPart5sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.findByKeywordAndPart5sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện đọc phần 5 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ReadingExercise> findByPart5sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.readingExerciseRepository.findByPart5sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 5 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ReadingExercise> findByKeywordAndPart5sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ReadingExercise> list = this.readingExerciseRepository.findByKeywordAndPart5sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ReadingExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện đọc phần 5 theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			if (!readingExerciseRepository.existsById(id)) {
				throw new EntityNotFoundException("Bài luyện đọc phần 5 với ID " + id + " không tồn tại.");
			}
			this.readingExerciseRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("Lỗi khi xóa bài luyện đọc phần 5 với ID: " + id, e);
		}
	}

	@Override
	public ReadingExercise save(ReadingExercise readingExercise) {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.save(readingExercise);
		} catch (Exception e) {
			logger.error("Lỗi khi lưu bài bài luyện đọc phần 5: " + readingExercise, e);
			throw new RuntimeException("Không thể lưu bài luyện đọc phần 5", e);
		}
	}

	@Override
	public List<ReadingExercise> findByKeywordAndPart6sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.findByKeywordAndPart6sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện đọc phần 6 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ReadingExercise> findByPart6sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.readingExerciseRepository.findByPart6sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 6 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ReadingExercise> findByKeywordAndPart6sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ReadingExercise> list = this.readingExerciseRepository.findByKeywordAndPart6sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ReadingExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện đọc phần 6 theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	@Override
	public List<ReadingExercise> findByKeywordAndPart7sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.findByKeywordAndPart7sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện đọc phần 7 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ReadingExercise> findByPart7sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.readingExerciseRepository.findByPart7sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 7 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ReadingExercise> findByKeywordAndPart7sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ReadingExercise> list = this.readingExerciseRepository.findByKeywordAndPart7sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ReadingExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện đọc phần 7 theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	@Override
	public ReadingExercise findById(Long id) {
		try {
			return this.readingExerciseRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Bài luyện đọc với ID " + id + " không tồn tại."));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện đọc với ID: " + id, e);
			return null;
		}
	}

	@Override
	public List<ReadingExercise> findByPart5sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.findByPart5sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 5: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public List<ReadingExercise> findByPart6sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.findByPart6sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 6: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public List<ReadingExercise> findByPart7sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.readingExerciseRepository.findByPart7sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện đọc phần 7: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public long countAllReadingExercises() {
		// TODO Auto-generated method stub
		try {
			return readingExerciseRepository.count();
		} catch (Exception e) {
			logger.error("Lỗi khi đếm tổng số bài luyện đọc hiểu", e);
			return -1;
		}
	}
}
