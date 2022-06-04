package com.find.findcore.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

	String uploadFile(MultipartFile multipartFile, String folder);

	List<String> uploadFiles(MultipartFile[] multipartFile, String folder);

	String deleteFile(String uniqueFileName, String folder);

}