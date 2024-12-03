package com.api.k.services;

import com.api.k.models.AccountModel;
import com.api.k.models.TransactionModel;
import com.ofxr.OFXProcess;
import com.ofxr.dtos.AccountStatementDto;
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

    public AccountModel uploadOFX(MultipartFile file) throws Exception {

        try {
            InputStream inputStream = file.getInputStream();
            AccountStatementDto accountStatement = ofxProcess.processOFX(inputStream);

            AccountModel account = new AccountModel();
            account.setOrganization(accountStatement.getOrganization());
            account.setCurrency(accountStatement.getCurrency());
            account.setBankCod(accountStatement.getBankId());
            account.setBranchCod(accountStatement.getBranchId());
            account.setAccountCod(accountStatement.getAccountId());
            account.setAccountType(accountStatement.getAccountType());

            AccountModel savedAccount = accountService.createAccount(account);

            accountStatement.getTransactions().forEach(tx -> {
                TransactionModel transaction = new TransactionModel();
                transaction.setAccount(savedAccount);
                transaction.setTransactionCod(tx.getTransactionId());
                transaction.setAmount(tx.getAmount());
                transaction.setDate(tx.getDate());
                transactionService.createTransaction(transaction);
            });

            return savedAccount;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
