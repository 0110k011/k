package com.api.k.services;

import com.api.k.models.AccountModel;
import com.api.k.repositories.AccountRepository;
import com.ofxr.dtos.AccountStatementDto;
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
    public AccountModel createAccount(AccountStatementDto accountStatementDto) {

        Optional<AccountModel> existingAccount = accountRepository.findByAccountCode(accountStatementDto.getAccountCode());

        if (existingAccount.isPresent()) {
            return existingAccount.get();
        }

        AccountModel account = new AccountModel();
        account.setOrganization(accountStatementDto.getOrganization());
        account.setCurrency(accountStatementDto.getCurrency());
        account.setBankCode(accountStatementDto.getBankCode());
        account.setBranchCode(accountStatementDto.getBranchCode());
        account.setAccountCode(accountStatementDto.getAccountCode());
        account.setAccountType(accountStatementDto.getAccountType());

        return accountRepository.save(account);
    }

    public List<AccountModel> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountModel> getAccountById(UUID id) {
        return accountRepository.findById(id);
    }

}
