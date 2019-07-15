package com.bonuspoint.rest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bonuspoint.exception.FileStorageException;
import com.bonuspoint.exception.MyFileNotFoundException;
import com.bonuspoint.exception.ResourceNotFoundException;
import com.bonuspoint.model.Merchant;
import com.bonuspoint.model.Project;
import com.bonuspoint.property.FileStorageProperties;
import com.bonuspoint.repository.MerchantRepository;
import com.bonuspoint.repository.ProjectRepository;

@Service
public class FileStorageService {
	
	@Autowired
	MerchantRepository mRepository;
	
	@Autowired 
	ProjectRepository pRepository;
	
	private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

	public String storeFile(MultipartFile file , String ID) {
		// Normalize file name
        String oldfileName = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = FilenameUtils.getExtension(oldfileName);
        String fileName = "logo_"+ ID + "." + ext;

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}

	public Resource loadFileAsResource(String fileName) {
		try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
	}

	public void checkMerchant(String iD) {
		if(mRepository.findByMerchantID(iD)== null) {
			throw new ResourceNotFoundException("Merchant", "merchantID", iD);
		}
		
	}

	public void checkProject(String iD) {
		if(pRepository.findByProjectID(iD)== null) {
			throw new ResourceNotFoundException("Project", "projectID", iD);
		}
		
	}

	public void updateMerchant(String iD, String fileDownloadUri) {
		Merchant mer = mRepository.findByMerchantID(iD);
		mer.setLogo(fileDownloadUri);
		mRepository.save(mer);
	}

	public void updateProject(String iD, String fileDownloadUri) {
		Project proj = pRepository.findByProjectID(iD);
		proj.setLogo(fileDownloadUri);
		pRepository.save(proj);
		
	}

}
