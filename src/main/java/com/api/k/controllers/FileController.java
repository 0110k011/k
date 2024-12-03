package com.api.k.controllers;

import com.api.k.models.AccountModel;
import com.api.k.services.FileService;
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

    @PostMapping(value = "/ofxx", consumes = "multipart/form-data")
    public AccountModel uploadOFX(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.uploadOFX(file);
    }

}

