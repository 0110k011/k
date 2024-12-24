package com.api.k.repositories;

import com.api.k.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
    List<TransactionModel> findByAccountId(UUID accountId);
    Optional<TransactionModel> findByTransactionCode(String code);
    Optional<TransactionModel> findByNfCode(String nfCode);
}