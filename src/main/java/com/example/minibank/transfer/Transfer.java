package com.example.minibank.transfer;

import com.example.common.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transfers")
public class Transfer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = BankAccount.class)
    @JoinColumn(name = "from_bank_account_id", referencedColumnName = "id", nullable = false)
    private BankAccount fromBankAccountId;

    @ManyToOne(targetEntity = BankAccount.class)
    @JoinColumn(name = "to_bank_account_id", referencedColumnName = "id", nullable = false)
    private BankAccount toBankAccountId;

    @Column(name = "transferred_amount")
    private double transferredAmount;

    @Column
    @Enumerated(EnumType.STRING)
    private Commodity commodity;

    @Builder
    public Transfer(Integer id, BankAccount fromBankAccountId, BankAccount toBankAccountId, double transferredAmount, Commodity commodity) {
        this.id = id;
        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.transferredAmount = transferredAmount;
        this.commodity = commodity;
    }

    public enum Commodity {
        not_specified,
        income,
        a2a_transfer,
        food_drinks,
        clothing,
        medical_services,
        loan,
        rent_payment
    }
}
