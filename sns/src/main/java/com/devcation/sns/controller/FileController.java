package com.devcation.sns.controller;

import com.devcation.sns.data.FileDTO;
import com.devcation.sns.data.SnsDTO;
import com.devcation.sns.model.FileEntity;
import com.devcation.sns.model.SnsEntity;
import com.devcation.sns.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("res")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/img")
    public ResponseEntity<Resource> downloadFile(@AuthenticationPrincipal String userId,
                                                 @RequestBody FileDTO fileDTO,
                                                 HttpServletRequest request) {
        // Load file as Resource
        log.info("userId{}", userId);

        FileEntity fileEntity = FileDTO.toFileEntity(fileDTO);
        fileEntity.setUserid(userId);

        log.info("fileEntityP{}", fileEntity.toString());
        Resource resource = fileStorageService.loadFileAsResource(fileEntity);


        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/img/{userid}/{id}/{filename}")
    public ResponseEntity<Resource> imgUrl(@PathVariable String userid,
                                           @PathVariable String filename,
                                           @PathVariable String id,
                                           HttpServletRequest request) {
        // Load file as Resource
        log.info("userId {}", userid);


        Resource resource = fileStorageService.loadFileAsResource2(userid+"/"+id+"/"+filename);


        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
