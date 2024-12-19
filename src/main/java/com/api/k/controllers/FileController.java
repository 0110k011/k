package com.api.k.controllers;

import com.api.k.models.AccountModel;
import com.api.k.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${g-recaptcha-response.env}")
    private String gRecaptchaResponse;

    @Value("${nfe.url}")
    private String nfeUrl;

    @PostMapping(value = "/ofx", consumes = "multipart/form-data")
    public ResponseEntity<AccountModel> uploadOFX(@RequestParam("file") MultipartFile file) throws Exception {
        return new ResponseEntity<>(fileService.uploadOFX(file), HttpStatus.CREATED);
    }

    @GetMapping(value = "/test")
    public String test(@RequestParam("code") String code) throws Exception {
        return "teste";
    }
}

