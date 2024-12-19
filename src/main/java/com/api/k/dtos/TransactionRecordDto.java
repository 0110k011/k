package com.api.k.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record TransactionRecordDto(Set<TransactionDescriptionDto> transactionDescriptions,
                                   String transactionCode,
                                   BigDecimal amount,
                                   BigDecimal amountApproximateTaxes,
                                   LocalDateTime date,
                                   String transactionType,
                                   String description,
                                   String corporateReason,
                                   String name,
                                   String cnpj,
                                   String paymentType,
                                   String paymentMethod,
                                   String nfCode) {
}
