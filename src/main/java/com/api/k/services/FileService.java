package com.api.k.services;

import com.ofxr.OFXProcess;
import com.ofxr.dtos.AccountStatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileService {

    private final OFXProcess ofxProcess;

    @Autowired
    public FileService(OFXProcess ofxProcess) {
        this.ofxProcess = ofxProcess;
    }

    public AccountStatementDto uploadOFX(MultipartFile file) throws Exception {

        try {
            InputStream inputStream = file.getInputStream();
            return ofxProcess.processOFX(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
