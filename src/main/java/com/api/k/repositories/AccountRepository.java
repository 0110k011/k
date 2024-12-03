package com.api.k.repositories;

import com.api.k.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, UUID> {
    Optional<AccountModel> findByAccountCod(String accountCod);
}