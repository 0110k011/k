package com.api.k.dtos;

import java.math.BigDecimal;

public class TransactionDescriptionDto {

    private String description;
    private BigDecimal quantity;
    private String unity;
    private BigDecimal amount;

    public TransactionDescriptionDto() {
    }

    public TransactionDescriptionDto(String description, BigDecimal quantity, String unity, BigDecimal amount) {
        this.description = description;
        this.quantity = quantity;
        this.unity = unity;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
