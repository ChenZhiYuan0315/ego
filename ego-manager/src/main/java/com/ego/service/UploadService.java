package com.ego.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
 
	Map<String, Object> upload(MultipartFile file) throws IOException;
}
