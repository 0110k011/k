package com.api.k.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_transaction")
@Getter
@Setter
public class TransactionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private AccountModel account;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "transaction")//, fetch = FetchType.LAZY
    @JsonManagedReference
    private Set<TransactionDescriptionModel> transactionDescriptions = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String transactionCode;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = true, precision = 19, scale = 2)
    private BigDecimal amountApproximateTaxes;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String corporateReason;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String cnpj;

    @Column(nullable = true)
    private String paymentType;

    @Column(nullable = true)
    private String paymentMethod;

    @Column(nullable = true)
    private String nfCode;

}
