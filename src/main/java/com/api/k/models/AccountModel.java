package com.api.k.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
public class AccountModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<TransactionModel> transactions = new HashSet<>();

    @Column(nullable = false)
    private String organization;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String bankCod;

    @Column(nullable = false)
    private String branchCod;

    @Column(nullable = false, unique = true)
    private String accountCod;

    @Column(nullable = false)
    private String accountType;

}
