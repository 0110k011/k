package com.api.k.dtos;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TransactionDto {

    private Set<TransactionDescriptionDto> transactionDescriptions = new HashSet<>();
    private String transactionCode;
    private BigDecimal amount;
    private BigDecimal amountApproximateTaxes;
    private LocalDateTime date;
    private String transactionType;
    private String description;
    private String corporateReason;
    private String name;
    private String cnpj;
    private String paymentType;
    private String paymentMethod;
    private String nfCode;

    public TransactionDto() {
    }

    public TransactionDto(Set<TransactionDescriptionDto> transactionDescriptions, String transactionCode, BigDecimal amount, BigDecimal amountApproximateTaxes, LocalDateTime date, String transactionType, String description, String corporateReason, String name, String cnpj, String paymentType, String paymentMethod, String nfCode) {
        this.transactionDescriptions = transactionDescriptions;
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.amountApproximateTaxes = amountApproximateTaxes;
        this.date = date;
        this.transactionType = transactionType;
        this.description = description;
        this.corporateReason = corporateReason;
        this.name = name;
        this.cnpj = cnpj;
        this.paymentType = paymentType;
        this.paymentMethod = paymentMethod;
        this.nfCode = nfCode;
    }

    public Set<TransactionDescriptionDto> getTransactionDescriptions() {
        return transactionDescriptions;
    }

    public void setTransactionDescriptions(Set<TransactionDescriptionDto> transactionDescriptions) {
        this.transactionDescriptions = transactionDescriptions;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountApproximateTaxes() {
        return amountApproximateTaxes;
    }

    public void setAmountApproximateTaxes(BigDecimal amountApproximateTaxes) {
        this.amountApproximateTaxes = amountApproximateTaxes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorporateReason() {
        return corporateReason;
    }

    public void setCorporateReason(String corporateReason) {
        this.corporateReason = corporateReason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNfCode() {
        return nfCode;
    }

    public void setNfCode(String nfCode) {
        this.nfCode = nfCode;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "transactionDescriptions=" + transactionDescriptions +
                ", transactionCode='" + transactionCode + '\'' +
                ", amount=" + amount +
                ", amountApproximateTaxes=" + amountApproximateTaxes +
                ", date=" + date +
                ", transactionType='" + transactionType + '\'' +
                ", description='" + description + '\'' +
                ", corporateReason='" + corporateReason + '\'' +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", nfCode='" + nfCode + '\'' +
                '}';
    }
}
