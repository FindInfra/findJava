package com.find.findcore.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.find.findcore.service.AWSS3Service;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3ServiceImpl.class);

	@Autowired
	private AmazonS3 amazonS3;
	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	private final static String URL = "https://findimagevideo.s3.ap-southeast-1.amazonaws.com";

	@Override
	// @Async annotation ensures that the method is executed in a different
	// background thread
	// but not consume the main thread.
	@Async
	public String uploadFile(final MultipartFile multipartFile, String folder) {
		LOGGER.info("File upload in progress.");
		try {
			final File file = convertMultiPartFileToFile(multipartFile);
			String uniqueFileName = uploadFileToS3Bucket(bucketName + folder, file);
			LOGGER.info("File upload is completed.");
			file.delete(); // To remove the file locally created in the project folder.
			String finalUrl = URL + folder + "/" + uniqueFileName;
			return finalUrl;
		} catch (final AmazonServiceException ex) {
			LOGGER.info("File upload is failed.");
			LOGGER.error("Error= {} while uploading file.", ex.getMessage());
		}
		return null;
	}

	@Override
	public List<String> uploadFiles(MultipartFile[] files, String folder) {
		LOGGER.info("File upload in progress.");
		List<String> fileNames = new ArrayList<>();
		try {
			Arrays.asList(files).stream().forEach(multipartFile -> {
				final File file = convertMultiPartFileToFile(multipartFile);
				String uniqueFileName = uploadFileToS3Bucket(bucketName + folder, file);
				LOGGER.info("File upload is completed.");
				file.delete(); // To remove the file locally created in the project folder.
				String finalUrl = URL + folder + "/" + uniqueFileName;
				fileNames.add(finalUrl);
			});
			return fileNames;
		} catch (final AmazonServiceException ex) {
			LOGGER.info("File upload is failed.");
			LOGGER.error("Error= {} while uploading file.", ex.getMessage());
		}
		return null;
	}

	@Override
	public String deleteFile(String uniqueFileName, String folder) {
		try {
			deleteFileToS3Bucket(bucketName + folder, uniqueFileName);
			return uniqueFileName;
		} catch (final AmazonServiceException ex) {
			LOGGER.info("File delete is failed.");
			LOGGER.error("Error= {} while deleting file.", ex.getMessage());
		}
		return null;
	}

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

	private String uploadFileToS3Bucket(final String bucketName, final File file) {
		final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
		LOGGER.info("Uploading file with name= " + uniqueFileName);
		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
		amazonS3.putObject(putObjectRequest);
		return uniqueFileName;
	}

	private void deleteFileToS3Bucket(final String bucketName, String uniqueFileName) {
		LOGGER.info("Deleting file with name= " + uniqueFileName);
		final DeleteObjectRequest putObjectRequest = new DeleteObjectRequest(bucketName, uniqueFileName);
		amazonS3.deleteObject(putObjectRequest);
	}

}