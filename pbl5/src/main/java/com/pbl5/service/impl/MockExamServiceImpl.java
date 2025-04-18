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

import com.pbl5.model.MockExam;
import com.pbl5.repository.MockExamRepository;
import com.pbl5.service.MockExamService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MockExamServiceImpl implements MockExamService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private MockExamRepository mockExamRepository;

	@Override
	public List<MockExam> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		try {
			return this.mockExamRepository.findByKeyword(keyword);
		} catch (Exception e) {
			logger.error("Lỗi khi tìm bài thi thử với keyword: " + keyword, e);
			return List.of(); // Trả về danh sách rỗng nếu có lỗi
		}
	}

	@Override
	public Page<MockExam> findAll(Integer pageno) {
		// TODO Auto-generated method stub
		try {
			Pageable pageable = PageRequest.of(pageno - 1, 5);
			return this.mockExamRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Lỗi khi lấy danh sách bài thi thử trang " + pageno, e);
			return Page.empty(); // Trả về trang rỗng nếu có lỗi
		}
	}

	@Override
	public Page<MockExam> findByKeyword(String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		try {
            List<MockExam> list = this.mockExamRepository.findByKeyword(keyword);
            Pageable pageable = PageRequest.of(pageNo - 1, 5);
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(), list.size());

            if (start > list.size()) {
                return Page.empty();
            }

            List<MockExam> sublist = list.subList(start, end);
            return new PageImpl<>(sublist, pageable, list.size());
        } catch (Exception e) {
            logger.error("Lỗi khi tìm bài thi thử theo keyword: " + keyword + " trang " + pageNo, e);
            return Page.empty();
        }
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
            if (!mockExamRepository.existsById(id)) {
                throw new EntityNotFoundException("Bài thi thử với ID " + id + " không tồn tại.");
            }
            this.mockExamRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            logger.warn(e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi khi xóa bài thi thử với ID: " + id, e);
        }
	}

	@Override
	public MockExam save(MockExam mockExam) {
		// TODO Auto-generated method stub
		try {
            return this.mockExamRepository.save(mockExam);
        } catch (Exception e) {
            logger.error("Lỗi khi lưu bài thi thử: " + mockExam, e);
            throw new RuntimeException("Không thể lưu bài thi thử", e);
        }
	}

	@Override
	public MockExam findById(Long id) {
		// TODO Auto-generated method stub
		try {
            return this.mockExamRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài thi thử với ID: " + id));
        } catch (EntityNotFoundException e) {
            logger.warn(e.getMessage());
            return null; // Hoặc có thể throw lại exception nếu muốn
        } catch (Exception e) {
            logger.error("Lỗi khi tìm bài thi thử với ID: " + id, e);
            return null;
        }
	}

}
