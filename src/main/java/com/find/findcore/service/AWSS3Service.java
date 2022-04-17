package com.find.findcore.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

	void uploadFile(MultipartFile multipartFile);

}
