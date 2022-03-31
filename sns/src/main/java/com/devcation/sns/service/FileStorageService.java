package com.devcation.sns.service;


import com.devcation.sns.common.property.FileStorageProperties;
import com.devcation.sns.common.exception.FileStorageException;
import com.devcation.sns.common.exception.MyFileNotFoundException;
import com.devcation.sns.model.FileEntity;
import com.devcation.sns.persistence.FileRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private FileRepository fileRepository;

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

    public List<FileEntity> storeFile(ArrayList<MultipartFile> files, FileEntity entity) {


        for(MultipartFile file : files) {
            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileEntity fileEntity = FileEntity.builder()
                    .userid(entity.getUserid())
                    .snsid(entity.getSnsid())
                    .build();

            log.info("1111111");
            log.info("fileEntity.getId() : {} ", fileEntity.getId());
            log.info("fileEntity.getUserid() : {} ", fileEntity.getUserid());
            log.info("fileEntity.getFilename() : {} ", fileEntity.getFilename());
            log.info("fileEntity.getFilename() : {} ", fileEntity.getSnsid());

            try {
                // Check if the file's name contains invalid characters
                if(fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }

                // Copy file to the target location (Replacing existing file with the same name)


                Path targetLocation = this.fileStorageLocation.resolve(this.fileStorageLocation.toString()+"/" + fileEntity.getUserid() + "/" + fileEntity.getSnsid()+"/"+fileName);
                Files.createDirectories(targetLocation);


                log.info("createDirectories {}: ",targetLocation.toString());
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                fileEntity.setFilename(fileName);
                fileRepository.save(fileEntity);

                log.info("222222");
                log.info("fileEntity.getId() : {} ", fileEntity.getId());
                log.info("fileEntity.getUserid() : {} ", fileEntity.getUserid());
                log.info("fileEntity.getFilename() : {} ", fileEntity.getFilename());
                log.info("fileEntity.getFilename() : {} ", fileEntity.getSnsid());
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }

        }

        return  fileRepository.findByUseridAndSnsid(entity.getUserid(), entity.getSnsid());

    }

    public Resource loadFileAsResource(FileEntity fileEntity) {
        try {

            Path filePath = this.fileStorageLocation.resolve(this.fileStorageLocation.toString()+"/" + fileEntity.getUserid() + "/" + fileEntity.getSnsid()+"/"+fileEntity.getFilename());

            log.info("filePath {}", filePath.toString());

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileEntity.getFilename());
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileEntity.getFilename(), ex);
        }
    }

    public Resource loadFileAsResource2(String filename) {
        try {

            Path filePath = this.fileStorageLocation.resolve(this.fileStorageLocation.toString()+"/" + filename);

            log.info("filePath {}", filePath.toString());

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + filename, ex);
        }
    }
}
