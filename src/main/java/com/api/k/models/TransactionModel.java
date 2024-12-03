package com.api.k.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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

    @Column(nullable = false)
    private String transactionCod;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private String date;

}
