package com.example.minibank.transfer;

import com.example.common.Auditable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "bank_accounts")
@Entity
public class BankAccount extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private AccountType account;

    @Column
    private double balance;

    @Builder
    public BankAccount(Integer id, User user, String username, AccountType account, double balance) {
        this.id = id;
        this.userId = user;
        this.username = username;
        this.account = account;
        this.balance = balance;
    }

    public enum AccountType {
        checking_account,
        savings_account,
        money_market_account,
        certificate_of_deposit_account
    }
}
