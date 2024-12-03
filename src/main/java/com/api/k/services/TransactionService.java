package com.api.k.services;

import com.api.k.models.TransactionModel;
import com.api.k.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionModel createTransaction(TransactionModel transactionModel) {
        return transactionRepository.save(transactionModel);
    }

    public List<TransactionModel> getTransactionsByAccount(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

}
