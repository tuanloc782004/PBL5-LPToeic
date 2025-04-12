package com.pbl5.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.model.Role;
import com.pbl5.repository.RoleRepository;
import com.pbl5.service.RoleService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		try {
			return this.roleRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Role với ID " + id + " không tồn tại."));
		} catch (EntityNotFoundException e) {
			logger.warn(e.getMessage());
			return null;
		} catch (Exception e) {
			logger.error("Lỗi khi tìm role với ID: " + id, e);
			return null;
		}
	}

}
