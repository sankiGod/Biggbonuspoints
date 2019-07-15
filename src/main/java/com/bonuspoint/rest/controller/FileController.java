package com.bonuspoint.rest.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bonuspoint.exception.CustomErrorException;
import com.bonuspoint.model.UploadFileResponse;
import com.bonuspoint.rest.service.FileStorageService;

@RestController
@RequestMapping("/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageService service;

	@PostMapping("/uploadLogo/{ID}")
	public UploadFileResponse uploadFile(@PathVariable String ID, @PathParam("type") String type,
			@RequestParam("file") MultipartFile file) {
		if (type == null) {
			throw new CustomErrorException("UploadLogo", "Please provide Id Type With The Request", 109);
		} else {
			if (type.equalsIgnoreCase("merchant")) {
				service.checkMerchant(ID);
			} else if (type.equalsIgnoreCase("project")) {
				service.checkProject(ID);
			} else
				throw new CustomErrorException("UploadLogo",
						"Unsupported Type Value. Should be either 'merchant' or 'project'", 110);
		}
		String fileName = service.storeFile(file, ID);

		String fileDownloadUri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/file/downloadLogo/")
				.path(fileName).toUriString();

		if (type.equalsIgnoreCase("merchant")) {
			service.updateMerchant(ID, fileDownloadUri);
		} else {
			service.updateProject(ID, fileDownloadUri);
		}

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadLogo/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = service.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadDocFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = service.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
