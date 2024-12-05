package com.api.k.services;

import com.api.k.models.AccountModel;
import com.api.k.models.TransactionModel;
import com.api.k.repositories.TransactionRepository;
import com.ofxr.dtos.TransactionDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public List<TransactionModel> createTransaction(List<TransactionDto> transactionDto, AccountModel account) {

        return transactionDto.stream().map(tx -> {

            Optional<TransactionModel> existingTransaction = transactionRepository.findByTransactionCode(tx.getTransactionCode());

            if (existingTransaction.isPresent()) {
                return existingTransaction.get();
            }

            TransactionModel transaction = new TransactionModel();
            transaction.setAccount(account);
            transaction.setTransactionCode(tx.getTransactionCode());
            transaction.setAmount(tx.getAmount());
            transaction.setDate(tx.getDate());
            transaction.setTransactionType(tx.getTransactionType());
            transaction.setDescription(tx.getDescription());
            return transactionRepository.save(transaction);
        }).toList();
    }

    public List<TransactionModel> getTransactionsByAccount(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

}
