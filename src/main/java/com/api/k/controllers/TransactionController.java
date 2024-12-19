package com.api.k.controllers;

import com.api.k.dtos.TransactionDto;
import com.api.k.models.TransactionModel;
import com.api.k.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<TransactionModel> updateTransaction(@PathVariable(value = "id") UUID id,
                                                              @RequestBody @Valid TransactionDto transactionDto) throws Exception {

        return transactionService.updateTransactionByNFCode(id, transactionDto);

    }

}
