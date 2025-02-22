package com.pbl5.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	public void init();

	public void storage(MultipartFile file, String url);
	
}
