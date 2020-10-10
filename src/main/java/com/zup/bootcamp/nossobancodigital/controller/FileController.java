package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file, @PathVariable("id") String id) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, fileUploadService.saveFile(file, id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") String id) throws Exception{
        Resource resource = fileUploadService.loadFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
        + resource.getFilename() + "\"").body(resource);
    }

}
