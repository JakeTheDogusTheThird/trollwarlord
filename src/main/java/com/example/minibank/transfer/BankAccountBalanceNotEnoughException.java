package com.example.minibank.transfer;

public class BankAccountBalanceNotEnoughException extends RuntimeException{

    public BankAccountBalanceNotEnoughException(BankAccount bankAccount) {
        super("Bank account " + bankAccount.getUsername() +
                "balance " + bankAccount.getBalance() + " is not enough for the transaction");
    }

    public BankAccountBalanceNotEnoughException(String errorMessage) {
        super(errorMessage);
    }
}
