package com.api.k.services;

import com.api.k.models.AccountModel;
import com.api.k.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountModel createAccount(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    public List<AccountModel> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountModel> getAccountById(UUID id) {
        return accountRepository.findById(id);
    }

}
