package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.NossoBancoDigitalApplication;
import com.zup.bootcamp.nossobancodigital.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(NossoBancoDigitalApplication.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file, @PathVariable("id") String id) throws IOException {
        logger.info("Requisição de upload de imagem");
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, fileService.saveFile(file, id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") String id) throws FileNotFoundException {
        logger.info("Requisição de download de imagem");
        Resource resource = fileService.loadFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

}
