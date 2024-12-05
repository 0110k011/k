package com.api.k.services;

import com.api.k.models.AccountModel;
import com.ofxr.OFXProcess;
import com.ofxr.dtos.AccountStatementDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileService {

    private final OFXProcess ofxProcess;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public FileService(OFXProcess ofxProcess, AccountService accountService, TransactionService transactionService) {
        this.ofxProcess = ofxProcess;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public AccountModel uploadOFX(MultipartFile file) throws Exception {

        try {
            InputStream inputStream = file.getInputStream();
            AccountStatementDto accountStatement = ofxProcess.processOFX(inputStream);

            AccountModel account = accountService.createAccount(accountStatement);

            transactionService.createTransaction(accountStatement.getTransactions(), account);

            return account;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
