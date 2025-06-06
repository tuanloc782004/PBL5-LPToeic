package com.pbl5.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    private final Path rootLocation;

    public StorageServiceImpl() {
        this.rootLocation = Paths.get("src/main/resources/static/upload-dir");
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            logger.info("Storage directory initialized at: {}", rootLocation);
        } catch (IOException e) {
            logger.error("Lỗi khi khởi tạo thư mục lưu trữ", e);
            throw new RuntimeException("Không thể khởi tạo thư mục lưu trữ", e);
        }
    }

    @Override
    public void storage(MultipartFile file, String url) {
        if (file.isEmpty()) {
            logger.warn("File tải lên rỗng: {}", url);
            throw new IllegalArgumentException("Không thể lưu file rỗng.");
        }

        Path destinationFile = rootLocation.resolve(Paths.get(url)).normalize().toAbsolutePath();

        if (!destinationFile.startsWith(rootLocation.toAbsolutePath())) {
            logger.warn("Cố gắng lưu file ngoài thư mục được phép: {}", destinationFile);
            throw new SecurityException("Không thể lưu file ngoài thư mục upload-dir.");
        }

        try {
            Files.createDirectories(destinationFile.getParent());

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                logger.info("Lưu file thành công: {}", destinationFile);
            }
        } catch (IOException e) {
            logger.error("Lỗi khi lưu file: {}", url, e);
            throw new RuntimeException("Lỗi khi lưu file", e);
        }
    }
    
}


// StorageServiceImpl là một triển khai cụ thể của interface StorageService, 
// chịu trách nhiệm lưu trữ file trong một thư mục trên hệ thống tệp.