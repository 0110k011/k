package com.api.k.repositories;

import com.api.k.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountModel, UUID> {

}
