package com.api.k.repositories;

import com.api.k.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {
}
