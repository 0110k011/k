package com.api.k.services;

import com.api.k.dtos.TransactionDescriptionDto;
import com.api.k.dtos.TransactionDto;
import com.api.k.models.AccountModel;
import com.api.k.models.TransactionDescriptionModel;
import com.api.k.models.TransactionModel;
import com.api.k.repositories.TransactionRepository;
import com.k.webscraper.WebScraperParser;
import com.k.webscraper.dtos.NFParsedDto;
import com.k.webscraper.dtos.ProductServiceDataDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionService {

    @Value("${g-recaptcha-response.env}")
    private String gRecaptchaResponse;

    @Value("${nfe.url}")
    private String nfeUrl;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public List<TransactionModel> createTransaction(List<com.ofxr.dtos.TransactionDto> transactionDto, AccountModel account) {

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
            return this.saveTransaction(transaction);
        }).toList();
    }

    public ResponseEntity<TransactionModel> updateTransactionByNFCode(UUID id, TransactionDto transactionDto) throws Exception {

        Optional<TransactionModel> transactionOpt = this.getTransactionById(id);
        if (transactionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        WebScraperParser webScraperParser = new WebScraperParser();
        NFParsedDto nfParsedDto = webScraperParser.getNFData(transactionDto.getNfCode(), gRecaptchaResponse);

        BigDecimal totalAmount = nfParsedDto.getProductServiceData().stream()
                .map(ProductServiceDataDto::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (!transactionDto.getAmount().equals(totalAmount)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }

        transactionDto.setAmountApproximateTaxes(nfParsedDto.getApproximateTaxAmount());
        transactionDto.setCorporateReason(nfParsedDto.getCorporateReason());
        transactionDto.setName(nfParsedDto.getName());
        transactionDto.setCnpj(nfParsedDto.getCnpj());
        transactionDto.setPaymentType(nfParsedDto.getPaymentType());

        TransactionModel transactionModel = transactionOpt.get();
        Set<TransactionDescriptionModel> transactionDescriptionList = new HashSet<>();
        BeanUtils.copyProperties(transactionDto, transactionModel);

        for (ProductServiceDataDto descriptionDto : nfParsedDto.getProductServiceData()) {
            TransactionDescriptionModel descriptionModel = new TransactionDescriptionModel();
            descriptionModel.setDescription(descriptionDto.getDescription());
            descriptionModel.setTransaction(transactionModel);
            descriptionModel.setQuantity(descriptionDto.getQuantity());
            descriptionModel.setUnity(descriptionDto.getUnity());
            descriptionModel.setAmount(descriptionDto.getAmount());
            transactionDescriptionList.add(descriptionModel);
        }

        transactionModel.setTransactionDescriptions(transactionDescriptionList);


        return ResponseEntity.status(HttpStatus.OK).body(this.saveTransaction(transactionModel));

    }

    public TransactionModel saveTransaction(TransactionModel transaction) {
        return transactionRepository.save(transaction);
    }

    public List<TransactionModel> getTransactionsByAccount(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Optional<TransactionModel> getTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }

}
