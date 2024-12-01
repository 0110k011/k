package com.api.k.controllers;

import com.api.k.services.FileService;
import com.ofxr.dtos.AccountStatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/ofx", consumes = "multipart/form-data")
    public AccountStatementDto uploadOFX(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.uploadOFX(file);
    }

}

