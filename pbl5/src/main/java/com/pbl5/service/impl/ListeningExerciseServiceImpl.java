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

	private static final Logger logger = LoggerFactory.getLogger(ListeningExerciseServiceImpl.class);

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

	// ========== PART2 ===========
	@Override
	public List<ListeningExercise> findByKeywordAndPart2sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByKeywordAndPart2sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện nghe phần 2 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByPart2sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.listeningExerciseRepository.findByPart2sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 2 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByKeywordAndPart2sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ListeningExercise> list = this.listeningExerciseRepository.findByKeywordAndPart2sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ListeningExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện nghe phần 2 theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	// ========== PART3 ===========
	@Override
	public List<ListeningExercise> findByKeywordAndPart3sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByKeywordAndPart3sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện nghe phần 3 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByPart3sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.listeningExerciseRepository.findByPart3sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 3 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByKeywordAndPart3sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ListeningExercise> list = this.listeningExerciseRepository.findByKeywordAndPart3sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ListeningExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện nghe phần 3 theo keyword: " + keyword + " trang " + pageNo, e);
			return Page.empty();
		}
	}

	// ========== PART4 ===========
	@Override
	public List<ListeningExercise> findByKeywordAndPart4sIsNotEmpty(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByKeywordAndPart4sIsNotEmpty(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện nghe phần 4 với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByPart4sIsNotEmpty(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.listeningExerciseRepository.findByPart4sIsNotEmpty(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 4 trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<ListeningExercise> findByKeywordAndPart4sIsNotEmpty(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
			List<ListeningExercise> list = this.listeningExerciseRepository.findByKeywordAndPart4sIsNotEmpty(keyword);
			Pageable pageable = PageRequest.of(pageNo - 1, 5);
			int start = (int) pageable.getOffset();
			int end = Math.min(start + pageable.getPageSize(), list.size());

			if (start > list.size()) {
				return Page.empty();
			}

			List<ListeningExercise> sublist = list.subList(start, end);
			return new PageImpl<>(sublist, pageable, list.size());
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài bài luyện nghe phần 4 theo keyword: " + keyword + " trang " + pageNo, e);
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

	@Override
	public ListeningExercise findById(Long id) {
		try {
			return this.listeningExerciseRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Bài luyện nghe với ID " + id + " không tồn tại."));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài luyện nghe với ID: " + id, e);
			return null;
		}
	}

	@Override
	public List<ListeningExercise> findByPart1sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByPart1sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 1: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public List<ListeningExercise> findByPart2sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByPart2sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 2: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public List<ListeningExercise> findByPart3sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByPart3sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 3: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public List<ListeningExercise> findByPart4sIsNotEmpty() {
		// TODO Auto-generated method stub
		try {
			return this.listeningExerciseRepository.findByPart4sIsNotEmpty();
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài luyện nghe phần 4: " + e);
			return List.of(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public long countAllListeningExercises() {
		// TODO Auto-generated method stub
		try {
			return listeningExerciseRepository.count();
		} catch (Exception e) {
			logger.error("Lỗi khi đếm tổng số bài luyện nghe", e);
			return -1;
		}
	}

}
