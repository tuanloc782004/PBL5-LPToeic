package com.pbl5.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public void init();

	public void storage(MultipartFile file, String url);
	
}


// StorageService là một interface trong Java được sử dụng để quản lý lưu trữ tệp tin (file storage) trong một ứng dụng Spring Boot.